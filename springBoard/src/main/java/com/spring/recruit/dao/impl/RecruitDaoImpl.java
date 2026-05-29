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
		   
		RecruitVo result = sqlSession.selectOne("recruit.userLogin1", recruitVo);
		
		return result;
	}
		
	// 회원가입 메소드
	@Override
	public int userSignup(RecruitVo recruitVo) throws Exception {
			   
		return sqlSession.insert("recruit.userSignup1", recruitVo);
	}
	
	// 넣는 방법
	public int updateRecruit(RecruitVo recruitVo) throws Exception {
	    
		return sqlSession.update("recruit.updateRecruit", recruitVo);
	}

	// 저장한거 가져오기
	public RecruitVo getRecruit(String seq) throws Exception {
	    return sqlSession.selectOne("recruit.getRecruit", seq);
	}
}
