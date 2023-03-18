package com.gcs.monolith.domain.mavlink.factory;

import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.common.msg_command_int;
import com.MAVLink.common.msg_command_long;
import com.MAVLink.common.msg_set_position_target_local_ned;

import static com.MAVLink.enums.MAV_CMD.*;
import static com.MAVLink.enums.MAV_COMPONENT.*;
import static com.MAVLink.enums.MAV_FRAME.*;
import static com.MAVLink.enums.MAV_MODE_FLAG.*;
import static com.gcs.domain.mavlink.mask.MavLinkPositionMask.MAVLINK_MSG_SET_POSITION_TARGET_LOCAL_NED_POSITION;

public class MavLinkMessageFactory {
    private static final int PX4_CUSTOM_MAIN_MODE_AUTO = 4;
    private static final int PX4_CUSTOM_SUB_MODE_AUTO_LAND = 6;
    private static final int PX4_CUSTOM_MAIN_MODE_OFFBOARD = 6;
    private static final int LAND_MODE = MAV_MODE_FLAG_SAFETY_ARMED |
            MAV_MODE_FLAG_STABILIZE_ENABLED |
            MAV_MODE_FLAG_GUIDED_ENABLED |
            MAV_MODE_FLAG_AUTO_ENABLED |
            MAV_MODE_FLAG_CUSTOM_MODE_ENABLED;

    private static final int OFFBOARD_MODE = MAV_MODE_FLAG_SAFETY_ARMED |
            MAV_MODE_FLAG_CUSTOM_MODE_ENABLED;

    public static MAVLinkMessage armMessage(int sysid){
        msg_command_long msg = new msg_command_long();
        msg.sysid = sysid;

        msg.target_system = (short) sysid;
        msg.command = MAV_CMD_COMPONENT_ARM_DISARM;
        msg.confirmation = 1;
        msg.param1 = 1.0F;

        msg.isMavlink2 = true;

        return msg;
    }

    public static MAVLinkMessage disarmMessage(int sysid){
        msg_command_long msg = new msg_command_long();
        msg.sysid = sysid;

        msg.target_system = (short) sysid;
        msg.command = MAV_CMD_COMPONENT_ARM_DISARM;
        msg.confirmation = 1;
        msg.param1 = 0.F;

        msg.isMavlink2 = true;

        return msg;
    }


    public static MAVLinkMessage landMessage(int sysid){
        msg_command_long msg = new msg_command_long();
        msg.sysid = sysid;

        msg.target_system = (short) sysid;
        msg.command = MAV_CMD_DO_SET_MODE;
        msg.confirmation = 1;
        msg.param1 = LAND_MODE;
        msg.param2 = PX4_CUSTOM_MAIN_MODE_AUTO;
        msg.param3 = PX4_CUSTOM_SUB_MODE_AUTO_LAND;

        msg.isMavlink2 = true;

        return msg;
    }

    public static MAVLinkMessage rebootMessage(int sysid){
        msg_command_int msg = new msg_command_int();
        msg.sysid = sysid;

        msg.target_system = (short) sysid;
        msg.target_component = MAV_COMP_ID_ALL;
        msg.command = MAV_CMD_PREFLIGHT_REBOOT_SHUTDOWN;
        msg.param1 = 1;

        msg.isMavlink2 = true;

        return msg;
    }

    public static MAVLinkMessage takeOffMessage(int sysid, float alt){
        msg_command_long msg = new msg_command_long();
        msg.sysid = sysid;

        msg.target_system = (short) sysid;
        msg.target_component = (short) sysid;
        msg.command = MAV_CMD_NAV_TAKEOFF;
        msg.confirmation = 0;
        msg.param1 = -1;
        msg.param4 = Float.NaN;
        msg.param5 = Float.NaN;
        msg.param6 = Float.NaN;
        msg.param7 = alt;

        msg.isMavlink2 = true;

        return msg;
    }

    public static MAVLinkMessage offBoardMessage(int sysid){
        msg_command_long msg = new msg_command_long();
        msg.sysid = sysid;

        msg.target_system = (short) sysid;
        msg.target_component = 0;
        msg.command = MAV_CMD_DO_SET_MODE;
        msg.confirmation = 1;
        msg.param1 = OFFBOARD_MODE;
        msg.param2 = PX4_CUSTOM_MAIN_MODE_OFFBOARD;

        msg.isMavlink2 = true;

        return msg;
    }

    public static MAVLinkMessage setPositionTargetLocalNedMessage(int sysid, float x, float y, float z){
        msg_set_position_target_local_ned msg = new msg_set_position_target_local_ned();

        msg.target_system = (short) sysid;
        msg.target_component = 0;
        msg.type_mask = MAVLINK_MSG_SET_POSITION_TARGET_LOCAL_NED_POSITION;
        msg.coordinate_frame = MAV_FRAME_LOCAL_NED;
        msg.time_boot_ms = System.currentTimeMillis();
        msg.x = x;
        msg.y = y;
        msg.z = z;

        msg.yaw = 0.F;
        msg.yaw_rate = 0;

        return msg;
    }
}
