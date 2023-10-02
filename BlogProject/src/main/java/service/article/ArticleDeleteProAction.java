package service.article;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.article.ArticleDAO;
import service.Action;

public class ArticleDeleteProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nowpage = Integer.parseInt(request.getParameter("page"));
		int id = Integer.parseInt(request.getParameter("id"));
		
		ArticleDAO dao = ArticleDAO.getInstance();
		int row = dao.articleDelete(id);

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(row==1) {
			out.print("<script>");
			out.print("alert('글이삭제되었습니다');");
			out.print("window.opener.location.href='/Article?cmd=article_list.do&page="+ nowpage +"';");
			out.print("self.close();");
			out.print("</script>");
		}else {
			out.print("<script>");
			out.print("alert('오류');");
			out.print("history.back()");
			out.print("</script>");			
		}


	}

}
