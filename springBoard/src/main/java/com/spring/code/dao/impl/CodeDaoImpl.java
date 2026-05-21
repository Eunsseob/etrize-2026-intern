package com.spring.code.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.code.dao.CodeDao;
import com.spring.code.vo.CodeVo;

@Repository
public class CodeDaoImpl implements CodeDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<CodeVo> selectCodeList() throws Exception {
		// selectList를 써야 데이터가 여러 개(일반, Q&A 등) 담긴 리스트로 넘어옵니다.
		return sqlSession.selectList("code.selectCodeList");
	}
}
