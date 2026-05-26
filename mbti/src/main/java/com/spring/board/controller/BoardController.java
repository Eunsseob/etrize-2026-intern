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
import com.spring.board.vo.PageVo;
import com.spring.common.CommonUtil;

@Controller
public class BoardController {
	
	@Autowired 
	boardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/board/boardList.do", method = RequestMethod.GET)
	public String boardList(Locale locale, Model model,PageVo pageVo,
			@RequestParam(value = "boardType", required = false) List<String> boardTypeList) throws Exception{
		
		System.out.println("pageNo : " + pageVo.getPageNo());
		System.out.println("boardTypeList : " + boardTypeList);
		
		List<BoardVo> boardList = new ArrayList<BoardVo>();
		// 코드 값과 매칭 시키기 위함
		Map<String, String> codeMap = new HashMap<>();
	    
	    
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

    // ② 결과 계산
    @RequestMapping(value = "/board/mbtiResult.do", method = RequestMethod.POST)
    public String mbtiResult(HttpServletRequest request, Model model) throws Exception {

        List<BoardVo> boardList = boardService.boardMbtiList();

        int eScore = 0, iScore = 0;
        int sScore = 0, nScore = 0;
        int fScore = 0, tScore = 0;
        int jScore = 0, pScore = 0;

        // 첫 번째 문항 동점 처리용
        String firstEiType = null;
        int firstEiScore = 0;

        for (BoardVo board : boardList) {
            String param = request.getParameter("q_" + board.getBoardNum());
            if (param == null) continue;

            int value = Integer.parseInt(param);
            String type = board.getBoardType();

            // value 1~7 → -3 ~ +3 변환 (4=중간=0)
            int score = 4 - value;

            // EI 계열
            if ("EI".equals(type)) {
                // 동의쪽(+)=E, 비동의쪽(-)=I
                if (score > 0) eScore += score;
                else if (score < 0) iScore += (-score);

                if (firstEiType == null) {
                    firstEiType = "EI";
                    firstEiScore = score;
                }

            } else if ("IE".equals(type)) {
                // 동의쪽(+)=I, 비동의쪽(-)=E
                if (score > 0) iScore += score;
                else if (score < 0) eScore += (-score);

                if (firstEiType == null) {
                    firstEiType = "IE";
                    firstEiScore = score;
                }

            // SN 계열
            } else if ("SN".equals(type)) {
                if (score > 0) sScore += score;
                else if (score < 0) nScore += (-score);

            } else if ("NS".equals(type)) {
                if (score > 0) nScore += score;
                else if (score < 0) sScore += (-score);

            // FT 계열
            } else if ("FT".equals(type)) {
                if (score > 0) fScore += score;
                else if (score < 0) tScore += (-score);

            } else if ("TF".equals(type)) {
                if (score > 0) tScore += score;
                else if (score < 0) fScore += (-score);

            // JP 계열
            } else if ("JP".equals(type)) {
                if (score > 0) jScore += score;
                else if (score < 0) pScore += (-score);

            } else if ("PJ".equals(type)) {
                if (score > 0) pScore += score;
                else if (score < 0) jScore += (-score);
            }
        }

        // 유형 판별
        String resultEi;
        if (eScore > iScore)       resultEi = "E";
        else if (iScore > eScore)  resultEi = "I";
        else {
            // 동점이면 첫 번째 문항 기준
            if ("EI".equals(firstEiType)) resultEi = firstEiScore >= 0 ? "E" : "I";
            else                          resultEi = firstEiScore >= 0 ? "I" : "E";
        }

        String resultSn = sScore >= nScore ? "S" : "N";
        String resultFt = fScore >= tScore ? "F" : "T";
        String resultJp = jScore >= pScore ? "J" : "P";

        String mbtiResult = resultEi + resultSn + resultFt + resultJp;

        model.addAttribute("mbtiResult", mbtiResult);
        model.addAttribute("eScore", eScore);
        model.addAttribute("iScore", iScore);
        model.addAttribute("sScore", sScore);
        model.addAttribute("nScore", nScore);
        model.addAttribute("fScore", fScore);
        model.addAttribute("tScore", tScore);
        model.addAttribute("jScore", jScore);
        model.addAttribute("pScore", pScore);

        return "board/mbtiResult";
    }
}
