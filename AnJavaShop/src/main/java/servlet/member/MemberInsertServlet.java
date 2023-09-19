package servlet.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shop.util.UserSHA256;
import shop.member.MemberDAO;
import shop.member.MemberDTO;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/Member/userinfo_insert.do")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("userinfo_insert.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO dto = new MemberDTO();
		
		dto.setUserid(request.getParameter("userid"));
		dto.setUsername(request.getParameter("username"));
		dto.setPass(UserSHA256.getSHA256(request.getParameter("password")));
		dto.setZipcode(request.getParameter("zipcode"));
		dto.setAddress(request.getParameter("address"));
		dto.setDe_address(request.getParameter("detailaddress"));
		
		int row = dao.MemberInsert(dto);
		request.setAttribute("row", row);
		
		RequestDispatcher rd = request.getRequestDispatcher("userinfo_insert_pro.jsp");
		rd.forward(request, response);
	}

}
