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
