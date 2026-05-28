package com.spring.recruit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.recruit.dao.RecruitDao;
import com.spring.recruit.service.recruitService;
import com.spring.recruit.vo.RecruitVo;
import com.spring.user.vo.UserVo;

@Service
public class recruitServiceImpl implements recruitService{
	
	@Autowired
	RecruitDao recruitDao;
	
	// 회원 가입하기
	@Override
	public int userSignup(RecruitVo recruitVo) throws Exception {
		return recruitDao.userSignup(recruitVo);  // 수정된 데이터 넘기기
	}
	
	// 로그인 하기
	@Override
	public RecruitVo userLogin(RecruitVo recruitVo) throws Exception {
	    return recruitDao.userLogin(recruitVo);  // 수정된 데이터 넘기기
	}
	
	public void insertRecruit(RecruitVo recruitVo) throws Exception {
	    recruitDao.insertRecruit(recruitVo);
	}

	public String getLastSeq() throws Exception {
	    return recruitDao.getLastSeq();
	}
}
