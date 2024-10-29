<template>
  <div>webSocket</div>
  <div>
    用户1
    <input v-model="inputValue" />
    <button @click="sendMsg">发送</button>
    <button>关闭</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { ElNotification } from 'element-plus'; // 引入 Element Plus 通知

let inputValue = ref('');
let maxTime = 3; // 重连最大的连接次数
let tryTime = 0; // 重连后出错的次数
let reconnentTimer; // 重连定时器
let client;
let headerBeatTimer; // 心跳定时器

const initWebSocket = () => {
  client = new WebSocket('ws://localhost:8080/websocket/1'); // 修改为你的后端 WebSocket 地址

  client.onopen = () => {
    clearTimeout(reconnentTimer);
    headerBeat(); // 连接上后就开启心跳检测
    console.log('连接上了');
  };

  client.onmessage = (msg) => {
    const data = JSON.parse(msg.data);
    if(data.type=="note"){
    // 显示 Element Plus 通知
    ElNotification({
      title: '新消息',
      message: data.msg, // 假设消息在data.msg中
      type: 'info',
    });}
  };

  client.onclose = () => {
    reconnect();
    console.log('close');
  };

  client.onerror = () => {
    tryTime += 1;
    reconnect();
    console.log('error');
  };
};

const reconnect = () => {
  clearTimeout(reconnentTimer);
  if (tryTime > maxTime) { // 重连次数超过3次就不再重连
    alert('重连次数超过3次，请联系管理员！');
    clearTimeout(reconnentTimer);
    clearInterval(headerBeatTimer);
    return;
  }
  reconnentTimer = setTimeout(() => {
    initWebSocket();
  }, 2000);
};

const headerBeat = () => {
  clearInterval(headerBeatTimer);
  headerBeatTimer = setInterval(() => {
    client.send(JSON.stringify({
      type: 'headerBeat',
      msg: 'test',
    }));
  }, 3000);
};

function sendMsg() {
  client.send(JSON.stringify({
      type: 'note',
      msg: inputValue.value,
    }));
  // client.send(inputValue.value);
}

// 初始化 WebSocket 连接
initWebSocket();
</script>

<style scoped></style>
