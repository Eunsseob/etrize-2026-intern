package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import board.DBConnectionMgr;

public class MemberDao {

	private DBConnectionMgr pool = DBConnectionMgr.getInstance();
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql;

	// 아이디 체크
	public boolean checkId(String userId) {
		boolean flag = false;
		
		try {
			con = pool.getConnection();
			sql = "select USER_ID from USER_INFO where USER_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			flag = rs.next();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	// login
		public String loginMember(String userId, String userPw) {
			Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    String userName = null;
			
			try {
				con = pool.getConnection();
				sql = "select USER_NAME from USER_INFO where USER_ID=? and USER_PW=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, userId); // 프롬프트 순서대로 값들어가는것을 지정해준다.
				pstmt.setString(2, userPw);
				
				rs = pstmt.executeQuery();
				if (rs.next()) {
		            // 로그인 성공 시 DB에서 이름을 꺼내어 저장
		            userName = rs.getString("USER_NAME"); 
		        }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return userName;
		}
		
	// 회원가입
		public boolean insertMember(Member bean) {
			boolean flag = false;
			try {
				con = pool.getConnection();
				sql = "insert into USER_INFO (USER_ID, USER_PW, USER_NAME, USER_PHONE1, USER_PHONE2, USER_PHONE3, CREATOR, USER_ADDR1, USER_COMPANY) values(?,?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bean.getUserId());
				pstmt.setString(2, bean.getUserPw());
				pstmt.setString(3, bean.getUserName());
				pstmt.setString(4, bean.getUserPhone1());
				pstmt.setString(5, bean.getUserPhone2());
				pstmt.setString(6, bean.getUserPhone3());
				pstmt.setString(7, bean.getCreator());
				pstmt.setString(8, bean.getUserAddr1());
				pstmt.setString(9, bean.getUserCompany());				
				if(pstmt.executeUpdate() == 1)
					flag = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con);
			}
			return flag;
		}
}
