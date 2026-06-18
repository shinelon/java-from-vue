import { defineStore } from 'pinia'
import { ref } from 'vue'
import { login as loginApi, register as registerApi } from '../api/auth'

interface UserInfo {
  id: number
  username: string
}

// localStorage 可能被篡改成非法 JSON，安全解析：失败当未登录，避免 store 初始化抛错导致白屏。
function loadUserInfo(): UserInfo | null {
  try {
    const raw = localStorage.getItem('userInfo')
    return raw ? (JSON.parse(raw) as UserInfo) : null
  } catch {
    return null
  }
}

// 第四篇 ch33：用户状态（token + 用户信息）。Pinia store。
export const useUserStore = defineStore('user', () => {
  const token = ref(localStorage.getItem('token') || '')
  const userInfo = ref<UserInfo | null>(loadUserInfo())

  async function login(username: string, password: string) {
    const data = await loginApi(username, password)
    token.value = data.token
    userInfo.value = data.user
    localStorage.setItem('token', data.token)
    localStorage.setItem('userInfo', JSON.stringify(data.user))
  }

  async function register(username: string, password: string) {
    // 注册成功后直接登录，省去用户再填一次
    await registerApi(username, password)
    await login(username, password)
  }

  function logout() {
    token.value = ''
    userInfo.value = null
    localStorage.removeItem('token')
    localStorage.removeItem('userInfo')
  }

  return { token, userInfo, login, register, logout }
})
