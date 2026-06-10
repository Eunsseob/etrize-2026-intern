<script setup>
import { ref, reactive, onMounted, computed } from "vue";
import {
  getSession,
  userInfo,
  updateUserInfo,
  submitUserInfo,
} from "@/api/user";
import CareerTable from "../components/CareerTable.vue";
import CertificateTable from "../components/CertificateTable.vue";
import EducationTable from "../components/EducationTable.vue";
import RecruitTable from "../components/RecruitTable.vue";
import axios from "axios";

const name = ref("");
const phone = ref("");
const recruit = ref(null);
const educationList = ref([]);
const careerList = ref([]);
const certificateList = ref([]);
const submitStatus = computed(() => recruit.value?.submit);

onMounted(async () => {
  const res = await getSession();
  console.log("세션 데이터:", res.data);
  name.value = res.data.name;
  phone.value = res.data.phone;

  const infoRes = await userInfo();
  console.log("유저 인포:", infoRes.data);

  recruit.value = infoRes.data.recruit;
  if (!recruit.value.location) recruit.value.location = "서울";
  if (!recruit.value.gender) recruit.value.gender = "남자";
  if (!recruit.value.workType) recruit.value.workType = "정규직";

  educationList.value = infoRes.data.educationList;
  if (educationList.value.length === 0) {
    educationList.value.push({
      eduSeq: null,
      startPeriod: "",
      endPeriod: "",
      division: "재학",
      location: "서울",
      schoolName: "",
      major: "",
      grade: "",
    });
  }

  careerList.value = infoRes.data.careerList;
  if (careerList.value.length === 0) {
    careerList.value.push({
      carSeq: null,
      startPeriod: "",
      endPeriod: "",
      compName: "",
      task: "",
      location: "",
    });
  }

  certificateList.value = infoRes.data.certificateList;
  if (certificateList.value.length === 0) {
    certificateList.value.push({
      certSeq: null,
      qualifiName: "",
      acquDate: "",
      organizeName: "",
    });
  }
});

const EducationTableData = reactive({
  header: ["재학기간", "구분", "학교명(소재지)", "전공", "학점"],
  rows: [
    {
      startPeriod: "",
      endPeriod: "",
      division: "",
      schoolName: "",
      location: "",
      major: "",
      grade: 0,
      id: null,
    },
  ],
});

const educationData = reactive({
  startPeriod: "",
  endPeriod: "",
  division: "",
  schoolName: "",
  major: "",
  grade: 0,
  id: null,
});

const educationChecked = ref([]);
const careerChecked = ref([]);
const certificateChecked = ref([]);

const deleteEducation = () => {
  const filtered = (educationList.value = educationList.value.filter(
    (edu) => !educationChecked.value.includes(edu.eduSeq),
  ));

  if (filtered.length === 0) {
    educationList.value = [
      {
        eduSeq: null,
        startPeriod: "",
        endPeriod: "",
        division: "재학",
        location: "서울",
        schoolName: "",
        major: "",
        grade: "",
      },
    ];
  } else {
    educationList.value = filtered;
  }

  educationChecked.value = [];
};

const deleteCareer = () => {
  const filtered = (careerList.value = careerList.value.filter(
    (car) => !careerChecked.value.includes(car.carSeq),
  ));

  if (filtered.length === 0) {
    careerList.value = [
      {
        carSeq: null,
        startPeriod: "",
        endPeriod: "",
        compName: "",
        task: "",
        location: "",
      },
    ];
  } else {
    careerList.value = filtered;
  }
  careerChecked.value = [];
};

const deleteCertificate = () => {
  const filtered = (certificateList.value = certificateList.value.filter(
    (cert) => !certificateChecked.value.includes(cert.certSeq),
  ));

  if (filtered.length === 0) {
    certificateList.value = [
      {
        certSeq: null,
        qualifiName: "",
        acquDate: "",
        organizeName: "",
      },
    ];
  } else {
    certificateList.value = filtered;
  }
  certificateChecked.value = [];
};

const CareerTableData = reactive({
  header: ["근무기간", "회사명", "부서/직급/직책", "지역"],
  rows: [
    {
      startPeriod: "",
      endPeriod: "",
      compName: "",
      task: "",
      location: "",
    },
  ],
});

const CertificateTableData = reactive({
  header: ["자격증명", "취득일", "발급기관"],
  rows: [
    {
      qualifiName: "",
      acquDate: "",
      organizeName: "",
    },
  ],
});

const nextId = ref(2);
const nextCertId = ref(CertificateTableData.rows.length + 1);
const nextEduId = ref(2);

const addEducation = () => {
  educationList.value.push({
    startPeriod: "",
    endPeriod: "",
    division: "재학",
    schoolName: "",
    location: "서울",
    major: "",
    grade: "",
  });
};

const recruitTableRef = ref(null);
const educationTableRef = ref(null);

const postUser = async () => {
  if (!recruitTableRef.value.validate()) return;
  if (!educationTableRef.value.validate()) return;
  const userInfo = {
    name: recruit.value?.name,
    phone: recruit.value?.phone,
    email: recruit.value?.email,
    addr: recruit.value?.addr,
    location: recruit.value?.location,
    birth: recruit.value?.birth,
    gender: recruit.value?.gender,
    workType: recruit.value?.workType,
    educationList: educationList.value,
    careerList: careerList.value,
    certificateList: certificateList.value,
  };

  console.log("보내는 데이터:", JSON.stringify(userInfo, null, 2));

  try {
    const response = await updateUserInfo(userInfo);
    console.log("저장 성공:", response.data);
    alert("저장이 완료되었습니다.");
    location.reload();
  } catch (error) {
    console.error("저장 실패:", error);
  }
};

const submitUser = async () => {
  const userInfo = {
    name: recruit.value?.name,
    phone: recruit.value?.phone,
    email: recruit.value?.email,
    addr: recruit.value?.addr,
    location: recruit.value?.location,
    birth: recruit.value?.birth,
    gender: recruit.value?.gender,
    workType: recruit.value?.workType,
    educationList: educationList.value,
    careerList: careerList.value,
    certificateList: certificateList.value,
  };

  console.log("제출하는 데이터:", JSON.stringify(userInfo, null, 2));

  try {
    const response = await submitUserInfo(userInfo);
    console.log("제출 성공:", response.data);
    alert("제출이 완료되었습니다.");
    location.reload();
  } catch (error) {
    console.error("제출 실패:", error);
  }
};
const addCareer = () => {
  careerList.value.push({
    startPeriod: "",
    endPeriod: "",
    compName: "",
    task: "",
    location: "",
  });
};

const addCertificate = () => {
  certificateList.value.push({
    qualifiName: "",
    acquDate: "",
    organizeName: "",
  });
};
</script>

<template>
  <h1>입사 지원서</h1>

  <table border="4" align="center" width="800px" height="400px">
    <br />
    <tbody>
      <RecruitTable
        ref="recruitTableRef"
        v-if="recruit"
        :tableData="recruit"
        :readOnly="submitStatus === 'Y'"
      /><br />
      <h2 align="left"><strong>학력</strong></h2>
      <p v-if="submitStatus !== 'Y'" align="right">
        <button @click="addEducation">추가</button>
        &emsp;
        <button @click="deleteEducation">삭제</button>
      </p>
      <br />
      <EducationTable
        ref="educationTableRef"
        :tableData="{ header: EducationTableData.header, rows: educationList }"
        :checked="educationChecked"
        :readOnly="submitStatus === 'Y'"
        @update:checked="educationChecked = $event"
      />
      <br />
      <h2 align="left"><strong>경력</strong></h2>

      <p v-if="submitStatus !== 'Y'" align="right">
        <button @click="addCareer">추가</button>
        &emsp;
        <button @click="deleteCareer">삭제</button>
      </p>
      <br />

      <CareerTable
        :tableData="{ header: CareerTableData.header, rows: careerList }"
        :checked="careerChecked"
        :readOnly="submitStatus === 'Y'"
        @update:checked="careerChecked = $event"
      /><br />
      <h2 align="left"><strong>자격증</strong></h2>

      <p v-if="submitStatus !== 'Y'" align="right">
        <button @click="addCertificate">추가</button>
        &emsp;
        <button @click="deleteCertificate">삭제</button>
      </p>
      <br />
      <CertificateTable
        :tableData="{
          header: CertificateTableData.header,
          rows: certificateList,
        }"
        :checked="certificateChecked"
        :readOnly="submitStatus === 'Y'"
        @update:checked="certificateChecked = $event"
      />
      <br />
    </tbody>
  </table>
  <br />
  <p v-if="submitStatus !== 'Y'">
    <button @click="postUser">저장</button>
    &emsp;
    <button @click="submitUser">제출</button>
  </p>
</template>
