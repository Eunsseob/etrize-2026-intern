package com.spring.board.dao;

import java.util.List;

import com.spring.board.vo.BoardVo;

public interface BoardDao {

	// 리스트 형식으로 MBTI의 리스트를 찾아야지
	public List<BoardVo> boardMbti() throws Exception;
}
