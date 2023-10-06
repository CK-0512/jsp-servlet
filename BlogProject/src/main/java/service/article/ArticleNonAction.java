package service.article;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.nonMember.NonMemberDAO;
import service.Action;

public class ArticleNonAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nowpage = Integer.parseInt(request.getParameter("page"));
		int id = Integer.parseInt(request.getParameter("id"));
		int btnType = Integer.parseInt(request.getParameter("btnType"));
		
		NonMemberDAO dao = NonMemberDAO.getInstance();
		String pass = dao.NonMemberSelect(id).getPass();
		
		request.setAttribute("page", nowpage);
		request.setAttribute("id", id);
		request.setAttribute("btnType", btnType);
		request.setAttribute("pass", pass);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Article/article_non.jsp");
		rd.forward(request, response);

	}

}
