package com.spring.certificate.dao;

import java.util.List;

import com.spring.certificate.vo.CertificateVo;

public interface CertificateDao {

	public void insertCertificateList(List<CertificateVo> list) throws Exception;

	public void deleteCertificate(String seq) throws Exception;

	public List<CertificateVo> getCertificate(String seq) throws Exception;

	public int updateCertificateList(CertificateVo vo) throws Exception;
	
    public void deleteCertificateByCertSeq(CertificateVo vo) throws Exception;
    
    public void insertCertificate(CertificateVo vo) throws Exception;

	public void syncCertificateList(String seq, List<CertificateVo> certificateList);
}
