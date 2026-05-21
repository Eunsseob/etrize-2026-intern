package com.spring.user.service;

import java.util.List;

import com.spring.user.vo.UserVo;


public interface userService {

	public String userLogin(UserVo userVo) throws Exception;

	public int userSignup(UserVo userVo) throws Exception;

	public int userIdcheck(String userId) throws Exception;
}
