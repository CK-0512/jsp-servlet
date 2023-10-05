package service.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import service.Action;

public class MemberIdChkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = String.valueOf(request.getParameter("userId"));

		MemberDAO dao = MemberDAO.getInstance();

		boolean isExists = dao.isAlreadyExistsUserId(userId);

		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

		String jsonResponse = "{\"success\": " + !isExists + ",\"loginId\":\"" + userId + "\"}";

		response.getWriter().write(jsonResponse);
	}

}
