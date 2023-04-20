package com.gcs.supporter.mavlink.param;

import com.MAVLink.enums.MAV_PARAM_TYPE;

public enum BatteryCalibration {

    BAT1_CAPACITY(
            "BAT1_CAPACITY",
            "Battery 1 capacity",
            -1,
            MAV_PARAM_TYPE.MAV_PARAM_TYPE_INT32,
            "mAh"
    ),
    BAT1_R_INTERNAL(
            "BAT1_N_CELLS",
            "Number of cells for battery 1",
            0,
            MAV_PARAM_TYPE.MAV_PARAM_TYPE_INT32,
            "Battery"
    );


    private String value;
    private String info;
    private Object defaultValue;
    private int valueType;
    private String unit;

    BatteryCalibration(String value, String info, Object defaultValue, int valueType, String unit) {
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
}
