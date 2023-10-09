package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Action;
import service.member.MemberDeleteProAction;
import service.member.MemberFindIdAction;
import service.member.MemberFindIdProAction;
import service.member.MemberFindPassAction;
import service.member.MemberFindPassProAction;
import service.member.MemberIdChkAction;
import service.member.MemberJoinAction;
import service.member.MemberJoinProAction;
import service.member.MemberListAction;
import service.member.MemberLoginAction;
import service.member.MemberLoginProAction;
import service.member.MemberLogoutProAction;
import service.member.MemberModifyAction;
import service.member.MemberModifyProAction;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/Member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		System.out.println("BoardController에서 요청을 받음 :" + cmd);
		
		Action action = null;
		
		if(cmd.equals("member_list.do")) {
			action = new MemberListAction();
		}else if(cmd.equals("member_idChk.do")) {
			action = new MemberIdChkAction();
		}else if(cmd.equals("member_join.do")) {
			action = new MemberJoinAction();
		}else if(cmd.equals("member_join_pro.do")) {
			action = new MemberJoinProAction();
		}else if(cmd.equals("member_login.do")) {
			action = new MemberLoginAction();
		}else if(cmd.equals("member_login_pro.do")) {
			action = new MemberLoginProAction();
		}else if(cmd.equals("member_logout_pro.do")) {
			action = new MemberLogoutProAction();
		}else if(cmd.equals("member_modify.do")) {
			action = new MemberModifyAction();
		}else if(cmd.equals("member_modify_pro.do")) {
			action = new MemberModifyProAction();
		}else if(cmd.equals("member_delete_pro.do")) {
			action = new MemberDeleteProAction();
		}else if(cmd.equals("member_findId.do")) {
			action = new MemberFindIdAction();
		}else if(cmd.equals("member_findId_pro.do")) {
			action = new MemberFindIdProAction();
		}else if(cmd.equals("member_findPass.do")) {
			action = new MemberFindPassAction();
		}else if(cmd.equals("member_findPass_pro.do")) {
			action = new MemberFindPassProAction();
		}
		
		action.execute(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
