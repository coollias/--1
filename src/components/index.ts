import SvgIcon from "@/components/SvgIcon/index.vue";
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import { App } from "vue";
//@ts-ignore
const allGlobalComponent = { SvgIcon };
export default {
  install(app: App) {
    //注册项目全局组件
    Object.keys(allGlobalComponent).forEach((key) => {
      app.component(key, allGlobalComponent[key]);
    });
    //注册el的icon注册为全局组件
    for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
      app.component(key, component)
    };
  },
};
