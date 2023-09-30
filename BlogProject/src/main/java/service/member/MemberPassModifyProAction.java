package service.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.article.ArticleDAO;
import model.article.ArticleDTO;
import service.Action;

public class MemberPassModifyProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nowpage = Integer.parseInt(request.getParameter("page"));
		
		ArticleDTO board = new ArticleDTO();
		board.setIdx(Integer.parseInt(request.getParameter("idx")));
		board.setEmail(request.getParameter("email"));
		board.setSubject(request.getParameter("subject"));
		board.setContents(request.getParameter("contents"));
		board.setPass(request.getParameter("pass"));

		ArticleDAO dao = ArticleDAO.getInstance();
		
		int row = dao.boardModify(board);
		//System.out.println("idx : " + board.getIdx());
		//System.out.println("pass : " + board.getPass());
		//System.out.println("row : " + row);
		
		response.sendRedirect("/Board?cmd=board_list.do&page="+nowpage);


	}

}
