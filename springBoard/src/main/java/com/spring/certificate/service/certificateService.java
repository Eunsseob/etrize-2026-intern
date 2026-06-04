package com.spring.certificate.service;

import java.util.List;

import com.spring.certificate.vo.CertificateVo;

public interface certificateService {

	public void insertCertificateList(List<CertificateVo> list) throws Exception;

	public void deleteCertificate(String seq) throws Exception;

	public List<CertificateVo> getCertificate(String seq) throws Exception;

	public void syncCertificateList(String seq, List<CertificateVo> certificateList) throws Exception;
}
