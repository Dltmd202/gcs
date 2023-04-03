/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE HIGH_LATENCY2 PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.Description;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
import com.MAVLink.Messages.Units;

/**
 * Message appropriate for high latency connections like Iridium (version 2)
 */
public class msg_high_latency2 extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_HIGH_LATENCY2 = 235;
    public static final int MAVLINK_MSG_LENGTH = 42;
    private static final long serialVersionUID = MAVLINK_MSG_ID_HIGH_LATENCY2;

    
    /**
     * Timestamp (milliseconds since boot or Unix epoch)
     */
    @Description("Timestamp (milliseconds since boot or Unix epoch)")
    @Units("ms")
    public long timestamp;
    
    /**
     * Latitude
     */
    @Description("Latitude")
    @Units("degE7")
    public int latitude;
    
    /**
     * Longitude
     */
    @Description("Longitude")
    @Units("degE7")
    public int longitude;
    
    /**
     * A bitfield for use for autopilot-specific flags (2 byte version).
     */
    @Description("A bitfield for use for autopilot-specific flags (2 byte version).")
    @Units("")
    public int custom_mode;
    
    /**
     * Altitude above mean sea level
     */
    @Description("Altitude above mean sea level")
    @Units("m")
    public short altitude;
    
    /**
     * Altitude setpoint
     */
    @Description("Altitude setpoint")
    @Units("m")
    public short target_altitude;
    
    /**
     * Distance to target waypoint or position
     */
    @Description("Distance to target waypoint or position")
    @Units("dam")
    public int target_distance;
    
    /**
     * Current waypoint number
     */
    @Description("Current waypoint number")
    @Units("")
    public int wp_num;
    
    /**
     * Bitmap of failure flags.
     */
    @Description("Bitmap of failure flags.")
    @Units("")
    public int failure_flags;
    
    /**
     * Type of the MAV (quadrotor, helicopter, etc.)
     */
    @Description("Type of the MAV (quadrotor, helicopter, etc.)")
    @Units("")
    public short type;
    
    /**
     * Autopilot type / class. Use MAV_AUTOPILOT_INVALID for components that are not flight controllers.
     */
    @Description("Autopilot type / class. Use MAV_AUTOPILOT_INVALID for components that are not flight controllers.")
    @Units("")
    public short autopilot;
    
    /**
     * Heading
     */
    @Description("Heading")
    @Units("deg/2")
    public short heading;
    
    /**
     * Heading setpoint
     */
    @Description("Heading setpoint")
    @Units("deg/2")
    public short target_heading;
    
    /**
     * Throttle
     */
    @Description("Throttle")
    @Units("%")
    public short throttle;
    
    /**
     * Airspeed
     */
    @Description("Airspeed")
    @Units("m/s*5")
    public short airspeed;
    
    /**
     * Airspeed setpoint
     */
    @Description("Airspeed setpoint")
    @Units("m/s*5")
    public short airspeed_sp;
    
    /**
     * Groundspeed
     */
    @Description("Groundspeed")
    @Units("m/s*5")
    public short groundspeed;
    
    /**
     * Windspeed
     */
    @Description("Windspeed")
    @Units("m/s*5")
    public short windspeed;
    
    /**
     * Wind heading
     */
    @Description("Wind heading")
    @Units("deg/2")
    public short wind_heading;
    
    /**
     * Maximum error horizontal position since last message
     */
    @Description("Maximum error horizontal position since last message")
    @Units("dm")
    public short eph;
    
    /**
     * Maximum error vertical position since last message
     */
    @Description("Maximum error vertical position since last message")
    @Units("dm")
    public short epv;
    
    /**
     * Air temperature from airspeed sensor
     */
    @Description("Air temperature from airspeed sensor")
    @Units("degC")
    public byte temperature_air;
    
    /**
     * Maximum climb rate magnitude since last message
     */
    @Description("Maximum climb rate magnitude since last message")
    @Units("dm/s")
    public byte climb_rate;
    
    /**
     * Battery level (-1 if field not provided).
     */
    @Description("Battery level (-1 if field not provided).")
    @Units("%")
    public byte battery;
    
    /**
     * Field for custom payload.
     */
    @Description("Field for custom payload.")
    @Units("")
    public byte custom0;
    
    /**
     * Field for custom payload.
     */
    @Description("Field for custom payload.")
    @Units("")
    public byte custom1;
    
    /**
     * Field for custom payload.
     */
    @Description("Field for custom payload.")
    @Units("")
    public byte custom2;
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = sysid;
        packet.compid = compid;
        packet.msgid = MAVLINK_MSG_ID_HIGH_LATENCY2;

        packet.payload.putUnsignedInt(timestamp);
        packet.payload.putInt(latitude);
        packet.payload.putInt(longitude);
        packet.payload.putUnsignedShort(custom_mode);
        packet.payload.putShort(altitude);
        packet.payload.putShort(target_altitude);
        packet.payload.putUnsignedShort(target_distance);
        packet.payload.putUnsignedShort(wp_num);
        packet.payload.putUnsignedShort(failure_flags);
        packet.payload.putUnsignedByte(type);
        packet.payload.putUnsignedByte(autopilot);
        packet.payload.putUnsignedByte(heading);
        packet.payload.putUnsignedByte(target_heading);
        packet.payload.putUnsignedByte(throttle);
        packet.payload.putUnsignedByte(airspeed);
        packet.payload.putUnsignedByte(airspeed_sp);
        packet.payload.putUnsignedByte(groundspeed);
        packet.payload.putUnsignedByte(windspeed);
        packet.payload.putUnsignedByte(wind_heading);
        packet.payload.putUnsignedByte(eph);
        packet.payload.putUnsignedByte(epv);
        packet.payload.putByte(temperature_air);
        packet.payload.putByte(climb_rate);
        packet.payload.putByte(battery);
        packet.payload.putByte(custom0);
        packet.payload.putByte(custom1);
        packet.payload.putByte(custom2);
        
        if (isMavlink2) {
            
        }
        return packet;
    }

    /**
     * Decode a high_latency2 message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.timestamp = payload.getUnsignedInt();
        this.latitude = payload.getInt();
        this.longitude = payload.getInt();
        this.custom_mode = payload.getUnsignedShort();
        this.altitude = payload.getShort();
        this.target_altitude = payload.getShort();
        this.target_distance = payload.getUnsignedShort();
        this.wp_num = payload.getUnsignedShort();
        this.failure_flags = payload.getUnsignedShort();
        this.type = payload.getUnsignedByte();
        this.autopilot = payload.getUnsignedByte();
        this.heading = payload.getUnsignedByte();
        this.target_heading = payload.getUnsignedByte();
        this.throttle = payload.getUnsignedByte();
        this.airspeed = payload.getUnsignedByte();
        this.airspeed_sp = payload.getUnsignedByte();
        this.groundspeed = payload.getUnsignedByte();
        this.windspeed = payload.getUnsignedByte();
        this.wind_heading = payload.getUnsignedByte();
        this.eph = payload.getUnsignedByte();
        this.epv = payload.getUnsignedByte();
        this.temperature_air = payload.getByte();
        this.climb_rate = payload.getByte();
        this.battery = payload.getByte();
        this.custom0 = payload.getByte();
        this.custom1 = payload.getByte();
        this.custom2 = payload.getByte();
        
        if (isMavlink2) {
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_high_latency2() {
        this.msgid = MAVLINK_MSG_ID_HIGH_LATENCY2;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_high_latency2( long timestamp, int latitude, int longitude, int custom_mode, short altitude, short target_altitude, int target_distance, int wp_num, int failure_flags, short type, short autopilot, short heading, short target_heading, short throttle, short airspeed, short airspeed_sp, short groundspeed, short windspeed, short wind_heading, short eph, short epv, byte temperature_air, byte climb_rate, byte battery, byte custom0, byte custom1, byte custom2) {
        this.msgid = MAVLINK_MSG_ID_HIGH_LATENCY2;

        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.custom_mode = custom_mode;
        this.altitude = altitude;
        this.target_altitude = target_altitude;
        this.target_distance = target_distance;
        this.wp_num = wp_num;
        this.failure_flags = failure_flags;
        this.type = type;
        this.autopilot = autopilot;
        this.heading = heading;
        this.target_heading = target_heading;
        this.throttle = throttle;
        this.airspeed = airspeed;
        this.airspeed_sp = airspeed_sp;
        this.groundspeed = groundspeed;
        this.windspeed = windspeed;
        this.wind_heading = wind_heading;
        this.eph = eph;
        this.epv = epv;
        this.temperature_air = temperature_air;
        this.climb_rate = climb_rate;
        this.battery = battery;
        this.custom0 = custom0;
        this.custom1 = custom1;
        this.custom2 = custom2;
        
    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_high_latency2( long timestamp, int latitude, int longitude, int custom_mode, short altitude, short target_altitude, int target_distance, int wp_num, int failure_flags, short type, short autopilot, short heading, short target_heading, short throttle, short airspeed, short airspeed_sp, short groundspeed, short windspeed, short wind_heading, short eph, short epv, byte temperature_air, byte climb_rate, byte battery, byte custom0, byte custom1, byte custom2, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_HIGH_LATENCY2;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.custom_mode = custom_mode;
        this.altitude = altitude;
        this.target_altitude = target_altitude;
        this.target_distance = target_distance;
        this.wp_num = wp_num;
        this.failure_flags = failure_flags;
        this.type = type;
        this.autopilot = autopilot;
        this.heading = heading;
        this.target_heading = target_heading;
        this.throttle = throttle;
        this.airspeed = airspeed;
        this.airspeed_sp = airspeed_sp;
        this.groundspeed = groundspeed;
        this.windspeed = windspeed;
        this.wind_heading = wind_heading;
        this.eph = eph;
        this.epv = epv;
        this.temperature_air = temperature_air;
        this.climb_rate = climb_rate;
        this.battery = battery;
        this.custom0 = custom0;
        this.custom1 = custom1;
        this.custom2 = custom2;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_high_latency2(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_HIGH_LATENCY2;

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
        return "MAVLINK_MSG_ID_HIGH_LATENCY2 - sysid:"+sysid+" compid:"+compid+" timestamp:"+timestamp+" latitude:"+latitude+" longitude:"+longitude+" custom_mode:"+custom_mode+" altitude:"+altitude+" target_altitude:"+target_altitude+" target_distance:"+target_distance+" wp_num:"+wp_num+" failure_flags:"+failure_flags+" type:"+type+" autopilot:"+autopilot+" heading:"+heading+" target_heading:"+target_heading+" throttle:"+throttle+" airspeed:"+airspeed+" airspeed_sp:"+airspeed_sp+" groundspeed:"+groundspeed+" windspeed:"+windspeed+" wind_heading:"+wind_heading+" eph:"+eph+" epv:"+epv+" temperature_air:"+temperature_air+" climb_rate:"+climb_rate+" battery:"+battery+" custom0:"+custom0+" custom1:"+custom1+" custom2:"+custom2+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_HIGH_LATENCY2";
    }
}
        