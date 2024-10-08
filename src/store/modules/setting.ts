//小仓库：layout配置仓库
import { defineStore } from "pinia";

let useLayOutSettingStore =defineStore('SettingStore',{
  state:()=>{
    return {
      fold:false,//用于控制菜单折叠
    }
  }
})

export default useLayOutSettingStore;