package com.spring.user.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	
	
	@RequestMapping(value = "/user/userLogin.do", method = RequestMethod.POST)
	public String userLogin(Model model, UserVo userVo, HttpServletRequest request) throws Exception {
		
	    String loginUser = userService.userLogin(userVo);

	    if(loginUser != null) {
	    	HttpSession session = request.getSession();
	    	session.setAttribute("userName", loginUser); 
	        
	        // 맨 앞의 /를 지워서 상대 경로로 리다이렉트 시킵니다.
	        return "redirect:../board/boardList.do";
	    } else {
	        model.addAttribute("msg", "아이디 또는 비밀번호가 틀렸습니다.");
	        return "user/userLogin";  // 실패 → 다시 로그인
	    }
	}
	
	// 회원가입 폼을 받아오는 방법입니다. 한글이 꺠질시 produces 추가하기
	@RequestMapping(value = "/user/userSignup.do", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	public String userSignForm() throws Exception {
				
	   return "user/userSignup";
	}
	
	@RequestMapping(value = "/user/userSignup.do", method = RequestMethod.POST)
	public String userSignup(UserVo userVo) throws Exception {
	    userService.userSignup(userVo);
	    return "redirect:/board/boardList.do";
	}
			
			@RequestMapping(value = "/user/userIdcheck.do", method = RequestMethod.POST)
			@ResponseBody // 이 어노테이션이 있어야 페이지 이동 x
			public int userIdcheck(UserVo userVo) throws Exception {
			    
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
