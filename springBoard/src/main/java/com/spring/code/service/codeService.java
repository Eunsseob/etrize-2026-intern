package com.spring.code.service;

import java.util.List;

import com.spring.code.vo.CodeVo;

public interface codeService {

	public List<CodeVo> selectCodeList() throws Exception;
}
