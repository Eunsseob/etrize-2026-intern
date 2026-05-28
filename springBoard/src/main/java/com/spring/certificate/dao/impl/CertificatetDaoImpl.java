package com.spring.certificate.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.career.vo.CareerVo;
import com.spring.certificate.dao.CertificateDao;
import com.spring.certificate.vo.CertificateVo;
import com.spring.recruit.dao.RecruitDao;

@Repository
public class CertificatetDaoImpl implements CertificateDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	// 넣는방법
	@Override
	public void insertCertificateList(List<CertificateVo> list) throws Exception {
	    sqlSession.insert("certificate.insertCertificateList", list);
	}
}
