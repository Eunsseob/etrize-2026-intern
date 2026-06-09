import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './router/AppRouter.js'

// 라우터 쓰는 방법
createApp(App).use(router).mount('#app')
