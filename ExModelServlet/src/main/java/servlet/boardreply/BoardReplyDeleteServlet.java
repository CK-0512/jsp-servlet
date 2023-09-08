package servlet.boardreply;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.boardreply.BoardReplyDAO;

/**
 * Servlet implementation class BoardReplyDeleteServlet
 */
@WebServlet("/BoardReply/board_delete.do")
public class BoardReplyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReplyDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));			
		
		request.setAttribute("idx", idx);
		RequestDispatcher dispater = 
				request.getRequestDispatcher("board_delete.jsp");
		dispater.forward(request, response);

		//response.sendRedirect("board_list");
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		String pass = request.getParameter("pass");
		
		BoardReplyDAO dao = BoardReplyDAO.getInstance();
		int cnt = dao.replySearch(idx);// 답변글 유무 검사
		int row = 0;
		
		if(cnt==0) {//답변글이 없으면
			row=dao.boardDelete(idx,pass);
		}else {
			row = -1; //답변글이 있을경우 삭제 불가
		}
		
		request.setAttribute("row", row);

		RequestDispatcher dispater = 
				request.getRequestDispatcher("board_delete_pro.jsp");
		dispater.forward(request, response);

		//response.sendRedirect("board_list");
		
	}

}
