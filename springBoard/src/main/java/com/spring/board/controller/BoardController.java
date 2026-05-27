package com.spring.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import com.spring.code.service.codeService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.PageVo;
import com.spring.code.vo.CodeVo;
import com.spring.common.CommonUtil;

@Controller
public class BoardController {
	
	@Autowired 
	boardService boardService;
	
	@Autowired 
    codeService codeService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/board/boardList.do", method = RequestMethod.GET)
	public String boardList(Locale locale, Model model,PageVo pageVo,
			@RequestParam(value = "boardType", required = false) List<String> boardTypeList) throws Exception{
		
		System.out.println("pageNo : " + pageVo.getPageNo());
		System.out.println("boardTypeList : " + boardTypeList);
		
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		List<CodeVo> codeList = codeService.selectCodeList();
		
		// 코드 값과 매칭 시키기 위함
		Map<String, String> codeMap = new HashMap<>();
	    for(CodeVo vo : codeList) {
	        codeMap.put(vo.getCodeId(), vo.getCodeName());
	    }
	    
		int page = 1;
		int totalCnt = 0;
		
		if(pageVo.getPageNo() == 0){
			pageVo.setPageNo(page);;
		}
		
		pageVo.setBoardTypeList(boardTypeList);
		
		boardList = boardService.SelectBoardList(pageVo);
		// 넘기는 이유 Cnt 동적 작용
		totalCnt = boardService.selectBoardCnt(pageVo);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageNo", page);
		model.addAttribute("codeList", codeList);
		model.addAttribute("codeMap", codeMap);
		
		return "board/boardList";
	}
	
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardView.do", method = RequestMethod.GET)
	public String boardView(Locale locale, Model model
			,@PathVariable("boardType")String boardType
			,@PathVariable("boardNum")int boardNum) throws Exception{
		
		BoardVo boardVo = new BoardVo();
		
		boardVo = boardService.selectBoard(boardType,boardNum);
		
		model.addAttribute("boardType", boardType);
		model.addAttribute("boardNum", boardNum);
		model.addAttribute("board", boardVo);
		
		return "board/boardView";
	}
	
	@RequestMapping(value = "/board/boardWrite.do", method = RequestMethod.GET)
	public String boardWrite(Locale locale, Model model) throws Exception{
		// 1. 드롭다운에 뿌릴 코드 목록 조회해서 model에 담기
        List<CodeVo> codeList = codeService.selectCodeList();
        model.addAttribute("codeList", codeList);        
		return "board/boardWrite";
	}
	
	// 보드에 삽입하는 함수입니다.
	@RequestMapping(value = "/board/boardWriteAction.do", method = RequestMethod.POST)
	@ResponseBody
	public String boardWriteAction(Locale locale,BoardVo boardVo) throws Exception{
		System.out.println("boardType: " + boardVo.getBoardType()); 
		System.out.println("boardName: " + boardVo.getBoardTitle()); 
		System.out.println("boardComment: " + boardVo.getBoardComment()); 
		
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		
		int resultCnt = boardService.boardInsert(boardVo);
		
		result.put("success", (resultCnt > 0)?"Y":"N");
		String callbackMsg = commonUtil.getJsonCallBackString(" ",result);
		
		System.out.println("callbackMsg::"+callbackMsg);
		
		return callbackMsg;
	}
	
	// 수정메소드
	// 수정 폼
	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardUpdate.do", method = RequestMethod.GET)
	public String boardEditForm(Locale locale, Model model,
	                            @PathVariable String boardType, 
	                            @PathVariable int boardNum  
	) throws Exception {

	    BoardVo boardVo = boardService.selectBoard(boardType, boardNum);

	    model.addAttribute("boardType", boardType);
	    model.addAttribute("boardNum", boardNum);
	    model.addAttribute("board", boardVo);

	    return "board/boardUpdate"; 
	}

	@RequestMapping(value = "/board/{boardType}/{boardNum}/boardUpdate.do", method = RequestMethod.POST)
	public String boardUpdate(@ModelAttribute BoardVo boardVo) throws Exception {

	    boardService.boardUpdate(boardVo);

	    return "redirect:/board/boardList.do"; 
	}
	
//	// 삭제하는 메소드 버전 1
//	@RequestMapping(value = "/board/boardDeleteAction.do", method = RequestMethod.POST)
//	public String boardDelete(@ModelAttribute BoardVo boardVo, Model model) throws Exception {
//		System.out.println("boardNum: " + boardVo.getBoardNum()); 
////	    
////		// 먼저, 이미 해당 게시물이 있는지 확인
//		BoardVo boardVo2 = boardService.selectBoard(boardVo.getBoardType(), boardVo.getBoardNum());
//
//	    // 보드가 없으면 이미 삭제된 게시물 
//	    if(boardVo2 == null) {
//	    	System.out.println(123123);
			// 리다이렉트로 보내면 해당값이 같이 전달이 안된다
			// 따라서 보낼꺼면 forward 방식으로 보내는 것이 좋음
//	        model.addAttribute("msg", "이미 삭제된 게시물입니다.");
//		    return "common/error"; // 쫓아내기
//	    }
//
//	    boardService.boardDelete(boardVo);
//	    return "redirect:/board/boardList.do";
//	}
	
	// 삭제하는 메소드 2
	@RequestMapping(value = "/board/boardDeleteAction.do", method = RequestMethod.POST)
	@ResponseBody // 움직이지 않는 메소드
	public String boardDelete(@ModelAttribute BoardVo boardVo) throws Exception {
		// HashMap 형태로 던져줍니다.
		HashMap<String, String> result = new HashMap<String, String>();
		CommonUtil commonUtil = new CommonUtil();
		    
//		// 쿼리 하나 사용
		int resultCnt = boardService.boardDelete(boardVo);
		
		result.put("success", (resultCnt > 0) ? "Y" : "N");
	    String callbackMsg = commonUtil.getJsonCallBackString(" ", result);
	    
	    System.out.println("callbackMsg::" + callbackMsg);
	    
	    return callbackMsg;
	}
}
