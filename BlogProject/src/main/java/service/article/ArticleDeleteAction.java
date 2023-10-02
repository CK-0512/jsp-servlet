package service.article;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Action;

public class ArticleDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nowpage = Integer.parseInt(request.getParameter("page"));
		int id = Integer.parseInt(request.getParameter("id"));
		
		request.setAttribute("page", nowpage);
		request.setAttribute("id", id);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Article/article_delete.jsp");
		rd.forward(request, response);


	}

}
