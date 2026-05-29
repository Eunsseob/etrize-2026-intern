package com.spring.career.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.career.dao.CareerDao;
import com.spring.career.vo.CareerVo;
import com.spring.certificate.dao.CertificateDao;
import com.spring.certificate.vo.CertificateVo;
import com.spring.education.vo.EducationVo;
import com.spring.recruit.dao.RecruitDao;

@Repository
public class CareerDaoImpl implements CareerDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	// 넣는방법
	@Override
	public void insertCareerList(List<CareerVo> list) throws Exception {
		for(CareerVo car : list) {
			sqlSession.insert("career.insertCareerList", car);
		}
	}
	
	@Override
	public void deleteCareer(String seq) throws Exception {
	    sqlSession.delete("career.deleteCareer", seq);
	}
	
	@Override
	public List<CareerVo> getCareer(String seq) throws Exception {
	    return sqlSession.selectList("career.getCareerList", seq);
	}
}
