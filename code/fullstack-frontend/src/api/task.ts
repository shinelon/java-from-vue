import request from './request'

// 第四篇 ch33：任务 CRUD 接口
export interface TaskPage {
  records: Task[]
  total: number
  current: number
  size: number
}

export interface Task {
  id: number
  title: string
  description?: string
  status: number
}

export function getTasks(params: { current?: number; size?: number; status?: number }): Promise<TaskPage> {
  // request 拦截器把 res.data 透出，但 axios 的 TS 类型仍是 AxiosResponse，故用断言还原真实返回类型
  return request.get('/tasks', { params }) as unknown as Promise<TaskPage>
}

export function createTask(data: { title: string; description?: string; status?: number }): Promise<Task> {
  return request.post('/tasks', data) as unknown as Promise<Task>
}

export function updateTask(id: number, data: { title: string; description?: string; status?: number }): Promise<Task> {
  return request.put(`/tasks/${id}`, data) as unknown as Promise<Task>
}

export function deleteTask(id: number): Promise<void> {
  return request.delete(`/tasks/${id}`) as unknown as Promise<void>
}
