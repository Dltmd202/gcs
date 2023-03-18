import * as SockJS from 'sockjs-client';
import StompJs from '@stomp/stompjs';

const defaultOnConnect = (frame) => {
    console.log(`Connected ${frame}`)
}

const defaultOnError = (frame) => {
    console.log(`Error ${frame}`)
}

const StompClient = ({
    endpoint,
    onConnect = defaultOnError,
    onError = defaultOnError
}) => {
    const client = new StompJs.Client({
        brokerURL: `${process.env.REACT_APP_REALTIME_END_POINT}/api/realtime/info`,
        reconnectDelay: 5000,
        heartbeatIncoming: 4000,
        heartbeatOutgoing: 4000,
    })

    if(typeof StompClient !== 'function'){
        client.webSocketFactory = function () {
            return new SockJS(`http://localhost:8080/${endpoint}`)
        }
    }

    return client;
}

export default StompClient;