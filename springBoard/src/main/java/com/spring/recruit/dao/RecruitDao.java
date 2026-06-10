package com.spring.recruit.dao;

import java.util.List;

import com.spring.recruit.vo.RecruitVo;

public interface RecruitDao {

	// 회원가입 메소드
	public int userSignup(RecruitVo recruitVo) throws Exception;

	//로그인 메소드
	public RecruitVo userLogin(RecruitVo recruitVo) throws Exception;

	// 성공여부 int 값으로 가져오기
	public int updateRecruit(RecruitVo recruitVo) throws Exception;
	
	public RecruitVo getRecruit(String seq) throws Exception;

	public int submitRecruit(RecruitVo recruitVo) throws Exception;

	public int phoneCheck(RecruitVo recruitVo) throws Exception;

	public int mergeUser(RecruitVo recruitVo) throws Exception;
}
