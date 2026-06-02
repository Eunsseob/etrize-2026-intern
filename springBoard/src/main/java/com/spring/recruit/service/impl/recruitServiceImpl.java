package com.spring.recruit.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.career.service.careerService;
import com.spring.career.vo.CareerVo;
import com.spring.certificate.service.certificateService;
import com.spring.certificate.vo.CertificateVo;
import com.spring.education.service.educationService;
import com.spring.education.vo.EducationVo;
import com.spring.recruit.dao.RecruitDao;
import com.spring.recruit.service.recruitService;
import com.spring.recruit.vo.RecruitVo;
import com.spring.user.vo.UserVo;

@Service
public class recruitServiceImpl implements recruitService{
	
	@Autowired
	private educationService educationService;
	
	@Autowired
	private careerService careerService;
	
	@Autowired
	private certificateService certificateService;
	
	@Autowired
	RecruitDao recruitDao;
	
	private String certSeq;
	
	// 회원 가입하기
	@Override
	public int userSignup(RecruitVo recruitVo) throws Exception {
		return recruitDao.userSignup(recruitVo);  // 수정된 데이터 넘기기
	}
	
	@Override
	public int phoneCheck(RecruitVo recruitVo) throws Exception {
		return recruitDao.phoneCheck(recruitVo);  // 수정된 데이터 넘기기
	}
	
	// 로그인 하기
	@Override
	public RecruitVo userLogin(RecruitVo recruitVo) throws Exception {
	    return recruitDao.userLogin(recruitVo);  // 수정된 데이터 넘기기
	}
	
	@Override
	public int updateRecruit(RecruitVo recruitVo) throws Exception {
	    return recruitDao.updateRecruit(recruitVo);
	}

	// 저장된거 가져오기
	@Override
	public RecruitVo getRecruit(String seq) throws Exception {
	    return recruitDao.getRecruit(seq);
	}
	
	@Override
	public void submitRecruit(String seq) throws Exception {
	    recruitDao.submitRecruit(seq);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void updateAllRecruitInfo(String seq, RecruitVo recruitVo) throws Exception {
	    // 1. 기존 데이터 삭제
	    educationService.deleteEducation(seq);
	    careerService.deleteCareer(seq);
	    certificateService.deleteCertificate(seq);
	    recruitDao.updateRecruit(recruitVo);
	    // 2. 학력 필터링 및 등록
	    if(recruitVo.getEducationList() != null) {
	        List<EducationVo> validEduList = new ArrayList<>();
	        for(EducationVo edu : recruitVo.getEducationList()) {
	            // 학교명이나 전공 등 필수 항목이 비어있지 않은 경우만 추가
	            if(edu.getSchoolName() != null && !edu.getSchoolName().trim().isEmpty()) {
	                edu.setSeq(seq);
	                validEduList.add(edu);
	            }
	        }
	        if(!validEduList.isEmpty()) educationService.insertEducationList(validEduList);
	    }
	    
	    // 3. 경력 필터링 및 등록
	    if(recruitVo.getCareerList() != null) {
	        List<CareerVo> validCarList = new ArrayList<>();
	        for(CareerVo car : recruitVo.getCareerList()) {
	            // 회사명이 비어있지 않은 경우만 추가
	            if(car.getCompName() != null && !car.getCompName().trim().isEmpty()) {
	                car.setSeq(seq);
	                validCarList.add(car);
	            }
	        }
	        if(!validCarList.isEmpty()) careerService.insertCareerList(validCarList);
	    }
	    
	    // 4. 자격증 필터링 및 등록
	    if(recruitVo.getCertificateList() != null) {
	        List<CertificateVo> validCerList = new ArrayList<>();
	        for(CertificateVo cer : recruitVo.getCertificateList()) {
	            // 자격증명이 비어있지 않은 경우만 추가
	            if(cer.getQualifiName() != null && !cer.getQualifiName().trim().isEmpty()) {
	                cer.setSeq(seq);
	                validCerList.add(cer);
	            }
	        }
	        if(!validCerList.isEmpty()) certificateService.insertCertificateList(validCerList);
	    }
	}
}
