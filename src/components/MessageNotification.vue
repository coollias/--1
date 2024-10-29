import { Stomp } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

const socket = new SockJS('http://localhost:8080/ws');
const stompClient = Stomp.over(socket);

stompClient.connect({}, (frame) => {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/messages', (message) => {
        // 显示 Element Plus 通知
        this.$notify({
            title: '消息通知',
            message: message.body,
            type: 'info',
        });
    });
});

// 发送消息
function sendMessage(msg) {
    stompClient.send("/app/send", {}, msg);
}
