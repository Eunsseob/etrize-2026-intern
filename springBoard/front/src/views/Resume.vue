<script setup>
import { ref, reactive, onMounted } from "vue";
import { getSession, userInfo } from "@/api/user";
import CareerTable from "../components/CareerTable.vue";
import CertificateTable from "../components/CertificateTable.vue";
import EducationTable from "../components/EducationTable.vue";
import RecruitTable from "../components/RecruitTable.vue";
import axios from "axios";

const name = ref("");
const phone = ref("");

onMounted(async () => {
  const res = await getSession();
  console.log("세션 데이터:", res.data);
  name.value = res.data.name;
  phone.value = res.data.phone;
});

onMounted(async () => {
  const res = await getSession();
  name.value = res.data.name;
  phone.value = res.data.phone;
});

const EducationTableData = reactive({
  header: ["재학기간", "구분", "학교명(소재지)", "전공", "학점"],
  rows: [
    {
      startPeriod: "2020-03",
      endPeriod: "2024-02",
      division: "졸업",
      schoolName: "경기대학교(서울)",
      major: "컴퓨터공학",
      grade: 3.5,
      id: 1,
    },
  ],
});

const educationChecked = ref([]);

const deleteEducation = () => {
  EducationTableData.rows = EducationTableData.rows.filter(
    (row) => !educationChecked.value.includes(row.id),
  );
  educationChecked.value = [];
};

const CareerTableData = reactive({
  header: ["근무기간", "회사명", "부서/직급/직책", "지역"],
  rows: [
    {
      startPeriod: "2020-01-01 ~ 2022-12-31",
      endPeriod: "ABC 회사",
      compName: "ABC 회사",
      task: "개발부 / 대리 / 프론트엔드 개발자",
      location: "서울",
    },
    {
      startPeriod: "2023-01-01 ~ 2023-12-31",
      endPeriod: "XYZ 회사",
      compName: "XYZ 회사",
      task: "마케팅부 / 사원 / 마케팅 전문가",
      location: "부산",
    },
  ],
});

const CertificateTableData = reactive({
  header: ["자격증명", "취득일", "발급기관"],
  keys: ["qualifiName", "acquDate", "organizeName"],
  rows: [
    {
      qualifiName: "홍길동",
      acquDate: "2020-01-01",
      organizeName: "ABC 기관",
    },
  ],
});

const addCertificate = () => {
  CertificateTableData.rows.push({
    id: nextCertId.value++,
    qualifiName: "",
    acquDate: "",
    organizeName: "",
  });
};
const nextId = ref(2);
const nextCertId = ref(2);
const nextEduId = ref(2);

const addEducation = () => {
  EducationTableData.rows.push({
    id: nextEduId.value++,
    startPeriod: "",
    endPeriod: "",
    division: "",
    schoolName: "",
    major: "",
    grade: "",
  });
};

const addCareer = () => {
  CareerTableData.rows.push({
    id: nextId.value++,
    startPeriod: "",
    endPeriod: "",
    compName: "",
    task: "",
    location: "",
  });
};
</script>

<template>
  <h1>입사 지원서</h1>

  <table border="4" align="center" width="800px" height="400px">
    <br />
    <tbody>
      <RecruitTable :name="name" :phone="phone" /><br />
      <h2 align="left"><strong>학력</strong></h2>
      <p align="right">
        <button @click="addEducation">추가</button>
        &emsp;
        <button @click="deleteEducation">삭제</button>
      </p>
      <br />
      <EducationTable
        :tableData="EducationTableData"
        :checked="educationChecked"
        @update:checked="educationChecked = $event"
      />
      <br />
      <h2 align="left"><strong>경력</strong></h2>

      <p align="right">
        <button @click="addCareer">추가</button>
        &emsp;
        <button @click="deleteCareer">삭제</button>
      </p>
      <br />

      <CareerTable :tableData="CareerTableData" /><br />
      <h2 align="left"><strong>자격증</strong></h2>

      <p align="right">
        <button @click="addCertificate">추가</button>
        &emsp;
        <button @click="deleteCertificate">삭제</button>
      </p>
      <CertificateTable
        :tableData="CertificateTableData"
        :deleteCertificate="deleteCertificate"
        :checked="certificateChecked"
        @update:checked="certificateChecked = $event"
      />
      <br />
    </tbody>
  </table>
  <br />
  <p>
    <button @click="addUser">저장</button>
    &emsp;
    <button @click="updateUser">제출</button>
  </p>
</template>
