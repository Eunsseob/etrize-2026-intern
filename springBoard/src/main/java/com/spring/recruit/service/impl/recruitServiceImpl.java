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
public class recruitServiceImpl implements recruitService {

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
		return recruitDao.userLogin(recruitVo); // 수정된 데이터 넘기기
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

		recruitDao.updateRecruit(recruitVo);

		// 교육 리스트
		educationService.syncEducationList(seq, recruitVo.getEducationList());

		// 커리어 리스트
		careerService.syncCareerList(seq, recruitVo.getCareerList());

		// 자격증 → MERGE 방식으로 교체
		certificateService.syncCertificateList(seq, recruitVo.getCertificateList());
	}
}
