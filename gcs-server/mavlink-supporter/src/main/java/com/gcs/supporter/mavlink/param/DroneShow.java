package com.gcs.supporter.mavlink.param;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.MAVLink.enums.MAV_PARAM_TYPE.*;

public enum DroneShow implements ParamameterKey {
    MC_ROLLRATE_P(
            "MC_ROLLRATE_P",
            "Roll rate P gain",
            0.20F,
            MAV_PARAM_TYPE_REAL32,
            ""
    ),
    MC_ROLLRATE_I(
            "MC_ROLLRATE_I",
            "Roll rate I gain",
            0.20F,
            MAV_PARAM_TYPE_REAL32,
            ""
    ),
    MC_ROLLRATE_MAX(
            "MC_ROLLRATE_MAX",
            "Max roll rate",
            90.0F,
            MAV_PARAM_TYPE_REAL32,
            "deg/s"
    ),
    MC_PITCHRATE_P(
            "MC_PITCHRATE_P",
            "Pitch rate P gain",
            0.2F,
            MAV_PARAM_TYPE_REAL32,
            ""
    ),
    MC_PITCHRATE_I(
            "MC_PITCHRATE_I",
            "Pitch rate I gain",
            0.5F,
            MAV_PARAM_TYPE_REAL32,
            ""
    ),
    MC_PITCHRATE_MAX(
            "MC_PITCHRATE_MAX",
            "",
            90.0F,
            MAV_PARAM_TYPE_REAL32,
            ""
    ),


    MPC_XY_VEL_MAX(
            "MPC_XY_VEL_MAX",
            "Maximum horizontal velocity error",
            3.00F,
            MAV_PARAM_TYPE_REAL32,
            "m/s"
    ),
    MPC_XY_VEL_P_ACC(
            "MPC_XY_VEL_P_ACC",
            "Proportional gain for horizontal velocity error",
            3.20F,
            MAV_PARAM_TYPE_REAL32,
            ""
    ),
    MPC_Z_VEL_MAX_DN(
            "MPC_Z_VEL_MAX_DN",
            "Maximum descent velocity",
            3.00F,
            MAV_PARAM_TYPE_REAL32,
            "m/s"
    ),
    MPC_Z_VEL_MAX_UP(
            "MPC_Z_VEL_MAX_UP",
            "Maximum ascent velocity",
            3.0F,
            MAV_PARAM_TYPE_REAL32,
            "m/s"
    ),
    MPC_LAND_SPEED(
            "MPC_LAND_SPEED",
            "Landing descend rate",
            0.2F,
            MAV_PARAM_TYPE_REAL32,
            "m/s"
    ),
    MPC_LAND_ALT2(
            "MPC_LAND_ALT2",
            "Altitude for 2. step of slow landing(descend)",
            0.5F,
            MAV_PARAM_TYPE_REAL32,
            "m"
    ),
    MPC_LAND_ALT1(
            "MPC_LAND_ALT1",
            "Altitude for 1. step of slow landing(descend)",
            2.5F,
            MAV_PARAM_TYPE_REAL32,
            "m"
    ),
    MPC_LAND_CRWL(
            "MPC_LAND_CRWL",
            "Land crawl descend rate. Used below",
            0.1F,
            MAV_PARAM_TYPE_REAL32,
            "m"
    ),


    EKF2_HGT_MODE(
            "EKF2_HGT_MODE",
            "Barometric pressure Determines the primary source of height data used by the EKF",
            1,
            MAV_PARAM_TYPE_INT32,
            "m"
    ),
    EKF2_GPS_P_NOISE(
            "EKF2_GPS_P_NOISE",
            "Measurement noise for gps position",
            0.2F,
            MAV_PARAM_TYPE_REAL32,
            "m"
    ),
    EKF2_GPS_V_NOISE(
            "EKF2_GPS_V_NOISE",
            "Measurement noise for gps horizontal velocity",
            0.2F,
            MAV_PARAM_TYPE_REAL32,
            "m/s"
    ),


    BAT1_V_DIV(
            "BAT1_V_DIV",
            "",
            12.12273693F,
            MAV_PARAM_TYPE_REAL32,
            ""
    ),
    BAT1_V_CHARGED(
            "BAT1_V_CHARGED",
            "Full cell voltage (5c load)",
            4.20F,
            MAV_PARAM_TYPE_REAL32,
            "V"
    ),
    BAT1_N_CELLS(
            "BAT1_N_CELLS",
            "This parameter is deprecated. Please use BAT1_N_CELLS instead",
            4,
            MAV_PARAM_TYPE_INT32,
            ""
    ),
    BAT1_V_EMPTY(
            "BAT1_V_EMPTY",
            "Empty cell voltage (5C load)",
            3.50F,
            MAV_PARAM_TYPE_REAL32,
            "V"
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
            "COM_FLTMODE6",
            "",
            11,
            MAV_PARAM_TYPE_INT32,
            ""
    ),
    RC_MAP_OFFB_SW(
            "RC_MAP_OFFB_SW",
            "Offboard switch channel",
            0,
            MAV_PARAM_TYPE_INT32,
            ""
    ),
    SDLOG_MODE(
            "SDLOG_MODE",
            "Logging Mode",
            1,
            MAV_PARAM_TYPE_INT32,
            ""
    ),
    MAV_0_MODE(
            "MAV_0_MODE",
            "",
            1,
            MAV_PARAM_TYPE_INT32,
            ""
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
