/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

package com.MAVLink.enums;

/**
 * 
 */
public class MAV_ODID_ID_TYPE {
   public static final int MAV_ODID_ID_TYPE_NONE = 0; /* No type defined. | */
   public static final int MAV_ODID_ID_TYPE_SERIAL_NUMBER = 1; /* Manufacturer Serial Number (ANSI/CTA-2063 format). | */
   public static final int MAV_ODID_ID_TYPE_CAA_REGISTRATION_ID = 2; /* CAA (Civil Aviation Authority) registered ID. Format: [ICAO Country Code].[CAA Assigned ID]. | */
   public static final int MAV_ODID_ID_TYPE_UTM_ASSIGNED_UUID = 3; /* UTM (Unmanned Traffic Management) assigned UUID (RFC4122). | */
   public static final int MAV_ODID_ID_TYPE_SPECIFIC_SESSION_ID = 4; /* A 20 byte ID for a specific flight/session. The exact ID type is indicated by the first byte of uas_id and these type values are managed by ICAO. | */
   public static final int MAV_ODID_ID_TYPE_ENUM_END = 5; /*  | */
}
            