package com.gcs.api.web.context.api;

import com.gcs.api.domain.context.service.AgentContextService;
import com.gcs.api.web.context.dto.AgentContextResponse;
import com.gcs.api.web.context.dto.StoredContextResponse;
import com.gcs.supporter.domain.filestore.FileStore;
import com.gcs.supporter.domain.filestore.dto.UploadFile;
import com.gcs.supporter.domain.security.JwtAuthentication;
import com.gcs.supporter.error.exception.ApiException;
import com.gcs.supporter.error.exception.ErrorCode;
import com.gcs.supporter.util.api.ApiUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(("/api/context"))
public class ContextApiController {
    private final FileStore fileStore;
    private final AgentContextService contextService;

    @PostMapping("/{contextId}")
    public ResponseEntity<ApiUtil.ApiResult<AgentContextResponse>> holdContext(
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @PathVariable Long contextId
    ){

        return ResponseEntity
                .ok(ApiUtil.success(
                        new AgentContextResponse(
                                contextService.holdRunningAgentContext(
                                        jwtAuthentication.getId(),
                                        contextId
                                )
                        )
                ));
    }

    @DeleteMapping
    public ResponseEntity<ApiUtil.ApiResult<Boolean>> cleanContext(){
        return ResponseEntity
                .ok(
                        ApiUtil.success(contextService.cleanContext())
                );
    }

    @GetMapping("/exist")
    public ResponseEntity<ApiUtil.ApiResult<Boolean>> isCurrentContext(){
        return ResponseEntity
                .ok(
                        ApiUtil.success(contextService.isRunningContext())
                );
    }

    @GetMapping("/current")
    public ResponseEntity<ApiUtil.ApiResult<AgentContextResponse>> setContext(){
        return ResponseEntity
                .ok(ApiUtil.success(
                        new AgentContextResponse(
                                contextService.getRunningContext()
                        )
                ));
    }

    @GetMapping("/file/{contextId}")
    public Resource downloadContextConfigurationFile(
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @PathVariable Long contextId
    ){
        try {
            return new UrlResource(
                    fileStore.getFileSchemaFullPath(
                        contextService.
                                getAgentContextEntityByContextId(
                                        jwtAuthentication.getId(),
                                        contextId
                                ).getStoreFileName()
                    )
            );
        } catch (MalformedURLException e) {
            throw new ApiException(ErrorCode.NOT_FOUND_RESOURCE_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<ApiUtil.ApiResult<List<StoredContextResponse>>> getContextFileList(
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ){
        // TODO web conf block

        return ResponseEntity.ok(
                ApiUtil.success(
                    contextService.getUserContextFile(jwtAuthentication.getId())
                            .stream()
                            .map(StoredContextResponse::new)
                            .collect(Collectors.toList())
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiUtil.ApiResult<Long>> postUserContextFile(
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @RequestParam MultipartFile file
            ){
        UploadFile uploadFile = fileStore.storeFile(file);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                    ApiUtil.success(
                            contextService.saveAgentContextFile(
                                    jwtAuthentication.getId(),
                                    uploadFile.getUploadFileName(),
                                    uploadFile.getStoreFileName()
                            )
                    )
                );
    }

    @PostMapping("/setPoint")
    public ResponseEntity<ApiUtil.ApiResult<Boolean>> toggleContextSetPointMessage(){
        return ResponseEntity.ok(
                ApiUtil.success(
                    contextService.toggleContextRepeatedSetPointMessage())
        );
    }

    @GetMapping("/setPoint")
    public ResponseEntity<ApiUtil.ApiResult<Boolean>> isContextRepeatSetPoint(){
        return ResponseEntity.ok(
                ApiUtil.success(
                        contextService.isRepeatedSetPoint()
                )
        );
    }


}
