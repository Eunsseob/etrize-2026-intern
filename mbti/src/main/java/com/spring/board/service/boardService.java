package com.spring.board.service;

import java.util.List;

import com.spring.board.vo.BoardVo;

public interface boardService {

	// mbti 가져오기
	public List<BoardVo> boardMbtiList() throws Exception;
}
