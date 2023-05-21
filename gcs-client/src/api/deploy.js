import { request, authRequest } from './config';

const deployApi = {
    takeOff: (sysid, x, y, z, yaw) => authRequest.post(
      `/api/deploy/${sysid}/takeoff?x=${x}&y=${y}&z=${z}&yaw=${yaw}`
    ),
    land: (sysid) => authRequest.post(`/api/deploy/${sysid}/land`),
    move: (sysid, x, y, z, yaw) => authRequest.post(
      `/api/deploy/${sysid}/move?x=${x}&y=${y}&z=${z}&yaw=${yaw}`),

};

export default deployApi;
