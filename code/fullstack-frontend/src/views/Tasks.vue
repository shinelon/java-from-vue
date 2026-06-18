<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { getTasks, createTask, deleteTask, updateTask, type Task } from '../api/task'

// 第四篇 ch33：任务列表页（对接后端 CRUD）
const tasks = ref<Task[]>([])
const newTitle = ref('')
const loading = ref(false)

// 任务状态文案：0 待办 / 1 进行中 / 2 已完成（和后端 t_task.status 一致）
const statusText: Record<number, string> = { 0: '待办', 1: '进行中', 2: '已完成' }

// 状态切换：0 待办 → 1 进行中 → 2 已完成 → 0 待办
const nextStatus = (s: number): number => (s + 1) % 3

async function load() {
  loading.value = true
  try {
    const page = await getTasks({ current: 1, size: 20 })
    tasks.value = page.records
  } catch {
    // 失败提示已由 request 拦截器统一 alert
  } finally {
    loading.value = false
  }
}

async function add() {
  if (!newTitle.value.trim()) return
  try {
    await createTask({ title: newTitle.value })
    newTitle.value = ''
    await load()
  } catch {
    // 同上
  }
}

async function toggle(t: Task) {
  try {
    await updateTask(t.id, { title: t.title, status: nextStatus(t.status) })
    await load()
  } catch {
    // 同上
  }
}

async function remove(id: number) {
  if (!confirm('确定删除这个任务吗？')) return
  try {
    await deleteTask(id)
    await load()
  } catch {
    // 同上
  }
}

onMounted(load)
</script>

<template>
  <div class="tasks">
    <h2>我的任务</h2>
    <div class="add">
      <input v-model="newTitle" placeholder="新任务标题" :disabled="loading" @keyup.enter="add" />
      <button @click="add" :disabled="loading || !newTitle.trim()">添加</button>
    </div>
    <p v-if="loading && !tasks.length" class="hint">加载中...</p>
    <p v-else-if="!tasks.length" class="hint">暂无任务，添加第一个吧</p>
    <ul v-else>
      <li v-for="t in tasks" :key="t.id">
        <span class="title">{{ t.title }}</span>
        <span class="status">{{ statusText[t.status] ?? '未知' }}</span>
        <button @click="toggle(t)" :disabled="loading">切换状态</button>
        <button @click="remove(t.id)">删除</button>
      </li>
    </ul>
  </div>
</template>

<style scoped>
.add {
  display: flex;
  gap: 8px;
  margin-bottom: 16px;
}
.add input {
  flex: 1;
}
.hint {
  color: #888;
}
li {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 8px 0;
  border-bottom: 1px solid #eee;
}
.title {
  flex: 1;
}
.status {
  font-size: 12px;
  color: #1976d2;
}
button {
  padding: 4px 8px;
}
</style>
