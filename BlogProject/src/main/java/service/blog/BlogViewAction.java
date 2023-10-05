package service.blog;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.blog.BlogDAO;
import model.blog.BlogDTO;
import service.Action;

public class BlogViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BlogDAO dao = BlogDAO.getInstance();
				
		BlogDTO blog = dao.getBlogInformation();
		
		request.setAttribute("blog", blog);
		//forword
		RequestDispatcher rd = request.getRequestDispatcher("/BlogInformation/blog_view.jsp");
		rd.forward(request, response);


	}

}
