import { request, authRequest } from './config';

const userApi = {
    signUp: (userInfo) => request.post('/api/user', userInfo),

    signIn: (userInfo) => request.post('/api/user/login', userInfo),

    getUser: () => authRequest.get(`/api/user`),

    updateUser: (formData) => authRequest.put(`/api/user`, formData),

    validateUsername: (username) => request.get('/api/user/username', { params: { value: username } }),
};

export default userApi;
