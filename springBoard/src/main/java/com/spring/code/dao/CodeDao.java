package com.spring.code.dao;

import java.util.List;

import com.spring.code.vo.CodeVo;

public interface CodeDao {

	// 코드 리스트 조회하는 기능 + 결과물은 CodeVo가 담긴 List로 전달
	public List<CodeVo> selectCodeList() throws Exception;
}
