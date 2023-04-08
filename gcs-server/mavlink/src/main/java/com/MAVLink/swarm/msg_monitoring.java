/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE MONITORING PACKING
package com.MAVLink.swarm;
import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
import com.MAVLink.Messages.Units;
import com.MAVLink.Messages.Description;

/**
 * monitoring
 */
public class msg_monitoring extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_MONITORING = 184;
    public static final int MAVLINK_MSG_LENGTH = 67;
    private static final long serialVersionUID = MAVLINK_MSG_ID_MONITORING;

    
    /**
     * Time Of Week
     */
    @Description("Time Of Week")
    @Units("")
    public long tow;
    
    /**
     * current position x
     */
    @Description("current position x")
    @Units("")
    public float pos_x;
    
    /**
     * current position y
     */
    @Description("current position y")
    @Units("")
    public float pos_y;
    
    /**
     * current position z
     */
    @Description("current position z")
    @Units("")
    public float pos_z;
    
    /**
     * current heading
     */
    @Description("current heading")
    @Units("")
    public float head;
    
    /**
     * x velocity in NED frame
     */
    @Description("x velocity in NED frame")
    @Units("")
    public float vx;
    
    /**
     * y velocity in NED frame
     */
    @Description("y velocity in NED frame")
    @Units("")
    public float vy;
    
    /**
     * z velocity in NED frame
     */
    @Description("z velocity in NED frame")
    @Units("")
    public float vz;
    
    /**
     * Roll
     */
    @Description("Roll")
    @Units("")
    public float roll;
    
    /**
     * Pitch
     */
    @Description("Pitch")
    @Units("")
    public float pitch;
    
    /**
     * Yaw
     */
    @Description("Yaw")
    @Units("")
    public float yaw;
    
    /**
     * status #1
     */
    @Description("status #1")
    @Units("")
    public long status1;
    
    /**
     * status #2 (RESERVED)
     */
    @Description("status #2 (RESERVED)")
    @Units("")
    public long status2;
    
    /**
     * Rtk Baseline North coordinate
     */
    @Description("Rtk Baseline North coordinate")
    @Units("")
    public float rtk_n;
    
    /**
     * Rtk Baseline East coordinate
     */
    @Description("Rtk Baseline East coordinate")
    @Units("")
    public float rtk_e;
    
    /**
     * Rtk Baseline Down coordinate
     */
    @Description("Rtk Baseline Down coordinate")
    @Units("")
    public float rtk_d;
    
    /**
     * the number of base satellite
     */
    @Description("the number of base satellite")
    @Units("")
    public short rtk_nbase;
    
    /**
     * the number of rover satellite
     */
    @Description("the number of rover satellite")
    @Units("")
    public short rtk_nrover;
    
    /**
     * Battery (%)
     */
    @Description("Battery (%)")
    @Units("")
    public short battery;
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = sysid;
        packet.compid = compid;
        packet.msgid = MAVLINK_MSG_ID_MONITORING;

        packet.payload.putUnsignedInt(tow);
        packet.payload.putFloat(pos_x);
        packet.payload.putFloat(pos_y);
        packet.payload.putFloat(pos_z);
        packet.payload.putFloat(head);
        packet.payload.putFloat(vx);
        packet.payload.putFloat(vy);
        packet.payload.putFloat(vz);
        packet.payload.putFloat(roll);
        packet.payload.putFloat(pitch);
        packet.payload.putFloat(yaw);
        packet.payload.putUnsignedInt(status1);
        packet.payload.putUnsignedInt(status2);
        packet.payload.putFloat(rtk_n);
        packet.payload.putFloat(rtk_e);
        packet.payload.putFloat(rtk_d);
        packet.payload.putUnsignedByte(rtk_nbase);
        packet.payload.putUnsignedByte(rtk_nrover);
        packet.payload.putUnsignedByte(battery);
        
        if (isMavlink2) {
            
        }
        return packet;
    }

    /**
     * Decode a monitoring message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.tow = payload.getUnsignedInt();
        this.pos_x = payload.getFloat();
        this.pos_y = payload.getFloat();
        this.pos_z = payload.getFloat();
        this.head = payload.getFloat();
        this.vx = payload.getFloat();
        this.vy = payload.getFloat();
        this.vz = payload.getFloat();
        this.roll = payload.getFloat();
        this.pitch = payload.getFloat();
        this.yaw = payload.getFloat();
        this.status1 = payload.getUnsignedInt();
        this.status2 = payload.getUnsignedInt();
        this.rtk_n = payload.getFloat();
        this.rtk_e = payload.getFloat();
        this.rtk_d = payload.getFloat();
        this.rtk_nbase = payload.getUnsignedByte();
        this.rtk_nrover = payload.getUnsignedByte();
        this.battery = payload.getUnsignedByte();
        
        if (isMavlink2) {
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_monitoring() {
        this.msgid = MAVLINK_MSG_ID_MONITORING;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_monitoring( long tow, float pos_x, float pos_y, float pos_z, float head, float vx, float vy, float vz, float roll, float pitch, float yaw, long status1, long status2, float rtk_n, float rtk_e, float rtk_d, short rtk_nbase, short rtk_nrover, short battery) {
        this.msgid = MAVLINK_MSG_ID_MONITORING;

        this.tow = tow;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.pos_z = pos_z;
        this.head = head;
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
        this.roll = roll;
        this.pitch = pitch;
        this.yaw = yaw;
        this.status1 = status1;
        this.status2 = status2;
        this.rtk_n = rtk_n;
        this.rtk_e = rtk_e;
        this.rtk_d = rtk_d;
        this.rtk_nbase = rtk_nbase;
        this.rtk_nrover = rtk_nrover;
        this.battery = battery;
        
    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_monitoring( long tow, float pos_x, float pos_y, float pos_z, float head, float vx, float vy, float vz, float roll, float pitch, float yaw, long status1, long status2, float rtk_n, float rtk_e, float rtk_d, short rtk_nbase, short rtk_nrover, short battery, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_MONITORING;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.tow = tow;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.pos_z = pos_z;
        this.head = head;
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
        this.roll = roll;
        this.pitch = pitch;
        this.yaw = yaw;
        this.status1 = status1;
        this.status2 = status2;
        this.rtk_n = rtk_n;
        this.rtk_e = rtk_e;
        this.rtk_d = rtk_d;
        this.rtk_nbase = rtk_nbase;
        this.rtk_nrover = rtk_nrover;
        this.battery = battery;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_monitoring(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_MONITORING;

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
        return "MAVLINK_MSG_ID_MONITORING - sysid:"+sysid+" compid:"+compid+" tow:"+tow+" pos_x:"+pos_x+" pos_y:"+pos_y+" pos_z:"+pos_z+" head:"+head+" vx:"+vx+" vy:"+vy+" vz:"+vz+" roll:"+roll+" pitch:"+pitch+" yaw:"+yaw+" status1:"+status1+" status2:"+status2+" rtk_n:"+rtk_n+" rtk_e:"+rtk_e+" rtk_d:"+rtk_d+" rtk_nbase:"+rtk_nbase+" rtk_nrover:"+rtk_nrover+" battery:"+battery+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_MONITORING";
    }
}
        