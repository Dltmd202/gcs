import axios from 'axios';
import getToken from "../module/token/tokenHook";

const setInterceptors = (instance) => {
    instance.interceptors.request.use(
        (config) => {
            config.headers = {
                ...config.headers,
            };
            return config;
        },
        (error) => {
            return Promise.reject(error.response);
        },
    );

    instance.interceptors.response.use(
        (response) => {
            return response;
        },
        (error) => {
            return Promise.reject(error.response);
        },
    );
    return instance;
};

const setAuthInterceptors = (instance) => {
    instance.interceptors.request.use(
        (config) => {
            config.headers = {
                ...config.headers,
                Authorization: getToken(),
            };
            return config;
        },
        (error) => {
            return Promise.reject(error.response);
        },
    );

    instance.interceptors.response.use(
        (response) => {
            return response;
        },
        (error) => {
            return Promise.reject(error.response);
        },
    );
    return instance;
};

const createInstance = () => {
    const instance = axios.create({
        baseURL: process.env.REACT_APP_API_END_POINT,
        timeout: 5000,
    });
    return setInterceptors(instance);
};
export const request = createInstance();

const createInstanceWithAuth = () => {
    const instance = axios.create({
        baseURL: process.env.REACT_APP_API_END_POINT,
        timeout: 5000,
    });
    return setAuthInterceptors(instance);
};
export const authRequest = createInstanceWithAuth();
