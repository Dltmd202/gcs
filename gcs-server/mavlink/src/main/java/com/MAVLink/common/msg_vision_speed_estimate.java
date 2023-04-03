/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE VISION_SPEED_ESTIMATE PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.Description;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
import com.MAVLink.Messages.Units;

/**
 * Speed estimate from a vision source.
 */
public class msg_vision_speed_estimate extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_VISION_SPEED_ESTIMATE = 103;
    public static final int MAVLINK_MSG_LENGTH = 57;
    private static final long serialVersionUID = MAVLINK_MSG_ID_VISION_SPEED_ESTIMATE;

    
    /**
     * Timestamp (UNIX time or time since system boot)
     */
    @Description("Timestamp (UNIX time or time since system boot)")
    @Units("us")
    public long usec;
    
    /**
     * Global X speed
     */
    @Description("Global X speed")
    @Units("m/s")
    public float x;
    
    /**
     * Global Y speed
     */
    @Description("Global Y speed")
    @Units("m/s")
    public float y;
    
    /**
     * Global Z speed
     */
    @Description("Global Z speed")
    @Units("m/s")
    public float z;
    
    /**
     * Row-major representation of 3x3 linear velocity covariance matrix (states: vx, vy, vz; 1st three entries - 1st row, etc.). If unknown, assign NaN value to first element in the array.
     */
    @Description("Row-major representation of 3x3 linear velocity covariance matrix (states: vx, vy, vz; 1st three entries - 1st row, etc.). If unknown, assign NaN value to first element in the array.")
    @Units("")
    public float covariance[] = new float[9];
    
    /**
     * Estimate reset counter. This should be incremented when the estimate resets in any of the dimensions (position, velocity, attitude, angular speed). This is designed to be used when e.g an external SLAM system detects a loop-closure and the estimate jumps.
     */
    @Description("Estimate reset counter. This should be incremented when the estimate resets in any of the dimensions (position, velocity, attitude, angular speed). This is designed to be used when e.g an external SLAM system detects a loop-closure and the estimate jumps.")
    @Units("")
    public short reset_counter;
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = sysid;
        packet.compid = compid;
        packet.msgid = MAVLINK_MSG_ID_VISION_SPEED_ESTIMATE;

        packet.payload.putUnsignedLong(usec);
        packet.payload.putFloat(x);
        packet.payload.putFloat(y);
        packet.payload.putFloat(z);
        
        if (isMavlink2) {
             
        for (int i = 0; i < covariance.length; i++) {
            packet.payload.putFloat(covariance[i]);
        }
                    
             packet.payload.putUnsignedByte(reset_counter);
            
        }
        return packet;
    }

    /**
     * Decode a vision_speed_estimate message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.usec = payload.getUnsignedLong();
        this.x = payload.getFloat();
        this.y = payload.getFloat();
        this.z = payload.getFloat();
        
        if (isMavlink2) {
             
        for (int i = 0; i < this.covariance.length; i++) {
            this.covariance[i] = payload.getFloat();
        }
                
             this.reset_counter = payload.getUnsignedByte();
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_vision_speed_estimate() {
        this.msgid = MAVLINK_MSG_ID_VISION_SPEED_ESTIMATE;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_vision_speed_estimate( long usec, float x, float y, float z, float[] covariance, short reset_counter) {
        this.msgid = MAVLINK_MSG_ID_VISION_SPEED_ESTIMATE;

        this.usec = usec;
        this.x = x;
        this.y = y;
        this.z = z;
        this.covariance = covariance;
        this.reset_counter = reset_counter;
        
    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_vision_speed_estimate( long usec, float x, float y, float z, float[] covariance, short reset_counter, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_VISION_SPEED_ESTIMATE;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.usec = usec;
        this.x = x;
        this.y = y;
        this.z = z;
        this.covariance = covariance;
        this.reset_counter = reset_counter;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_vision_speed_estimate(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_VISION_SPEED_ESTIMATE;

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
        return "MAVLINK_MSG_ID_VISION_SPEED_ESTIMATE - sysid:"+sysid+" compid:"+compid+" usec:"+usec+" x:"+x+" y:"+y+" z:"+z+" covariance:"+covariance+" reset_counter:"+reset_counter+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_VISION_SPEED_ESTIMATE";
    }
}
        