function getToken() {
    const accessToken = localStorage.getItem('token');
    return `Bearer ${accessToken}`;
}

export default getToken;