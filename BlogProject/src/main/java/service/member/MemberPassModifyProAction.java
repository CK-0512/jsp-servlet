package service.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Rq.Rq;
import model.member.MemberDAO;
import service.Action;

public class MemberPassModifyProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String userPass = request.getParameter("userPass");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		Rq rq = new Rq(request, response);

		int row = dao.modifyPass(rq.getLoginedMemberId(), userPass);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if (row == 1) {
			rq.logout();
			
			out.print("<script>");
			out.print("alert('비밀번호 변경 성공, 다시 로그인 해주십시오.');");
			out.print("window.opener.location.href='/Member?cmd=member_login.do';");
			out.print("self.close();");
			out.print("</script>");
		} else {
			out.print("<script>");
			out.print("alert('오류');");
			out.print("window.opener.history.back();");
			out.print("self.close();");
			out.print("</script>");
		}

	}

}
