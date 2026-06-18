import request from './request'

// 第四篇 ch32：认证相关接口
// 登录成功后端返回 { token, user: { id, username } }（已被 request 拦截器拆出 data 层）。
export interface LoginResult {
  token: string
  user: { id: number; username: string }
}

export function login(username: string, password: string): Promise<LoginResult> {
  // request 拦截器把 res.data 透出，但 axios 的 TS 类型仍是 AxiosResponse，故用断言还原真实返回类型
  return request.post('/auth/login', { username, password }) as unknown as Promise<LoginResult>
}

export function register(username: string, password: string): Promise<void> {
  return request.post('/auth/register', { username, password }) as unknown as Promise<void>
}
