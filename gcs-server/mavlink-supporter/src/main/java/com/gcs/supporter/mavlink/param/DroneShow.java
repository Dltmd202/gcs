package com.gcs.supporter.mavlink.param;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.MAVLink.enums.MAV_PARAM_TYPE.*;

public enum DroneShow implements ParamameterKey {
    BAT1_CAPACITY(
            "BAT1_CAPACITY",
            "Battery 1 capacity",
            -1,
            MAV_PARAM_TYPE_INT32,
            "mAh"
    ),
    PWM_MAIN_MIN(
            "PWM_MAIN_MIN",
            "PWM main maxmimum value",
            1000,
            MAV_PARAM_TYPE_INT32,
            "us"
    ),
    BAT_N_CELLS(
            "BAT_N_CELLS",
            "This parameter is deprecated. Please use BAT1_N_CELLS instead",
            3,
            MAV_PARAM_TYPE_INT32,
            ""
    ),

    MIS_TAKEOFF_ALT(
            "MIS_TAKEOFF_ALT",
            "Take-off altitude",
            2.5F,
            MAV_PARAM_TYPE_REAL32,
            "m"
    ),
    RTL_RETURN_ALT(
            "RTL_RETURN_ALT",
            "Return mode return altitude",
            60.0F,
            MAV_PARAM_TYPE_REAL32,
            "m"
    ),
    MPC_MANTHR_MIN(
            "MPC_MANTHR_MIN",
            "Minimum manual thrust",
            8.0F,
            MAV_PARAM_TYPE_REAL32,
            "%"
    ),
    MPC_LAND_SPEED(
            "MPC_LAND_SPEED",
            "Landing descend rate",
            0.7F,
            MAV_PARAM_TYPE_REAL32,
            "m/s"
    ),
    MPC_LAND_ALT1(
            "MPC_LAND_ALT1",
            "Altitude for 1. step of slow landing(descend)",
            10.0F,
            MAV_PARAM_TYPE_REAL32,
            "m"
    ),
    MPC_LAND_ALT2(
            "MPC_LAND_ALT2",
            "Altitude for 2. step of slow landing(descend)",
            5.0F,
            MAV_PARAM_TYPE_REAL32,
            "m"
    ),
    MPC_LAND_CRWL(
            "MPC_LAND_CRWL",
            "Land crawl descend rate. Used below",
            5.0F,
            MAV_PARAM_TYPE_REAL32,
            "m"
    ),
    MPC_TILTMAX_AIR(
            "MPC_TILTMAX_AIR",
            "Maximum tilt angle in air",
            45.0F,
            MAV_PARAM_TYPE_REAL32,
            "deg"
    ),
    MPC_XY_P(
             "MPC_XY_P",
             "Proportional gain for horizontal position error",
             0.95F,
             MAV_PARAM_TYPE_REAL32,
             ""
    ),
    MPC_XY_VEL_P_ACC(
                "MPC_XY_VEL_P_ACC",
                "Proportional gain for horizontal velocity error",
                1.80F,
                MAV_PARAM_TYPE_REAL32,
                ""
    ),
    MPC_XY_VEL_I_ACC(
                "MPC_XY_VEL_I_ACC",
                "Integral gain for horizontal velocity error",
                0.400F,
                MAV_PARAM_TYPE_REAL32,
                ""
    ),
    MPC_XY_VEL_D_ACC(
                "MPC_XY_VEL_D_ACC",
                "Differential gain for horizontal velocity error. Small values help reduce fast oscillations.",
                0.200F,
                MAV_PARAM_TYPE_REAL32,
                ""
    ),
    MPC_XY_VEL_MAX(
                  "MPC_XY_VEL_MAX",
                  "Maximum horizontal velocity error",
                  12.00,
                  MAV_PARAM_TYPE_REAL32,
                  "m/s"
    ),
    MPC_Z_P(
            "MPC_Z_P",
            "Proportional gain for vertical position error",
            1.00F,
            MAV_PARAM_TYPE_REAL32,
            ""
    ),
    MPC_Z_VEL_MAX_UP(
            "MPC_Z_VEL_MAX_UP",
            "Maximum ascent velocity",
        3.0F,
            MAV_PARAM_TYPE_REAL32,
            "m/s"
    ),
    MPC_Z_VEL_MAX_DN(
                    "MPC_Z_VEL_MAX_DN",
                    "Maximum descent velocity",
                    1.0F,
                    MAV_PARAM_TYPE_REAL32,
                    "m/s"
    ),
    MPC_Z_VEL_P_ACC(
               "MPC_Z_VEL_P_ACC",
               "Proportional gain for vertical velocity error",
               4.00F,
               MAV_PARAM_TYPE_REAL32,
               ""
    ),
    MPC_Z_VEL_I_ACC(
               "MPC_Z_VEL_I_ACC",
               "Integral gain for vertical velocity error",
               2.000F,
               MAV_PARAM_TYPE_REAL32,
               ""
    ),
    MPC_Z_VEL_D_ACC(
               "MPC_Z_VEL_D_ACC",
               "Differential gain vertical velocity error",
               0.000F,
               MAV_PARAM_TYPE_REAL32,
               ""
    ),
    MPC_THR_MIN(
               "MPC_THR_MIN",
               "Minimum collective thrust in auto thrust control",
               12.00F,
               MAV_PARAM_TYPE_REAL32,
               "%"
    ),
    MPC_THR_MAX(
               "MPC_THR_MAX",
            "Maxtimum collective thrust in auto thrust control",
               100.00F,
               MAV_PARAM_TYPE_REAL32,
               "%"
    ),
    MPC_THR_HOVER(
                 "MPC_THR_HOVER",
                 "Hover thrust",
                 50.00F,
                 MAV_PARAM_TYPE_REAL32,
                 "%"
    ),
    MPC_HOLD_MAX_XY(
                   "MPC_HOLD_MAX_XY",
                   "Maximum horizontal velocity for which position hold is enabled (use 0 to disable check)",
                   0.80F,
                   MAV_PARAM_TYPE_REAL32,
                   "m/s"
    ),
    MC_ROLL_P(
             "MC_ROLL_P",
             "Roll P gain",
             6.50F,
             MAV_PARAM_TYPE_REAL32,
             ""
    ),
    MC_ROLLRATE_P(
                 "MC_ROLLRATE_P",
                 "Roll rate P gain",
                 0.150F,
                 MAV_PARAM_TYPE_REAL32,
                 ""
    ),
    MC_ROLLRATE_I(
                 "MC_ROLLRATE_I",
                 "Roll rate I gain",
                 0.200F,
                 MAV_PARAM_TYPE_REAL32,
                 ""
    ),
    MC_ROLLRATE_D(
                 "MC_ROLLRATE_D",
                "Roll rate D gain",
                0.0030F,
                MAV_PARAM_TYPE_REAL32,
                ""
    ),
    MC_ROLLRATE_MAX(
                 "MC_ROLLRATE_MAX",
                "Max roll rate",
                220.0,
                MAV_PARAM_TYPE_REAL32,
                "deg/s"
    ),
    MC_PITCH_P(
              "MC_PITCH_P",
            "Pitch P gain",
            6.50F,
            MAV_PARAM_TYPE_REAL32,
            ""
    ),
    MC_PITCHRATE_P(
                  "MC_PITCHRATE_P",
                  "Pitch rate P gain",
                  0.150F,
                  MAV_PARAM_TYPE_REAL32,
                  ""
    ),
    MC_PITCHRATE_I(
            "MC_PITCHRATE_I",
            "Pitch rate I gain",
            0.200F,
            MAV_PARAM_TYPE_REAL32,
            ""
    ),
    MC_PITCHRATE_D(
                  "MC_PITCHRATE_D",
                  "Pitch rate D gain",
                  0.0030,
                  MAV_PARAM_TYPE_REAL32,
                  ""
    ),
    MC_YAW_P(
             "MC_YAW_P",
             "Yaw P gain",
             2.80F,
             MAV_PARAM_TYPE_REAL32,
             ""
    ),
    MC_YAWRATE_P(
                "MC_YAWRATE_P",
                "Yaw rate P gain",
                0.20F,
                MAV_PARAM_TYPE_REAL32,
                ""
    ),
    MC_YAWRATE_I(
                "MC_YAWRATE_I",
                "Yaw rate I gain",
                0.10F,
                MAV_PARAM_TYPE_REAL32,
                ""
    ),
    MC_YAWRATE_D(
                "MC_YAWRATE_D",
                "Yaw rate D gain",
                0.00F,
                MAV_PARAM_TYPE_REAL32,
                ""
    ),
    MC_YAWRATE_MAX(
                  "MC_YAWRATE_MAX",
                  "Max yaw rate",
            200.0F,
                  MAV_PARAM_TYPE_REAL32,
                  "deg/s"
    ),
    SYS_MC_EST_GROUP(
                    "SYS_MC_EST_GROUP",
                    "Set multicopter estimator group",
                    0,
                    MAV_PARAM_TYPE_INT32,
                    ""
    ),
    ATT_W_MAG(
             "ATT_W_MAG",
             "",
             0.15F,
             MAV_PARAM_TYPE_REAL32,
             ""
    ),
    COM_ARM_IMU_ACC(
                   "COM_ARM_IMU_ACC",
                   "",
                   0.7F,
                   MAV_PARAM_TYPE_REAL32,
                   ""
    ),
    COM_ARM_IMU_GYR(
                   "COM_ARM_IMU_GYR",
                   "",
                   0.25F,
                   MAV_PARAM_TYPE_REAL32,
                   ""
    ),
    SDLOG_MODE(
              "SDLOG_MODE",
              "Logging Mode",
              1,
              MAV_PARAM_TYPE_INT32,
              ""
    ),
    CBRK_VELPOSERR(
                  "CBRK_VELPOSERR",
                  "Circuit breaker for position error check",
                  1,
                  MAV_PARAM_TYPE_INT32,
                  ""
    ),
    COM_RC_LOSS_T(
                 "COM_RC_LOSS_T",
                 "RC loss time threshold",
                 2400.0F,
                 MAV_PARAM_TYPE_REAL32,
                 "s"
    ),
    COM_FLTMODE1(
                 "COM_FLTMODE1",
                 "First flightmode slot(1000-1160)",
                 0,
                 MAV_PARAM_TYPE_INT32,
                 ""
    ),
    COM_FLTMODE4(
                 "COM_FLTMODE4",
                 "Fourth flightmode slot(1480-1640)",
                 2,
                 MAV_PARAM_TYPE_INT32,
                 ""
    ),
    COM_FLTMODE6(
                 "COM_FLTMODE4",
                 "Sixth flightmode slot(1800-2000)",
                 11,
                 MAV_PARAM_TYPE_INT32,
                 ""
    ),
    EKF2_HGT_MODE(
            "EKF2_HGT_MODE",
            "Barometric pressure Determines the primary source of height data used by the EKF",
            0.5F,
            MAV_PARAM_TYPE_REAL32,
            "m"
    ),
    EKF2_GPS_P_NOISE(
            "EKF2_GPS_P_NOISE",
            "Measurement noise for gps position",
            0.5F,
            MAV_PARAM_TYPE_REAL32,
            "m"
    ),
    EKF2_GPS_V_NOISE(
            "EKF2_GPS_V_NOISE",
            "Measurement noise for gps horizontal velocity",
            0.3F,
            MAV_PARAM_TYPE_REAL32,
            "m/s"
    );

    private String value;
    private String info;
    private Object defaultValue;
    private int valueType;
    private String unit;

    DroneShow(String value, String info, Object defaultValue, int valueType, String unit) {
        this.value = value;
        this.info = info;
        this.defaultValue = defaultValue;
        this.valueType = valueType;
        this.unit = unit;
    }

    public static BatteryCalibration of(String name){
        for (BatteryCalibration batteryCalibration : BatteryCalibration.values()) {
            if(batteryCalibration.value.equalsIgnoreCase(name))
                return batteryCalibration;
        }
        return null;
    }

    public static List<ParameterKeyDto> of(){
        return Arrays.stream(DroneShow.values())
                .map(ParameterKeyDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public Object getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Integer getValueType() {
        return valueType;
    }

    @Override
    public String getUnit() {
        return unit;
    }
}
