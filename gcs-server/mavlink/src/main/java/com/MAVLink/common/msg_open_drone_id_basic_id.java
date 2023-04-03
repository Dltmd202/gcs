/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE OPEN_DRONE_ID_BASIC_ID PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.Description;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
import com.MAVLink.Messages.Units;

/**
 * Data for filling the OpenDroneID Basic ID message. This and the below messages are primarily meant for feeding data to/from an OpenDroneID implementation. E.g. https://github.com/opendroneid/opendroneid-core-c. These messages are compatible with the ASTM F3411 Remote ID standard and the ASD-STAN prEN 4709-002 Direct Remote ID standard. Additional information and usage of these messages is documented at https://mavlink.io/en/services/opendroneid.html.
 */
public class msg_open_drone_id_basic_id extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_OPEN_DRONE_ID_BASIC_ID = 12900;
    public static final int MAVLINK_MSG_LENGTH = 44;
    private static final long serialVersionUID = MAVLINK_MSG_ID_OPEN_DRONE_ID_BASIC_ID;

    
    /**
     * System ID (0 for broadcast).
     */
    @Description("System ID (0 for broadcast).")
    @Units("")
    public short target_system;
    
    /**
     * Component ID (0 for broadcast).
     */
    @Description("Component ID (0 for broadcast).")
    @Units("")
    public short target_component;
    
    /**
     * Only used for drone ID data received from other UAs. See detailed description at https://mavlink.io/en/services/opendroneid.html. 
     */
    @Description("Only used for drone ID data received from other UAs. See detailed description at https://mavlink.io/en/services/opendroneid.html. ")
    @Units("")
    public short id_or_mac[] = new short[20];
    
    /**
     * Indicates the format for the uas_id field of this message.
     */
    @Description("Indicates the format for the uas_id field of this message.")
    @Units("")
    public short id_type;
    
    /**
     * Indicates the type of UA (Unmanned Aircraft).
     */
    @Description("Indicates the type of UA (Unmanned Aircraft).")
    @Units("")
    public short ua_type;
    
    /**
     * UAS (Unmanned Aircraft System) ID following the format specified by id_type. Shall be filled with nulls in the unused portion of the field.
     */
    @Description("UAS (Unmanned Aircraft System) ID following the format specified by id_type. Shall be filled with nulls in the unused portion of the field.")
    @Units("")
    public short uas_id[] = new short[20];
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = sysid;
        packet.compid = compid;
        packet.msgid = MAVLINK_MSG_ID_OPEN_DRONE_ID_BASIC_ID;

        packet.payload.putUnsignedByte(target_system);
        packet.payload.putUnsignedByte(target_component);
        
        for (int i = 0; i < id_or_mac.length; i++) {
            packet.payload.putUnsignedByte(id_or_mac[i]);
        }
                    
        packet.payload.putUnsignedByte(id_type);
        packet.payload.putUnsignedByte(ua_type);
        
        for (int i = 0; i < uas_id.length; i++) {
            packet.payload.putUnsignedByte(uas_id[i]);
        }
                    
        
        if (isMavlink2) {
            
        }
        return packet;
    }

    /**
     * Decode a open_drone_id_basic_id message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.target_system = payload.getUnsignedByte();
        this.target_component = payload.getUnsignedByte();
        
        for (int i = 0; i < this.id_or_mac.length; i++) {
            this.id_or_mac[i] = payload.getUnsignedByte();
        }
                
        this.id_type = payload.getUnsignedByte();
        this.ua_type = payload.getUnsignedByte();
        
        for (int i = 0; i < this.uas_id.length; i++) {
            this.uas_id[i] = payload.getUnsignedByte();
        }
                
        
        if (isMavlink2) {
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_open_drone_id_basic_id() {
        this.msgid = MAVLINK_MSG_ID_OPEN_DRONE_ID_BASIC_ID;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_open_drone_id_basic_id( short target_system, short target_component, short[] id_or_mac, short id_type, short ua_type, short[] uas_id) {
        this.msgid = MAVLINK_MSG_ID_OPEN_DRONE_ID_BASIC_ID;

        this.target_system = target_system;
        this.target_component = target_component;
        this.id_or_mac = id_or_mac;
        this.id_type = id_type;
        this.ua_type = ua_type;
        this.uas_id = uas_id;
        
    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_open_drone_id_basic_id( short target_system, short target_component, short[] id_or_mac, short id_type, short ua_type, short[] uas_id, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_OPEN_DRONE_ID_BASIC_ID;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.target_system = target_system;
        this.target_component = target_component;
        this.id_or_mac = id_or_mac;
        this.id_type = id_type;
        this.ua_type = ua_type;
        this.uas_id = uas_id;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_open_drone_id_basic_id(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_OPEN_DRONE_ID_BASIC_ID;

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
        return "MAVLINK_MSG_ID_OPEN_DRONE_ID_BASIC_ID - sysid:"+sysid+" compid:"+compid+" target_system:"+target_system+" target_component:"+target_component+" id_or_mac:"+id_or_mac+" id_type:"+id_type+" ua_type:"+ua_type+" uas_id:"+uas_id+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_OPEN_DRONE_ID_BASIC_ID";
    }
}
        