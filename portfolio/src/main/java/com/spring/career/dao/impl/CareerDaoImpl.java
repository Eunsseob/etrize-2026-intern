package com.spring.career.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.career.dao.CareerDao;

@Repository
public class CareerDaoImpl implements CareerDao{
	
	@Autowired
	private SqlSession sqlSession;
	
}
