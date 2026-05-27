package com.spring.career.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.career.dao.CareerDao;
import com.spring.career.service.careerService;

@Service
public class careerServiceImpl implements careerService{
	
	@Autowired
	CareerDao careerDao;
	
}
