package com.gcs.supporter.mavlink.notification;

import lombok.Getter;

@Getter
public enum MavLinkMonitoringStatus {
    INFO("INFO"),
    WARNING("WARNING"),
    ERROR("ERROR");

    private String value;

    MavLinkMonitoringStatus(String value) {
        this.value = value;
    }
}
