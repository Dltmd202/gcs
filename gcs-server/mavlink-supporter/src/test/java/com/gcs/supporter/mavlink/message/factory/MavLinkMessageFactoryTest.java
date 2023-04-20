package com.gcs.supporter.mavlink.message.factory;

import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.common.msg_command_int;
import com.MAVLink.common.msg_command_long;
import com.MAVLink.common.msg_set_position_target_local_ned;
import com.MAVLink.minimal.msg_heartbeat;
import com.MAVLink.swarm.msg_scenario_cmd;
import com.gcs.supporter.mavlink.util.MAVLinkUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.Arrays;

import static com.MAVLink.enums.MAV_CMD.*;
import static com.MAVLink.enums.MAV_FRAME.MAV_FRAME_LOCAL_NED;
import static com.MAVLink.enums.SCENARIO_CMD_ENUM.SCENARIO_CMD_SET_CONFIGS;
import static com.MAVLink.enums.SCENARIO_CMD_ENUM.SCENARIO_CMD_STOP_SCENARIO;
import static com.gcs.supporter.mavlink.message.factory.MavLinkModeConstants.MAVLINK_MSG_ID_HEARTBEAT;
import static com.gcs.supporter.mavlink.message.factory.MavLinkPositionMask.MAVLINK_MSG_SET_POSITION_TARGET_LOCAL_NED_POSITION;
import static org.assertj.core.api.Assertions.*;

class MavLinkMessageFactoryTest {

    @Test
    @DisplayName("시동 메시지 테스트")
    void armMessage(){
        int sysid = 2;

        MAVLinkMessage mavLinkMessage = MavLinkMessageFactory.armMessage(sysid);
        byte[] packetData = MAVLinkUtils.getMessage(mavLinkMessage);

        MAVLinkMessage decoded = MAVLinkUtils.getMessage(packetData, packetData.length).get();

        assertThat(decoded).isNotNull();
        assertThat(decoded).isInstanceOf(msg_command_long.class);

        msg_command_long msg = (msg_command_long) decoded;

        assertThat(msg.sysid).isEqualTo(sysid);
        assertThat(msg.target_system).isEqualTo((short)sysid);
        assertThat(msg.command).isEqualTo(MAV_CMD_COMPONENT_ARM_DISARM);
        assertThat(msg.confirmation).isEqualTo((short)1);
        assertThat(msg.param1).isEqualTo(1.0F);
        assertThat(msg.isMavlink2).isEqualTo(true);
    }


    @Test
    @DisplayName("시동 끄기 메시지 테스트")
    void disarmMessage(){
        int sysid = 3;

        MAVLinkMessage mavLinkMessage = MavLinkMessageFactory.disarmMessage(sysid);
        byte[] packetData = MAVLinkUtils.getMessage(mavLinkMessage);

        MAVLinkMessage decoded = MAVLinkUtils.getMessage(packetData, packetData.length).get();

        assertThat(decoded).isNotNull();
        assertThat(decoded).isInstanceOf(msg_command_long.class);

        msg_command_long msg = (msg_command_long) decoded;

        assertThat(msg.sysid).isEqualTo(sysid);
        assertThat(msg.target_system).isEqualTo((short)sysid);
        assertThat(msg.command).isEqualTo(MAV_CMD_COMPONENT_ARM_DISARM);
        assertThat(msg.confirmation).isEqualTo((short)1);
        assertThat(msg.param1).isEqualTo(0.0F);
        assertThat(msg.isMavlink2).isEqualTo(true);
    }

    @Test
    @Disabled
    @DisplayName("리부트 메시지 테스트")
    void rebootMessage(){
        int sysid = 4;

        MAVLinkMessage mavLinkMessage = MavLinkMessageFactory.disarmMessage(sysid);
        byte[] packetData = MAVLinkUtils.getMessage(mavLinkMessage);

        MAVLinkMessage decoded = MAVLinkUtils.getMessage(packetData, packetData.length).get();

        assertThat(decoded).isNotNull();
        assertThat(decoded).isInstanceOf(msg_command_int.class);

        // TODO msg_command_long 으로 변환 됨
        msg_command_int msg = (msg_command_int) decoded;

        assertThat(msg.sysid).isEqualTo(sysid);
        assertThat(msg.target_system).isEqualTo((short)sysid);
        assertThat(msg.command).isEqualTo(MAV_CMD_PREFLIGHT_REBOOT_SHUTDOWN);
        assertThat(msg.param1).isEqualTo(1.0F);
        assertThat(msg.isMavlink2).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(floats = {
            10.5F,
            20.121F,
            100.1F,
            2000.10F,
            20000000.100110F,
            1012301230123014120301.123124821321514124F
    })
    @DisplayName("다양한 고도에 따른 이륙 메시지 테스트")
    void takeoffMessage(float altitude){
        int sysid = 10;

        MAVLinkMessage mavLinkMessage = MavLinkMessageFactory.takeOffMessage(sysid, altitude);
        byte[] packetData = MAVLinkUtils.getMessage(mavLinkMessage);

        MAVLinkMessage decoded = MAVLinkUtils.getMessage(packetData, packetData.length).get();

        assertThat(decoded).isNotNull();
        assertThat(decoded).isInstanceOf(msg_command_long.class);

        msg_command_long msg = (msg_command_long) decoded;

        assertThat(msg.sysid).isEqualTo(sysid);
        assertThat(msg.target_system).isEqualTo((short)sysid);
        assertThat(msg.target_component).isEqualTo((short)sysid);
        assertThat(msg.command).isEqualTo(MAV_CMD_NAV_TAKEOFF);
        assertThat(msg.confirmation).isEqualTo((short)0);
        assertThat(msg.param1).isEqualTo(-1.F);
        assertThat(msg.param2).isEqualTo(0.F);
        assertThat(msg.param3).isEqualTo(0.F);
        assertThat(msg.param7).isEqualTo(altitude);
        assertThat(msg.isMavlink2).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(floats = {
            0.F,
            0.1F,
            10.5F,
            20.121F,
            100.1F,
            2000.10F,
            20000000.100110F,
            1012301230123014120301.123124821321514124F
    })
    @DisplayName("메시지 디코딩 시간 측정 테스트")
    void messageDecodeTimeTest(float altitude){
        int sysid = 10;

        MAVLinkMessage mavLinkMessage = MavLinkMessageFactory.takeOffMessage(sysid, altitude);

        Assertions.assertTimeout(Duration.ofMillis(3), () -> {
            byte[] packetData = MAVLinkUtils.getMessage(mavLinkMessage);
        });

    }

    @Test
    @DisplayName("setPoint 테스트")
    void setPointMessage(){
        int sysid = 200;
        float x = 20.0F;
        float y = 10.0F;
        float z = -20.0F;

        MAVLinkMessage mavLinkMessage = MavLinkMessageFactory.setPoint(sysid, x, y, z);
        byte[] packetData = MAVLinkUtils.getMessage(mavLinkMessage);

        MAVLinkMessage decoded = MAVLinkUtils.getMessage(packetData, packetData.length).get();

        assertThat(decoded).isNotNull();
        assertThat(decoded).isInstanceOf(msg_set_position_target_local_ned.class);


        msg_set_position_target_local_ned msg = (msg_set_position_target_local_ned) decoded;

        assertThat(msg.sysid).isEqualTo(sysid);
        assertThat(msg.target_system).isEqualTo((short)sysid);
        assertThat(msg.target_component).isEqualTo((short)0);
        assertThat(msg.type_mask).isEqualTo(MAVLINK_MSG_SET_POSITION_TARGET_LOCAL_NED_POSITION);
        assertThat(msg.coordinate_frame).isEqualTo((short) MAV_FRAME_LOCAL_NED);
        assertThat(msg.time_boot_ms).isEqualTo((short)0);
        assertThat(msg.x).isEqualTo(x);
        assertThat(msg.y).isEqualTo(y);
        assertThat(msg.z).isEqualTo(z);
    }

    @Test
    @DisplayName("heartbeat 테스트")
    void heartbeatMessage(){
        int sysid = 201;

        MAVLinkMessage mavLinkMessage = MavLinkMessageFactory.heartBeat(sysid);
        byte[] packetData = MAVLinkUtils.getMessage(mavLinkMessage);

        MAVLinkMessage decoded = MAVLinkUtils.getMessage(packetData, packetData.length).get();

        assertThat(decoded).isNotNull();
        assertThat(decoded).isInstanceOf(msg_heartbeat.class);


        msg_heartbeat msg = (msg_heartbeat) decoded;

        assertThat(msg.sysid).isEqualTo(sysid);
        assertThat(msg.msgid).isEqualTo(MAVLINK_MSG_ID_HEARTBEAT);
        assertThat(msg.type).isEqualTo((short)0x06);
        assertThat(msg.autopilot).isEqualTo((short)0x08);
        assertThat(msg.base_mode).isEqualTo((short)0xc0);
        assertThat(msg.custom_mode).isEqualTo((short)0x0000);
        assertThat(msg.system_status).isEqualTo((short)0x04);
        assertThat(msg.mavlink_version).isEqualTo((short)0x03);
    }


    @ParameterizedTest
    @ValueSource(floats = {10.5F, 20.121F, 40.113214123123351F})
    @DisplayName("시나리오 시작 시간 설정 메시지 테스트")
    void scenarioParameterizedStartTime(float startTime) {
        int sysid = 1;

        MAVLinkMessage mavLinkMessage = MavLinkMessageFactory.setScenarioStartTime(sysid, startTime);
        byte[] packetData = MAVLinkUtils.getMessage(mavLinkMessage);

        MAVLinkMessage decoded = MAVLinkUtils.getMessage(packetData, packetData.length).get();


        assertThat(decoded).isNotNull();
        assertThat(decoded).isInstanceOf(msg_scenario_cmd.class);

        msg_scenario_cmd msg = (msg_scenario_cmd) decoded;

        assertThat(msg.sysid).isEqualTo(sysid);
        assertThat(msg.target_system).isEqualTo((short)sysid);
        assertThat(msg.param1).isEqualTo(startTime);
        assertThat(msg.param2).isEqualTo(0);
        assertThat(msg.param3).isEqualTo(0);
        assertThat(msg.param4).isEqualTo(0);
        assertThat(msg.isMavlink2).isEqualTo(true);
    }



    @Test
    void stopScenario() {
        int sysid = 203;

        MAVLinkMessage mavLinkMessage = MavLinkMessageFactory.stopScenario(sysid);
        byte[] packetData = MAVLinkUtils.getMessage(mavLinkMessage);

        MAVLinkMessage decoded = MAVLinkUtils.getMessage(packetData, packetData.length).get();


        assertThat(decoded).isNotNull();
        assertThat(decoded).isInstanceOf(msg_scenario_cmd.class);

        msg_scenario_cmd msg = (msg_scenario_cmd) decoded;

        assertThat(msg.sysid).isEqualTo(sysid);
        assertThat(msg.target_system).isEqualTo((short)sysid);
        assertThat(msg.cmd).isEqualTo((short) SCENARIO_CMD_STOP_SCENARIO);
        assertThat(msg.isMavlink2).isEqualTo(true);
    }

    @Test
    @DisplayName("시나리오 리셋 메시지 테스트")
    void resetScenario() {
        int sysid = 203;

        MAVLinkMessage mavLinkMessage = MavLinkMessageFactory.stopScenario(sysid);
        byte[] packetData = MAVLinkUtils.getMessage(mavLinkMessage);

        MAVLinkMessage decoded = MAVLinkUtils.getMessage(packetData, packetData.length).get();


        assertThat(decoded).isNotNull();
        assertThat(decoded).isInstanceOf(msg_scenario_cmd.class);

        msg_scenario_cmd msg = (msg_scenario_cmd) decoded;

        assertThat(msg.sysid).isEqualTo(sysid);
        assertThat(msg.target_system).isEqualTo((short)sysid);
        assertThat(msg.cmd).isEqualTo((short) SCENARIO_CMD_STOP_SCENARIO);
        assertThat(msg.isMavlink2).isEqualTo(true);
    }

    @Test
    @DisplayName("시나리오 config 메시지 테스트")
    void setScenarioConfigs() {
        int sysid = 203;
        float x = 1.0F;
        float y = 2.0F;
        float rot = -20.1F;
        String path = "circle";


        MAVLinkMessage mavLinkMessage = MavLinkMessageFactory.setScenarioConfigs(sysid, x, y, rot, path);
        byte[] packetData = MAVLinkUtils.getMessage(mavLinkMessage);

        MAVLinkMessage decoded = MAVLinkUtils.getMessage(packetData, packetData.length).get();


        assertThat(decoded).isNotNull();
        assertThat(decoded).isInstanceOf(msg_scenario_cmd.class);

        msg_scenario_cmd msg = (msg_scenario_cmd) decoded;

        assertThat(msg.sysid).isEqualTo(sysid);
        assertThat(msg.target_system).isEqualTo((short)sysid);
        assertThat(msg.cmd).isEqualTo((short) SCENARIO_CMD_SET_CONFIGS);
        assertThat(msg.param1).isEqualTo(x);
        assertThat(msg.param2).isEqualTo(y);

        short[] msgPathShort = msg.param5;
        char[] pathChar = new char[path.length()];
        for (int i = 0; i < path.length(); i++) {
            pathChar[i] = (char) msgPathShort[i];
        }

        System.out.println(Arrays.toString(msgPathShort));
        System.out.println(Arrays.toString(pathChar));
        String decodedPath = String.valueOf(pathChar);

        System.out.println(decodedPath);
        assertThat(decodedPath).isEqualTo(path);
    }

    @Test
    @DisplayName("시나리오 리셋 메시지 테스트")
    void emergencyLanding() {

    }

    @Test
    @DisplayName("자이로 센서 캘리브레이션 테스트")
    void calibrationGyro(){

    }

    @Test
    @DisplayName("레벨 센서 캘리브레이션 테스트")
    void calibrationLevel(){

    }

    @Test
    @DisplayName("엑셀레이터 캘리브레이션 테스트")
    void calibrationAccel(){

    }

    @Test
    @DisplayName("드론의 파라미터 요청 테스트")
    void requestParamTest(){

    }


    @Test
    @DisplayName("드론의 파라미터 변경 메시지 테스트")
    void setParamTest(){
        int sysid = 203;
        String paramName = "BAT1";

        MAVLinkMessage mavLinkMessage = MavLinkMessageFactory.requestParams(sysid, paramName);
        byte[] packetData = MAVLinkUtils.getMessage(mavLinkMessage);

        MAVLinkMessage decoded = MAVLinkUtils.getMessage(packetData, packetData.length).get();


        assertThat(decoded).isNotNull();
        assertThat(decoded).isInstanceOf(msg_scenario_cmd.class);

        msg_scenario_cmd msg = (msg_scenario_cmd) decoded;



    }
}