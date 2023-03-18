import { request, authRequest } from './config';

const agentApi = {
    arm: (sysid) => authRequest.post(`/api/agent/${sysid}/arm`),
    globalArm: () => authRequest.post(`/api/agent/arm`),
    disarm: (sysid) => authRequest.post(`/api/agent/${sysid}/disarm`),
    gloablDisarm: () => authRequest.post(`/api/agent/disarm`),
    takeOff: (sysid, alt) => authRequest.post(`/api/agent/${sysid}/takeoff?alt=${alt}`),
    globalTakeOff: (alt) => authRequest.post(`/api/agent/takeoff?alt=${alt}`),
    land: (sysid) => authRequest.post(`/api/agent/${sysid}/land`),
    globalLand: () => authRequest.post(`/api/agent/land`),
    offboard: (sysid) => authRequest.post(`/api/agent/${sysid}/offboard`),
    globalOffboard: () => authRequest.post(`/api/agent/offboard`),
    reboot: (sysid) => authRequest.post(`/api/agent/${sysid}/reboot`),
    globalReboot: () => authRequest.post(`/api/agent/reboot`),
    position: (sysid, x, y, z) => authRequest.post(
      `/api/agent/${sysid}/position?x=${x}&y=${y}&z=${z}`
    ),
    globalPosition: (x, y, z) => authRequest.post(
      `/api/agent/position?x=${x}&y=${y}&z=${z}`
    ),
};

export default agentApi;
