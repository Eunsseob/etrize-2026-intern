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
//	
	@Autowired 
	recruitService recruitService;
//	
	@Autowired
	educationService educationService;

	@Autowired
	careerService careerService;

	@Autowired
	certificateService certificateService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/recruit/login", method = RequestMethod.GET)
    public String login(Locale locale, Model model) throws Exception {
        return "recruit/login";
    }
	
	@RequestMapping(value = "/userSignup.do", method = RequestMethod.POST)
	public @ResponseBody String userSignup(RecruitVo recruitVo, HttpServletRequest request) throws Exception {
		System.out.println("들어옴");
		
		// DB에서 이름+전화번호로 조회
	    RecruitVo existUser = recruitService.userLogin(recruitVo);
	    
	    if(existUser == null) {
	    	recruitService.userSignup(recruitVo);
	        existUser = recruitService.userLogin(recruitVo);
	    } 
	    // 있으면 로그인 처리
        HttpSession session = request.getSession();
        session.setAttribute("name", existUser.getName());
        session.setAttribute("phone", existUser.getPhone());
        session.setAttribute("seq", existUser.getSeq());
        return "redirect: /recruit/main.do";
	}
	
	@RequestMapping(value = "/recruit/main.do", method = RequestMethod.GET)
    public String main(Model model, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
	    String seq = (String) session.getAttribute("seq");

	    RecruitVo recruit = recruitService.getRecruit(seq);
	    List<EducationVo> educationList = educationService.getEducation(seq);
	    List<CareerVo> careerList = careerService.getCareer(seq);
	    List<CertificateVo> certificateList = certificateService.getCertificate(seq);

	    model.addAttribute("recruit", recruit);
	    model.addAttribute("educationList", educationList);
	    model.addAttribute("careerList", careerList);
	    model.addAttribute("certificateList", certificateList);
		return "recruit/main";
    }
	
	@RequestMapping(value = "/recruit/userSignup.do", method = RequestMethod.POST)
	public @ResponseBody String userSignup1(RecruitVo recruitVo, HttpServletRequest request) throws Exception {

		// 세션에서 seq 가져오기
	    HttpSession session = request.getSession();
	    String seq = (String) session.getAttribute("seq");

	    recruitVo.setSeq(seq);
	    
	    recruitService.updateRecruit(recruitVo);
	    
        educationService.deleteEducation(seq);
        careerService.deleteCareer(seq);
        certificateService.deleteCertificate(seq);
        
	    // 3. 학력 insert (필수)
	    if(recruitVo.getEducationList() != null && recruitVo.getEducationList().size() > 0) {
	        for(EducationVo edu : recruitVo.getEducationList()) {
	            edu.setSeq(seq);
	        }
	        educationService.insertEducationList(recruitVo.getEducationList());
	    }
	    
	    System.out.println("경력 들어간다");
	    
	    // 4. 경력 insert (선택)
	    if(recruitVo.getCareerList() != null && recruitVo.getCareerList().size() > 0) {
	    	for(CareerVo car : recruitVo.getCareerList()) {
	    	    car.setSeq(seq);
	    	}
	        careerService.insertCareerList(recruitVo.getCareerList());
	    }

	    System.out.println("자격증 들어간다");
	    
	    // 5. 자격증 insert (선택)
	    if(recruitVo.getCertificateList() != null && recruitVo.getCertificateList().size() > 0) {
	        for(CertificateVo cer : recruitVo.getCertificateList()) {
	            cer.setSeq(seq);
	        }
	        certificateService.insertCertificateList(recruitVo.getCertificateList());
	    }
	    
	    return "success";
	}
}