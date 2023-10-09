package service.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberDTO;
import service.Action;

public class MemberFindIdProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		MemberDTO member = dao.getMemberByEmail(email);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print("<script>");
		out.print("alert('회원님의 아이디는 " + member.getUserid() + " 입니다.');");
		out.print("location.href='/Member?cmd=member_login.do';");
		out.print("</script>");
	}

}
