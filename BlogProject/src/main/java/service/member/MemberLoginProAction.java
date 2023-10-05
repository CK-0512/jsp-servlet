package service.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Rq.Rq;
import model.member.MemberDAO;
import model.member.MemberDTO;
import service.Action;

public class MemberLoginProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		Rq rq = new Rq(request, response);
		
		String userId = request.getParameter("userId");
		String userPass = request.getParameter("userPass");
		
		if (rq.getLoginedMemberId() != 0) {
			out.print("<script>");
			out.print("alert('로그아웃 후 이용해주세요.');");
			out.print("history.back();");
			out.print("</script>");
		}
		
		MemberDAO dao = MemberDAO.getInstance();
		
		int row = dao.memberLogin(userId, userPass);
		
		if (row == 1) {
			MemberDTO member = dao.getMember(userId, userPass);
			rq.login(member);
			
			out.print("<script>");
			out.print("alert('로그인 성공');");
			out.print("location.href='/BlogInformation?cmd=blog_view';");
			out.print("</script>");
			
		} else if (row == 0) {
			out.print("<script>");
			out.print("alert('존재하지 않는 아이디입니다.');");
			out.print("history.back();");
			out.print("</script>");
		} else if (row == -1) {
			out.print("<script>");
			out.print("alert('비밀번호 오류');");
			out.print("history.back();");
			out.print("</script>");
		}


	}

}
