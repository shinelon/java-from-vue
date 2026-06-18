import { createRouter, createWebHistory } from 'vue-router'
import { useUserStore } from '../stores/user'

// 第四篇 ch33：路由 + 全局守卫（对标后端的 JwtInterceptor，前端版本）
const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/login', component: () => import('../views/Login.vue') },
    { path: '/', component: () => import('../views/Tasks.vue'), meta: { requiresAuth: true } },
  ],
})

// 没登录访问受保护页面 → 跳登录
router.beforeEach((to) => {
  const userStore = useUserStore()
  if (to.meta.requiresAuth && !userStore.token) {
    return '/login'
  }
})

export default router
