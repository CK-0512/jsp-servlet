package service.article;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Rq.Rq;
import model.article.ArticleDAO;
import model.article.ArticleDTO;
import service.Action;

public class ArticleWriteProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		ArticleDAO dao = ArticleDAO.getInstance();
		ArticleDTO article = new ArticleDTO();
		Rq rq = new Rq(request, response);
		
		int nowpage = Integer.parseInt(request.getParameter("page"));
		
		article.setTitle(request.getParameter("title"));
		article.setBody(request.getParameter("body"));
		article.setMemberId(rq.getLoginedMemberId());
		article.setMemberType(rq.getLoginedMember().getAuthLevel());
		
		int row = dao.articleWrite(article);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(row==1) {
			out.print("<script>");
			out.print("alert('글이 생성되었습니다');");
			out.print("window.opener.location.href='/Article?cmd=article_list.do?page="+ nowpage +"';");
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
