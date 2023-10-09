package service.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Rq.Rq;
import model.member.MemberDAO;
import model.member.MemberDTO;
import service.Action;

public class MemberModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberDAO dao = MemberDAO.getInstance();
		Rq rq = new Rq(request, response);
		MemberDTO member = dao.memberSelect(rq.getLoginedMemberId());
		
		request.setAttribute("member", member);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Member/member_modify.jsp");
		rd.forward(request, response);

	}

}
