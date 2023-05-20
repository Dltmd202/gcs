import { request, authRequest } from './config';

const agentApi = {
    arm: (sysid) => authRequest.post(`/api/agent/${sysid}/arm`),
    globalArm: () => authRequest.post(`/api/agent/arm`),
    disarm: (sysid) => authRequest.post(`/api/agent/${sysid}/disarm`),
    globalDisarm: () => authRequest.post(`/api/agent/disarm`),
    takeOff: (sysid, alt) => authRequest.post(`/api/agent/${sysid}/takeoff?alt=${alt}`),
    globalTakeOff: (alt) => authRequest.post(`/api/agent/takeoff?alt=${alt}`),
    land: (sysid) => authRequest.post(`/api/agent/${sysid}/land`),
    globalLand: () => authRequest.post(`/api/agent/land`),
    offboard: (sysid) => authRequest.post(`/api/agent/${sysid}/offboard`),
    globalOffboard: () => authRequest.post(`/api/agent/offboard`),
    reboot: (sysid) => authRequest.post(`/api/agent/${sysid}/reboot`),
    globalReboot: () => authRequest.post(`/api/agent/reboot`),
    globalLED: (type, r, g, b, brightness, speed) => authRequest.post(
      `/api/agent/led?type=${type}&r=${r}&g=${g}&b=${b}&brightness=${brightness}&speed=${speed}`),
    destination: (sysid, x, y, z) => authRequest.post(
      `/api/agent/${sysid}/destination?x=${x}&y=${y}&z=${z}`),
    derectionDestination: (sysid, x, y, z, yaw) => authRequest.post(
      `/api/agent/${sysid}/destination?x=${x}&y=${y}&z=${z}&yaw=${yaw}`),
    globalDestination: (x, y, z) => authRequest.post(
      `/api/agent/destination?x=${x}&y=${y}&z=${z}`),
    globalDirectionDestination: (x, y, z, yaw) => authRequest.post(
      `/api/agent/destination?x=${x}&y=${y}&z=${z}&yaw=${yaw}`),
    globalScenarioConfiguration: (x, y, rotation, path) => authRequest.post(
      `/api/scenario?x=${x}&y=${y}&rot=${rotation}&path=${path}`),
    scenarioConfiguration: (sysid, x, y, rotation, path) => authRequest.post(
      `/api/scenario/${sysid}?x=${x}&y=${y}&rot=${rotation}&path=${path}`),
    scenarioSync: (time) => authRequest.post(
      `/api/scenario/sync?time=${time}`),
    scenarioStop: () => authRequest.post(`/api/scenario/stop`),
    scenarioReset: () => authRequest.post(`/api/scenario/reset`)

};

export default agentApi;
