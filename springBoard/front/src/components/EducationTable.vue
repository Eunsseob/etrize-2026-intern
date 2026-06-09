<script setup>
defineProps({
  tableData: Object,
  checked: Array,
});
defineEmits(["update:checked"]);
</script>

<template>
  <table
    border="1"
    cellpadding="6"
    cellspacing="0"
    width="600px"
    align="center"
  >
    <tr>
      <th></th>
      <th>재학기간</th>
      <th>구분</th>
      <th>학교명(소재지)</th>
      <th>전공</th>
      <th>학점</th>
    </tr>
    <tr v-for="row in tableData.rows" :key="row.id">
      <td>
        <input
          type="checkbox"
          :value="row.id"
          :checked="checked.includes(row.id)"
          @change="
            (e) =>
              e.target.checked
                ? $emit('update:checked', [...checked, row.id])
                : $emit(
                    'update:checked',
                    checked.filter((id) => id !== row.id),
                  )
          "
        />
      </td>
      <td>
        <input type="month" v-model="row.startPeriod" /> ~
        <input type="month" v-model="row.endPeriod" />
      </td>
      <td>
        <select v-model="row.division">
          <option value="재학">재학</option>
          <option value="중퇴">중퇴</option>
          <option value="졸업">졸업</option>
        </select>
      </td>
      <td><input type="text" v-model="row.schoolName" /></td>
      <td><input type="text" v-model="row.major" /></td>
      <td><input type="number" v-model="row.grade" step="0.1" /></td>
    </tr>
  </table>
</template>
