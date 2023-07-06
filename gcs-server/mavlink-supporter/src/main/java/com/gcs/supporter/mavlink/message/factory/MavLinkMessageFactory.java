package com.gcs.supporter.mavlink.message.factory;

import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.common.*;
import com.MAVLink.enums.MAV_PARAM_TYPE;
import com.MAVLink.minimal.msg_heartbeat;
import com.MAVLink.swarm.msg_led_control;
import com.MAVLink.swarm.msg_scenario_cmd;
import com.gcs.domain.agent.model.Agent;
import com.gcs.domain.coordinate.ned.NedLocatable;
import com.gcs.error.exception.GcsException;

import java.util.Arrays;
import java.util.Objects;

import static com.MAVLink.enums.MAV_CMD.*;
import static com.MAVLink.enums.MAV_COMPONENT.MAV_COMP_ID_ALL;
import static com.MAVLink.enums.MAV_FRAME.MAV_FRAME_LOCAL_NED;
import static com.MAVLink.enums.SCENARIO_CMD_ENUM.*;
import static com.gcs.supporter.mavlink.message.factory.MavLinkModeConstants.*;
import static com.gcs.supporter.mavlink.message.factory.MavLinkPositionMask.*;

public class MavLinkMessageFactory {

    public static MAVLinkMessage armMessage(Agent agentState){
        return armMessage(agentState.getSysid());
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
     * @param agentState
     * @return
     */
    public static MAVLinkMessage disarmMessage(Agent agentState){
        return disarmMessage(agentState.getSysid());
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

    public static MAVLinkMessage landMessage(Agent agentState){
        return landMessage(agentState.getSysid());
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

    public static MAVLinkMessage rebootMessage(Agent agentState){
        return rebootMessage(agentState.getSysid());
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

    public static MAVLinkMessage offBoardMessage(Agent agentState){
        return offBoardMessage(agentState.getSysid());
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

    public static MAVLinkMessage setPoint(Agent agentState, NedLocatable ned){
        return setPoint(agentState.getSysid(), ned.getX(), ned.getY(), ned.getZ(), 0);
    }

    public static MAVLinkMessage setPoint(Agent agentState, NedLocatable ned, float yaw){
        return setPoint(agentState.getSysid(), ned.getX(), ned.getY(), ned.getZ(), yaw);
    }

    public static MAVLinkMessage setPoint(int sysid, float x, float y, float z){
        return setPoint(sysid, x, y, z, 0.F);
    }

    public static MAVLinkMessage setPoint(int sysid, float x, float y, float z, float yaw){
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

        msg.yaw = yaw;
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

    /**
     * calibtration gyro sensor
     * @param sysid
     * @return
     */
    public static MAVLinkMessage calibrationGyro(int sysid){
        msg_command_long msg = new msg_command_long();

        msg.sysid = sysid;
        msg.target_system = (short) sysid;
        msg.target_component = 0;
        msg.command = MAV_CMD_PREFLIGHT_CALIBRATION;
        msg.confirmation = 1;

        // gyro
        msg.param1 = 1;
        // Ground pressure:
        msg.param3 = 0;
        // 1: accelerometer calib, 2: (board offset)level calib
        msg.param5 = 0;

        msg.param2 = 0;
        msg.param4 = 0;
        msg.param6 = 0;
        msg.param7 = 0;

        return msg;
    }

    public static MAVLinkMessage calibrationLevel(int sysid){
        msg_command_long msg = new msg_command_long();

        msg.sysid = sysid;
        msg.target_system = (short) sysid;
        msg.target_component = 0;
        msg.command = MAV_CMD_PREFLIGHT_CALIBRATION;
        msg.confirmation = 1;

        // gyro
        msg.param1 = 1;
        // Ground pressure:
        msg.param3 = 0;
        // 1: accelerometer calib, 2: (board offset)level calib
        msg.param5 = 2;

        msg.param2 = 0;
        msg.param4 = 0;
        msg.param6 = 0;
        msg.param7 = 0;

        return msg;
    }

    public static MAVLinkMessage calibrationAccel(int sysid){
        msg_command_long msg = new msg_command_long();

        msg.sysid = sysid;
        msg.target_system = (short) sysid;
        msg.target_component = 0;
        msg.command = MAV_CMD_PREFLIGHT_CALIBRATION;
        msg.confirmation = 1;

        // gyro
        msg.param1 = 0;
        // Ground pressure:
        msg.param3 = 0;
        // 1: accelerometer calib, 2: (board offset)level calib
        msg.param5 = 1;

        msg.param2 = 0;
        msg.param4 = 0;
        msg.param6 = 0;
        msg.param7 = 0;

        return msg;
    }

    public static MAVLinkMessage parameterList(int sysid){
        msg_param_request_list msg = new msg_param_request_list();

        msg.sysid = sysid;
        msg.target_system = (short) sysid;
        msg.target_component = (short) sysid;

        return msg;
    }

    /**
     * 드론의 파라미터 읽기 요청 메시지
     * @param sysid
     * @param paramName
     * @return msg_param_request_read
     */
    public static MAVLinkMessage requestParams(int sysid, String paramName){
        msg_param_request_read msg = new msg_param_request_read();

        msg.sysid = sysid;
        msg.target_system = (short) sysid;
        msg.target_component = 1;
        System.arraycopy(paramName.getBytes(), 0, msg.param_id, 0, paramName.getBytes().length);
        msg.param_index = -1;

        return msg;
    }

    /**
     * 드론의 파라미터 변경 메시지
     * @param sysid
     * @param paramName
     * @return msg_param_set
     */
    public static MAVLinkMessage setParam(int sysid, String paramName, Object paramValue){
        msg_param_set msg = new msg_param_set();

        msg.sysid = sysid;
        msg.target_system = (short) sysid;
        msg.target_component = 1;
        System.arraycopy(paramName.getBytes(), 0, msg.param_id, 0, paramName.getBytes().length);

        if(Objects.equals(paramValue.getClass(), Character.class)){
            msg.param_type = MAV_PARAM_TYPE.MAV_PARAM_TYPE_INT8;
            char value = (char) paramValue;
            msg.param_value = Float.intBitsToFloat((int) value);
        }
        else if(Objects.equals(paramValue.getClass(), Integer.class)){
            msg.param_type = MAV_PARAM_TYPE.MAV_PARAM_TYPE_INT32;
            int value = (int) paramValue;
            msg.param_value = Float.intBitsToFloat(value);

        } else if(Objects.equals(paramValue.getClass(), Long.class)){
            msg.param_type = MAV_PARAM_TYPE.MAV_PARAM_TYPE_UINT32;
            long value = (long) paramValue;
            int fomedData = 0;

            if(value > (long) Integer.MAX_VALUE){
                fomedData |= (1 << 31);
                value %= (long) Integer.MAX_VALUE;
            }
            fomedData = fomedData | (int) value;

            msg.param_value = Float.intBitsToFloat(fomedData);

        } else if(Objects.equals(paramValue.getClass(), Double.class)){
            msg.param_type = MAV_PARAM_TYPE.MAV_PARAM_TYPE_REAL64;
            float value = (float) paramValue;
            msg.param_value = value;

        } else if(Objects.equals(paramValue.getClass(), Float.class)){
            msg.param_type = MAV_PARAM_TYPE.MAV_PARAM_TYPE_REAL32;
            float value = (float) paramValue;
            msg.param_value = value;

        } else {
            throw new GcsException("Wrong Parameter Value");
        }

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
