package com.spring.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.dao.BoardDao;
import com.spring.board.service.boardService;
import com.spring.board.vo.BoardVo;
import com.spring.board.vo.PageVo;
import com.spring.code.dao.CodeDao;
import com.spring.code.service.codeService;
import com.spring.code.vo.CodeVo;

@Service
public class codeServiceImpl implements codeService{
	
	@Autowired
	CodeDao codeDao;
	
	// 반환 타입을 List<CodeVo>로 지정해야 여러개 가져옴.
	@Override
	public List<CodeVo> selectCodeList() throws Exception {
			
		//DAO에게 목록을 통째로 달라고 요청
		return codeDao.selectCodeList();
	}
}
