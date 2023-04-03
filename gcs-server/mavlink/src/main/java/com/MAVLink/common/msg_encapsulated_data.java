/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE ENCAPSULATED_DATA PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.Description;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
import com.MAVLink.Messages.Units;

/**
 * Data packet for images sent using the Image Transmission Protocol: https://mavlink.io/en/services/image_transmission.html.
 */
public class msg_encapsulated_data extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_ENCAPSULATED_DATA = 131;
    public static final int MAVLINK_MSG_LENGTH = 255;
    private static final long serialVersionUID = MAVLINK_MSG_ID_ENCAPSULATED_DATA;

    
    /**
     * sequence number (starting with 0 on every transmission)
     */
    @Description("sequence number (starting with 0 on every transmission)")
    @Units("")
    public int seqnr;
    
    /**
     * image data bytes
     */
    @Description("image data bytes")
    @Units("")
    public short data[] = new short[253];
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = sysid;
        packet.compid = compid;
        packet.msgid = MAVLINK_MSG_ID_ENCAPSULATED_DATA;

        packet.payload.putUnsignedShort(seqnr);
        
        for (int i = 0; i < data.length; i++) {
            packet.payload.putUnsignedByte(data[i]);
        }
                    
        
        if (isMavlink2) {
            
        }
        return packet;
    }

    /**
     * Decode a encapsulated_data message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.seqnr = payload.getUnsignedShort();
        
        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = payload.getUnsignedByte();
        }
                
        
        if (isMavlink2) {
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_encapsulated_data() {
        this.msgid = MAVLINK_MSG_ID_ENCAPSULATED_DATA;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_encapsulated_data( int seqnr, short[] data) {
        this.msgid = MAVLINK_MSG_ID_ENCAPSULATED_DATA;

        this.seqnr = seqnr;
        this.data = data;
        
    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_encapsulated_data( int seqnr, short[] data, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_ENCAPSULATED_DATA;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.seqnr = seqnr;
        this.data = data;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_encapsulated_data(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_ENCAPSULATED_DATA;

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
        return "MAVLINK_MSG_ID_ENCAPSULATED_DATA - sysid:"+sysid+" compid:"+compid+" seqnr:"+seqnr+" data:"+data+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_ENCAPSULATED_DATA";
    }
}
        