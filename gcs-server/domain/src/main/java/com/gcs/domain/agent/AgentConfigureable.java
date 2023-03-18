package com.gcs.domain.agent;

public interface AgentConfigureable {
    Integer getSysid();
    String getMode();
    String getType();
    Integer getGroup();
    String getVehicle();
    String getIp();
    Integer getPort();
}