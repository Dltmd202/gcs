package com.gcs.supporter.mavlink.param;

public interface ParamameterKey {
    String getValue();
    String getInfo();
    Object getDefaultValue();
    Integer getValueType();
    String getUnit();
}
