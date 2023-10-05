package service.article;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Rq.Rq;
import model.article.ArticleDAO;
import model.article.ArticleDTO;
import model.blog.BlogDAO;
import model.notice.NoticeDAO;
import model.notice.NoticeDTO;
import service.Action;

public class ArticleWriteProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		ArticleDAO dao = ArticleDAO.getInstance();
		ArticleDTO article = new ArticleDTO();
		Rq rq = new Rq(request, response);
		
		article.setTitle(request.getParameter("title"));
		article.setBody(request.getParameter("body"));
		if (request.getParameter("boardId") == null) {
			article.setBoardId(6);
		}
		else 
			article.setBoardId(Integer.parseInt(request.getParameter("boardId")));
		article.setMemberId(rq.getLoginedMemberId());
		int memberType = 0;
		if (rq.getLoginedMemberId() != 0) memberType = 1;
		article.setMemberType(memberType);
		
		int row = dao.articleWrite(article);
		if(row == 1) {
			BlogDAO blog = BlogDAO.getInstance();
			blog.updateBlog();
		}
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(row==1) {
			if (request.getParameter("boardId") == null) {
				int articleId = dao.lastArticleId();
				NoticeDAO nDao = NoticeDAO.getInstance();
				NoticeDTO notice = new NoticeDTO();
				notice.setType(1);
				notice.setMemberId(1);
				notice.setArticleId(articleId);
				notice.setUrl("/Article?cmd=article_view&id="+articleId);
				nDao.sendNotice(notice);
			}		
			out.print("<script>");
			out.print("alert('글이 생성되었습니다');");
			out.print("location.href='/Article?cmd=article_list_all';");
			out.print("</script>");
		}else {
			out.print("<script>");
			out.print("alert('오류');");
			out.print("history.back()");
			out.print("</script>");			
		}
	}
}
