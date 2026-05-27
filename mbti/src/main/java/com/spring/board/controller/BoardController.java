package com.spring.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.spring.common.CommonUtil;

@Controller
public class BoardController {
	
	@Autowired 
	boardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/board/mbti.do", method = RequestMethod.GET)
    public String mbtiMain(Locale locale, Model model) throws Exception {
        List<BoardVo> boardList = boardService.boardMbtiList();
        model.addAttribute("boardList", boardList);
        return "board/mbti";
    }
	
	@RequestMapping(value = "/board/mbtiResult.do", method = RequestMethod.GET)
    public String mbtiResult(Locale locale, Model model) throws Exception {
        return "board/mbtiResult";
    }
	

	// 동점 방지 함수
	private String dues(Map<String, Integer> scores, String a, String b) {
		// 각각 항목별 점수를 가져와서
		int k = scores.get(a) - scores.get(b);
		System.out.println(k);
		
		if (k>0) return a;
		else if (k<0) return b;
		// 동점일때 a가 b보다 사전순으로 빠르냐 ex) a.compareTo(b) ==> -1 반환 why a가 더 빠르니깐.
		else return 
				a.compareTo(b) < 0 ? a:b;
	}
	
    @RequestMapping(value = "/board/mbtiResult.do", method = RequestMethod.POST)
    public String mbtiResult(HttpServletRequest request, Model model) throws Exception {

        List<BoardVo> boardList = boardService.boardMbtiList();

        // 1번 점수 저장용 Map (E, I, S, N, F, T, J, P)
        Map<String, Integer> scores = new HashMap<>();
        
        // 0점으로 초기화
        String[] allTypes = {"E", "I", "S", "N", "F", "T", "J", "P"};
        for (String t : allTypes) scores.put(t, 0);

        // 2번 규칙 정의 (타입 8개가 있으니, 8개로 나누는 형식)
        Map<String, String[]> rules = new HashMap<>();
        
        // 룰에있는것을 가져와, 해당 스트링 값에 있는 것에 점수를 부여하겠다.
        rules.put("EI", new String[]{"E", "I"}); rules.put("IE", new String[]{"I", "E"});
        rules.put("SN", new String[]{"S", "N"}); rules.put("NS", new String[]{"N", "S"});
        rules.put("FT", new String[]{"F", "T"}); rules.put("TF", new String[]{"T", "F"});
        rules.put("JP", new String[]{"J", "P"}); rules.put("PJ", new String[]{"P", "J"});
        
        
        // 동의 로직이 보이게
        for (BoardVo board : boardList) {
            String param = request.getParameter("q_" + board.getBoardNum());
            if (param == null) continue;

            int score = Integer.parseInt(param);
            String type = board.getBoardType();
            String[] rule = rules.get(type);

            switch (score) {
	            case 7:  scores.put(rule[0], scores.get(rule[0]) + 3); break; // 매우 비동의 + 보드타입에 있는 맨 앞에있는 문자열 + 3
	            case 6:  scores.put(rule[0], scores.get(rule[0]) + 2); break; // 비동의       
	            case 5:  scores.put(rule[0], scores.get(rule[0]) + 1); break; // 약간 비동의 
	            case 4: break;  
	            case 3: scores.put(rule[1], scores.get(rule[1]) + 1); break; // 약간 동의
	            case 2: scores.put(rule[1], scores.get(rule[1]) + 2); break; // 동의     
	            case 1: scores.put(rule[1], scores.get(rule[1]) + 3); break; // 매우 동의 + 보드타입에 있는 맨 뒤에있는 문자열 +3
            }
        }
        
        // 사전순으로 바꾸기
        // 동점일 경우 사전순으로 빠른 쪽(E, S, F, J)이 선택
        String resultEi = dues(scores, "E", "I"); 
        String resultSn = dues(scores, "S", "N"); 
        String resultFt = dues(scores, "F", "T");
        String resultJp = dues(scores, "J", "P");
        
        String mbtiResult = resultEi + resultSn + resultFt + resultJp;

        // 5. 결과 전달
        model.addAttribute("mbtiResult", mbtiResult);
        model.addAttribute("eScore", scores.get("E"));
        model.addAttribute("iScore", scores.get("I"));
        model.addAttribute("sScore", scores.get("S"));
        model.addAttribute("nScore", scores.get("N"));
        model.addAttribute("fScore", scores.get("F"));
        model.addAttribute("tScore", scores.get("T"));
        model.addAttribute("jScore", scores.get("J"));
        model.addAttribute("pScore", scores.get("P"));

        return "board/mbtiResult";
    }
}