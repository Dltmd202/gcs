package com.gcs.domain.mavlink.message;

import com.gcs.domain.mavlink.mask.MavLinkMonitoringFlag;

public class MavLinkNotificationMessage {
    private int sysid;
    private String status;
    private String message;

    public MavLinkNotificationMessage(int sysid, String status, String message) {
        this.sysid = sysid;
        this.status = status;
        this.message = message;
    }

    public MavLinkNotificationMessage(int sysid, MavLinkMonitoringFlag flag){
        this.sysid = sysid;
        this.status = flag.getStatus().getValue();
        this.message = flag.getMessage();
    }
}
