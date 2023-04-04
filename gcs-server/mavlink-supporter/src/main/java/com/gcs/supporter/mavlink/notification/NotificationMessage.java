package com.gcs.supporter.mavlink.notification;

public class NotificationMessage {
    private int sysid;
    private String status;
    private String message;

    public NotificationMessage(int sysid, String status, String message) {
        this.sysid = sysid;
        this.status = status;
        this.message = message;
    }

    public NotificationMessage(int sysid, MavLinkMonitoringFlag flag){
        this.sysid = sysid;
        this.status = flag.getStatus().getValue();
        this.message = flag.getMessage();
    }
}
