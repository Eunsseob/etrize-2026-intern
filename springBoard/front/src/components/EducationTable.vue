<script setup>
const props = defineProps({
  tableData: Object,
  checked: Array,
  readOnly: Boolean,
});
defineEmits(["update:checked"]);

const validate = () => {
  for (let i = 0; i < props.tableData.rows.length; i++) {
    const row = props.tableData.rows[i];
    const fields = [
      {
        key: "startPeriod",
        msg: "재학 시작기간을 입력하세요",
        selector: `.edu-start-${i}`,
      },
      {
        key: "endPeriod",
        msg: "재학 종료기간을 입력하세요",
        selector: `.edu-end-${i}`,
      },
      {
        key: "schoolName",
        msg: "학교명을 입력하세요",
        selector: `.edu-school-${i}`,
      },
      { key: "major", msg: "전공을 입력하세요", selector: `.edu-major-${i}` },
      { key: "grade", msg: "학점을 입력하세요", selector: `.edu-grade-${i}` },
    ];

    for (const field of fields) {
      if (!row[field.key]) {
        alert(field.msg);
        document.querySelector(field.selector)?.focus();
        return false;
      }
    }
  }
  return true;
};

defineExpose({ validate });
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
      <th v-if="!readOnly"></th>
      <th>재학기간</th>
      <th>구분</th>
      <th>학교명(소재지)</th>
      <th>전공</th>
      <th>학점</th>
    </tr>
    <tr v-for="(row, index) in tableData.rows" :key="index">
      <td v-if="!readOnly">
        <input
          type="checkbox"
          :value="row.eduSeq"
          :checked="checked.includes(row.eduSeq)"
          @change="
            $emit(
              'update:checked',
              checked.includes(row.eduSeq)
                ? checked.filter((id) => id !== row.eduSeq)
                : [...checked, row.eduSeq],
            )
          "
        />
      </td>
      <td>
        <span v-if="readOnly">{{ row.startPeriod }} ~ {{ row.endPeriod }}</span>
        <template v-else>
          <input
            type="month"
            :class="`edu-start-${index}`"
            v-model="row.startPeriod"
          />
          ~
          <input
            type="month"
            :class="`edu-end-${index}`"
            v-model="row.endPeriod"
          />
        </template>
      </td>
      <td>
        <span v-if="readOnly">{{ row.division }}</span>
        <select v-else v-model="row.division">
          <option value="재학">재학</option>
          <option value="중퇴">중퇴</option>
          <option value="졸업">졸업</option>
        </select>
      </td>
      <td>
        <span v-if="readOnly">{{ row.schoolName }} ({{ row.location }})</span>
        <template v-else>
          <input
            type="text"
            :class="`edu-school-${index}`"
            v-model="row.schoolName"
          />
          <br />
          <select v-model="row.location">
            <option value="서울">서울</option>
            <option value="경기">경기</option>
            <option value="지방">지방</option>
          </select>
        </template>
      </td>
      <td>
        <span v-if="readOnly">{{ row.major }}</span>
        <input
          v-else
          type="text"
          :class="`edu-major-${index}`"
          v-model="row.major"
        />
      </td>
      <td>
        <span v-if="readOnly">{{ row.grade }}</span>
        <input
          v-else
          type="number"
          :class="`edu-grade-${index}`"
          v-model="row.grade"
          step="0.1"
        />
      </td>
    </tr>
  </table>
</template>
