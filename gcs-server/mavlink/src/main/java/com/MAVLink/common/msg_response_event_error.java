/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE RESPONSE_EVENT_ERROR PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.Description;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
import com.MAVLink.Messages.Units;

/**
 * Response to a REQUEST_EVENT in case of an error (e.g. the event is not available anymore).
 */
public class msg_response_event_error extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_RESPONSE_EVENT_ERROR = 413;
    public static final int MAVLINK_MSG_LENGTH = 7;
    private static final long serialVersionUID = MAVLINK_MSG_ID_RESPONSE_EVENT_ERROR;

    
    /**
     * Sequence number.
     */
    @Description("Sequence number.")
    @Units("")
    public int sequence;
    
    /**
     * Oldest Sequence number that is still available after the sequence set in REQUEST_EVENT.
     */
    @Description("Oldest Sequence number that is still available after the sequence set in REQUEST_EVENT.")
    @Units("")
    public int sequence_oldest_available;
    
    /**
     * System ID
     */
    @Description("System ID")
    @Units("")
    public short target_system;
    
    /**
     * Component ID
     */
    @Description("Component ID")
    @Units("")
    public short target_component;
    
    /**
     * Error reason.
     */
    @Description("Error reason.")
    @Units("")
    public short reason;
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = sysid;
        packet.compid = compid;
        packet.msgid = MAVLINK_MSG_ID_RESPONSE_EVENT_ERROR;

        packet.payload.putUnsignedShort(sequence);
        packet.payload.putUnsignedShort(sequence_oldest_available);
        packet.payload.putUnsignedByte(target_system);
        packet.payload.putUnsignedByte(target_component);
        packet.payload.putUnsignedByte(reason);
        
        if (isMavlink2) {
            
        }
        return packet;
    }

    /**
     * Decode a response_event_error message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.sequence = payload.getUnsignedShort();
        this.sequence_oldest_available = payload.getUnsignedShort();
        this.target_system = payload.getUnsignedByte();
        this.target_component = payload.getUnsignedByte();
        this.reason = payload.getUnsignedByte();
        
        if (isMavlink2) {
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_response_event_error() {
        this.msgid = MAVLINK_MSG_ID_RESPONSE_EVENT_ERROR;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_response_event_error( int sequence, int sequence_oldest_available, short target_system, short target_component, short reason) {
        this.msgid = MAVLINK_MSG_ID_RESPONSE_EVENT_ERROR;

        this.sequence = sequence;
        this.sequence_oldest_available = sequence_oldest_available;
        this.target_system = target_system;
        this.target_component = target_component;
        this.reason = reason;
        
    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_response_event_error( int sequence, int sequence_oldest_available, short target_system, short target_component, short reason, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_RESPONSE_EVENT_ERROR;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.sequence = sequence;
        this.sequence_oldest_available = sequence_oldest_available;
        this.target_system = target_system;
        this.target_component = target_component;
        this.reason = reason;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_response_event_error(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_RESPONSE_EVENT_ERROR;

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
        return "MAVLINK_MSG_ID_RESPONSE_EVENT_ERROR - sysid:"+sysid+" compid:"+compid+" sequence:"+sequence+" sequence_oldest_available:"+sequence_oldest_available+" target_system:"+target_system+" target_component:"+target_component+" reason:"+reason+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_RESPONSE_EVENT_ERROR";
    }
}
        