package board;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/boardUpdate")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nowPage = request.getParameter("nowPage");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		Board dbBean = (Board)session.getAttribute("bean");
		
		Board upBean = new Board();
		upBean.setBoardTitle(request.getParameter("BOARD_TITLE"));
		upBean.setBoardComment(request.getParameter("BOARD_COMMENT"));
		upBean.setBoardNum(dbBean.getBoardNum());
		
		BoardDao bDao = new BoardDao();
		bDao.updateBoard(upBean);

	    response.sendRedirect("read.jsp?boardNum=" + upBean.getBoardNum());
	}

}
