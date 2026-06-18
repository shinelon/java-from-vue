/// <reference types="vite/client" />

// 让普通 tsc / IDE 也能识别 .vue 单文件组件（vue-tsc 原生支持，这里作兜底声明）
declare module '*.vue' {
  import type { DefineComponent } from 'vue'
  const component: DefineComponent<{}, {}, any>
  export default component
}
