import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// 第四篇 ch32：开发期用 Vite 代理，把 /api/* 转发到后端 8080，避开跨域。
// 生产环境改由 Nginx 反代（见 ch35），前端代码不用改。
export default defineConfig({
  plugins: [vue()],
  server: {
    port: 5173,
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ''),
      },
    },
  },
})
