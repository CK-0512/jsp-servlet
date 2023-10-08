package service.reply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Rq.Rq;
import model.article.ArticleDAO;
import model.article.ArticleDTO;
import model.nonMember.NonMemberDAO;
import model.nonMember.NonMemberDTO;
import model.notice.NoticeDAO;
import model.notice.NoticeDTO;
import model.reply.ReplyDAO;
import model.reply.ReplyDTO;
import service.Action;

public class ReplyWriteNonProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int nowpage = Integer.parseInt(request.getParameter("page"));
		int articleId = Integer.parseInt(request.getParameter("articleId"));
		ReplyDAO dao = ReplyDAO.getInstance();
		ReplyDTO reply = new ReplyDTO();
		
		reply.setBody(request.getParameter("body"));
		reply.setArticleId(articleId);
		reply.setMemberId(1);
		reply.setMemberType(2);
		
		int row = dao.replyWrite(reply);
		NonMemberDAO ndao = NonMemberDAO.getInstance();
		NonMemberDTO non = new NonMemberDTO();
		non.setNickname(request.getParameter("nickname"));
		non.setPass(request.getParameter("pass"));
		non.setType(2);
		non.setArticleId(articleId);
		ndao.insertNon(non);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(row==1) {
			NoticeDAO nDao = NoticeDAO.getInstance();
			NoticeDTO notice = new NoticeDTO();
			ArticleDAO aDao = ArticleDAO.getInstance();
			ArticleDTO article = aDao.articleSelect(articleId);
			notice.setType(2);
			notice.setArticleId(articleId);
			notice.setMemberId(article.getMemberId());
			notice.setUrl("/Article?cmd=article_view&id="+articleId);
			nDao.sendNotice(notice);
			out.print("<script>");
			out.print("alert('댓글이 생성되었습니다');");
			out.print("location.href='/Article?cmd=article_view&id="+articleId+"&page="+ nowpage +"';");
			out.print("</script>");
		}else {
			out.print("<script>");
			out.print("alert('오류');");
			out.print("history.back()");
			out.print("</script>");			
		}
	}
}
