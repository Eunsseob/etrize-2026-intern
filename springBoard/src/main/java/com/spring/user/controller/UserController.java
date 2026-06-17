package com.spring.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.board.HomeController;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.PageVo;
import com.spring.user.vo.UserVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	com.spring.user.service.userService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// 로그인 폼을 받아오는 방법입니다.
	@RequestMapping(value = "/user/userLogin.do", method = RequestMethod.GET)
	public String userLoginForm() throws Exception {
		
	    return "user/userLogin";
	}
	
	
	
	@RequestMapping(value = "/user/userLoginApi.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userLoginApi(@RequestBody UserVo userVo, HttpServletRequest request) throws Exception {
	    Map<String, Object> result = new HashMap<>();

	    UserVo loginUser = userService.userLogin(userVo);

	    if (loginUser != null) {
	        HttpSession session = request.getSession();
	        session.setAttribute("userName", loginUser.getUserName());
	        result.put("success", true);
	        result.put("userName", loginUser.getUserName());
	        result.put("isOwner", loginUser.getIsOwner());
	    } else {
	        result.put("success", false);
	        result.put("msg", "아이디 또는 비밀번호가 틀렸습니다.");
	    }
	    return result;
	}
	
	// 회원가입 폼을 받아오는 방법입니다. 한글이 꺠질시 produces 추가하기
	@RequestMapping(value = "/user/userSignup.do", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public String userSignForm() throws Exception {
				
	   return "user/userSignup";
	}
	
	@RequestMapping(value = "/user/userSignup.do", method = RequestMethod.POST)
	@ResponseBody
	public String userSignup(@RequestBody UserVo userVo) throws Exception {
	    System.out.println(userVo.getUserId());
	    System.out.println(userVo.getUserPw());
		
		userService.userSignup(userVo);
		
		return "success";
	}
			
	@RequestMapping(value="/user/userIdcheck.do", method=RequestMethod.POST)
	@ResponseBody
	public int userIdcheck(@RequestBody UserVo userVo) throws Exception {

	    int count = userService.userIdcheck(userVo.getUserId());

	    return count;
	}
	
	// 로그아웃
	@RequestMapping(value = "/user/userLogout.do", method = RequestMethod.GET)
	public String userLogout(HttpServletRequest request) throws Exception {
	    
	    HttpSession session = request.getSession(false);
	    
	    if(session != null) {
	        session.invalidate(); // 세션 삭제
	    }
	    
	    return "redirect:../board/boardList.do";
	}
}
