package com.gcs.supporter.mavlink.param;

import lombok.Getter;

@Getter
public class ParameterKeyDto {
    private String value;
    private String info;
    private Object defaultValue;
    private int valueType;
    private String unit;

    public ParameterKeyDto(ParamameterKey paramameterKey){
        this.value = paramameterKey.getValue();
        this.info = paramameterKey.getInfo();
        this.defaultValue = paramameterKey.getDefaultValue();
        this.valueType = paramameterKey.getValueType();
        this.unit = paramameterKey.getUnit();
    }
}
