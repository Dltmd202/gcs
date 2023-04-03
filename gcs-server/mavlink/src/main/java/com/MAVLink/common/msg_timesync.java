/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE TIMESYNC PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.Description;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
import com.MAVLink.Messages.Units;

/**
 * Time synchronization message.
 */
public class msg_timesync extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_TIMESYNC = 111;
    public static final int MAVLINK_MSG_LENGTH = 16;
    private static final long serialVersionUID = MAVLINK_MSG_ID_TIMESYNC;

    
    /**
     * Time sync timestamp 1
     */
    @Description("Time sync timestamp 1")
    @Units("")
    public long tc1;
    
    /**
     * Time sync timestamp 2
     */
    @Description("Time sync timestamp 2")
    @Units("")
    public long ts1;
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = sysid;
        packet.compid = compid;
        packet.msgid = MAVLINK_MSG_ID_TIMESYNC;

        packet.payload.putLong(tc1);
        packet.payload.putLong(ts1);
        
        if (isMavlink2) {
            
        }
        return packet;
    }

    /**
     * Decode a timesync message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.tc1 = payload.getLong();
        this.ts1 = payload.getLong();
        
        if (isMavlink2) {
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_timesync() {
        this.msgid = MAVLINK_MSG_ID_TIMESYNC;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_timesync( long tc1, long ts1) {
        this.msgid = MAVLINK_MSG_ID_TIMESYNC;

        this.tc1 = tc1;
        this.ts1 = ts1;
        
    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_timesync( long tc1, long ts1, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_TIMESYNC;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.tc1 = tc1;
        this.ts1 = ts1;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_timesync(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_TIMESYNC;

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
        return "MAVLINK_MSG_ID_TIMESYNC - sysid:"+sysid+" compid:"+compid+" tc1:"+tc1+" ts1:"+ts1+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_TIMESYNC";
    }
}
        