package service.blog;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.blog.BlogDAO;
import service.Action;

public class BlogModifyProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String introduction = request.getParameter("body");

		BlogDAO dao = BlogDAO.getInstance();
		
		int row = dao.blogModify(introduction);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(row==1) {
			out.print("<script>");
			out.print("alert('소개글이 수정되었습니다');");
			out.print("location.href='/BlogInformation?cmd=blog_view';");
			out.print("</script>");
		}else {
			out.print("<script>");
			out.print("alert('오류');");
			out.print("history.back();");
			out.print("</script>");			
		}

	}

}
