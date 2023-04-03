package com.gcs.tester.mavlink;

import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.common.msg_command_long;
import com.gcs.tester.mavlink.message.MAVLinkUtils;

import static com.MAVLink.enums.MAV_CMD.MAV_CMD_NAV_TAKEOFF;

public class Message {
    public static void main(String[] args) {
        msg_command_long msg = new msg_command_long();
        msg.sysid = 1;

        msg.target_system = (short) 1;
        msg.target_component = (short) 1;
        msg.command = MAV_CMD_NAV_TAKEOFF;
        msg.confirmation = 1;
        msg.param1 = -1;
        msg.param4 = Float.NaN;
        msg.param5 = Float.NaN;
        msg.param6 = Float.NaN;
        msg.param7 = 497.996F;

        msg.isMavlink2 = true;

        byte[] data = MAVLinkUtils.getPacketData(msg);

        for (byte b : data) {
            System.out.printf("%02x ", b);
        }
        System.out.println();

        MAVLinkMessage mavLinkMessage = MAVLinkUtils.getMessage(data, data.length)
                .orElseThrow(() -> new IllegalArgumentException());

        System.out.println(mavLinkMessage);


    }
}
