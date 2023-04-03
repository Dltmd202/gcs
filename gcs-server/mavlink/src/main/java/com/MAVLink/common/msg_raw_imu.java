/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE RAW_IMU PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.Description;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
import com.MAVLink.Messages.Units;

/**
 * The RAW IMU readings for a 9DOF sensor, which is identified by the id (default IMU1). This message should always contain the true raw values without any scaling to allow data capture and system debugging.
 */
public class msg_raw_imu extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_RAW_IMU = 27;
    public static final int MAVLINK_MSG_LENGTH = 29;
    private static final long serialVersionUID = MAVLINK_MSG_ID_RAW_IMU;

    
    /**
     * Timestamp (UNIX Epoch time or time since system boot). The receiving end can infer timestamp format (since 1.1.1970 or since system boot) by checking for the magnitude of the number.
     */
    @Description("Timestamp (UNIX Epoch time or time since system boot). The receiving end can infer timestamp format (since 1.1.1970 or since system boot) by checking for the magnitude of the number.")
    @Units("us")
    public long time_usec;
    
    /**
     * X acceleration (raw)
     */
    @Description("X acceleration (raw)")
    @Units("")
    public short xacc;
    
    /**
     * Y acceleration (raw)
     */
    @Description("Y acceleration (raw)")
    @Units("")
    public short yacc;
    
    /**
     * Z acceleration (raw)
     */
    @Description("Z acceleration (raw)")
    @Units("")
    public short zacc;
    
    /**
     * Angular speed around X axis (raw)
     */
    @Description("Angular speed around X axis (raw)")
    @Units("")
    public short xgyro;
    
    /**
     * Angular speed around Y axis (raw)
     */
    @Description("Angular speed around Y axis (raw)")
    @Units("")
    public short ygyro;
    
    /**
     * Angular speed around Z axis (raw)
     */
    @Description("Angular speed around Z axis (raw)")
    @Units("")
    public short zgyro;
    
    /**
     * X Magnetic field (raw)
     */
    @Description("X Magnetic field (raw)")
    @Units("")
    public short xmag;
    
    /**
     * Y Magnetic field (raw)
     */
    @Description("Y Magnetic field (raw)")
    @Units("")
    public short ymag;
    
    /**
     * Z Magnetic field (raw)
     */
    @Description("Z Magnetic field (raw)")
    @Units("")
    public short zmag;
    
    /**
     * Id. Ids are numbered from 0 and map to IMUs numbered from 1 (e.g. IMU1 will have a message with id=0)
     */
    @Description("Id. Ids are numbered from 0 and map to IMUs numbered from 1 (e.g. IMU1 will have a message with id=0)")
    @Units("")
    public short id;
    
    /**
     * Temperature, 0: IMU does not provide temperature values. If the IMU is at 0C it must send 1 (0.01C).
     */
    @Description("Temperature, 0: IMU does not provide temperature values. If the IMU is at 0C it must send 1 (0.01C).")
    @Units("cdegC")
    public short temperature;
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = sysid;
        packet.compid = compid;
        packet.msgid = MAVLINK_MSG_ID_RAW_IMU;

        packet.payload.putUnsignedLong(time_usec);
        packet.payload.putShort(xacc);
        packet.payload.putShort(yacc);
        packet.payload.putShort(zacc);
        packet.payload.putShort(xgyro);
        packet.payload.putShort(ygyro);
        packet.payload.putShort(zgyro);
        packet.payload.putShort(xmag);
        packet.payload.putShort(ymag);
        packet.payload.putShort(zmag);
        
        if (isMavlink2) {
             packet.payload.putUnsignedByte(id);
             packet.payload.putShort(temperature);
            
        }
        return packet;
    }

    /**
     * Decode a raw_imu message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.time_usec = payload.getUnsignedLong();
        this.xacc = payload.getShort();
        this.yacc = payload.getShort();
        this.zacc = payload.getShort();
        this.xgyro = payload.getShort();
        this.ygyro = payload.getShort();
        this.zgyro = payload.getShort();
        this.xmag = payload.getShort();
        this.ymag = payload.getShort();
        this.zmag = payload.getShort();
        
        if (isMavlink2) {
             this.id = payload.getUnsignedByte();
             this.temperature = payload.getShort();
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_raw_imu() {
        this.msgid = MAVLINK_MSG_ID_RAW_IMU;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_raw_imu( long time_usec, short xacc, short yacc, short zacc, short xgyro, short ygyro, short zgyro, short xmag, short ymag, short zmag, short id, short temperature) {
        this.msgid = MAVLINK_MSG_ID_RAW_IMU;

        this.time_usec = time_usec;
        this.xacc = xacc;
        this.yacc = yacc;
        this.zacc = zacc;
        this.xgyro = xgyro;
        this.ygyro = ygyro;
        this.zgyro = zgyro;
        this.xmag = xmag;
        this.ymag = ymag;
        this.zmag = zmag;
        this.id = id;
        this.temperature = temperature;
        
    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_raw_imu( long time_usec, short xacc, short yacc, short zacc, short xgyro, short ygyro, short zgyro, short xmag, short ymag, short zmag, short id, short temperature, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_RAW_IMU;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.time_usec = time_usec;
        this.xacc = xacc;
        this.yacc = yacc;
        this.zacc = zacc;
        this.xgyro = xgyro;
        this.ygyro = ygyro;
        this.zgyro = zgyro;
        this.xmag = xmag;
        this.ymag = ymag;
        this.zmag = zmag;
        this.id = id;
        this.temperature = temperature;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_raw_imu(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_RAW_IMU;

        this.sysid = mavLinkPacket.sysid;
        this.compid = mavLinkPacket.compid;
        this.isMavlink2 = mavLinkPacket.isMavlink2;
        unpack(mavLinkPacket.payload);
    }

                            
    /**
     * Returns a string with the MSG name and data
     */
    @Override
    public String toString() {
        return "MAVLINK_MSG_ID_RAW_IMU - sysid:"+sysid+" compid:"+compid+" time_usec:"+time_usec+" xacc:"+xacc+" yacc:"+yacc+" zacc:"+zacc+" xgyro:"+xgyro+" ygyro:"+ygyro+" zgyro:"+zgyro+" xmag:"+xmag+" ymag:"+ymag+" zmag:"+zmag+" id:"+id+" temperature:"+temperature+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_RAW_IMU";
    }
}
        