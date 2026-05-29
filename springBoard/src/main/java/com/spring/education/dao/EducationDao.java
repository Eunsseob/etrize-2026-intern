package com.spring.education.dao;

import java.util.List;

import com.spring.education.vo.EducationVo;


public interface EducationDao {

	public void insertEducationList(List<EducationVo> list) throws Exception;

	public void deleteEducation(String seq) throws Exception;
	
	public List<EducationVo> getEducation(String seq) throws Exception;
}
