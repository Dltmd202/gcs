import { request, authRequest } from './config';

const paramApi = {
    paramKeyList: () => authRequest.get(`/api/param/key`),
    paramList: (sysid) => authRequest.get(`/api/param/${sysid}`),
    queryDroneShowParamList: (sysid) => authRequest.post(`/api/param/ds/${sysid}`),
    getDroneShowParamList: (sysid) => authRequest.get(`/api/param/ds/${sysid}`),
    globalParamList: (sysid) => authRequest.patch(`/api/param`),
    setParam: (sysid, paramKey, val) => authRequest.patch(`/api/param/${sysid}/${paramKey}?val=${val}`),
    globalSetParam: (paramKey, val) => authRequest.patch(`/api/param/${paramKey}?val=${val}`),
};

export default paramApi;
