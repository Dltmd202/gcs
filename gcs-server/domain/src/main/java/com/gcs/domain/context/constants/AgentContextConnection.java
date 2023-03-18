package com.gcs.domain.context.constants;

import lombok.Getter;

@Getter
public enum AgentContextConnection {
    ROS("ros"),
    MAV_LINK("mav_link");

    private String var;

    AgentContextConnection(String var) {
        this.var = var;
    }
}
