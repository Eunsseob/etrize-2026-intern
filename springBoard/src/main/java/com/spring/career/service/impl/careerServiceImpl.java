package com.spring.career.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.career.service.careerService;
import com.spring.certificate.service.certificateService;
import com.spring.recruit.dao.RecruitDao;
import com.spring.recruit.service.recruitService;

@Service
public class careerServiceImpl implements careerService{
	
	@Autowired
	RecruitDao boardDao;
	
	
}
