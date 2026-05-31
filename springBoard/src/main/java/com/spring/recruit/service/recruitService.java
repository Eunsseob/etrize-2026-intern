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

	public int updateRecruit(RecruitVo recruitVo) throws Exception;

	public RecruitVo getRecruit(String seq) throws Exception;

	public void submitRecruit(String seq) throws Exception;
}
