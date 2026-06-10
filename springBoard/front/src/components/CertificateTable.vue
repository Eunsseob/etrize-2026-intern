<script setup>
defineProps({
  tableData: Object,
  checked: Array,
  readOnly: Boolean,
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
      <th v-if="!readOnly"></th>
      <th>자격증명</th>
      <th>취득일</th>
      <th>발급기관</th>
    </tr>
    <tr v-for="row in tableData.rows" :key="row.certSeq">
      <td v-if="!readOnly">
        <input
          type="checkbox"
          :value="row.certSeq"
          :checked="checked.includes(row.certSeq)"
          @change="
            $emit(
              'update:checked',
              checked.includes(row.certSeq)
                ? checked.filter((id) => id !== row.certSeq)
                : [...checked, row.certSeq],
            )
          "
        />
      </td>
      <td>
        <span v-if="readOnly">{{ row.qualifiName }}</span>
        <input
          v-else
          type="text"
          v-model="row.qualifiName"
          placeholder="자격증명"
        />
      </td>
      <td>
        <span v-if="readOnly">{{ row.acquDate }}</span>
        <input v-else type="month" v-model="row.acquDate" />
      </td>
      <td>
        <span v-if="readOnly">{{ row.organizeName }}</span>
        <input
          v-else
          type="text"
          v-model="row.organizeName"
          placeholder="발급기관"
        />
      </td>
    </tr>
  </table>
</template>
