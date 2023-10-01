package service.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Rq.Rq;
import model.member.MemberDAO;
import service.Action;

public class MemberPassChkProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userPass = request.getParameter("userPass");
		
		Rq rq = new Rq(request, response);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if (rq.getLoginedMember().getUserPass().equals(userPass)) {
			out.print("<script>");
			out.print("alert('인증 성공');");
			out.print("window.opener.location.href='/Member?cmd=member_modify.do';");
			out.print("self.close();");
			out.print("</script>");
		} else {
			out.print("<script>");
			out.print("alert('비밀번호 오류');");
			out.print("window.opener.history.back();");
			out.print("self.close();");
			out.print("</script>");
		}

	}

}
