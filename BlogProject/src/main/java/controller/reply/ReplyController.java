package controller.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Action;
import service.reply.ReplyDeleteProAction;
import service.reply.ReplyModifyAction;
import service.reply.ReplyModifyProAction;
import service.reply.ReplyWriteProAction;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/Reply")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyController() {
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
		
		if(cmd.equals("reply_modify")) {//입력 폼
			action = new ReplyModifyAction();
		}else if(cmd.equals("reply_write_pro")) {//입력 처리
			action = new ReplyWriteProAction();
		}else if(cmd.equals("reply_modify_pro")) {
			action = new ReplyModifyProAction();
		}else if(cmd.equals("reply_delete_pro")) {
			action = new ReplyDeleteProAction();
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
