package com.spring.code.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.board.HomeController;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.PageVo;
import com.spring.code.service.codeService;
import com.spring.code.vo.CodeVo;
import com.spring.common.CommonUtil;

@Controller
public class CodeController {
	
	@Autowired 
	codeService codeService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	// 인터넷 브라우저에 매핑해주는 문문
	@RequestMapping(value = "/code/codeList.do", method = RequestMethod.GET)
	public String boardWrite(Model model) throws Exception {
	    List<CodeVo> codeList = codeService.selectCodeList();
	    // codeList << jsp 화면단에서 이름 불러 데이터 호출 가능
	    model.addAttribute("codeList", codeList);
	    
	    // 최종 목적지로 가라
	    return "board/boardWrite";
	}
}
