package com.spring.user.dao;

import java.util.List;

import com.spring.user.vo.UserVo;

public interface UserDao {
	
	// 로그인 메소드
	public UserVo userLogin(UserVo userVo) throws Exception;

	// 회원가입 메소드
	public void userSignup(UserVo userVo) throws Exception;

	// 아이디 체크 메소드
	public int userIdcheck(String userId) throws Exception;
}
