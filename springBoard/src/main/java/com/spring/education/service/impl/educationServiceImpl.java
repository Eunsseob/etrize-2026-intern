package com.spring.education.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.education.dao.EducationDao;
import com.spring.education.service.educationService;
import com.spring.education.vo.EducationVo;
import com.spring.recruit.dao.RecruitDao;
import com.spring.recruit.service.recruitService;

@Service
public class educationServiceImpl implements educationService{
	
	@Autowired
	EducationDao educationDao;
	
	@Override
	public void insertEducationList(List<EducationVo> list) throws Exception{
		educationDao.insertEducationList(list);
	}
}
