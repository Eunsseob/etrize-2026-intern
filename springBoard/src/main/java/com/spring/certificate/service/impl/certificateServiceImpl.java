package com.spring.certificate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.career.vo.CareerVo;
import com.spring.certificate.dao.CertificateDao;
import com.spring.certificate.service.certificateService;
import com.spring.certificate.vo.CertificateVo;
import com.spring.recruit.dao.RecruitDao;
import com.spring.recruit.service.recruitService;

@Service
public class certificateServiceImpl implements certificateService{
	
	@Autowired
	CertificateDao certificateDao;
	
	@Override
	public void insertCertificateList(List<CertificateVo> list) throws Exception{
		certificateDao.insertCertificateList(list);
	}
	
	@Override
	public void deleteCertificate(String seq) throws Exception{
		certificateDao.deleteCertificate(seq);
	}
	
	@Override
	public List<CertificateVo> getCertificate(String seq) throws Exception{
		return certificateDao.getCertificate(seq);
	}
}
