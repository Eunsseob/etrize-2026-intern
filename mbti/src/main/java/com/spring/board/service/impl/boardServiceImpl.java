package com.spring.board.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.PageVo;

@Service
public class boardServiceImpl implements boardService{
	
	@Autowired
	BoardDao boardDao;
	
	@Override
	public String selectTest() throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectTest();
	}
	
	@Override
	public List<BoardVo> SelectBoardList(PageVo pageVo) throws Exception {
		// TODO Auto-generated method stub
		
		return boardDao.selectBoardList(pageVo);
	}
	
	// 토탈 갯수를 전달받기 위해 맵으로 받아오기.
	// pageVo에 있는 "보드 타입" -> 반환으로 보드의 갯수 주는 방법
	@Override
	public int selectBoardCnt(PageVo pageVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.selectBoardCnt(pageVo);
	}
	
	@Override
	public BoardVo selectBoard(String boardType, int boardNum) throws Exception {
		// TODO Auto-generated method stub
		BoardVo boardVo = new BoardVo();
		
		boardVo.setBoardType(boardType);
		boardVo.setBoardNum(boardNum);
		
		return boardDao.selectBoard(boardVo);
	}
	
	@Override
	public int boardInsert(BoardVo boardVo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.boardInsert(boardVo);
	}

	// 수정 하기
	@Override
	public int boardUpdate(BoardVo boardVo) throws Exception {
	    return boardDao.boardUpdate(boardVo);  // 수정된 데이터 넘기기
	}
	
	// 삭제하기
	@Override
	public int boardDelete(BoardVo boardVo) throws Exception {
		return boardDao.boardDelete(boardVo);  // 수정된 데이터 넘기기
	}
	
	@Override
	public List<BoardVo> boardMbtiList(PageVo pageVo) throws Exception {
		// TODO Auto-generated method stub	
		return boardDao.boardMbti(pageVo);
	}
}
