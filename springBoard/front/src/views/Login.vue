<script setup>
import { ref, reactive, onMounted } from "vue";
import { userSignup, userLogin } from "@/api/user";
import { useRouter } from "vue-router";

const router = useRouter();
const name = ref("");
const phone = ref("");

const nameRef = ref(null);
const phoneRef = ref(null);

const autoHyphen = (e) => {
  e.target.value = e.target.value
    .replace(/[^0-9]/g, "")
    .replace(/^(\d{2,3})(\d{3,4})(\d{4})$/g, "$1-$2-$3")
    .replace(/(\-{1,2})$/g, "");
  phone.value = e.target.value;
};

const signup = async () => {
  if (!name.value) {
    alert("이름을 입력하세요");
    nameRef.value.focus();
    return;
  }
  if (!phone.value) {
    alert("휴대폰번호를 입력하세요");
    phoneRef.value.focus();
    return;
  }
  if (phone.value.length !== 13) {
    alert("휴대폰 번호 11자리를 입력하세요");
    phoneRef.value.focus();
    return;
  }
  try {
    const res = await userLogin(name.value, phone.value);
    if (res.data == "success") {
      router.push("/resume");
    } else if (res.data === "fail") {
      const res2 = await userSignup(name.value, phone.value);
      if (res2.data === "signup_success") {
        router.push("/resume");
      } else {
        alert("해당 전화번호는 이미 등록되어 있습니다.");
      }
    }
  } catch (err) {
    console.error("실패:", err);
  }
};
</script>

<template>
  <h1>입사지원 로그인</h1>

  <table border="1" width="400px" height="20px" align="center">
    <tbody>
      <tr>
        <td>
          <strong><label for="id">이름</label></strong>
        </td>
        <td>
          <input
            type="text"
            ref="nameref"
            v-model="name"
            placeholder="이름을 입력하세요"
          />
        </td>
      </tr>
      <tr>
        <td>
          <strong><label for="phone">휴대폰번호</label></strong>
        </td>
        <td>
          <input
            type="tel"
            ref="phoneref"
            name="phone"
            v-model="phone"
            placeholder="휴대폰번호를 입력하세요"
            @input="autoHyphen"
            maxlength="13"
          />
        </td>
      </tr>
      <tr>
        <td colspan="2" align="center">
          <button @click="signup">입사지원</button>
        </td>
      </tr>
    </tbody>
  </table>
</template>
