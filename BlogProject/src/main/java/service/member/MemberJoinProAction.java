package service.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberDTO;
import service.Action;

public class MemberJoinProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO member = new MemberDTO();
		
		member.setNickname(request.getParameter("nickname"));
		member.setUserid(request.getParameter("userId"));
		member.setUserPass(request.getParameter("userPass"));
		member.setEmail(request.getParameter("email"));
		
		int row = dao.memberWrite(member);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print("<script>");
		out.print("alert('회원가입 성공');");
		out.print("location.href='/Member?cmd=member_login.do';");
		out.print("</script>");

	}

}
