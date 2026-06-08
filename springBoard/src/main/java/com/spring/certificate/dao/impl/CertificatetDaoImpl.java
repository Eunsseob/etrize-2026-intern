package com.spring.certificate.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.career.vo.CareerVo;
import com.spring.certificate.dao.CertificateDao;
import com.spring.certificate.vo.CertificateVo;
import com.spring.education.vo.EducationVo;
import com.spring.recruit.dao.RecruitDao;

@Repository
public class CertificatetDaoImpl implements CertificateDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	// 넣는방법
	@Override
	public void insertCertificateList(List<CertificateVo> list) throws Exception {
		for(CertificateVo cer : list) {
			sqlSession.insert("certificate.insertCertificateList", cer);
		}
	}
	
	@Override
	public void deleteCertificate(String seq) throws Exception {
	    sqlSession.delete("certificate.deleteCertificate", seq);
	}
	
	@Override
	public List<CertificateVo> getCertificate(String seq) throws Exception {
	    return sqlSession.selectList("certificate.getCertificateList", seq);
	}
	
	@Override
    public int updateCertificateList(CertificateVo vo) {
        return sqlSession.update("certificate.updateCertificateList", vo);
    }

    @Override
    public void deleteCertificateByCertSeq(CertificateVo vo) {
         sqlSession.delete("certificate.deleteCertificateByCertSeq", vo);
    }
    
    @Override
    public void insertCertificate(CertificateVo vo) throws Exception {
    	sqlSession.insert("certificate.insertCertificate", vo);
    }

	@Override
	public void syncCertificateList(String seq, List<CertificateVo> certificateList) {
		// TODO Auto-generated method stub
		
	}
}
