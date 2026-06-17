package com.spring.user.service;

import java.util.List;

import com.spring.user.vo.UserVo;


public interface userService {

	public UserVo userLogin(UserVo userVo) throws Exception;

	public void userSignup(UserVo userVo) throws Exception;

	public int userIdcheck(String userId) throws Exception;
}
