package com.spring.recruit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.career.vo.CareerVo;
import com.spring.education.vo.EducationVo;
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
}
