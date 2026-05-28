package com.spring.education.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.education.dao.EducationDao;
import com.spring.education.vo.EducationVo;
import com.spring.recruit.dao.RecruitDao;

@Repository
public class EducationDaoImpl implements EducationDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	// 넣는방법
	@Override
	public void insertEducationList(List<EducationVo> list) throws Exception {
	    sqlSession.insert("education.insertEducationList", list);
	}
}
