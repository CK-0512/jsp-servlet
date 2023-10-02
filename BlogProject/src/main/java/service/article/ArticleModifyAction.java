package service.article;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.article.ArticleDAO;
import model.article.ArticleDTO;
import service.Action;

public class ArticleModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int nowpage = Integer.parseInt(request.getParameter("page"));
		
		ArticleDAO dao = ArticleDAO.getInstance();
		
		ArticleDTO board = dao.articleSelect(id);
		
		request.setAttribute("page", nowpage);
		request.setAttribute("board", board);
	
		RequestDispatcher rd = request.getRequestDispatcher("/Article/article_modify.jsp");
		rd.forward(request, response);


	}

}
