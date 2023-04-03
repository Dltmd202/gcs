/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE BATTERY_STATUS PACKING
package com.MAVLink.common;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.Description;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
import com.MAVLink.Messages.Units;

/**
 * Battery information. Updates GCS with flight controller battery status. Smart batteries also use this message, but may additionally send SMART_BATTERY_INFO.
 */
public class msg_battery_status extends MAVLinkMessage {

    public static final int MAVLINK_MSG_ID_BATTERY_STATUS = 147;
    public static final int MAVLINK_MSG_LENGTH = 54;
    private static final long serialVersionUID = MAVLINK_MSG_ID_BATTERY_STATUS;

    
    /**
     * Consumed charge, -1: autopilot does not provide consumption estimate
     */
    @Description("Consumed charge, -1: autopilot does not provide consumption estimate")
    @Units("mAh")
    public int current_consumed;
    
    /**
     * Consumed energy, -1: autopilot does not provide energy consumption estimate
     */
    @Description("Consumed energy, -1: autopilot does not provide energy consumption estimate")
    @Units("hJ")
    public int energy_consumed;
    
    /**
     * Temperature of the battery. INT16_MAX for unknown temperature.
     */
    @Description("Temperature of the battery. INT16_MAX for unknown temperature.")
    @Units("cdegC")
    public short temperature;
    
    /**
     * Battery voltage of cells 1 to 10 (see voltages_ext for cells 11-14). Cells in this field above the valid cell count for this battery should have the UINT16_MAX value. If individual cell voltages are unknown or not measured for this battery, then the overall battery voltage should be filled in cell 0, with all others set to UINT16_MAX. If the voltage of the battery is greater than (UINT16_MAX - 1), then cell 0 should be set to (UINT16_MAX - 1), and cell 1 to the remaining voltage. This can be extended to multiple cells if the total voltage is greater than 2 * (UINT16_MAX - 1).
     */
    @Description("Battery voltage of cells 1 to 10 (see voltages_ext for cells 11-14). Cells in this field above the valid cell count for this battery should have the UINT16_MAX value. If individual cell voltages are unknown or not measured for this battery, then the overall battery voltage should be filled in cell 0, with all others set to UINT16_MAX. If the voltage of the battery is greater than (UINT16_MAX - 1), then cell 0 should be set to (UINT16_MAX - 1), and cell 1 to the remaining voltage. This can be extended to multiple cells if the total voltage is greater than 2 * (UINT16_MAX - 1).")
    @Units("mV")
    public int voltages[] = new int[10];
    
    /**
     * Battery current, -1: autopilot does not measure the current
     */
    @Description("Battery current, -1: autopilot does not measure the current")
    @Units("cA")
    public short current_battery;
    
    /**
     * Battery ID
     */
    @Description("Battery ID")
    @Units("")
    public short id;
    
    /**
     * Function of the battery
     */
    @Description("Function of the battery")
    @Units("")
    public short battery_function;
    
    /**
     * Type (chemistry) of the battery
     */
    @Description("Type (chemistry) of the battery")
    @Units("")
    public short type;
    
    /**
     * Remaining battery energy. Values: [0-100], -1: autopilot does not estimate the remaining battery.
     */
    @Description("Remaining battery energy. Values: [0-100], -1: autopilot does not estimate the remaining battery.")
    @Units("%")
    public byte battery_remaining;
    
    /**
     * Remaining battery time, 0: autopilot does not provide remaining battery time estimate
     */
    @Description("Remaining battery time, 0: autopilot does not provide remaining battery time estimate")
    @Units("s")
    public int time_remaining;
    
    /**
     * State for extent of discharge, provided by autopilot for warning or external reactions
     */
    @Description("State for extent of discharge, provided by autopilot for warning or external reactions")
    @Units("")
    public short charge_state;
    
    /**
     * Battery voltages for cells 11 to 14. Cells above the valid cell count for this battery should have a value of 0, where zero indicates not supported (note, this is different than for the voltages field and allows empty byte truncation). If the measured value is 0 then 1 should be sent instead.
     */
    @Description("Battery voltages for cells 11 to 14. Cells above the valid cell count for this battery should have a value of 0, where zero indicates not supported (note, this is different than for the voltages field and allows empty byte truncation). If the measured value is 0 then 1 should be sent instead.")
    @Units("mV")
    public int voltages_ext[] = new int[4];
    
    /**
     * Battery mode. Default (0) is that battery mode reporting is not supported or battery is in normal-use mode.
     */
    @Description("Battery mode. Default (0) is that battery mode reporting is not supported or battery is in normal-use mode.")
    @Units("")
    public short mode;
    
    /**
     * Fault/health indications. These should be set when charge_state is MAV_BATTERY_CHARGE_STATE_FAILED or MAV_BATTERY_CHARGE_STATE_UNHEALTHY (if not, fault reporting is not supported).
     */
    @Description("Fault/health indications. These should be set when charge_state is MAV_BATTERY_CHARGE_STATE_FAILED or MAV_BATTERY_CHARGE_STATE_UNHEALTHY (if not, fault reporting is not supported).")
    @Units("")
    public long fault_bitmask;
    

    /**
     * Generates the payload for a mavlink message for a message of this type
     * @return
     */
    @Override
    public MAVLinkPacket pack() {
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH,isMavlink2);
        packet.sysid = sysid;
        packet.compid = compid;
        packet.msgid = MAVLINK_MSG_ID_BATTERY_STATUS;

        packet.payload.putInt(current_consumed);
        packet.payload.putInt(energy_consumed);
        packet.payload.putShort(temperature);
        
        for (int i = 0; i < voltages.length; i++) {
            packet.payload.putUnsignedShort(voltages[i]);
        }
                    
        packet.payload.putShort(current_battery);
        packet.payload.putUnsignedByte(id);
        packet.payload.putUnsignedByte(battery_function);
        packet.payload.putUnsignedByte(type);
        packet.payload.putByte(battery_remaining);
        
        if (isMavlink2) {
             packet.payload.putInt(time_remaining);
             packet.payload.putUnsignedByte(charge_state);
             
        for (int i = 0; i < voltages_ext.length; i++) {
            packet.payload.putUnsignedShort(voltages_ext[i]);
        }
                    
             packet.payload.putUnsignedByte(mode);
             packet.payload.putUnsignedInt(fault_bitmask);
            
        }
        return packet;
    }

    /**
     * Decode a battery_status message into this class fields
     *
     * @param payload The message to decode
     */
    @Override
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();

        this.current_consumed = payload.getInt();
        this.energy_consumed = payload.getInt();
        this.temperature = payload.getShort();
        
        for (int i = 0; i < this.voltages.length; i++) {
            this.voltages[i] = payload.getUnsignedShort();
        }
                
        this.current_battery = payload.getShort();
        this.id = payload.getUnsignedByte();
        this.battery_function = payload.getUnsignedByte();
        this.type = payload.getUnsignedByte();
        this.battery_remaining = payload.getByte();
        
        if (isMavlink2) {
             this.time_remaining = payload.getInt();
             this.charge_state = payload.getUnsignedByte();
             
        for (int i = 0; i < this.voltages_ext.length; i++) {
            this.voltages_ext[i] = payload.getUnsignedShort();
        }
                
             this.mode = payload.getUnsignedByte();
             this.fault_bitmask = payload.getUnsignedInt();
            
        }
    }

    /**
     * Constructor for a new message, just initializes the msgid
     */
    public msg_battery_status() {
        this.msgid = MAVLINK_MSG_ID_BATTERY_STATUS;
    }

    /**
     * Constructor for a new message, initializes msgid and all payload variables
     */
    public msg_battery_status( int current_consumed, int energy_consumed, short temperature, int[] voltages, short current_battery, short id, short battery_function, short type, byte battery_remaining, int time_remaining, short charge_state, int[] voltages_ext, short mode, long fault_bitmask) {
        this.msgid = MAVLINK_MSG_ID_BATTERY_STATUS;

        this.current_consumed = current_consumed;
        this.energy_consumed = energy_consumed;
        this.temperature = temperature;
        this.voltages = voltages;
        this.current_battery = current_battery;
        this.id = id;
        this.battery_function = battery_function;
        this.type = type;
        this.battery_remaining = battery_remaining;
        this.time_remaining = time_remaining;
        this.charge_state = charge_state;
        this.voltages_ext = voltages_ext;
        this.mode = mode;
        this.fault_bitmask = fault_bitmask;
        
    }

    /**
     * Constructor for a new message, initializes everything
     */
    public msg_battery_status( int current_consumed, int energy_consumed, short temperature, int[] voltages, short current_battery, short id, short battery_function, short type, byte battery_remaining, int time_remaining, short charge_state, int[] voltages_ext, short mode, long fault_bitmask, int sysid, int compid, boolean isMavlink2) {
        this.msgid = MAVLINK_MSG_ID_BATTERY_STATUS;
        this.sysid = sysid;
        this.compid = compid;
        this.isMavlink2 = isMavlink2;

        this.current_consumed = current_consumed;
        this.energy_consumed = energy_consumed;
        this.temperature = temperature;
        this.voltages = voltages;
        this.current_battery = current_battery;
        this.id = id;
        this.battery_function = battery_function;
        this.type = type;
        this.battery_remaining = battery_remaining;
        this.time_remaining = time_remaining;
        this.charge_state = charge_state;
        this.voltages_ext = voltages_ext;
        this.mode = mode;
        this.fault_bitmask = fault_bitmask;
        
    }

    /**
     * Constructor for a new message, initializes the message with the payload
     * from a mavlink packet
     *
     */
    public msg_battery_status(MAVLinkPacket mavLinkPacket) {
        this.msgid = MAVLINK_MSG_ID_BATTERY_STATUS;

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
        return "MAVLINK_MSG_ID_BATTERY_STATUS - sysid:"+sysid+" compid:"+compid+" current_consumed:"+current_consumed+" energy_consumed:"+energy_consumed+" temperature:"+temperature+" voltages:"+voltages+" current_battery:"+current_battery+" id:"+id+" battery_function:"+battery_function+" type:"+type+" battery_remaining:"+battery_remaining+" time_remaining:"+time_remaining+" charge_state:"+charge_state+" voltages_ext:"+voltages_ext+" mode:"+mode+" fault_bitmask:"+fault_bitmask+"";
    }

    /**
     * Returns a human-readable string of the name of the message
     */
    @Override
    public String name() {
        return "MAVLINK_MSG_ID_BATTERY_STATUS";
    }
}
        