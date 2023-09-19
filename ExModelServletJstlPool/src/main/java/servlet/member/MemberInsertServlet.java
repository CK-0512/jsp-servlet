package servlet.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.member.MemberDAO;
import model.member.MemberDTO;
import model.util.UserSHA256;

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
		RequestDispatcher rd = 
				request.getRequestDispatcher("/Member/userinfo_insert.jsp");
		rd.forward(request, response);	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String userid = request.getParameter("userid");
		// 비번암호화
		String passwd = UserSHA256.getSHA256(request.getParameter("passwd"));
		String gubun = request.getParameter("gubun");
		String zip = request.getParameter("zip");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String[] farr = request.getParameterValues("fa");
		String job = request.getParameter("job");
		String intro = request.getParameter("intro");
		
		String  favorite=null;
		if(farr==null) {
			favorite=null;
		}else{
			favorite= farr[0];
			if(farr.length >1) {
				for(int i=1;i<farr.length;i++) {
					favorite =favorite + "," + farr[i];
				}
			}
		}
		
		MemberDTO mem = new MemberDTO();
		mem.setName(name);
		mem.setUserid(userid);
		mem.setPasswd(passwd);
		mem.setGubun(gubun);
		mem.setZip(zip);
		mem.setAddr1(addr1);
		mem.setAddr2(addr2);
		mem.setTel(tel);
		mem.setEmail(email);
		mem.setFavorite(favorite);
		mem.setJob(job);
		mem.setIntro(intro);
		
		MemberDAO dao = MemberDAO.getInstance();
		int row = dao.memberWrite(mem);

/*		
  		if(row==1){//등록성공
			response.sendRedirect("userlogin_form.do");
		}else{//실패
			response.sendRedirect("userinfo_insert.do?row=0");
		}
*/
		request.setAttribute("row", row);
		RequestDispatcher rd = 
				request.getRequestDispatcher("/Member/userinfo_insert_pro.jsp");
		rd.forward(request, response);
	}

}
