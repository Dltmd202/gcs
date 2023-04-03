/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE MISSION_ITEM_INT PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.Description;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
import com.MAVLink.Messages.Units;

/**
 * Message encoding a mission item. This message is emitted to announce
                the presence of a mission item and to set a mission item on the system. The mission item can be either in x, y, z meters (type: LOCAL) or x:lat, y:lon, z:altitude. Local frame is Z-down, right handed (NED), global frame is Z-up, right handed (ENU). NaN or INT32_MAX may be used in float/integer params (respectively) to indicate optional/default values (e.g. to use the component's current latitude, yaw rather than a specific value). See also https://mavlink.io/en/services/mission.html.
 */
public class msg_mission_item_int extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_MISSION_ITEM_INT = 73;
    public static final int MAVLINK_MSG_LENGTH = 38;
    private static final long serialVersionUID = MAVLINK_MSG_ID_MISSION_ITEM_INT;

    
    /**
     * PARAM1, see MAV_CMD enum
     */
    @Description("PARAM1, see MAV_CMD enum")
    @Units("")
    public float param1;
    
    /**
     * PARAM2, see MAV_CMD enum
     */
    @Description("PARAM2, see MAV_CMD enum")
    @Units("")
    public float param2;
    
    /**
     * PARAM3, see MAV_CMD enum
     */
    @Description("PARAM3, see MAV_CMD enum")
    @Units("")
    public float param3;
    
    /**
     * PARAM4, see MAV_CMD enum
     */
    @Description("PARAM4, see MAV_CMD enum")
    @Units("")
    public float param4;
    
    /**
     * PARAM5 / local: x position in meters * 1e4, global: latitude in degrees * 10^7
     */
    @Description("PARAM5 / local: x position in meters * 1e4, global: latitude in degrees * 10^7")
    @Units("")
    public int x;
    
    /**
     * PARAM6 / y position: local: x position in meters * 1e4, global: longitude in degrees *10^7
     */
    @Description("PARAM6 / y position: local: x position in meters * 1e4, global: longitude in degrees *10^7")
    @Units("")
    public int y;
    
    /**
     * PARAM7 / z position: global: altitude in meters (relative or absolute, depending on frame.
     */
    @Description("PARAM7 / z position: global: altitude in meters (relative or absolute, depending on frame.")
    @Units("")
    public float z;
    
    /**
     * Waypoint ID (sequence number). Starts at zero. Increases monotonically for each waypoint, no gaps in the sequence (0,1,2,3,4).
     */
    @Description("Waypoint ID (sequence number). Starts at zero. Increases monotonically for each waypoint, no gaps in the sequence (0,1,2,3,4).")
    @Units("")
    public int seq;
    
    /**
     * The scheduled action for the waypoint.
     */
    @Description("The scheduled action for the waypoint.")
    @Units("")
    public int command;
    
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
     * The coordinate system of the waypoint.
     */
    @Description("The coordinate system of the waypoint.")
    @Units("")
    public short frame;
    
    /**
     * false:0, true:1
     */
    @Description("false:0, true:1")
    @Units("")
    public short current;
    
    /**
     * Autocontinue to next waypoint
     */
    @Description("Autocontinue to next waypoint")
    @Units("")
    public short autocontinue;
    
    /**
     * Mission type.
     */
    @Description("Mission type.")
    @Units("")
    public short mission_type;
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = sysid;
        packet.compid = compid;
        packet.msgid = MAVLINK_MSG_ID_MISSION_ITEM_INT;

        packet.payload.putFloat(param1);
        packet.payload.putFloat(param2);
        packet.payload.putFloat(param3);
        packet.payload.putFloat(param4);
        packet.payload.putInt(x);
        packet.payload.putInt(y);
        packet.payload.putFloat(z);
        packet.payload.putUnsignedShort(seq);
        packet.payload.putUnsignedShort(command);
        packet.payload.putUnsignedByte(target_system);
        packet.payload.putUnsignedByte(target_component);
        packet.payload.putUnsignedByte(frame);
        packet.payload.putUnsignedByte(current);
        packet.payload.putUnsignedByte(autocontinue);
        
        if (isMavlink2) {
             packet.payload.putUnsignedByte(mission_type);
            
        }
        return packet;
    }

    /**
     * Decode a mission_item_int message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.param1 = payload.getFloat();
        this.param2 = payload.getFloat();
        this.param3 = payload.getFloat();
        this.param4 = payload.getFloat();
        this.x = payload.getInt();
        this.y = payload.getInt();
        this.z = payload.getFloat();
        this.seq = payload.getUnsignedShort();
        this.command = payload.getUnsignedShort();
        this.target_system = payload.getUnsignedByte();
        this.target_component = payload.getUnsignedByte();
        this.frame = payload.getUnsignedByte();
        this.current = payload.getUnsignedByte();
        this.autocontinue = payload.getUnsignedByte();
        
        if (isMavlink2) {
             this.mission_type = payload.getUnsignedByte();
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_mission_item_int() {
        this.msgid = MAVLINK_MSG_ID_MISSION_ITEM_INT;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_mission_item_int( float param1, float param2, float param3, float param4, int x, int y, float z, int seq, int command, short target_system, short target_component, short frame, short current, short autocontinue, short mission_type) {
        this.msgid = MAVLINK_MSG_ID_MISSION_ITEM_INT;

        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.param4 = param4;
        this.x = x;
        this.y = y;
        this.z = z;
        this.seq = seq;
        this.command = command;
        this.target_system = target_system;
        this.target_component = target_component;
        this.frame = frame;
        this.current = current;
        this.autocontinue = autocontinue;
        this.mission_type = mission_type;
        
    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_mission_item_int( float param1, float param2, float param3, float param4, int x, int y, float z, int seq, int command, short target_system, short target_component, short frame, short current, short autocontinue, short mission_type, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_MISSION_ITEM_INT;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.param4 = param4;
        this.x = x;
        this.y = y;
        this.z = z;
        this.seq = seq;
        this.command = command;
        this.target_system = target_system;
        this.target_component = target_component;
        this.frame = frame;
        this.current = current;
        this.autocontinue = autocontinue;
        this.mission_type = mission_type;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_mission_item_int(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_MISSION_ITEM_INT;

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
        return "MAVLINK_MSG_ID_MISSION_ITEM_INT - sysid:"+sysid+" compid:"+compid+" param1:"+param1+" param2:"+param2+" param3:"+param3+" param4:"+param4+" x:"+x+" y:"+y+" z:"+z+" seq:"+seq+" command:"+command+" target_system:"+target_system+" target_component:"+target_component+" frame:"+frame+" current:"+current+" autocontinue:"+autocontinue+" mission_type:"+mission_type+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_MISSION_ITEM_INT";
    }
}
        