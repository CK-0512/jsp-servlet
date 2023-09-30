package service.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.article.ArticleDAO;
import model.article.ArticleDTO;
import service.Action;

public class MemberPassChkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int nowpage = Integer.parseInt(request.getParameter("page"));
		
		ArticleDAO dao = ArticleDAO.getInstance();
		
		ArticleDTO board = dao.boardSelect(idx);
		
		request.setAttribute("page", nowpage);
		request.setAttribute("board", board);
	
		RequestDispatcher rd = request.getRequestDispatcher("/Board/board_modify.jsp");
		rd.forward(request, response);


	}

}
