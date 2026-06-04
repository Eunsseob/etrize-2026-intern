package com.spring.career.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.career.vo.CareerVo;
import com.spring.certificate.vo.CertificateVo;

public interface careerService {

	public void insertCareerList(List<CareerVo> list) throws Exception;

	public void deleteCareer(String seq) throws Exception;

	public List<CareerVo> getCareer(String seq) throws Exception;
	
	public void syncCareerList(String seq, List<CareerVo> careerList) throws Exception;
}
