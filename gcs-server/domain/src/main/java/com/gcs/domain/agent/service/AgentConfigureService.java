package com.gcs.domain.agent.service;

import java.net.InetAddress;

public interface AgentConfigureService {
    String getAgentHostAddress(int key);
    Integer getAgentPort(int key);
    InetAddress getAgentInetAddress(int key);
}
