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
    cellpadding="4"
    cellspacing="0"
    align="center"
    width="600px"
  >
    <tr>
      <th></th>
      <th>자격증명</th>
      <th>취득일</th>
      <th>발급기관</th>
    </tr>
    <tr v-for="row in tableData.rows" :key="row.id">
      <td>
        <input type="checkbox" />
      </td>
      <td>
        <input type="text" v-model="row.qualifiName" placeholder="자격증명" />
      </td>
      <td><input type="date" v-model="row.acquDate" /></td>
      <td>
        <input type="text" v-model="row.organizeName" placeholder="발급기관" />
      </td>
    </tr>
  </table>
</template>
