package com.spring.career.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.career.dao.CareerDao;
import com.spring.career.service.careerService;
import com.spring.career.vo.CareerVo;
import com.spring.certificate.service.certificateService;
import com.spring.education.vo.EducationVo;
import com.spring.recruit.dao.RecruitDao;
import com.spring.recruit.service.recruitService;

@Service
public class careerServiceImpl implements careerService{
	
	@Autowired
	CareerDao careerDao;
	
	@Override
	public void insertCareerList(List<CareerVo> list) throws Exception{
		careerDao.insertCareerList(list);
	}
	
	@Override
	public void deleteCareer(String seq) throws Exception{
		careerDao.deleteCareer(seq);
	}
	
	@Override
	public List<CareerVo> getCareer(String seq) throws Exception{
		return careerDao.getCareer(seq);
	}
	
	@Transactional(rollbackFor = Exception.class)
    public void syncCareerList(String seq, List<CareerVo> uiList) throws Exception {
        // 1. 현재 DB에 저장된 전체 리스트를 가져옴
        List<CareerVo> dbList = careerDao.getCareer(seq);

        // 2. [INSERT & UPDATE] 화면(UI)에서 넘어온 리스트 처리
        if (uiList != null) {
            for (CareerVo uiCer : uiList) {
                // 이름이 없으면 빈 행으로 간주하고 건너뜀
                if (uiCer.getCompName() == null || uiCer.getCompName().trim().isEmpty()) continue;

                if (uiCer.getCarSeq() != null && !uiCer.getCarSeq().isEmpty()) {
                    // DB 리스트에서 같은 certSeq 찾기
                	CareerVo dbCer = null;
                    for (CareerVo d : dbList) {
                        if (d.getCarSeq().equals(uiCer.getCarSeq())) {
                            dbCer = d;
                            break;
                        }
                    }
                    // 다를 때만 UPDATE
                    if (dbCer != null && (
                        !uiCer.getCompName().equals(dbCer.getCompName()) ||
                        !uiCer.getLocation().equals(dbCer.getLocation()) ||
                        !uiCer.getEndPeriod().equals(dbCer.getEndPeriod()) ||
                        !uiCer.getTask().equals(dbCer.getTask()) ||
                        !uiCer.getStartPeriod().equals(dbCer.getStartPeriod())
                    )) {
                        careerDao.updateCareerList(uiCer);
                    }
                } else {
                    // [INSERT]
                    uiCer.setSeq(seq);
                    careerDao.insertCareer(uiCer);
                }
            }
        }

        // 3. [DELETE] DB에는 있는데 UI에는 없는 항목 삭제
        if (dbList != null) {
            for (CareerVo dbCer : dbList) {
                boolean isExist = false;
                if (uiList != null) {
                    for (CareerVo uiCer : uiList) {
                        // certSeq가 일치하는 것이 하나라도 있으면 삭제 대상 아님
                        if (uiCer.getCarSeq() != null && uiCer.getCarSeq().equals(dbCer.getCarSeq())) {
                            isExist = true;
                            break;
                        }
                    }
                }
                if (!isExist) {
                    // 화면에서 삭제된 항목이므로 DB에서 삭제
                	careerDao.deleteCareerByCarSeq(dbCer);
                }
            }
        }
    }
}
