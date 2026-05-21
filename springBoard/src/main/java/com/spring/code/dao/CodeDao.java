package com.spring.code.dao;

import java.util.List;

import com.spring.code.vo.CodeVo;

public interface CodeDao {

	public List<CodeVo> selectCodeList() throws Exception;
}
