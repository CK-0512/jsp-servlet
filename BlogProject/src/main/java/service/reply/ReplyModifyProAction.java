package service.reply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.reply.ReplyDAO;
import model.reply.ReplyDTO;
import service.Action;

public class ReplyModifyProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int nowpage = Integer.parseInt(request.getParameter("page"));
		String body = request.getParameter("body");

		ReplyDAO dao = ReplyDAO.getInstance();
		ReplyDTO reply = dao.getReplyById(id);
		
		int row = dao.replyModify(id, body);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(row==1) {
			out.print("<script>");
			out.print("alert('댓글이 수정되었습니다');");
			out.print("location.href='/Article?cmd=article_view&id="+reply.getArticleId()+"&page="+ nowpage +"';");
			out.print("</script>");
		}else {
			out.print("<script>");
			out.print("alert('오류');");
			out.print("history.back()");
			out.print("</script>");			
		}

	}

}
