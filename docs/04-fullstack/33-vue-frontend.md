# 用 Vue 3 实现前端

你已经会写 Vue，这一章就是把任务管理系统的前端搭出来——登录页 + 任务列表，对接你自己写的后端。

## 状态管理：Pinia store

token 和用户信息存 Pinia（+ localStorage 持久化）：

```typescript
--8<-- "fullstack-frontend/src/stores/user.ts"
```

`login` 调接口拿 token 存起来，`logout` 清掉。对标后端：前端存 token，后端签发/校验 token。

## 路由 + 守卫

```typescript
--8<-- "fullstack-frontend/src/router/index.ts"
```

`meta.requiresAuth` 标记需要登录的页面，全局守卫 `beforeEach` 检查 token。这就是前端的"鉴权拦截器"，和后端 `JwtInterceptor` 遥相呼应。

## 登录页

```html
--8<-- "fullstack-frontend/src/views/Login.vue"
```

支持**登录 / 注册切换**：调 `userStore.login` 或 `userStore.register`，成功后跳首页；注册成功会自动登录。空字段校验在前端拦一道，失败提示由 `request` 拦截器统一 `alert`，这里只管按钮 loading 状态。

## 任务列表页

```html
--8<-- "fullstack-frontend/src/views/Tasks.vue"
```

`onMounted` 加载列表、`add` 新建、`toggle` 切换状态、`remove` 删除——全是调第 32 章封装的 task api。注意 `getTasks` 返回的 `page.records` 就是当前页任务数组（后端 MyBatis-Plus 分页的返回结构）。

## 跑起来

```bash
cd code/fullstack-frontend
npm install
npm run dev        # 开 http://localhost:5173
```

同时后端跑着（`mvn spring-boot:run`，8080）。打开 5173，注册 → 登录 → 加任务，**前后端跑通闭环**。

## 目录结构

```
fullstack-frontend/
├── vite.config.ts          # 开发代理
├── src/
│   ├── main.ts             # 入口
│   ├── App.vue
│   ├── api/
│   │   ├── request.ts      # axios 封装（第 32 章）
│   │   ├── auth.ts
│   │   └── task.ts
│   ├── stores/user.ts      # Pinia
│   ├── router/index.ts     # 路由 + 守卫
│   └── views/
│       ├── Login.vue
│       └── Tasks.vue
```

这就是一个最小但完整的 Vue 3 全栈前端。真实项目再往上叠 UI 库（Element Plus）、更多页面、表单校验等。

---

[:octicons-arrow-left-16: 上一章：前后端联调总览](32-integration-overview.md) ｜ 下一章：联调常见坑
