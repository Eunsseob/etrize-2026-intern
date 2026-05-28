package com.spring.education.dao;

import java.util.List;

import com.spring.education.vo.EducationVo;


public interface EducationDao {

	public void insertEducationList(List<EducationVo> list) throws Exception;

}
