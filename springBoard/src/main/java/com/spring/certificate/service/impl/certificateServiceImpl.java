package com.spring.certificate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional(rollbackFor = Exception.class)
    public void syncCertificateList(String seq, List<CertificateVo> uiList) throws Exception {
        // 1. 현재 DB에 저장된 전체 리스트를 가져옴
        List<CertificateVo> dbList = certificateDao.getCertificate(seq);

        // 2. [INSERT & UPDATE] 화면(UI)에서 넘어온 리스트 처리
        if (uiList != null) {
            for (CertificateVo uiCer : uiList) {
                // 이름이 없으면 빈 행으로 간주하고 건너뜀
                if (uiCer.getQualifiName() == null || uiCer.getQualifiName().trim().isEmpty()) continue;

                if (uiCer.getCertSeq() != null && !uiCer.getCertSeq().isEmpty()) {
                    // [UPDATE] 기존 데이터라면 수정
                	certificateDao.updateCertificateList(uiCer);
                } else {
                    // [INSERT] 새로운 데이터라면 등록
                    uiCer.setSeq(seq);
                    certificateDao.insertCertificateList(dbList);
                }
            }
        }

        // 3. [DELETE] DB에는 있는데 UI에는 없는 항목 삭제
        if (dbList != null) {
            for (CertificateVo dbCer : dbList) {
                boolean isExist = false;
                if (uiList != null) {
                    for (CertificateVo uiCer : uiList) {
                        // certSeq가 일치하는 것이 하나라도 있으면 삭제 대상 아님
                        if (uiCer.getCertSeq() != null && uiCer.getCertSeq().equals(dbCer.getCertSeq())) {
                            isExist = true;
                            break;
                        }
                    }
                }
                if (!isExist) {
                    // 화면에서 삭제된 항목이므로 DB에서 삭제
                	certificateDao.deleteCertificateByCertSeq(seq);
                }
            }
        }
    }
}
