package com.gcs.api.util.confparser;

import com.gcs.domain.agent.dto.AgentDto;
import com.gcs.domain.agent.dto.RealTimeAgentDto;
import com.gcs.domain.context.AgentContext;
import com.gcs.domain.context.dto.AgentContextDto;
import com.gcs.domain.context.exception.ConfigurationParseException;
import com.gcs.domain.context.exception.DuplicateSysIdException;
import com.gcs.api.constants.InitialCoordinateConstants;
import com.gcs.supporter.util.tracker.TimeTracker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import static com.gcs.domain.agent.constants.AgentProperty.*;
import static com.gcs.domain.context.constants.AgentContextConnection.MAV_LINK;
import static com.gcs.domain.context.constants.AgentContextConnection.ROS;

/**
 * ScenarioConfigurationXMLParser는
 * <br/>
 * Agent의 Configuration에 대한 .conf 파일을 파싱하는 컴포넌트
 * @author Dltmd202
 */
@Slf4j
@Component
public class AgentContextXMLParser {

    private final DocumentBuilder documentBuilder;
    private final InitialCoordinateConstants initCoordinateConfigure;

    public AgentContextXMLParser(InitialCoordinateConstants initCoordinateConfigure) {
        this.initCoordinateConfigure = initCoordinateConfigure;
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new ConfigurationParseException(e);
        }
    }

    @TimeTracker
    public AgentContext parseConfiguration(MultipartFile confFile){
        InputStream confFileInputStream = extractInputStream(confFile);
        AgentContextDto scenarioDto = parseConfiguration(confFileInputStream);

        log.debug("{}", scenarioDto.agentStore());
        return scenarioDto;
    }

    public AgentContextDto parseConfiguration(InputStream inputStream) {
        Document confDocument = parse(inputStream);
        Element confElement = confDocument.getDocumentElement();
        AgentContextDto agentContext = new AgentContextDto();

        String connection = confElement.getAttribute("connection").toLowerCase().trim();

        if(connection.equals(ROS.getVar())){
            agentContext.setConnection(ROS);
        } else if(connection.equals(MAV_LINK.getVar())){
            agentContext.setConnection(MAV_LINK);
        }

        NodeList agentsNodeList = confDocument.getChildNodes();

        for (int agentsSeq = 0; agentsSeq < agentsNodeList.getLength(); agentsSeq++) {
            Node agentsNode = agentsNodeList.item(agentsSeq);
            NodeList agentsChildNodes = agentsNode.getChildNodes();

            for (int agentSeq = 0; agentSeq < agentsChildNodes.getLength(); agentSeq++) {
                Node agent = agentsChildNodes.item(agentSeq);
                if(!agent.getNodeName().toLowerCase().trim().equals("agent")) continue;

                RealTimeAgentDto agentDto = new RealTimeAgentDto();
                Set<Integer> sysidValidator = new HashSet<>();
                NodeList agentChildNodes = agent.getChildNodes();

                NamedNodeMap attributes = agent.getAttributes();
                String id = attributes.getNamedItem("id").getNodeValue();
                agentDto.setId(Integer.parseInt(id));

                for (int agentChild = 0; agentChild < agentChildNodes.getLength(); agentChild++) {
                    Node agentProperty = agentChildNodes.item(agentChild);
                    if(agentProperty.getNodeName().equals("#text")) continue;

                    if(SYSID.getVar().equals(agentProperty.getNodeName().toLowerCase())){
                        int sysid = Integer.parseInt(agentProperty.getTextContent().trim());
                        if(sysidValidator.contains(sysid))
                            throw new DuplicateSysIdException();
                        sysidValidator.add(sysid);
                        agentDto.setSysid(
                                Integer.parseInt(agentProperty.getTextContent().trim()));
                    } else if(MODE.getVar().equals(agentProperty.getNodeName().toLowerCase())){
                        agentDto.setMode(
                                agentProperty.getTextContent().trim()
                        );
                    } else if(TYPE.getVar().equals(agentProperty.getNodeName().toLowerCase())){
                        agentDto.setType(
                                agentProperty.getTextContent().trim()
                        );
                    } else if(GROUP.getVar().equals(agentProperty.getNodeName().toLowerCase())){
                        agentDto.setGroup(
                                Integer.parseInt(agentProperty.getTextContent().trim())
                        );
                    } else if(VEHICLE.getVar().equals(agentProperty.getNodeName().toLowerCase())){
                        agentDto.setVehicle(
                                agentProperty.getTextContent().trim()
                        );
                    } else if(IP.getVar().equals(agentProperty.getNodeName().toLowerCase())){
                        agentDto.setIp(
                                agentProperty.getTextContent().trim()
                        );
                    } else if(PORT.getVar().equals(agentProperty.getNodeName().toLowerCase())){
                        agentDto.setPort(
                                Integer.parseInt(agentProperty.getTextContent().trim())
                        );
                    }
                    // TODO unknown 프로퍼티에 대한 정책
                }

                if(agentContext.agentStore().containsKey(agentDto.getSysid()))
                    throw new ConfigurationParseException("sysid가 중복되었습니다.");
                agentContext.agentStore().put(agentDto.getSysid(), agentDto);
            }

        }
        return agentContext;
    }

    private String getAttribute(Node node, String attr){
        if(node.getNodeType() == Node.ELEMENT_NODE){
            Element element = (Element) node;
            if(element.getAttributes().getNamedItem(attr) != null)
                return ((Element) node).getAttributes().getNamedItem(attr).getNodeValue();
            else return "";
        } else return "";
    }

    private Document parse(InputStream inputStream){
        try {
            return documentBuilder.parse(inputStream);
        } catch (SAXException e) {
            throw new ConfigurationParseException(e);
        } catch (IOException e) {
            throw new ConfigurationParseException(e);
        }
    }

    private InputStream extractInputStream(MultipartFile confFile){
        try {
            return confFile.getInputStream();
        } catch (IOException e) {
            throw new ConfigurationParseException(e);
        }
    }
}
