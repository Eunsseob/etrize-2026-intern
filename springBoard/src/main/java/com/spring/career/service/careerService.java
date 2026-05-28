package com.spring.career.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.career.vo.CareerVo;

public interface careerService {

	public void insertCareerList(List<CareerVo> list) throws Exception;

}
