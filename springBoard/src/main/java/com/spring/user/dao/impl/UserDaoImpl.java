package com.spring.user.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.user.dao.UserDao;
import com.spring.user.vo.UserVo;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	// 로그인 메소드
	@Override
	public String userLogin(UserVo userVo) throws Exception {
	   
	    UserVo result = sqlSession.selectOne("user.userLogin", userVo);
	    if(result != null) {
	        return result.getUserName();
	    }
	    return null;
	}
	
	// 회원가입 메소드
		@Override
		public int userSignup(UserVo userVo) throws Exception {
		   
			return sqlSession.insert("user.userInsert", userVo);
		}
		
		// 아이디 체크 메소드
		@Override
		public int userIdcheck(String userId) throws Exception {
		   
			// 💡 int가 아니라 널을 허용하는 Integer 객체로 먼저 받습니다.
		    Integer count = sqlSession.selectOne("user.userIdcheck", userId);
		   
		    // 💡 만약 DB 결과가 null이면 0을 리턴하고, 값이 있으면 그 값을 리턴합니다.
		    return (count == null) ? 0 : count;
		}	
}
