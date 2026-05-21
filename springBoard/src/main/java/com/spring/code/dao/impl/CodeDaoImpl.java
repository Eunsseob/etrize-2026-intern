package com.spring.code.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.code.dao.CodeDao;
import com.spring.code.vo.CodeVo;

// DB와 직접 소통하는 DAO
@Repository
public class CodeDaoImpl implements CodeDao{

	// 자동으로 주입해줘
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<CodeVo> selectCodeList() throws Exception {
		// sql 만들었던 값들 가져옴 but 여러줄 나오기 때문에 selectList 사용
		return sqlSession.selectList("code.selectCodeList");
	}
}
