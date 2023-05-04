/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE CELLULAR_STATUS PACKING
package com.MAVLink.common;
import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
import com.MAVLink.Messages.Units;
import com.MAVLink.Messages.Description;

/**
 * Report current used cellular network status
 */
public class msg_cellular_status extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_CELLULAR_STATUS = 334;
    public static final int MAVLINK_MSG_LENGTH = 10;
    private static final long serialVersionUID = MAVLINK_MSG_ID_CELLULAR_STATUS;

    
    /**
     * Mobile country code. If unknown, set to UINT16_MAX
     */
    @Description("Mobile country code. If unknown, set to UINT16_MAX")
    @Units("")
    public int mcc;
    
    /**
     * Mobile network code. If unknown, set to UINT16_MAX
     */
    @Description("Mobile network code. If unknown, set to UINT16_MAX")
    @Units("")
    public int mnc;
    
    /**
     * Location area code. If unknown, set to 0
     */
    @Description("Location area code. If unknown, set to 0")
    @Units("")
    public int lac;
    
    /**
     * Cellular modem status
     */
    @Description("Cellular modem status")
    @Units("")
    public short status;
    
    /**
     * Failure reason when status in in CELLUAR_STATUS_FAILED
     */
    @Description("Failure reason when status in in CELLUAR_STATUS_FAILED")
    @Units("")
    public short failure_reason;
    
    /**
     * Cellular network radio type: gsm, cdma, lte...
     */
    @Description("Cellular network radio type: gsm, cdma, lte...")
    @Units("")
    public short type;
    
    /**
     * Signal quality in percent. If unknown, set to UINT8_MAX
     */
    @Description("Signal quality in percent. If unknown, set to UINT8_MAX")
    @Units("")
    public short quality;
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = sysid;
        packet.compid = compid;
        packet.msgid = MAVLINK_MSG_ID_CELLULAR_STATUS;

        packet.payload.putUnsignedShort(mcc);
        packet.payload.putUnsignedShort(mnc);
        packet.payload.putUnsignedShort(lac);
        packet.payload.putUnsignedByte(status);
        packet.payload.putUnsignedByte(failure_reason);
        packet.payload.putUnsignedByte(type);
        packet.payload.putUnsignedByte(quality);
        
        if (isMavlink2) {
            
        }
        return packet;
    }

    /**
     * Decode a cellular_status message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.mcc = payload.getUnsignedShort();
        this.mnc = payload.getUnsignedShort();
        this.lac = payload.getUnsignedShort();
        this.status = payload.getUnsignedByte();
        this.failure_reason = payload.getUnsignedByte();
        this.type = payload.getUnsignedByte();
        this.quality = payload.getUnsignedByte();
        
        if (isMavlink2) {
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_cellular_status() {
        this.msgid = MAVLINK_MSG_ID_CELLULAR_STATUS;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_cellular_status( int mcc, int mnc, int lac, short status, short failure_reason, short type, short quality) {
        this.msgid = MAVLINK_MSG_ID_CELLULAR_STATUS;

        this.mcc = mcc;
        this.mnc = mnc;
        this.lac = lac;
        this.status = status;
        this.failure_reason = failure_reason;
        this.type = type;
        this.quality = quality;
        
    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_cellular_status( int mcc, int mnc, int lac, short status, short failure_reason, short type, short quality, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_CELLULAR_STATUS;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.mcc = mcc;
        this.mnc = mnc;
        this.lac = lac;
        this.status = status;
        this.failure_reason = failure_reason;
        this.type = type;
        this.quality = quality;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_cellular_status(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_CELLULAR_STATUS;

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
        return "MAVLINK_MSG_ID_CELLULAR_STATUS - sysid:"+sysid+" compid:"+compid+" mcc:"+mcc+" mnc:"+mnc+" lac:"+lac+" status:"+status+" failure_reason:"+failure_reason+" type:"+type+" quality:"+quality+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_CELLULAR_STATUS";
    }
}
        