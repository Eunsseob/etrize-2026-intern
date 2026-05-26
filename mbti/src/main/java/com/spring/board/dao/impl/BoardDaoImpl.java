package com.spring.board.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.dao.BoardDao;
import com.spring.board.vo.BoardVo;

@Repository
public class BoardDaoImpl implements BoardDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	// mbti 리스트 가져오기입니다.
	@Override
	public List<BoardVo> boardMbti() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("board.boardMbti");
	}
}
