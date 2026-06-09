<script setup>
defineProps({
  tableData: Object,
  checked: Array,
});
defineEmits(["update:checked"]);
</script>

<template>
  <table border="1" cellpadding="5" cellspacing="0" align="center">
    <tr>
      <th></th>
      <th>근무기간</th>
      <th>회사명</th>
      <th>부서/직급/직책</th>
      <th>지역</th>
    </tr>
    <tr v-for="row in tableData.rows" :key="row.id">
      <td>
        <input type="checkbox" />
      </td>
      <td>
        <input type="month" v-model="row.startPeriod" />
        ~
        <input type="month" v-model="row.endPeriod" />
      </td>
      <td><input type="text" v-model="row.회사명" /></td>
      <td><input type="text" v-model="row.부서직급직책" /></td>
      <td><input type="text" v-model="row.지역" /></td>
    </tr>
  </table>
</template>
