package com.gcs.supporter.mavlink.message.adapter;

import com.MAVLink.common.msg_param_value;
import com.gcs.domain.agent.model.Parameter;
import lombok.Getter;

import java.io.Serializable;

import static com.MAVLink.enums.MAV_PARAM_TYPE.*;

@Getter
public class MessageParamValueAdapter implements Serializable, Parameter {
    private Integer sysid;
    private String paramId;
    private Object value;

    public MessageParamValueAdapter(msg_param_value msg) {
        this.sysid = msg.sysid;

        int len = 0;

        for (int i = msg.param_id.length - 1; i >= 0; i--) {
            if(msg.param_id[i] != 0){
                len = i + 1;
                break;

            }
        }

        this.paramId = new String(msg.param_id, 0, len);

        switch (msg.param_type){
            case MAV_PARAM_TYPE_INT8:
                this.value = (byte) Float.floatToIntBits(msg.param_value);
                break;
            case MAV_PARAM_TYPE_INT32:
            case MAV_PARAM_TYPE_INT16:
            case MAV_PARAM_TYPE_UINT8:
            case MAV_PARAM_TYPE_UINT16:
                this.value = Float.floatToIntBits(msg.param_value);
                break;
            case MAV_PARAM_TYPE_UINT32:
            case MAV_PARAM_TYPE_INT64:
                this.value = Double.doubleToLongBits(msg.param_value);
                break;
            case MAV_PARAM_TYPE_REAL32:
            case MAV_PARAM_TYPE_REAL64:
                this.value = msg.param_value;
                break;
            default:
                this.value = Float.NaN;
        }


    }
}
