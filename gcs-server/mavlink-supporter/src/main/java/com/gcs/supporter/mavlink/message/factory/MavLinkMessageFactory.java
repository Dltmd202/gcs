package com.gcs.supporter.mavlink.message.factory;

import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.common.msg_command_int;
import com.MAVLink.common.msg_command_long;
import com.MAVLink.common.msg_set_position_target_local_ned;
import com.MAVLink.minimal.msg_heartbeat;
import com.MAVLink.swarm.msg_led_control;
import com.MAVLink.swarm.msg_scenario_cmd;
import com.MAVLink.swarm.msg_scenario_cmd_start;
import com.gcs.domain.agent.Agent;
import com.gcs.domain.coordinate.ned.NedLocatable;
import com.gcs.error.exception.GcsException;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Objects;

import static com.MAVLink.enums.MAV_CMD.*;
import static com.MAVLink.enums.MAV_COMPONENT.MAV_COMP_ID_ALL;
import static com.MAVLink.enums.MAV_FRAME.MAV_FRAME_LOCAL_NED;
import static com.MAVLink.enums.SCENARIO_CMD_ENUM.*;
import static com.gcs.supporter.mavlink.message.factory.MavLinkModeConstants.*;
import static com.gcs.supporter.mavlink.message.factory.MavLinkPositionMask.*;

public class MavLinkMessageFactory {

    public static MAVLinkMessage armMessage(Agent agent){
        return armMessage(agent.getSysid());
    }

    /**
     * armMessage - 시동 메시지
     * @param sysid
     * @return
     */
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

    /**
     * disArm - 시동 끄기
     * @param agent
     * @return
     */
    public static MAVLinkMessage disarmMessage(Agent agent){
        return disarmMessage(agent.getSysid());
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

    public static MAVLinkMessage landMessage(Agent agent){
        return landMessage(agent.getSysid());
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

    public static MAVLinkMessage rebootMessage(Agent agent){
        return rebootMessage(agent.getSysid());
    }

    public static MAVLinkMessage rebootMessage(int sysid){
        msg_command_int msg = new msg_command_int();
        msg.sysid = sysid;

        msg.target_system = (short) sysid;
        msg.target_component = MAV_COMP_ID_ALL;
        msg.command = MAV_CMD_PREFLIGHT_REBOOT_SHUTDOWN;
        msg.param1 = 1.0F;

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

    public static MAVLinkMessage offBoardMessage(Agent agent){
        return offBoardMessage(agent.getSysid());
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

    public static MAVLinkMessage setPoint(Agent agent, NedLocatable ned){
        return setPoint(agent.getSysid(), ned.getX(), ned.getY(), ned.getZ());
    }



    public static MAVLinkMessage setPoint(int sysid, float x, float y, float z){
        msg_set_position_target_local_ned msg = new msg_set_position_target_local_ned();

        msg.sysid = sysid;
        msg.target_system = (short) sysid;
        msg.target_component = 0;
        msg.type_mask = MAVLINK_MSG_SET_POSITION_TARGET_LOCAL_NED_POSITION;
        msg.coordinate_frame = MAV_FRAME_LOCAL_NED;
        msg.time_boot_ms = 0;
        msg.x = x;
        msg.y = y;
        msg.z = z;

        msg.yaw = 0.F;
        msg.yaw_rate = 0;

        return msg;
    }

    public static MAVLinkMessage heartBeat(int sysid){
        msg_heartbeat msg = new msg_heartbeat();

        msg.msgid = MAVLINK_MSG_ID_HEARTBEAT;
        msg.sysid = sysid;
        msg.isMavlink2 = true;
        msg.type = 0x06;
        msg.autopilot = 0x08;
        msg.base_mode = 0xc0;
        msg.custom_mode = 0x0000;
        msg.system_status = 0x04;
        msg.mavlink_version = 3;

        return msg;
    }


    public static MAVLinkMessage setScenarioStartTime(int sysid, float statTime){
        msg_scenario_cmd msg = new msg_scenario_cmd();
        msg.isMavlink2 = true;
        msg.sysid = sysid;

        msg.cmd = SCENARIO_CMD_SET_START_TIME;
        msg.target_system = (short) sysid;
        msg.param1 = statTime;

        return msg;
    }

    public static MAVLinkMessage stopScenario(int sysid){
        msg_scenario_cmd msg = new msg_scenario_cmd();
        msg.isMavlink2 = true;
        msg.sysid = sysid;

        msg.cmd = SCENARIO_CMD_STOP_SCENARIO;
        msg.target_system = (short) sysid;

        return msg;
    }

    public static MAVLinkMessage resetScenario(int sysid){
        msg_scenario_cmd msg = new msg_scenario_cmd();
        msg.isMavlink2 = true;
        msg.sysid = sysid;

        msg.cmd = SCENARIO_CMD_RESET_CONFIGS;
        msg.target_system = (short) sysid;

        return msg;
    }

    public static MAVLinkMessage setScenarioConfigs(int sysid, float offsetX, float offsetY, float rotation, String filepath){
        // TODO handling
        checkValidScenarioPath(filepath);

        msg_scenario_cmd msg = new msg_scenario_cmd();
        msg.isMavlink2 = true;
        msg.sysid = sysid;

        msg.cmd = SCENARIO_CMD_SET_CONFIGS;
        msg.target_system = (short) sysid;

        msg.param1 = offsetX;
        msg.param2 = offsetY;
        msg.param3 = degreeToRadian(rotation);

        byte[] pathBytes = filepath.getBytes();
        Arrays.fill(msg.param5, (short) 0);
        for (int i = 0; i < filepath.length(); i++) {
            msg.param5[i] = pathBytes[i];
        }

        return msg;
    }

    public static MAVLinkMessage emergencyLanding(int sysid){
        msg_scenario_cmd msg = new msg_scenario_cmd();
        msg.isMavlink2 = true;
        msg.sysid = sysid;

        msg.cmd = SCENARIO_CMD_EMERGENCY_LAND;
        msg.target_system = (short) sysid;

        return msg;
    }

    public static MAVLinkMessage tesetRTKOff(int sysid){
        msg_scenario_cmd msg = new msg_scenario_cmd();
        msg.isMavlink2 = true;
        msg.sysid = sysid;

        msg.cmd = SCENARIO_CMD_ENUM_ENUM_END;
        msg.target_system = (short) sysid;

        return msg;
    }

    public static MAVLinkMessage ledCommand(int sysid, int type, int r, int g, int b, int brightness, int speed){
        msg_led_control msg = new msg_led_control();
        msg.isMavlink2 = true;
        msg.sysid = sysid;

        msg.r = (short) r;
        msg.g = (short) g;
        msg.b = (short) b;
        msg.brightness = (short) brightness;
        msg.type = (short) type;

        return msg;
    }


    private static void checkValidScenarioPath(String filepath){
        if(Objects.isNull(filepath) || filepath.length() >= 32)
            throw new GcsException("Wrong scenario Path");
    }


    // TODO RadianUtil modulation
    private static float degreeToRadian(float degree){
        return (float) (degree * Math.PI / 180);
    }


}
