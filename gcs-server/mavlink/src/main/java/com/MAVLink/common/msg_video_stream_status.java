/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE VIDEO_STREAM_STATUS PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.Description;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
import com.MAVLink.Messages.Units;

/**
 * Information about the status of a video stream. It may be requested using MAV_CMD_REQUEST_MESSAGE.
 */
public class msg_video_stream_status extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_VIDEO_STREAM_STATUS = 270;
    public static final int MAVLINK_MSG_LENGTH = 19;
    private static final long serialVersionUID = MAVLINK_MSG_ID_VIDEO_STREAM_STATUS;

    
    /**
     * Frame rate
     */
    @Description("Frame rate")
    @Units("Hz")
    public float framerate;
    
    /**
     * Bit rate
     */
    @Description("Bit rate")
    @Units("bits/s")
    public long bitrate;
    
    /**
     * Bitmap of stream status flags
     */
    @Description("Bitmap of stream status flags")
    @Units("")
    public int flags;
    
    /**
     * Horizontal resolution
     */
    @Description("Horizontal resolution")
    @Units("pix")
    public int resolution_h;
    
    /**
     * Vertical resolution
     */
    @Description("Vertical resolution")
    @Units("pix")
    public int resolution_v;
    
    /**
     * Video image rotation clockwise
     */
    @Description("Video image rotation clockwise")
    @Units("deg")
    public int rotation;
    
    /**
     * Horizontal Field of view
     */
    @Description("Horizontal Field of view")
    @Units("deg")
    public int hfov;
    
    /**
     * Video Stream ID (1 for first, 2 for second, etc.)
     */
    @Description("Video Stream ID (1 for first, 2 for second, etc.)")
    @Units("")
    public short stream_id;
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = sysid;
        packet.compid = compid;
        packet.msgid = MAVLINK_MSG_ID_VIDEO_STREAM_STATUS;

        packet.payload.putFloat(framerate);
        packet.payload.putUnsignedInt(bitrate);
        packet.payload.putUnsignedShort(flags);
        packet.payload.putUnsignedShort(resolution_h);
        packet.payload.putUnsignedShort(resolution_v);
        packet.payload.putUnsignedShort(rotation);
        packet.payload.putUnsignedShort(hfov);
        packet.payload.putUnsignedByte(stream_id);
        
        if (isMavlink2) {
            
        }
        return packet;
    }

    /**
     * Decode a video_stream_status message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.framerate = payload.getFloat();
        this.bitrate = payload.getUnsignedInt();
        this.flags = payload.getUnsignedShort();
        this.resolution_h = payload.getUnsignedShort();
        this.resolution_v = payload.getUnsignedShort();
        this.rotation = payload.getUnsignedShort();
        this.hfov = payload.getUnsignedShort();
        this.stream_id = payload.getUnsignedByte();
        
        if (isMavlink2) {
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_video_stream_status() {
        this.msgid = MAVLINK_MSG_ID_VIDEO_STREAM_STATUS;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_video_stream_status( float framerate, long bitrate, int flags, int resolution_h, int resolution_v, int rotation, int hfov, short stream_id) {
        this.msgid = MAVLINK_MSG_ID_VIDEO_STREAM_STATUS;

        this.framerate = framerate;
        this.bitrate = bitrate;
        this.flags = flags;
        this.resolution_h = resolution_h;
        this.resolution_v = resolution_v;
        this.rotation = rotation;
        this.hfov = hfov;
        this.stream_id = stream_id;
        
    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_video_stream_status( float framerate, long bitrate, int flags, int resolution_h, int resolution_v, int rotation, int hfov, short stream_id, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_VIDEO_STREAM_STATUS;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.framerate = framerate;
        this.bitrate = bitrate;
        this.flags = flags;
        this.resolution_h = resolution_h;
        this.resolution_v = resolution_v;
        this.rotation = rotation;
        this.hfov = hfov;
        this.stream_id = stream_id;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_video_stream_status(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_VIDEO_STREAM_STATUS;

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
        return "MAVLINK_MSG_ID_VIDEO_STREAM_STATUS - sysid:"+sysid+" compid:"+compid+" framerate:"+framerate+" bitrate:"+bitrate+" flags:"+flags+" resolution_h:"+resolution_h+" resolution_v:"+resolution_v+" rotation:"+rotation+" hfov:"+hfov+" stream_id:"+stream_id+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_VIDEO_STREAM_STATUS";
    }
}
        