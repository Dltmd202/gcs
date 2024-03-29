export const agentStatusMask = [
  {mask: 0b1, shift: 0, name: "SAFETY_LOCK"},
  {mask: 0b10, shift: 1, name: "ARM"},
  {mask: 0b100, shift: 2, name: "OFFBOARD"},
  {mask: 0b1000, shift: 3, name: "MANUAL"},
  {mask: 0b10000, shift: 4, name: "AUTO"},
  {mask: 0b100000, shift: 5, name: "FAIL_SAFE"},
  {mask: 0b1000000, shift: 6, name: "BATTERY_PROBLEM"},
  {mask: 0b10000000, shift: 7, name: "RTK_GPS_CONNECTION"},
  {mask: 0b100000000, shift: 8, name: "RTKGPS_BASE_RECV"},
  {mask: 0b1000000000, shift: 9, name: "RTKGPS_FIXED"},
  {mask: 0b10000000000, shift: 10, name: "RTKGPS_OFFSET"},
  {mask: 0b100000000000, shift: 11, name: "COMM_PROBLEM"},
  {mask: 0b1000000000000, shift: 12, name: "INIT_PITCH_PROBLEM"},
  {mask: 0b10000000000000, shift: 13, name: "INIT_ROLL_PROBLEM"},
  {mask: 0b100000000000000, shift: 14, name: "INIT_VELX_PROBLEM"},
  {mask: 0b1000000000000000, shift: 15, name: "INIT_VELY_PROBLEM"},
  {mask: 0b10000000000000000, shift: 16, name: "INIT_VELZ_PROBLEM"},
  {mask: 0b100000000000000000, shift: 17, name: "INIT_EMBEDDED_SC_OFFSET"},
  {mask: 0b1000000000000000000, shift: 18, name: "INIT_EMBEDDED_SC_FILE"},
  {mask: 0b10000000000000000000, shift: 19, name: "INIT_EMBEDDED_SC_LENGTH_ERR"},
  {mask: 0b100000000000000000000, shift: 20, name: "INIT_EMBEDDED_SC_CRC_ERR"},
  {mask: 0b1000000000000000000000, shift: 21, name: "INIT_EMBEDDED_SC_START_TIME"},
  {mask: 0b10000000000000000000000, shift: 22, name: "PERI_5V_POWER_PROB"},
  {mask: 0b100000000000000000000000, shift: 23, name: "TEMPERATURE_PROBLEM"},
  {mask: 0b1000000000000000000000000, shift: 24, name: "MAG_INCONSISTENT_PROBLEM"},
  {mask: 0b10000000000000000000000000, shift: 25, name: "ACC_INCONSISTENT_PROBLEM"},
  {mask: 0b100000000000000000000000000, shift: 26, name: "GYR_INCONSISTENT_PROBLEM"},
  {mask: 0b1000000000000000000000000000, shift: 27, name: "AGE_CORR_LV1_PROBLEM"},
  {mask: 0b10000000000000000000000000000, shift: 28, name: "AGE_CORR_LV2_PROBLEM"},
  {mask: 0b100000000000000000000000000000, shift: 29, name: "NUM_OF_MONITORING"},
]
