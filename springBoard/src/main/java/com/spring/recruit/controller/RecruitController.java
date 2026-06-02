package com.spring.recruit.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.HomeController;
import com.spring.career.service.careerService;
import com.spring.career.vo.CareerVo;
import com.spring.certificate.service.certificateService;
import com.spring.certificate.vo.CertificateVo;
import com.spring.common.CommonUtil;
import com.spring.education.service.educationService;
import com.spring.education.vo.EducationVo;
import com.spring.recruit.service.recruitService;
import com.spring.recruit.vo.RecruitVo;
import com.spring.user.vo.UserVo;

@Controller
public class RecruitController {
	
	@Autowired 
	recruitService recruitService;
	
	@Autowired
	educationService educationService;

	@Autowired
	careerService careerService;

	@Autowired
	certificateService certificateService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	// 로그인 폼 가져오기 위한 
	@RequestMapping(value = "/recruit/login", method = RequestMethod.GET)
    public String login(Locale locale, Model model) throws Exception {
        return "recruit/login";
    }
	
	@RequestMapping(value = "/checkPhone.do", method = RequestMethod.POST)
	@ResponseBody
	public int checkPhone(RecruitVo recruitVo) throws Exception {
	    return recruitService.phoneCheck(recruitVo);
	}
	
	@RequestMapping(value = "/userLogin.do", method = RequestMethod.POST)
	public @ResponseBody String userLogin(RecruitVo recruitVo, HttpServletRequest request) throws Exception {
		System.out.println("들어옴");
		
		// DB에서 이름+전화번호로 조회
	    RecruitVo existUser = recruitService.userLogin(recruitVo);
	    
	    if(existUser == null) {
	    	return "fail";
	    } 
	    
	    // 있으면 로그인 처리
        HttpSession session = request.getSession();
        session.setAttribute("name", existUser.getName());
        session.setAttribute("phone", existUser.getPhone());
        session.setAttribute("seq", existUser.getSeq());
        return "success";
	}
	
	@RequestMapping(value = "/userSignup.do", method = RequestMethod.POST)
	public @ResponseBody String userSignup(RecruitVo recruitVo, HttpServletRequest request) throws Exception {
		// 중복 체크
	    if (recruitService.phoneCheck(recruitVo) > 0) {
	        return "duplicated"; // 중복 시 가입 중단
	    }
	    recruitService.userSignup(recruitVo);
	    
	    // 3. 가입된 유저 정보 다시 조회 (세션에 담기 위해)
	    RecruitVo newUser = recruitService.userLogin(recruitVo);
	    
	    // 4. 즉시 세션 생성
        HttpSession session = request.getSession();
	    session.setAttribute("name", newUser.getName());
	    session.setAttribute("phone", newUser.getPhone());
	    session.setAttribute("seq", newUser.getSeq());
	    
	    return "signup_success";
	}
		
	private int calcMonths(String start, String end) {
	    try {
	        if (start == null || end == null || start.isEmpty() || end.isEmpty())
	        	return 0;
	        // 구분자 유연하게 처리하는 방법
	        String[] s = start.split("[.\\-/]");
	        String[] e = end.split("[.\\-/]");
	        
	        int sy = Integer.parseInt(s[0].trim());
	        int sm = Integer.parseInt(s[1].trim());
	        int ey = Integer.parseInt(e[0].trim());
	        int em = Integer.parseInt(e[1].trim());
	        
	        int startTotal = sy * 12 + sm;
	        int endTotal   = ey * 12 + em;

	        return Math.max(endTotal - startTotal, 0);

	    } catch (Exception ex) {
	        return 0;
	    }
	}
	
	// 제출 처리
	@RequestMapping("/recruit/submitRecruit.do")
	@ResponseBody
	public void submitRecruit(String seq) throws Exception {
	    recruitService.submitRecruit(seq);
	}

	@RequestMapping(value = "/recruit/main.do", method = RequestMethod.GET)
    public String main(Model model, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
	    String seq = (String) session.getAttribute("seq");

	    // 저장한 이력서들 다 가져오는 방법
	    RecruitVo recruit = recruitService.getRecruit(seq);
	    List<EducationVo> educationList = educationService.getEducation(seq);
	    List<CareerVo> careerList = careerService.getCareer(seq);
	    List<CertificateVo> certificateList = certificateService.getCertificate(seq);

	    // 학력 기간만 따로 계산
	    int eduMonths = 0;
	    if (educationList != null) {
	        for (EducationVo edu : educationList) {
	        	System.out.println("start: " + edu.getStartPeriod() + " end: " + edu.getEndPeriod());
	            eduMonths += calcMonths(edu.getStartPeriod(), edu.getEndPeriod());
	        }
	    }
	    int eduYears  = eduMonths / 23;
	    
	    if (eduYears>=2) {
	    	eduYears = 4;
	    } else if (eduYears >= 1){
	    	eduYears = 2;
	    } else {
	    	eduYears = 0;
	    }

	    for (EducationVo edu : educationList) {
	        System.out.println("start: " + edu.getStartPeriod() + " end: " + edu.getEndPeriod());
	    }
	    
	    // 학력 기간만 따로 계산
	    int carMonths = 0;
	    if (careerList != null) {
	        for (CareerVo car : careerList) {
	            carMonths += calcMonths(car.getStartPeriod(), car.getEndPeriod());
	        }
	    }
	    int carYears  = carMonths / 12;
	    int carMon    = carMonths % 12;
	    
	    model.addAttribute("eduPeriod", eduYears + "년");
	    model.addAttribute("carPeriod", carYears + "년 " + carMon + "개월");
	    model.addAttribute("recruit", recruit);
	    model.addAttribute("educationList", educationList);
	    model.addAttribute("careerList", careerList);
	    model.addAttribute("certificateList", certificateList);
		return "recruit/main";
    }
	
	@RequestMapping(value = "/recruit/userSignup.do", method = RequestMethod.POST)
	public @ResponseBody String userSignup1(@ModelAttribute RecruitVo recruitVo, HttpServletRequest request) throws Exception {
	    HttpSession session = request.getSession();
	    String seq = (String) session.getAttribute("seq");
	    recruitVo.setSeq(seq);

	    // 이제 서비스 메서드 하나만 호출하면 안전합니다!
	    recruitService.updateAllRecruitInfo(seq, recruitVo);
	    
	    return "success";
	}
}