<script setup>
defineProps({
  tableData: Object,
  checked: Array,
  readOnly: Boolean,
});
defineEmits(["update:checked"]);
</script>

<template>
  <table border="1" cellpadding="5" cellspacing="0" align="center">
    <tr>
      <th v-if="!readOnly"></th>
      <th>근무기간</th>
      <th>회사명</th>
      <th>부서/직급/직책</th>
      <th>지역</th>
    </tr>
    <tr v-for="row in tableData.rows" :key="row.carSeq">
      <td v-if="!readOnly">
        <input
          type="checkbox"
          :value="row.carSeq"
          :checked="checked.includes(row.carSeq)"
          @change="
            $emit(
              'update:checked',
              checked.includes(row.carSeq)
                ? checked.filter((id) => id !== row.carSeq)
                : [...checked, row.carSeq],
            )
          "
        />
      </td>
      <td>
        <span v-if="readOnly">{{ row.startPeriod }} ~ {{ row.endPeriod }}</span>
        <template v-else>
          <input type="month" v-model="row.startPeriod" />
          ~
          <input type="month" v-model="row.endPeriod" />
        </template>
      </td>
      <td>
        <span v-if="readOnly">{{ row.compName }}</span>
        <template v-else>
          <input type="text" v-model="row.compName" />
        </template>
      </td>
      <td>
        <span v-if="readOnly">{{ row.task }}</span>
        <template v-else>
          <input type="text" v-model="row.task" />
        </template>
      </td>
      <td>
        <span v-if="readOnly">{{ row.location }}</span>
        <template v-else>
          <input type="text" v-model="row.location" />
        </template>
      </td>
    </tr>
  </table>
</template>
