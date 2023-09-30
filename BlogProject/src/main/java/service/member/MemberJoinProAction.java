package service.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.article.ArticleDAO;
import model.article.ArticleDTO;
import service.Action;

public class MemberJoinProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		ArticleDAO dao = ArticleDAO.getInstance();
		ArticleDTO board = new ArticleDTO();
		
		int nowpage = Integer.parseInt(request.getParameter("page"));
		
		board.setName(request.getParameter("name"));
		board.setPass(request.getParameter("pass"));
		board.setEmail(request.getParameter("email"));
		board.setSubject(request.getParameter("subject"));
		board.setContents(request.getParameter("contents"));
		
		int row = dao.boardWrite(board);
	
		request.setAttribute("page", nowpage);
		request.setAttribute("row", row);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Board/board_write_pro.jsp");
		rd.forward(request, response);


	}

}
