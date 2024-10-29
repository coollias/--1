
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
import { ref } from 'vue'
let inputValue = ref('')
let maxTime = 3 //重连最大的连接次数
let tryTime = 0 //重连后出错的次数
let reconnentTimer //重连定时器
let client
let headerBeatTimer //心跳定时器
//建立和webSocket服务的链接
 
//webSocket不可能让所有人随便连接，所以我们需要携带token或者id
//心跳检测 定期向websocket服务发送消息维持心跳是为了避免websocket给我们断开连接
//定时重连 如果连接中断或者出错我们需要重新连接保证websocket连接的一个持续效果
/* 
1.直接带在url的query
2.带在请求头上
3.cookie
*/
const initWebSocket = () => {
  client = new WebSocket('ws://localhost:8080/ws');

  //也可以用addEventLister监听
  client.onopen = () => {
    clearTimeout(reconnentTimer)
    headerBeat() //连接上后就开启心跳检测
    console.log('连接上了')
  }
 
  client.onmessage = (msg) => {
    //后端有消息
    console.log(JSON.parse(msg.data))
  }
 
  client.onclose = () => {
    reconnect()
    console.log('close')
  }
 
  client.onerror = () => {
    tryTime += 1
    reconnect()
    console.log('error')
  }
}
 
//websocket重连方法 
/* 
  重连不可能永远都进行重新连接那样的话触发的就太频繁了 所以需要一个定时器
*/
const reconnect = () => {
  //清除定时器
  clearTimeout(reconnentTimer)
  //用一个定时器避免频繁重连
  if(tryTime > maxTime) { //重连次数超过3次就不再重连
     alert('重连次数超过3次，请联系管理员！')
     clearTimeout(reconnentTimer)
     clearInterval(headerBeatTimer)
     return
  }
  reconnentTimer = setTimeout(() => {
    initWebSocket()
  }, 2000)
}
 
//webSockte 心跳检测
const headerBeat = () => {
  clearInterval(headerBeatTimer)
  headerBeatTimer = setInterval(()=> {
    client.send(JSON.stringify({
      type: 'headerBeat',
      msg: 'test'
    }))
  },3000)
}
 
function sendMsg() {
  client.send(inputValue.value)
}
 
initWebSocket()
</script>
 

 
<style scoped></style>