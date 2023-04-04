package com.gcs.supporter.mavlink.notification;

import lombok.Getter;

import static com.gcs.supporter.mavlink.notification.MavLinkMonitoringStatus.*;


@Getter
public enum MavLinkMonitoringFlag {
    SAFETY_LOCK_STATUS(
            INFO,
            "Safety Lock",
            0b1
    ),
    ARM_STATUS(
            INFO,
            "ARM",
            0b10
    ),
    OFFBOARD_MODE(
            INFO,
            "OFFBoard",
            0b100
    ),
    MANUAL_MODE(
            INFO,
            "Manual Mode",
            0b1000
    ),
    AUTO_MODE(
            INFO,
            "AutoMode",
            0b10000
    ),
    FAIL_SAFE_MODE(
            WARNING,
            "SafetyMode 진입에 실패하였습니다.",
            0b100000
    ),
    BATTERY_PROBLEM(
            WARNING,
            "배터리 사용에 주의해야합니다.",
            0b1000000
    ),
    RTKGPS_CONNECTION(
            WARNING,
            "RTK GPS에 문제가 있습니다.",
            0b10000000
    ),
    RTKGPS_BASE_RECV(
            INFO,
            "RTK GPS 데이터를 수집하였습니다.",
            0b100000000
    ),
    RTKGPS_FIXED_MODE(
            INFO,
            "RTK GPS가 FIXED 모드입니다.",
            0b1000000000
    ),
    RTKGPS_OFFSET(
            INFO,
            // TODO 무슨 메시지인지 모르겠음
            "RTK GPS OFFSET을 업데이트합니다.",
            0b10000000000
    ),
    COMM_PROBLEM(
            WARNING,
            // TODO 무슨 메시지인지 모르겠음
            "COMM Problem",
            0b100000000000
    ),
    INIT_PITCH_PROBLEM(
            ERROR,
            "잘못된 Pitch 초기값입니다.",
            0b1000000000000
    ),
    INIT_ROLL_PROBLEM(
            ERROR,
            "잘못된 Roll 초기값입니다.",
            0b10000000000000
    ),
    INIT_VELX_PROBLEM(
            ERROR,
            "잘못된 VX 초기값입니다.",
            0b100000000000000
    ),
    INIT_VELY_PROBLEM(
            ERROR,
            "잘못된 VY 초기값입니다.",
            0b1000000000000000
    ),
    INIT_VELZ_PROBLEM(
            ERROR,
            "잘못된 VZ 초기값입니다.",
            0b10000000000000000
    ),
    INIT_EMBEDDED_SC_OFFSET(
            ERROR,
            "잘못된 시나리오 오프셋입니다.",
            0b100000000000000000
    ),
    INIT_EMBEDDED_SC_FILE(
            ERROR,
            "잘못된 시나리오 파일 오프셋입니다.",
            0b1000000000000000000
    ),
    INIT_EMBEDDED_SC_START_TIME(
            ERROR,
            "시나리오 시작 시간이 잘 못 되었습니다.",
            0b10000000000000000000
    ),
    PERI_5V_POWER_PROBLEM(
            ERROR,
            // TODO 무슨 오류인지 모르겠음
            "5V 전류 오류입니다.",
            0b100000000000000000000
    ),
    TEMPERATURE_PROBLEM(
            ERROR,
            "온도가 정상치가 아닙니다.",
            0b1000000000000000000000
    ),
    MAG_INCONSISTENT_PROBLEM(
            ERROR,
            "메시지 형식이 잘 못 되었습니다.",
            0b10000000000000000000000
    ),
    ACC_INCONSISTENT_PROBLEM(
            ERROR,
            // TODO 무슨 오류인지 모르겠음
            "ACC가 부정확합니다.",
            0b100000000000000000000000
    ),
    GYR_INCONSISTENT_PROBLEM(
            ERROR,
            // TODO 무슨 오류인지 모르겠음
            "GYR_INCONSISTENT_PROBLEM",
            0b1000000000000000000000000
    ),
    AGE_CORR_LV1_PROBLEM(
            ERROR,
            // TODO 무슨 오류인지 모르겠음
            "AGE_CORR_LV1_PROBLEM",
            0b10000000000000000000000000
    ),
    AGE_CORR_LV2_PROBLEM(
            ERROR,
            // TODO 무슨 오류인지 모르겠음
            "AGE_CORR_LV2_PROBLEM",
            0b100000000000000000000000000
    ),
    NUM_OF_MONITORING(
            ERROR,
            // TODO 무슨 오류인지 모르겠음
            "NUM_OF_MONITORING",
            0b1000000000000000000000000000
    );

    private MavLinkMonitoringStatus status;
    private String message;
    private long mask;

    MavLinkMonitoringFlag(MavLinkMonitoringStatus status, String message, long mask) {
        this.status = status;
        this.message = message;
        this.mask = mask;
    }
}
