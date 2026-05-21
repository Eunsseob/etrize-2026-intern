package com.spring.code.service;

import java.util.List;

import com.spring.code.vo.CodeVo;

public interface codeService {

	// 컨트롤러가 나를 호출할 통로 이름
	public List<CodeVo> selectCodeList() throws Exception;
}
