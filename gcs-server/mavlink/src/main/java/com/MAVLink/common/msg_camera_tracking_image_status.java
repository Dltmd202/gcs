/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE CAMERA_TRACKING_IMAGE_STATUS PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.Description;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
import com.MAVLink.Messages.Units;

/**
 * Camera tracking status, sent while in active tracking. Use MAV_CMD_SET_MESSAGE_INTERVAL to define message interval.
 */
public class msg_camera_tracking_image_status extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_CAMERA_TRACKING_IMAGE_STATUS = 275;
    public static final int MAVLINK_MSG_LENGTH = 31;
    private static final long serialVersionUID = MAVLINK_MSG_ID_CAMERA_TRACKING_IMAGE_STATUS;

    
    /**
     * Current tracked point x value if CAMERA_TRACKING_MODE_POINT (normalized 0..1, 0 is left, 1 is right), NAN if unknown
     */
    @Description("Current tracked point x value if CAMERA_TRACKING_MODE_POINT (normalized 0..1, 0 is left, 1 is right), NAN if unknown")
    @Units("")
    public float point_x;
    
    /**
     * Current tracked point y value if CAMERA_TRACKING_MODE_POINT (normalized 0..1, 0 is top, 1 is bottom), NAN if unknown
     */
    @Description("Current tracked point y value if CAMERA_TRACKING_MODE_POINT (normalized 0..1, 0 is top, 1 is bottom), NAN if unknown")
    @Units("")
    public float point_y;
    
    /**
     * Current tracked radius if CAMERA_TRACKING_MODE_POINT (normalized 0..1, 0 is image left, 1 is image right), NAN if unknown
     */
    @Description("Current tracked radius if CAMERA_TRACKING_MODE_POINT (normalized 0..1, 0 is image left, 1 is image right), NAN if unknown")
    @Units("")
    public float radius;
    
    /**
     * Current tracked rectangle top x value if CAMERA_TRACKING_MODE_RECTANGLE (normalized 0..1, 0 is left, 1 is right), NAN if unknown
     */
    @Description("Current tracked rectangle top x value if CAMERA_TRACKING_MODE_RECTANGLE (normalized 0..1, 0 is left, 1 is right), NAN if unknown")
    @Units("")
    public float rec_top_x;
    
    /**
     * Current tracked rectangle top y value if CAMERA_TRACKING_MODE_RECTANGLE (normalized 0..1, 0 is top, 1 is bottom), NAN if unknown
     */
    @Description("Current tracked rectangle top y value if CAMERA_TRACKING_MODE_RECTANGLE (normalized 0..1, 0 is top, 1 is bottom), NAN if unknown")
    @Units("")
    public float rec_top_y;
    
    /**
     * Current tracked rectangle bottom x value if CAMERA_TRACKING_MODE_RECTANGLE (normalized 0..1, 0 is left, 1 is right), NAN if unknown
     */
    @Description("Current tracked rectangle bottom x value if CAMERA_TRACKING_MODE_RECTANGLE (normalized 0..1, 0 is left, 1 is right), NAN if unknown")
    @Units("")
    public float rec_bottom_x;
    
    /**
     * Current tracked rectangle bottom y value if CAMERA_TRACKING_MODE_RECTANGLE (normalized 0..1, 0 is top, 1 is bottom), NAN if unknown
     */
    @Description("Current tracked rectangle bottom y value if CAMERA_TRACKING_MODE_RECTANGLE (normalized 0..1, 0 is top, 1 is bottom), NAN if unknown")
    @Units("")
    public float rec_bottom_y;
    
    /**
     * Current tracking status
     */
    @Description("Current tracking status")
    @Units("")
    public short tracking_status;
    
    /**
     * Current tracking mode
     */
    @Description("Current tracking mode")
    @Units("")
    public short tracking_mode;
    
    /**
     * Defines location of target data
     */
    @Description("Defines location of target data")
    @Units("")
    public short target_data;
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = sysid;
        packet.compid = compid;
        packet.msgid = MAVLINK_MSG_ID_CAMERA_TRACKING_IMAGE_STATUS;

        packet.payload.putFloat(point_x);
        packet.payload.putFloat(point_y);
        packet.payload.putFloat(radius);
        packet.payload.putFloat(rec_top_x);
        packet.payload.putFloat(rec_top_y);
        packet.payload.putFloat(rec_bottom_x);
        packet.payload.putFloat(rec_bottom_y);
        packet.payload.putUnsignedByte(tracking_status);
        packet.payload.putUnsignedByte(tracking_mode);
        packet.payload.putUnsignedByte(target_data);
        
        if (isMavlink2) {
            
        }
        return packet;
    }

    /**
     * Decode a camera_tracking_image_status message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.point_x = payload.getFloat();
        this.point_y = payload.getFloat();
        this.radius = payload.getFloat();
        this.rec_top_x = payload.getFloat();
        this.rec_top_y = payload.getFloat();
        this.rec_bottom_x = payload.getFloat();
        this.rec_bottom_y = payload.getFloat();
        this.tracking_status = payload.getUnsignedByte();
        this.tracking_mode = payload.getUnsignedByte();
        this.target_data = payload.getUnsignedByte();
        
        if (isMavlink2) {
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_camera_tracking_image_status() {
        this.msgid = MAVLINK_MSG_ID_CAMERA_TRACKING_IMAGE_STATUS;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_camera_tracking_image_status( float point_x, float point_y, float radius, float rec_top_x, float rec_top_y, float rec_bottom_x, float rec_bottom_y, short tracking_status, short tracking_mode, short target_data) {
        this.msgid = MAVLINK_MSG_ID_CAMERA_TRACKING_IMAGE_STATUS;

        this.point_x = point_x;
        this.point_y = point_y;
        this.radius = radius;
        this.rec_top_x = rec_top_x;
        this.rec_top_y = rec_top_y;
        this.rec_bottom_x = rec_bottom_x;
        this.rec_bottom_y = rec_bottom_y;
        this.tracking_status = tracking_status;
        this.tracking_mode = tracking_mode;
        this.target_data = target_data;
        
    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_camera_tracking_image_status( float point_x, float point_y, float radius, float rec_top_x, float rec_top_y, float rec_bottom_x, float rec_bottom_y, short tracking_status, short tracking_mode, short target_data, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_CAMERA_TRACKING_IMAGE_STATUS;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.point_x = point_x;
        this.point_y = point_y;
        this.radius = radius;
        this.rec_top_x = rec_top_x;
        this.rec_top_y = rec_top_y;
        this.rec_bottom_x = rec_bottom_x;
        this.rec_bottom_y = rec_bottom_y;
        this.tracking_status = tracking_status;
        this.tracking_mode = tracking_mode;
        this.target_data = target_data;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_camera_tracking_image_status(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_CAMERA_TRACKING_IMAGE_STATUS;

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
        return "MAVLINK_MSG_ID_CAMERA_TRACKING_IMAGE_STATUS - sysid:"+sysid+" compid:"+compid+" point_x:"+point_x+" point_y:"+point_y+" radius:"+radius+" rec_top_x:"+rec_top_x+" rec_top_y:"+rec_top_y+" rec_bottom_x:"+rec_bottom_x+" rec_bottom_y:"+rec_bottom_y+" tracking_status:"+tracking_status+" tracking_mode:"+tracking_mode+" target_data:"+target_data+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_CAMERA_TRACKING_IMAGE_STATUS";
    }
}
        