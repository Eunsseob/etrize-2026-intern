<script setup>
defineProps([
  "name",
  "phone",
  "email",
  "address",
  "location",
  "birth",
  "gender",
  "workType",
]);
</script>

<template>
  <table border="1" width="600px" align="center">
    <tbody>
      <tr>
        <td>
          <strong><label for="id">이름</label></strong>
        </td>
        <td>
          {{ name }}
        </td>
        <td>
          <strong><label for="birth">생년월일</label></strong>
        </td>
        <td>
          <input
            {{
            birth
            }}
            type="date"
            id="birth"
            name="birth"
            placeholder="생년월일을 입력하세요"
          />
        </td>
      </tr>
      <tr>
        <td>
          <strong><label for="gender">성별</label></strong>
        </td>
        <td>
          <select id="gender" name="gender">
            <option value="male">남성</option>
            <option value="female">여성</option>
          </select>
        </td>
        <td>
          <strong><label for="phone">휴대폰번호</label></strong>
        </td>
        <td>
          {{ phone }}
        </td>
      </tr>
      <tr>
        <td>
          <strong><label for="email">이메일</label></strong>
        </td>
        <td>
          <input
            {{
            email
            }}
            type="email"
            id="email"
            name="email"
            placeholder="이메일을 입력하세요"
          />
        </td>
        <td>
          <strong><label for="address">주소</label></strong>
        </td>
        <td>
          <input
            type="text"
            id="address"
            name="address"
            placeholder="주소를 입력하세요"
          />
        </td>
      </tr>
      <tr>
        <td>
          <strong><label for="location">희망근무지</label></strong>
        </td>
        <td>
          <select id="location" name="location">
            <option value="seoul">서울</option>
            <option value="busan">부산</option>
            <option value="daegu">대구</option>
          </select>
        </td>
        <td>
          <strong><label for="workType">근무형태</label></strong>
        </td>
        <td>
          <select id="workType" name="workType">
            <option value="full-time">정규직</option>
            <option value="part-time">파트타임</option>
            <option value="intern">인턴</option>
          </select>
        </td>
      </tr>
    </tbody>
  </table>
</template>
