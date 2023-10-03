package service.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Rq.Rq;
import service.Action;

public class MemberLogoutProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Rq rq = new Rq(request, response);
		
		if (rq.getLoginedMemberId() == 0) {
			out.print("<script>");
			out.print("alert('로그인 후 이용해주세요.');");
			out.print("history.back();");
			out.print("</script>");
		}
		
		rq.logout();
		out.print("<script>");
		out.print("alert('정상적으로 로그아웃 되었습니다.');");
		out.print("location.href='/';");
		out.print("</script>");

	}

}
