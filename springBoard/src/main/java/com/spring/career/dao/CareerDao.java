package com.spring.career.dao;

import java.util.List;

import com.spring.career.vo.CareerVo;


public interface CareerDao {

	public void insertCareerList(List<CareerVo> list) throws Exception;

	public void deleteCareer(String seq) throws Exception;

	public List<CareerVo> getCareer(String seq) throws Exception;

}
