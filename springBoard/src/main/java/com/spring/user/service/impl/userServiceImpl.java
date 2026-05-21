package com.spring.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.user.dao.UserDao;
import com.spring.user.service.userService;
import com.spring.user.vo.UserVo;

@Service
public class userServiceImpl implements userService{
	
	@Autowired
	UserDao userDao;
	
	// 로그인 하기
	@Override
	public String userLogin(UserVo userVo) throws Exception {
	    return userDao.userLogin(userVo);  // 수정된 데이터 넘기기
	}
	
	// 회원 가입하기
	@Override
	public int userSignup(UserVo userVo) throws Exception {
		return userDao.userSignup(userVo);  // 수정된 데이터 넘기기
	}
	
	// 아이디 조회
	@Override
	public int userIdcheck(String userId) throws Exception {
		return userDao.userIdcheck(userId);  // 수정된 데이터 넘기기
	}
}
