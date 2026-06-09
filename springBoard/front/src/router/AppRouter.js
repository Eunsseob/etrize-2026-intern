import { createRouter, createWebHistory } from "vue-router";

const routes = [
  { path: "/", component: () => import("@/App.vue") },
  { path: "/login", component: () => import("@/views/Login.vue") },
  { path: "/resume", component: () => import("@/views/Resume.vue") },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
