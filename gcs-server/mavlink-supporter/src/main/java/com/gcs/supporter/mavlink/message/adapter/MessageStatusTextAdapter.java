package com.gcs.supporter.mavlink.message.adapter;

import com.MAVLink.common.msg_statustext;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class MessageStatusTextAdapter implements Serializable {
    private Integer sysid;
    private String text;
    private String category;

    public MessageStatusTextAdapter(msg_statustext msg){
        this.sysid = msg.sysid;
        this.text = msg.getText();

        switch (msg.severity) {
            case 0 -> this.category = "EMERGENCY";
            case 1 -> this.category = "ALERT";
            case 2 -> this.category = "CRITICAL";
            case 3 -> this.category = "ERROR";
            case 4 -> this.category = "WARNING";
            case 5 -> this.category = "NOTICE";
            case 6 -> this.category = "INFO";
            case 7 -> this.category = "DEBUG";
            default -> this.category = "";
        }

    }
}
