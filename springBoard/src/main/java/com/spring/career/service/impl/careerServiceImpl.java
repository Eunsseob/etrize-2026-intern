package com.spring.career.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.career.dao.CareerDao;
import com.spring.career.service.careerService;
import com.spring.career.vo.CareerVo;
import com.spring.certificate.service.certificateService;
import com.spring.education.vo.EducationVo;
import com.spring.recruit.dao.RecruitDao;
import com.spring.recruit.service.recruitService;

@Service
public class careerServiceImpl implements careerService{
	
	@Autowired
	CareerDao careerDao;
	
	@Override
	public void insertCareerList(List<CareerVo> list) throws Exception{
		careerDao.insertCareerList(list);
	}
	
	@Override
	public void deleteCareer(String seq) throws Exception{
		careerDao.deleteCareer(seq);
	}
	
	@Override
	public List<CareerVo> getCareer(String seq) throws Exception{
		return careerDao.getCareer(seq);
	}
}
