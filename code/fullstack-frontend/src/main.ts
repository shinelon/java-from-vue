import { createApp } from 'vue'
import { createPinia } from 'pinia'
import App from './App.vue'
import router from './router'

// 第四篇 ch32：应用入口。对标后端的 TaskManagerApplication.main
createApp(App).use(createPinia()).use(router).mount('#app')
