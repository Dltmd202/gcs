import {authRequest, request} from './config';
import getToken from "../module/token/tokenHook";

const contextApi = {
  postContextConfigurationFile: (context) =>
      authRequest.post(`/api/context`, context, {
          headers: {
              Authorization: getToken(),
              'Content-Type': 'multipart/form-data;',
          }
      }),
  getMyContextList: () => authRequest.get(`/api/context`),
  getRunningContext: () => authRequest.get(`/api/context/current`),
  holdContext: (contextId) => authRequest.post(`/api/context/${contextId}`),
  cleanContext: () => authRequest.delete(`/api/context`),
  isCurrentContext: () => authRequest.get(`/api/context/exist`)
};

export default contextApi;
