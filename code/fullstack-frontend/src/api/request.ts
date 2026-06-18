import axios from 'axios'
import { useUserStore } from '../stores/user'

// 第四篇 ch32：axios 实例 + 拦截器（这是前后端对接的核心）
// 对标后端的 JwtInterceptor：前端在"发请求前"塞 token，后端在"进 Controller 前"校验。
const request = axios.create({
  baseURL: '/api',        // 走 Vite 代理 / Nginx 反代，统一加前缀
  timeout: 10000,
})

// 请求拦截器：每个请求自动带上 token
request.interceptors.request.use((config) => {
  const userStore = useUserStore()
  if (userStore.token) {
    config.headers.Authorization = `Bearer ${userStore.token}`
  }
  return config
})

// 响应拦截器：统一拆 code、处理 401
// 关键：后端全局异常处理器把所有异常都返回 HTTP 200，业务码放在 body.code 里。
// 所以"token 失效"是 HTTP 200 + body.code===401，必须在这里按 code 识别（不会触发下面 HTTP 401 分支）。
request.interceptors.response.use(
  (response) => {
    const res = response.data            // 后端统一返回 { code, message, data }
    if (res.code === 200) {
      return res.data                     // 直接把 data 拆出来，业务代码更干净
    }
    if (res.code === 401) {
      // token 失效：清登录态，跳登录页（业务码 401，不是 HTTP 401）
      useUserStore().logout()
      window.location.href = '/login'
    }
    alert(res.message || '请求失败')
    return Promise.reject(new Error(res.message || '请求失败'))
  },
  (error) => {
    // 网络层 / HTTP 非 2xx（后端目前不会走到这里，保留作防御）
    if (error.response?.status === 401) {
      useUserStore().logout()
      window.location.href = '/login'
    }
    return Promise.reject(error)
  },
)

export default request
