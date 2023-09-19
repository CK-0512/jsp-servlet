package servlet.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.member.MemberDAO;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/Member/loginIdDupChk.do")
public class MemberLoginIdChkDupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginIdChkDupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = String.valueOf(request.getParameter("userId"));

		MemberDAO dao = MemberDAO.getInstance();

		boolean isExists = dao.isAlreadyExistsUserId(userId);

		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

		String jsonResponse = "{\"success\": " + !isExists + "}";

		response.getWriter().write(jsonResponse);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
