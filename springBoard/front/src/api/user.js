import api from "@/lib/axios";

export const userSignup = (name, phone) => {
  return api.post(
    "/userSignup.do",
    { name, phone },
    {
      headers: { "Content-Type": "application/json" },
    },
  );
};

export const getSession = () => {
  return api.get("/getSession.do");
};

export const userLogin = (name, phone) => {
  return api.post(
    "/userLogin.do",
    { name, phone },
    {
      headers: { "Content-Type": "application/json" },
    },
  );
};

export const userInfo = () => {
  return api.get("recruit/main.do");
};

export const updateUserInfo = (userInfo) => {
  console.log("API 호출 데이터:", JSON.stringify(userInfo, null, 2));
  return api.post("recruit/userSignup.do", userInfo, {
    headers: { "Content-Type": "application/json" },
  });
};

export const submitUserInfo = (userInfo) => {
  console.log("API 호출 데이터:", JSON.stringify(userInfo, null, 2));
  return api.post("recruit/submitRecruit.do", userInfo, {
    headers: { "Content-Type": "application/json" },
  });
};
