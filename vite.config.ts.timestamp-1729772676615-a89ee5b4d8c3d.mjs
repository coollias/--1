// vite.config.ts
import { defineConfig } from "file:///D:/%E5%A4%A7%E4%B8%89%E4%B8%8A%E5%AE%9E%E8%AE%AD/BRANCH/--1/node_modules/vite/dist/node/index.js";
import vue from "file:///D:/%E5%A4%A7%E4%B8%89%E4%B8%8A%E5%AE%9E%E8%AE%AD/BRANCH/--1/node_modules/@vitejs/plugin-vue/dist/index.mjs";
import path from "path";
import { createSvgIconsPlugin } from "file:///D:/%E5%A4%A7%E4%B8%89%E4%B8%8A%E5%AE%9E%E8%AE%AD/BRANCH/--1/node_modules/vite-plugin-svg-icons/dist/index.mjs";
import { viteMockServe } from "file:///D:/%E5%A4%A7%E4%B8%89%E4%B8%8A%E5%AE%9E%E8%AE%AD/BRANCH/--1/node_modules/vite-plugin-mock/dist/index.js";
var vite_config_default = defineConfig(({ command }) => {
  return {
    plugins: [
      vue(),
      createSvgIconsPlugin({
        // 指定需要缓存的图标文件夹(路径为存放所有svg图标的文件夹不单个svg图标)
        iconDirs: [path.resolve(process.cwd(), "src/assets/icons")],
        // 指定symbolId格式
        symbolId: "icon-[dir]-[name]"
      }),
      viteMockServe({
        // default
        mockPath: "mock",
        localEnabled: command === "serve"
      })
    ],
    resolve: {
      alias: {
        "@": path.resolve("./src")
      }
    },
    css: {
      preprocessorOptions: {
        scss: {
          javascriptEnabled: true,
          addtionalData: '@import "./src/styles/variable.scss";'
        }
      }
    },
    //代理跨域
    server: {
      proxy: {
        // 使用 proxy 实例
        "/api": {
          target: "http://sph-api.atguigu.cn",
          changeOrigin: true,
          rewrite: (path2) => path2.replace(/^\/api/, "")
        }
      }
    }
  };
});
export {
  vite_config_default as default
};
//# sourceMappingURL=data:application/json;base64,ewogICJ2ZXJzaW9uIjogMywKICAic291cmNlcyI6IFsidml0ZS5jb25maWcudHMiXSwKICAic291cmNlc0NvbnRlbnQiOiBbImNvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9kaXJuYW1lID0gXCJEOlxcXFxcdTU5MjdcdTRFMDlcdTRFMEFcdTVCOUVcdThCQURcXFxcQlJBTkNIXFxcXC0tMVwiO2NvbnN0IF9fdml0ZV9pbmplY3RlZF9vcmlnaW5hbF9maWxlbmFtZSA9IFwiRDpcXFxcXHU1OTI3XHU0RTA5XHU0RTBBXHU1QjlFXHU4QkFEXFxcXEJSQU5DSFxcXFwtLTFcXFxcdml0ZS5jb25maWcudHNcIjtjb25zdCBfX3ZpdGVfaW5qZWN0ZWRfb3JpZ2luYWxfaW1wb3J0X21ldGFfdXJsID0gXCJmaWxlOi8vL0Q6LyVFNSVBNCVBNyVFNCVCOCU4OSVFNCVCOCU4QSVFNSVBRSU5RSVFOCVBRSVBRC9CUkFOQ0gvLS0xL3ZpdGUuY29uZmlnLnRzXCI7aW1wb3J0IHsgZGVmaW5lQ29uZmlnfSBmcm9tIFwidml0ZVwiO1xyXG5pbXBvcnQgdnVlIGZyb20gXCJAdml0ZWpzL3BsdWdpbi12dWVcIjtcclxuaW1wb3J0IHBhdGggZnJvbSBcInBhdGhcIjtcclxuaW1wb3J0IHsgY3JlYXRlU3ZnSWNvbnNQbHVnaW4gfSBmcm9tIFwidml0ZS1wbHVnaW4tc3ZnLWljb25zXCI7XHJcbmltcG9ydCB7IFVzZXJDb25maWdFeHBvcnQsIENvbmZpZ0VudiB9IGZyb20gJ3ZpdGUnXHJcbmltcG9ydCB7IHZpdGVNb2NrU2VydmUgfSBmcm9tICd2aXRlLXBsdWdpbi1tb2NrJ1xyXG4vLyBodHRwczovL3ZpdGVqcy5kZXYvY29uZmlnL1xyXG5leHBvcnQgZGVmYXVsdCBkZWZpbmVDb25maWcoKHsgY29tbWFuZCB9KT0+IHtcclxuICByZXR1cm4ge1xyXG4gICAgcGx1Z2luczogW1xyXG4gICAgICB2dWUoKSxcclxuICAgICAgY3JlYXRlU3ZnSWNvbnNQbHVnaW4oe1xyXG4gICAgICAgIC8vIFx1NjMwN1x1NUI5QVx1OTcwMFx1ODk4MVx1N0YxM1x1NUI1OFx1NzY4NFx1NTZGRVx1NjgwN1x1NjU4N1x1NEVGNlx1NTkzOShcdThERUZcdTVGODRcdTRFM0FcdTVCNThcdTY1M0VcdTYyNDBcdTY3MDlzdmdcdTU2RkVcdTY4MDdcdTc2ODRcdTY1ODdcdTRFRjZcdTU5MzlcdTRFMERcdTUzNTVcdTRFMkFzdmdcdTU2RkVcdTY4MDcpXHJcbiAgICAgICAgaWNvbkRpcnM6IFtwYXRoLnJlc29sdmUocHJvY2Vzcy5jd2QoKSwgXCJzcmMvYXNzZXRzL2ljb25zXCIpXSxcclxuICAgICAgICAvLyBcdTYzMDdcdTVCOUFzeW1ib2xJZFx1NjgzQ1x1NUYwRlxyXG4gICAgICAgIHN5bWJvbElkOiBcImljb24tW2Rpcl0tW25hbWVdXCIsXHJcbiAgICAgIH0pLFxyXG4gICAgICB2aXRlTW9ja1NlcnZlKHtcclxuICAgICAgICAvLyBkZWZhdWx0XHJcbiAgICAgICAgbW9ja1BhdGg6ICdtb2NrJyxcclxuICAgICAgICBsb2NhbEVuYWJsZWQ6IGNvbW1hbmQgPT09ICdzZXJ2ZScsXHJcbiAgICAgIH0pLFxyXG4gICAgXSxcclxuICAgIHJlc29sdmU6IHtcclxuICAgICAgYWxpYXM6IHtcclxuICAgICAgICBcIkBcIjogcGF0aC5yZXNvbHZlKFwiLi9zcmNcIiksXHJcbiAgICAgIH0sXHJcbiAgICB9LFxyXG4gICAgY3NzOiB7XHJcbiAgICAgIHByZXByb2Nlc3Nvck9wdGlvbnM6IHtcclxuICAgICAgICBzY3NzOiB7XHJcbiAgICAgICAgICBqYXZhc2NyaXB0RW5hYmxlZDogdHJ1ZSxcclxuICAgICAgICAgIGFkZHRpb25hbERhdGE6ICdAaW1wb3J0IFwiLi9zcmMvc3R5bGVzL3ZhcmlhYmxlLnNjc3NcIjsnLFxyXG4gICAgICAgIH0sXHJcbiAgICAgIH0sXHJcbiAgICB9LFxyXG4gICAgLy9cdTRFRTNcdTc0MDZcdThERThcdTU3REZcclxuICAgIHNlcnZlcjoge1xyXG4gICAgICBwcm94eTp7XHJcbiAgICAgICAgLy8gXHU0RjdGXHU3NTI4IHByb3h5IFx1NUI5RVx1NEY4QlxyXG4gICAgICAnL2FwaSc6IHtcclxuICAgICAgICB0YXJnZXQ6ICdodHRwOi8vc3BoLWFwaS5hdGd1aWd1LmNuJyxcclxuICAgICAgICBjaGFuZ2VPcmlnaW46IHRydWUsXHJcbiAgICAgICAgcmV3cml0ZTogKHBhdGgpID0+IHBhdGgucmVwbGFjZSgvXlxcL2FwaS8sICcnKSxcclxuICAgICAgfSxcclxuICAgICAgfVxyXG4gICAgfVxyXG5cclxuICB9XHJcbn0pO1xyXG5cclxuXHJcbiJdLAogICJtYXBwaW5ncyI6ICI7QUFBMlIsU0FBUyxvQkFBbUI7QUFDdlQsT0FBTyxTQUFTO0FBQ2hCLE9BQU8sVUFBVTtBQUNqQixTQUFTLDRCQUE0QjtBQUVyQyxTQUFTLHFCQUFxQjtBQUU5QixJQUFPLHNCQUFRLGFBQWEsQ0FBQyxFQUFFLFFBQVEsTUFBSztBQUMxQyxTQUFPO0FBQUEsSUFDTCxTQUFTO0FBQUEsTUFDUCxJQUFJO0FBQUEsTUFDSixxQkFBcUI7QUFBQTtBQUFBLFFBRW5CLFVBQVUsQ0FBQyxLQUFLLFFBQVEsUUFBUSxJQUFJLEdBQUcsa0JBQWtCLENBQUM7QUFBQTtBQUFBLFFBRTFELFVBQVU7QUFBQSxNQUNaLENBQUM7QUFBQSxNQUNELGNBQWM7QUFBQTtBQUFBLFFBRVosVUFBVTtBQUFBLFFBQ1YsY0FBYyxZQUFZO0FBQUEsTUFDNUIsQ0FBQztBQUFBLElBQ0g7QUFBQSxJQUNBLFNBQVM7QUFBQSxNQUNQLE9BQU87QUFBQSxRQUNMLEtBQUssS0FBSyxRQUFRLE9BQU87QUFBQSxNQUMzQjtBQUFBLElBQ0Y7QUFBQSxJQUNBLEtBQUs7QUFBQSxNQUNILHFCQUFxQjtBQUFBLFFBQ25CLE1BQU07QUFBQSxVQUNKLG1CQUFtQjtBQUFBLFVBQ25CLGVBQWU7QUFBQSxRQUNqQjtBQUFBLE1BQ0Y7QUFBQSxJQUNGO0FBQUE7QUFBQSxJQUVBLFFBQVE7QUFBQSxNQUNOLE9BQU07QUFBQTtBQUFBLFFBRU4sUUFBUTtBQUFBLFVBQ04sUUFBUTtBQUFBLFVBQ1IsY0FBYztBQUFBLFVBQ2QsU0FBUyxDQUFDQSxVQUFTQSxNQUFLLFFBQVEsVUFBVSxFQUFFO0FBQUEsUUFDOUM7QUFBQSxNQUNBO0FBQUEsSUFDRjtBQUFBLEVBRUY7QUFDRixDQUFDOyIsCiAgIm5hbWVzIjogWyJwYXRoIl0KfQo=
