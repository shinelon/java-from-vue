<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '../stores/user'

// 第四篇 ch33：登录页（登录/注册切换）
const username = ref('')
const password = ref('')
const loading = ref(false)
const isRegister = ref(false)
const router = useRouter()
const userStore = useUserStore()

async function submit() {
  if (!username.value.trim() || !password.value.trim()) {
    alert('用户名和密码不能为空')
    return
  }
  loading.value = true
  try {
    if (isRegister.value) {
      await userStore.register(username.value, password.value)
    } else {
      await userStore.login(username.value, password.value)
    }
    router.push('/')
  } catch {
    // 失败提示已由 request 拦截器统一 alert，这里只负责恢复按钮状态
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <div class="login">
    <h2>{{ isRegister ? '注册' : '登录' }}</h2>
    <input v-model="username" placeholder="用户名" :disabled="loading" />
    <input v-model="password" type="password" placeholder="密码" :disabled="loading" />
    <button @click="submit" :disabled="loading">
      {{ loading ? '处理中...' : (isRegister ? '注册' : '登录') }}
    </button>
    <a href="javascript:void(0)" class="toggle" @click="isRegister = !isRegister">
      {{ isRegister ? '已有账号？去登录' : '没有账号？去注册' }}
    </a>
  </div>
</template>

<style scoped>
.login {
  max-width: 320px;
  margin: 60px auto;
}
input {
  display: block;
  width: 100%;
  box-sizing: border-box;
  margin-bottom: 8px;
  padding: 8px;
}
button {
  width: 100%;
  padding: 8px;
}
.toggle {
  display: inline-block;
  margin-top: 12px;
  font-size: 14px;
}
</style>
