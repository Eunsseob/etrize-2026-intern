package com.spring.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.career.vo.CareerVo;
import com.spring.education.dao.EducationDao;
import com.spring.education.service.educationService;
import com.spring.education.vo.EducationVo;
import com.spring.recruit.dao.RecruitDao;
import com.spring.recruit.service.recruitService;
import com.spring.recruit.vo.RecruitVo;

@Service
public class educationServiceImpl implements educationService{
	
	@Autowired
	EducationDao educationDao;
	
	@Override
	public void insertEducationList(List<EducationVo> list) throws Exception{
		educationDao.insertEducationList(list);
	}
	
	@Override
	public void deleteEducation(String seq) throws Exception{
		educationDao.deleteEducation(seq);
	}
	
	@Override
	public List<EducationVo> getEducation(String seq) throws Exception {
		return educationDao.getEducation(seq);
	}
	
	@Transactional(rollbackFor = Exception.class)
    public void syncEducationList(String seq, List<EducationVo> uiList) throws Exception {
        // 현재 DB에 저장된 전체 리스트를 가져옴
        List<EducationVo> dbList = educationDao.getEducation(seq);

        // [INSERT & UPDATE] 화면(UI)에서 넘어온 리스트 처리
        if (uiList != null) {
            for (EducationVo uiCer : uiList) {
                // 이름이 없으면 빈 행으로 간주하고 건너뛰기
                if (uiCer.getMajor() == null || uiCer.getMajor().trim().isEmpty()) continue;

                if (uiCer.getEduSeq() != null && !uiCer.getEduSeq().isEmpty()) {
                    // DB 리스트에서 같은 certSeq 찾기
                	EducationVo dbCer = null;
                    for (EducationVo d : dbList) {
                        if (d.getEduSeq().equals(uiCer.getEduSeq())) {
                            dbCer = d;
                            break;
                        }
                    }
                    // 다를 때만 UPDATE
                    if (dbCer != null && (
                        !uiCer.getSchoolName().equals(dbCer.getSchoolName()) ||
                        !uiCer.getLocation().equals(dbCer.getLocation()) ||
                        !uiCer.getEndPeriod().equals(dbCer.getEndPeriod()) ||
                        !uiCer.getDivision().equals(dbCer.getDivision()) ||
                        !uiCer.getMajor().equals(dbCer.getMajor()) ||
                        !uiCer.getGrade().equals(dbCer.getGrade()) ||
                        !uiCer.getStartPeriod().equals(dbCer.getStartPeriod())
                    )) {
                    	educationDao.updateEducationList(uiCer);
                    }
                } else {
                    // INSERT
                    uiCer.setSeq(seq);
                    educationDao.insertEducation(uiCer);
                }
            }
        }

        // [DELETE] DB에는 있는데 UI에는 없는 항목 삭제
        if (dbList != null) {
            for (EducationVo dbCer : dbList) {
                boolean isExist = false;
                if (uiList != null) {
                    for (EducationVo uiCer : uiList) {
                        // certSeq가 일치하는 것이 하나라도 있으면 삭제 대상 아님
                        if (uiCer.getEduSeq() != null && uiCer.getEduSeq().equals(dbCer.getEduSeq())) {
                            isExist = true;
                            break;
                        }
                    }
                }
                if (!isExist) {
                    // 화면에서 삭제된 항목이므로 DB에서 삭제
                	educationDao.deleteEducationByEduSeq(dbCer);
                }
            }
        }
    }
}