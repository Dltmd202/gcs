package com.gcs.domain.agent.dto;

import com.gcs.domain.agent.AgentConfigureable;
import lombok.Getter;

@Getter
public class AgentConfig implements AgentConfigureable {
    Integer sysid;
    String mode;
    String type;
    Integer group;
    String vehicle;
    String ip;
    Integer port;
}
