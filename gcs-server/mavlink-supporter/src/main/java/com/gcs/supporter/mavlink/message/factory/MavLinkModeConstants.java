package com.gcs.supporter.mavlink.message.factory;

import static com.MAVLink.enums.MAV_MODE_FLAG.*;

public class MavLinkModeConstants {
    protected static final int PX4_CUSTOM_MAIN_MODE_AUTO = 4;
    protected static final int PX4_CUSTOM_SUB_MODE_AUTO_LAND = 6;
    protected static final int PX4_CUSTOM_MAIN_MODE_OFFBOARD = 6;
    protected static final int LAND_MODE = MAV_MODE_FLAG_SAFETY_ARMED |
            MAV_MODE_FLAG_STABILIZE_ENABLED |
            MAV_MODE_FLAG_GUIDED_ENABLED |
            MAV_MODE_FLAG_AUTO_ENABLED |
            MAV_MODE_FLAG_CUSTOM_MODE_ENABLED;

    protected static final int OFFBOARD_MODE = MAV_MODE_FLAG_SAFETY_ARMED | MAV_MODE_FLAG_CUSTOM_MODE_ENABLED;
    protected static final int MAVLINK_MSG_ID_HEARTBEAT = 0;
}
