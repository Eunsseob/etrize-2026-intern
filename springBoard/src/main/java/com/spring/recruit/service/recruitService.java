package com.spring.recruit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.career.vo.CareerVo;
import com.spring.education.vo.EducationVo;
import com.spring.recruit.vo.RecruitVo;

@Service
public interface recruitService {

	// 회원가입 하는 메서드 
	public int userSignup(RecruitVo recruitVo) throws Exception;
	
	// 로그인 하는 메서드 
	public RecruitVo userLogin(RecruitVo recruitVo) throws Exception;

	// 이력서 저장 메서드
	public int updateRecruit(RecruitVo recruitVo) throws Exception;

	// 이력서 가져오는 메서드
	public RecruitVo getRecruit(String seq) throws Exception;

	// 이력서 제출 메서드
	public void submitRecruit(String seq) throws Exception;

	// 전화번호 검증 로직
	public int phoneCheck(RecruitVo recruitVo) throws Exception;
	
	public void updateAllRecruitInfo(String seq, RecruitVo recruitVo) throws Exception;

}
