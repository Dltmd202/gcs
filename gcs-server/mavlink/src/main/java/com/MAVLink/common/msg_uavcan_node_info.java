/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE UAVCAN_NODE_INFO PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.Description;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
import com.MAVLink.Messages.Units;

/**
 * General information describing a particular UAVCAN node. Please refer to the definition of the UAVCAN service "uavcan.protocol.GetNodeInfo" for the background information. This message should be emitted by the system whenever a new node appears online, or an existing node reboots. Additionally, it can be emitted upon request from the other end of the MAVLink channel (see MAV_CMD_UAVCAN_GET_NODE_INFO). It is also not prohibited to emit this message unconditionally at a low frequency. The UAVCAN specification is available at http://uavcan.org.
 */
public class msg_uavcan_node_info extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_UAVCAN_NODE_INFO = 311;
    public static final int MAVLINK_MSG_LENGTH = 116;
    private static final long serialVersionUID = MAVLINK_MSG_ID_UAVCAN_NODE_INFO;

    
    /**
     * Timestamp (UNIX Epoch time or time since system boot). The receiving end can infer timestamp format (since 1.1.1970 or since system boot) by checking for the magnitude of the number.
     */
    @Description("Timestamp (UNIX Epoch time or time since system boot). The receiving end can infer timestamp format (since 1.1.1970 or since system boot) by checking for the magnitude of the number.")
    @Units("us")
    public long time_usec;
    
    /**
     * Time since the start-up of the node.
     */
    @Description("Time since the start-up of the node.")
    @Units("s")
    public long uptime_sec;
    
    /**
     * Version control system (VCS) revision identifier (e.g. git short commit hash). 0 if unknown.
     */
    @Description("Version control system (VCS) revision identifier (e.g. git short commit hash). 0 if unknown.")
    @Units("")
    public long sw_vcs_commit;
    
    /**
     * Node name string. For example, 'sapog.px4.io'.
     */
    @Description("Node name string. For example, 'sapog.px4.io'.")
    @Units("")
    public byte name[] = new byte[80];
    
    /**
     * Hardware major version number.
     */
    @Description("Hardware major version number.")
    @Units("")
    public short hw_version_major;
    
    /**
     * Hardware minor version number.
     */
    @Description("Hardware minor version number.")
    @Units("")
    public short hw_version_minor;
    
    /**
     * Hardware unique 128-bit ID.
     */
    @Description("Hardware unique 128-bit ID.")
    @Units("")
    public short hw_unique_id[] = new short[16];
    
    /**
     * Software major version number.
     */
    @Description("Software major version number.")
    @Units("")
    public short sw_version_major;
    
    /**
     * Software minor version number.
     */
    @Description("Software minor version number.")
    @Units("")
    public short sw_version_minor;
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = sysid;
        packet.compid = compid;
        packet.msgid = MAVLINK_MSG_ID_UAVCAN_NODE_INFO;

        packet.payload.putUnsignedLong(time_usec);
        packet.payload.putUnsignedInt(uptime_sec);
        packet.payload.putUnsignedInt(sw_vcs_commit);
        
        for (int i = 0; i < name.length; i++) {
            packet.payload.putByte(name[i]);
        }
                    
        packet.payload.putUnsignedByte(hw_version_major);
        packet.payload.putUnsignedByte(hw_version_minor);
        
        for (int i = 0; i < hw_unique_id.length; i++) {
            packet.payload.putUnsignedByte(hw_unique_id[i]);
        }
                    
        packet.payload.putUnsignedByte(sw_version_major);
        packet.payload.putUnsignedByte(sw_version_minor);
        
        if (isMavlink2) {
            
        }
        return packet;
    }

    /**
     * Decode a uavcan_node_info message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.time_usec = payload.getUnsignedLong();
        this.uptime_sec = payload.getUnsignedInt();
        this.sw_vcs_commit = payload.getUnsignedInt();
        
        for (int i = 0; i < this.name.length; i++) {
            this.name[i] = payload.getByte();
        }
                
        this.hw_version_major = payload.getUnsignedByte();
        this.hw_version_minor = payload.getUnsignedByte();
        
        for (int i = 0; i < this.hw_unique_id.length; i++) {
            this.hw_unique_id[i] = payload.getUnsignedByte();
        }
                
        this.sw_version_major = payload.getUnsignedByte();
        this.sw_version_minor = payload.getUnsignedByte();
        
        if (isMavlink2) {
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_uavcan_node_info() {
        this.msgid = MAVLINK_MSG_ID_UAVCAN_NODE_INFO;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_uavcan_node_info( long time_usec, long uptime_sec, long sw_vcs_commit, byte[] name, short hw_version_major, short hw_version_minor, short[] hw_unique_id, short sw_version_major, short sw_version_minor) {
        this.msgid = MAVLINK_MSG_ID_UAVCAN_NODE_INFO;

        this.time_usec = time_usec;
        this.uptime_sec = uptime_sec;
        this.sw_vcs_commit = sw_vcs_commit;
        this.name = name;
        this.hw_version_major = hw_version_major;
        this.hw_version_minor = hw_version_minor;
        this.hw_unique_id = hw_unique_id;
        this.sw_version_major = sw_version_major;
        this.sw_version_minor = sw_version_minor;
        
    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_uavcan_node_info( long time_usec, long uptime_sec, long sw_vcs_commit, byte[] name, short hw_version_major, short hw_version_minor, short[] hw_unique_id, short sw_version_major, short sw_version_minor, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_UAVCAN_NODE_INFO;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.time_usec = time_usec;
        this.uptime_sec = uptime_sec;
        this.sw_vcs_commit = sw_vcs_commit;
        this.name = name;
        this.hw_version_major = hw_version_major;
        this.hw_version_minor = hw_version_minor;
        this.hw_unique_id = hw_unique_id;
        this.sw_version_major = sw_version_major;
        this.sw_version_minor = sw_version_minor;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_uavcan_node_info(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_UAVCAN_NODE_INFO;

        this.sysid = mavLinkPacket.sysid;
        this.compid = mavLinkPacket.compid;
        this.isMavlink2 = mavLinkPacket.isMavlink2;
        unpack(mavLinkPacket.payload);
    }

           
    /**
    * Sets the buffer of this message with a string, adds the necessary padding
    */
    public void setName(String str) {
        int len = Math.min(str.length(), 80);
        for (int i=0; i<len; i++) {
            name[i] = (byte) str.charAt(i);
        }

        for (int i=len; i<80; i++) {            // padding for the rest of the buffer
            name[i] = 0;
        }
    }

    /**
    * Gets the message, formatted as a string
    */
    public String getName() {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < 80; i++) {
            if (name[i] != 0)
                buf.append((char) name[i]);
            else
                break;
        }
        return buf.toString();

    }
                                   
    /**
     * Returns a string with the MSG name and data
     */
    @Override
    public String toString() {
        return "MAVLINK_MSG_ID_UAVCAN_NODE_INFO - sysid:"+sysid+" compid:"+compid+" time_usec:"+time_usec+" uptime_sec:"+uptime_sec+" sw_vcs_commit:"+sw_vcs_commit+" name:"+name+" hw_version_major:"+hw_version_major+" hw_version_minor:"+hw_version_minor+" hw_unique_id:"+hw_unique_id+" sw_version_major:"+sw_version_major+" sw_version_minor:"+sw_version_minor+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_UAVCAN_NODE_INFO";
    }
}
        