<script setup>
import { ref, defineExpose } from "vue";

const emailRef = ref(null);
const birthRef = ref(null);
const addrRef = ref(null);

const props = defineProps({
  tableData: Object,
  readOnly: Boolean,
});

const validate = () => {
  if (!props.tableData.birth) {
    alert("생년월일을 입력하세요");
    birthRef.value.focus();
    return false;
  }
  if (!props.tableData.email) {
    alert("이메일을 입력하세요");
    emailRef.value.focus();
    return false;
  }
  if (!props.tableData.addr) {
    alert("주소를 입력하세요");
    addrRef.value.focus();
    return false;
  }
  return true;
};

defineExpose({ validate });
</script>

<template>
  <table
    border="1"
    cellpadding="4"
    cellspacing="0"
    width="600px"
    align="center"
  >
    <tr>
      <td>
        <strong><label>이름</label></strong>
      </td>
      <td>{{ tableData.name }}</td>
      <td>
        <strong><label for="birth">생년월일</label></strong>
      </td>
      <td>
        <span v-if="readOnly">{{ tableData.birth }}</span>
        <input
          v-else
          type="date"
          ref="birthRef"
          id="birth"
          name="birth"
          v-model="tableData.birth"
        />
      </td>
    </tr>
    <tr>
      <td>
        <strong><label for="gender">성별</label></strong>
      </td>
      <td>
        <span v-if="readOnly">{{ tableData.gender }}</span>
        <select
          v-else
          id="gender"
          name="gender"
          v-model="tableData.gender"
          :disabled="readOnly"
        >
          <option value="남자">남자</option>
          <option value="여자">여자</option>
        </select>
      </td>
      <td>
        <strong><label>휴대폰번호</label></strong>
      </td>
      <td>{{ tableData.phone }}</td>
    </tr>
    <tr>
      <td>
        <strong><label for="email">이메일</label></strong>
      </td>
      <td>
        <span v-if="readOnly">{{ tableData.email }}</span>
        <input
          v-else
          type="email"
          id="email"
          ref="emailRef"
          v-model="tableData.email"
          :disabled="readOnly"
        />
      </td>
      <td>
        <strong><label for="address">주소</label></strong>
      </td>
      <td>
        <span v-if="readOnly">{{ tableData.addr }}</span>
        <input
          v-else
          type="text"
          id="address"
          ref="addrRef"
          v-model="tableData.addr"
          :disabled="readOnly"
        />
      </td>
    </tr>
    <tr>
      <td>
        <strong><label for="location">희망근무지</label></strong>
      </td>
      <td>
        <span v-if="readOnly">{{ tableData.location }}</span>
        <select
          v-else
          id="location"
          v-model="tableData.location"
          :disabled="readOnly"
          selected="서울"
        >
          <option value="서울" selected>서울</option>
          <option value="부산">부산</option>
          <option value="대구">대구</option>
        </select>
      </td>
      <td>
        <strong><label for="workType">근무형태</label></strong>
      </td>
      <td>
        <span v-if="readOnly">{{ tableData.workType }}</span>
        <select
          v-else
          id="workType"
          v-model="tableData.workType"
          :disabled="readOnly"
        >
          <option value="정규직">정규직</option>
          <option value="파트타임">파트타임</option>
          <option value="인턴">인턴</option>
        </select>
      </td>
    </tr>
  </table>
</template>
