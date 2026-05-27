package com.spring.recruit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.recruit.dao.RecruitDao;
import com.spring.recruit.service.recruitService;

@Service
public class recruitServiceImpl implements recruitService{
	
	@Autowired
	RecruitDao boardDao;
	
	
}
