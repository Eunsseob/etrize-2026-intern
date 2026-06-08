package com.spring.education.dao;

import java.util.List;

import com.spring.education.vo.EducationVo;


public interface EducationDao {

	public void insertEducationList(List<EducationVo> list) throws Exception;

	public void deleteEducation(String seq) throws Exception;
	
	public List<EducationVo> getEducation(String seq) throws Exception;

	public int updateEducationList(EducationVo vo) throws Exception;

	public void deleteEducationByEduSeq(EducationVo vo) throws Exception;

	public void insertEducation(EducationVo vo) throws Exception;

	public void syncEducationList(String seq, List<EducationVo> educationList);
}
