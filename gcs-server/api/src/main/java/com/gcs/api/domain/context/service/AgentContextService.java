package com.gcs.api.domain.context.service;

import com.gcs.domain.agent.Agent;
import com.gcs.domain.agent.dto.AgentDto;
import com.gcs.domain.context.AgentContext;
import com.gcs.domain.context.exception.ConfigurationParseException;
import com.gcs.domain.context.exception.DuplicateSysIdException;
import com.gcs.api.domain.context.configuration.repository.AgentContextRepository;
import com.gcs.api.domain.context.repository.AgentContextHolder;
import com.gcs.api.domain.user.service.UserService;
import com.gcs.api.util.confparser.AgentContextXMLParser;
import com.gcs.supporter.domain.context.entity.AgentContextEntity;
import com.gcs.supporter.domain.filestore.FileStore;
import com.gcs.supporter.domain.user.entity.UserEntity;
import com.gcs.supporter.error.exception.ApiException;
import com.gcs.supporter.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AgentContextService {
    private final AgentContextRepository agentContextRepository;
    private final AgentContextHolder agentContextHolder;
    private final UserService userService;
    private final AgentContextXMLParser parser;
    private final FileStore fileStore;

    @Transactional
    public Long saveAgentContextFile(Long userId, String uploadFileName, String storeFilename){
        UserEntity user = userService.findUserById(userId);
        AgentContextEntity context = new AgentContextEntity(user, uploadFileName, storeFilename);
        return agentContextRepository.save(context).getId();
    }

    public AgentContextEntity getAgentContextEntityByContextId(Long userId, Long contextId){
        UserEntity user = userService.findUserById(userId);
        return agentContextRepository.getAgentContextEntitiesByIdAndUser(contextId, user)
                        .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND_CONTEXT_CONF));
    }

    public AgentContextEntity getContextByContextId(Long contextId){
        return agentContextRepository.findById(contextId)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND_CONTEXT_CONF));
    }

    public List<AgentContextEntity> getUserContextFile(Long userId){
        UserEntity user = userService.findUserById(userId);
        return agentContextRepository.getAgentContextEntitiesByUser(user);
    }


    public AgentContext holdRunningAgentContext(Long userId, Long contextId){
        AgentContext context = getContextDtoByContextFile(getAgentContextEntityByContextId(userId, contextId));
        agentContextHolder.saveContext(context);

        return getContextDtoByContextFile(
            getAgentContextEntityByContextId(userId, contextId)
        );
    }

    public AgentContext getRunningContext(){
        return agentContextHolder.getRunningContext()
                .orElseThrow(() -> new ApiException(ErrorCode.NO_RUNNING_CONTEXT_CONF));
    }

    public Agent getAgent(int sysid){
        if(!isRunningContext()) throw new ApiException(ErrorCode.NO_RUNNING_CONTEXT_CONF);
        return agentContextHolder.getRunningContext()
                .orElseThrow(() -> new ApiException(ErrorCode.NO_RUNNING_CONTEXT_CONF))
                .getAgent(sysid)
                .orElseThrow(() -> new ApiException(ErrorCode.NO_AGENT_FROM_CONTEXT_CONF));

    }

    public boolean isRunningContext(){
        return agentContextHolder.getRunningContext().isPresent();
    }

    private AgentContext getContextDtoByContextFile(AgentContextEntity context){
        InputStream stream = null;
        AgentContext scenarioDto = null;

        try {
            stream = new FileInputStream(
                    fileStore.getFullPath(context.getStoreFileName())
            );
            scenarioDto = parser.parseConfiguration(stream);

        } catch (FileNotFoundException e) {
            throw new ApiException(ErrorCode.FILE_NOT_FOUND);
        } catch (DuplicateSysIdException | ConfigurationParseException e){
            throw new ApiException(ErrorCode.NO_AGENT_FROM_CONTEXT_CONF);
        }
        return scenarioDto;
    }

    public Boolean cleanContext() {
        agentContextHolder.cleanContext();
        return true;
    }

    public boolean toggleContextRepeatedSetPointMessage() {
        return agentContextHolder.toggleRepeatedSetPoint();
    }

    public boolean isRepeatedSetPoint(){
        return agentContextHolder.isRepeatedSetPoint();
    }
}
