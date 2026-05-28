package com.spring.recruit.dao;

import java.util.List;

import com.spring.recruit.vo.RecruitVo;

public interface RecruitDao {

	// 회원가입 메소드
	public int userSignup(RecruitVo recruitVo) throws Exception;

	//로그인 메소드
	public RecruitVo userLogin(RecruitVo recruitVo) throws Exception;

	public void insertRecruit(RecruitVo recruitVo) throws Exception;
	public String getLastSeq() throws Exception;
}
