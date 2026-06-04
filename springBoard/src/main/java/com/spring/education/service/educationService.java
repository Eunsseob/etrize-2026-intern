package com.spring.education.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.career.vo.CareerVo;
import com.spring.education.vo.EducationVo;

@Service
public interface educationService {

	public void insertEducationList(List<EducationVo> list) throws Exception;

	public void deleteEducation(String seq) throws Exception;

	public List<EducationVo> getEducation(String seq) throws Exception;

	public void syncEducationList(String seq, List<EducationVo> educationList) throws Exception;
}
