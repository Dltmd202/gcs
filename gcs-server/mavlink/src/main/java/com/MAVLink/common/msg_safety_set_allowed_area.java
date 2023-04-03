/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE SAFETY_SET_ALLOWED_AREA PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.Description;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
import com.MAVLink.Messages.Units;

/**
 * Set a safety zone (volume), which is defined by two corners of a cube. This message can be used to tell the MAV which setpoints/waypoints to accept and which to reject. Safety areas are often enforced by national or competition regulations.
 */
public class msg_safety_set_allowed_area extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_SAFETY_SET_ALLOWED_AREA = 54;
    public static final int MAVLINK_MSG_LENGTH = 27;
    private static final long serialVersionUID = MAVLINK_MSG_ID_SAFETY_SET_ALLOWED_AREA;

    
    /**
     * x position 1 / Latitude 1
     */
    @Description("x position 1 / Latitude 1")
    @Units("m")
    public float p1x;
    
    /**
     * y position 1 / Longitude 1
     */
    @Description("y position 1 / Longitude 1")
    @Units("m")
    public float p1y;
    
    /**
     * z position 1 / Altitude 1
     */
    @Description("z position 1 / Altitude 1")
    @Units("m")
    public float p1z;
    
    /**
     * x position 2 / Latitude 2
     */
    @Description("x position 2 / Latitude 2")
    @Units("m")
    public float p2x;
    
    /**
     * y position 2 / Longitude 2
     */
    @Description("y position 2 / Longitude 2")
    @Units("m")
    public float p2y;
    
    /**
     * z position 2 / Altitude 2
     */
    @Description("z position 2 / Altitude 2")
    @Units("m")
    public float p2z;
    
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
     * Coordinate frame. Can be either global, GPS, right-handed with Z axis up or local, right handed, Z axis down.
     */
    @Description("Coordinate frame. Can be either global, GPS, right-handed with Z axis up or local, right handed, Z axis down.")
    @Units("")
    public short frame;
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = sysid;
        packet.compid = compid;
        packet.msgid = MAVLINK_MSG_ID_SAFETY_SET_ALLOWED_AREA;

        packet.payload.putFloat(p1x);
        packet.payload.putFloat(p1y);
        packet.payload.putFloat(p1z);
        packet.payload.putFloat(p2x);
        packet.payload.putFloat(p2y);
        packet.payload.putFloat(p2z);
        packet.payload.putUnsignedByte(target_system);
        packet.payload.putUnsignedByte(target_component);
        packet.payload.putUnsignedByte(frame);
        
        if (isMavlink2) {
            
        }
        return packet;
    }

    /**
     * Decode a safety_set_allowed_area message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.p1x = payload.getFloat();
        this.p1y = payload.getFloat();
        this.p1z = payload.getFloat();
        this.p2x = payload.getFloat();
        this.p2y = payload.getFloat();
        this.p2z = payload.getFloat();
        this.target_system = payload.getUnsignedByte();
        this.target_component = payload.getUnsignedByte();
        this.frame = payload.getUnsignedByte();
        
        if (isMavlink2) {
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_safety_set_allowed_area() {
        this.msgid = MAVLINK_MSG_ID_SAFETY_SET_ALLOWED_AREA;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_safety_set_allowed_area( float p1x, float p1y, float p1z, float p2x, float p2y, float p2z, short target_system, short target_component, short frame) {
        this.msgid = MAVLINK_MSG_ID_SAFETY_SET_ALLOWED_AREA;

        this.p1x = p1x;
        this.p1y = p1y;
        this.p1z = p1z;
        this.p2x = p2x;
        this.p2y = p2y;
        this.p2z = p2z;
        this.target_system = target_system;
        this.target_component = target_component;
        this.frame = frame;
        
    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_safety_set_allowed_area( float p1x, float p1y, float p1z, float p2x, float p2y, float p2z, short target_system, short target_component, short frame, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_SAFETY_SET_ALLOWED_AREA;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.p1x = p1x;
        this.p1y = p1y;
        this.p1z = p1z;
        this.p2x = p2x;
        this.p2y = p2y;
        this.p2z = p2z;
        this.target_system = target_system;
        this.target_component = target_component;
        this.frame = frame;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_safety_set_allowed_area(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_SAFETY_SET_ALLOWED_AREA;

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
        return "MAVLINK_MSG_ID_SAFETY_SET_ALLOWED_AREA - sysid:"+sysid+" compid:"+compid+" p1x:"+p1x+" p1y:"+p1y+" p1z:"+p1z+" p2x:"+p2x+" p2y:"+p2y+" p2z:"+p2z+" target_system:"+target_system+" target_component:"+target_component+" frame:"+frame+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_SAFETY_SET_ALLOWED_AREA";
    }
}
        