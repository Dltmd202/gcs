/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE ACTUATOR_OUTPUT_STATUS PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.Description;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
import com.MAVLink.Messages.Units;

/**
 * The raw values of the actuator outputs (e.g. on Pixhawk, from MAIN, AUX ports). This message supersedes SERVO_OUTPUT_RAW.
 */
public class msg_actuator_output_status extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_ACTUATOR_OUTPUT_STATUS = 375;
    public static final int MAVLINK_MSG_LENGTH = 140;
    private static final long serialVersionUID = MAVLINK_MSG_ID_ACTUATOR_OUTPUT_STATUS;

    
    /**
     * Timestamp (since system boot).
     */
    @Description("Timestamp (since system boot).")
    @Units("us")
    public long time_usec;
    
    /**
     * Active outputs
     */
    @Description("Active outputs")
    @Units("")
    public long active;
    
    /**
     * Servo / motor output array values. Zero values indicate unused channels.
     */
    @Description("Servo / motor output array values. Zero values indicate unused channels.")
    @Units("")
    public float actuator[] = new float[32];
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = sysid;
        packet.compid = compid;
        packet.msgid = MAVLINK_MSG_ID_ACTUATOR_OUTPUT_STATUS;

        packet.payload.putUnsignedLong(time_usec);
        packet.payload.putUnsignedInt(active);
        
        for (int i = 0; i < actuator.length; i++) {
            packet.payload.putFloat(actuator[i]);
        }
                    
        
        if (isMavlink2) {
            
        }
        return packet;
    }

    /**
     * Decode a actuator_output_status message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.time_usec = payload.getUnsignedLong();
        this.active = payload.getUnsignedInt();
        
        for (int i = 0; i < this.actuator.length; i++) {
            this.actuator[i] = payload.getFloat();
        }
                
        
        if (isMavlink2) {
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_actuator_output_status() {
        this.msgid = MAVLINK_MSG_ID_ACTUATOR_OUTPUT_STATUS;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_actuator_output_status( long time_usec, long active, float[] actuator) {
        this.msgid = MAVLINK_MSG_ID_ACTUATOR_OUTPUT_STATUS;

        this.time_usec = time_usec;
        this.active = active;
        this.actuator = actuator;
        
    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_actuator_output_status( long time_usec, long active, float[] actuator, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_ACTUATOR_OUTPUT_STATUS;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.time_usec = time_usec;
        this.active = active;
        this.actuator = actuator;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_actuator_output_status(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_ACTUATOR_OUTPUT_STATUS;

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
        return "MAVLINK_MSG_ID_ACTUATOR_OUTPUT_STATUS - sysid:"+sysid+" compid:"+compid+" time_usec:"+time_usec+" active:"+active+" actuator:"+actuator+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_ACTUATOR_OUTPUT_STATUS";
    }
}
        