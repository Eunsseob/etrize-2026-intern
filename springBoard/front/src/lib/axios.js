import axios from "axios";

const api = axios.create({
  baseURL: "https://etrize-2026-intern-1.onrender.com/",
  withCredentials: true, // 쿠키 사용 시 필요
});
AbortSignal;
api.interceptors.request.use((config) => {
  const token = localStorage.getItem("access_token");
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

export default api;
