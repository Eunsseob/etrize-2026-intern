package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDao {

	private DBConnectionMgr pool = DBConnectionMgr.getInstance();
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql;
	
	// 게시판 생성
	public boolean insertBoard(Board bean) {
		boolean flag = false;
		try {
			con = pool.getConnection();
			sql = "insert into BOARD (BOARD_TYPE, BOARD_TITLE, BOARD_COMMENT, CREATOR) values(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getBoardType());
			pstmt.setString(2, bean.getBoardTitle());
			pstmt.setString(3, bean.getBoardComment());
			pstmt.setString(4, bean.getCreator());
			if(pstmt.executeUpdate() == 1)
				flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return flag;
	}
	
	// 게시판 목록 가져오기
	public ArrayList<Board> getBoardList(String boardType, int boardNum, String boardTitle, int start, int end) {
		ArrayList<Board> alist = new ArrayList<>();	
		try {
			con = pool.getConnection();

			// 조건 + 페이징 추가
	        String sql = "SELECT * FROM BOARD ";
	        
	        // 필터링 조건 추가
	        if(boardType != null && !boardType.equals("") && !boardType.equals("전체")) {
	            sql += "WHERE BOARD_TYPE = ? ";
	        }
	        
	        sql += "ORDER BY BOARD_NUM DESC LIMIT ?, ?";
	        
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(boardType != null && !boardType.equals("") && !boardType.equals("전체")) {
	            pstmt.setString(1, boardType);
	            pstmt.setInt(2, start);
	            pstmt.setInt(3, end);
	        } else {
	            pstmt.setInt(1, start);
	            pstmt.setInt(2, end);
	        }
			
			while(rs.next()) {
				Board bean = new Board();
				bean.setBoardType(rs.getString("BOARD_TYPE"));
	            bean.setBoardNum(rs.getInt("BOARD_NUM"));
	            bean.setBoardTitle(rs.getString("BOARD_TITLE"));
	            alist.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con);
		}
		return alist;
	}
	
	// num에 해당하는 게시물 얻어오기
		public Board getBoard(int num) {
			Board bean = new Board();
			try {
				con = pool.getConnection();
				sql = "SELECT * FROM BOARD WHERE BOARD_NUM="+num;
				rs = con.createStatement().executeQuery(sql);
				if(rs.next()) {
					bean.setBoardTitle(rs.getString("BOARD_TITLE"));
					bean.setBoardComment(rs.getString("BOARD_COMMENT"));
					bean.setCreator(rs.getString("CREATOR"));
					bean.setBoardNum(rs.getInt("BOARD_NUM"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(con);
			}
			return bean;
		}
		
		// 게시물 수정
		public void updateBoard(Board bean) {
		    try {
		        con = pool.getConnection();
		        
		        // 로그 확인
		        System.out.println("=== updateBoard 호출 ===");
		        System.out.println("boardNum: " + bean.getBoardNum());
		        System.out.println("boardTitle: " + bean.getBoardTitle());
		        System.out.println("boardComment: " + bean.getBoardComment());
		        
		        sql = "UPDATE BOARD SET BOARD_TITLE=?, BOARD_COMMENT=? WHERE BOARD_NUM=?";
		        pstmt = con.prepareStatement(sql);
		        pstmt.setString(1, bean.getBoardTitle());
		        pstmt.setString(2, bean.getBoardComment());
		        pstmt.setInt(3, bean.getBoardNum());
		        
		        int result = pstmt.executeUpdate();
		        System.out.println("수정된 행 수: " + result);  // 0이면 WHERE절 문제
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        pool.freeConnection(con);
		    }
		}
}
