package service.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.article.ArticleDAO;
import model.article.ArticleDTO;
import model.member.MemberDAO;
import model.member.MemberDTO;
import service.Action;

public class MemberFindPassProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String userId = request.getParameter("userId");
		
		MemberDAO dao = MemberDAO.getInstance();
		
		MemberDTO member = dao.getMemberPass(userId, email);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print("<script>");
		out.print("alert('회원님의 비밀번호는 " + member.getUserPass() + " 입니다.');");
		out.print("window.opener.location.href='/Member?cmd=member_login.do';");
		out.print("self.close();");
		out.print("</script>");
	}

}
