package service.reply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.nonMember.NonMemberDAO;
import model.nonMember.NonMemberDTO;
import model.reply.ReplyDAO;
import model.reply.ReplyDTO;
import service.Action;

public class ReplyDeleteNonProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("id"));
		int nowpage = Integer.parseInt(request.getParameter("page"));
		ReplyDAO dao = ReplyDAO.getInstance();
		ReplyDTO reply = dao.getReplyById(id);
		NonMemberDAO ndao = NonMemberDAO.getInstance();
		NonMemberDTO non = new NonMemberDTO();
		non = ndao.NonMemberSelectOnReply(reply.getId());
		System.out.println(non.getPass());
		System.out.println(request.getParameter("pass"));
		if (non.getPass() != request.getParameter("pass")) {
			out.print("<script>");
			out.print("alert('비밀번호 불일치');");
			out.print("history.back();");
			out.print("</script>");
			return;
		}

		int row = dao.replyDelete(id);

		
		if(row==1) {
			out.print("<script>");
			out.print("alert('댓글이 삭제되었습니다');");
			out.print("location.href='/Article?cmd=article_view&id="+reply.getArticleId()+"&page="+ nowpage +"';");
			out.print("</script>");
		}else {
			out.print("<script>");
			out.print("alert('오류');");
			out.print("history.back();");
			out.print("</script>");			
		}


	}

}
