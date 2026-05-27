package com.spring.certificate.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.certificate.dao.CertificateDao;
import com.spring.recruit.dao.RecruitDao;

@Repository
public class CertificatetDaoImpl implements CertificateDao{
	
	@Autowired
	private SqlSession sqlSession;
	
}
