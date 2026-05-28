package com.spring.certificate.dao;

import java.util.List;

import com.spring.certificate.vo.CertificateVo;

public interface CertificateDao {

	public void insertCertificateList(List<CertificateVo> list) throws Exception;

}
