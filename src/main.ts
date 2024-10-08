import { createApp } from "vue";
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import App from "@/App.vue";
import zhCn from "element-plus/es/locale/lang/zh-cn";
import "virtual:svg-icons-register";
import "@/styles/index.scss";
import globalComponents from "@/components";
import axios from "axios";
import router from "./router";
import pinia from "./store";

// axios({
//   method: "post",
//   url: "/api/user/login",
//   data: {
//     username: "admin",
//     password: "111111",
//   },
// });

const app = createApp(App);
app.use(ElementPlus, {
  locale: zhCn,
});
app.use(globalComponents);
app.use(router);
app.use(pinia);
app.mount("#app");
