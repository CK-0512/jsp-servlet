package service.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberDTO;
import service.Action;

public class MemberModifyProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO member = new MemberDTO();
		
		member.setNickname(request.getParameter("nickname"));
		member.setEmail(request.getParameter("email"));
		
		int row = dao.memberUpdate(member);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print("<script>");
		out.print("alert('회원정보 수정이 완료되었습니다.');");
		out.print("window.opener.location.href='/Member?cmd=member_modify.do';");
		out.print("self.close();");
		out.print("</script>");


	}

}
