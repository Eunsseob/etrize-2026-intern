package com.spring.recruit.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.recruit.dao.RecruitDao;
import com.spring.recruit.vo.RecruitVo;
import com.spring.user.vo.UserVo;

@Repository
public class RecruitDaoImpl implements RecruitDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	// 로그인 메소드
	@Override
	public RecruitVo userLogin(RecruitVo recruitVo) throws Exception {
		   
		RecruitVo result = sqlSession.selectOne("user.userLogin1", recruitVo);
		
		return result;
	}
		
	// 회원가입 메소드
	@Override
	public int userSignup(RecruitVo recruitVo) throws Exception {
			   
		return sqlSession.insert("user.userSignup1", recruitVo);
	}
	
	// 넣는 방법
	public void insertRecruit(RecruitVo recruitVo) throws Exception {
	    sqlSession.insert("recruit.insertRecruit", recruitVo);
	}

	// 마지막 시퀀스 가져오는 방법
	public String getLastSeq() throws Exception {
	    return sqlSession.selectOne("recruit.getLastSeq");
	}
}
