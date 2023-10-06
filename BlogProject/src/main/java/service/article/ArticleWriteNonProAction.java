package service.article;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.article.ArticleDAO;
import model.article.ArticleDTO;
import model.blog.BlogDAO;
import model.nonMember.NonMemberDAO;
import model.nonMember.NonMemberDTO;
import model.notice.NoticeDAO;
import model.notice.NoticeDTO;
import service.Action;

public class ArticleWriteNonProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		ArticleDAO dao = ArticleDAO.getInstance();
		ArticleDTO article = new ArticleDTO();
		NonMemberDAO ndao = NonMemberDAO.getInstance();
		NonMemberDTO non = new NonMemberDTO();

		article.setMemberType(2);
		article.setTitle(request.getParameter("title"));
		article.setBody(request.getParameter("body"));
		article.setBoardId(6);
		article.setMemberId(1);

		int row = dao.articleWrite(article);
		if (row == 1) {
			BlogDAO blog = BlogDAO.getInstance();
			blog.updateBlog();
		}

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		if (row == 1) {
			int articleId = dao.lastArticleId();
			non.setNickname(request.getParameter("nickname"));
			non.setPass(request.getParameter("pass"));
			non.setType(1);
			non.setArticleId(articleId);
			ndao.insertNon(non);
			NoticeDAO nDao = NoticeDAO.getInstance();
			NoticeDTO notice = new NoticeDTO();
			notice.setType(1);
			notice.setMemberId(1);
			notice.setArticleId(articleId);
			notice.setUrl("/Article?cmd=article_view&id=" + articleId);
			nDao.sendNotice(notice);

			out.print("<script>");
			out.print("alert('글이 생성되었습니다');");
			out.print("location.href='/Article?cmd=article_list_all';");
			out.print("</script>");
		} else {
			out.print("<script>");
			out.print("alert('오류');");
			out.print("history.back()");
			out.print("</script>");
		}
	}
}
