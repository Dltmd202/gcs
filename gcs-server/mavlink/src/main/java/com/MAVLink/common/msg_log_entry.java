/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE LOG_ENTRY PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.Description;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
import com.MAVLink.Messages.Units;

/**
 * Reply to LOG_REQUEST_LIST
 */
public class msg_log_entry extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_LOG_ENTRY = 118;
    public static final int MAVLINK_MSG_LENGTH = 14;
    private static final long serialVersionUID = MAVLINK_MSG_ID_LOG_ENTRY;

    
    /**
     * UTC timestamp of log since 1970, or 0 if not available
     */
    @Description("UTC timestamp of log since 1970, or 0 if not available")
    @Units("s")
    public long time_utc;
    
    /**
     * Size of the log (may be approximate)
     */
    @Description("Size of the log (may be approximate)")
    @Units("bytes")
    public long size;
    
    /**
     * Log id
     */
    @Description("Log id")
    @Units("")
    public int id;
    
    /**
     * Total number of logs
     */
    @Description("Total number of logs")
    @Units("")
    public int num_logs;
    
    /**
     * High log number
     */
    @Description("High log number")
    @Units("")
    public int last_log_num;
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = sysid;
        packet.compid = compid;
        packet.msgid = MAVLINK_MSG_ID_LOG_ENTRY;

        packet.payload.putUnsignedInt(time_utc);
        packet.payload.putUnsignedInt(size);
        packet.payload.putUnsignedShort(id);
        packet.payload.putUnsignedShort(num_logs);
        packet.payload.putUnsignedShort(last_log_num);
        
        if (isMavlink2) {
            
        }
        return packet;
    }

    /**
     * Decode a log_entry message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.time_utc = payload.getUnsignedInt();
        this.size = payload.getUnsignedInt();
        this.id = payload.getUnsignedShort();
        this.num_logs = payload.getUnsignedShort();
        this.last_log_num = payload.getUnsignedShort();
        
        if (isMavlink2) {
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_log_entry() {
        this.msgid = MAVLINK_MSG_ID_LOG_ENTRY;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_log_entry( long time_utc, long size, int id, int num_logs, int last_log_num) {
        this.msgid = MAVLINK_MSG_ID_LOG_ENTRY;

        this.time_utc = time_utc;
        this.size = size;
        this.id = id;
        this.num_logs = num_logs;
        this.last_log_num = last_log_num;
        
    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_log_entry( long time_utc, long size, int id, int num_logs, int last_log_num, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_LOG_ENTRY;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.time_utc = time_utc;
        this.size = size;
        this.id = id;
        this.num_logs = num_logs;
        this.last_log_num = last_log_num;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_log_entry(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_LOG_ENTRY;

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
        return "MAVLINK_MSG_ID_LOG_ENTRY - sysid:"+sysid+" compid:"+compid+" time_utc:"+time_utc+" size:"+size+" id:"+id+" num_logs:"+num_logs+" last_log_num:"+last_log_num+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_LOG_ENTRY";
    }
}
        