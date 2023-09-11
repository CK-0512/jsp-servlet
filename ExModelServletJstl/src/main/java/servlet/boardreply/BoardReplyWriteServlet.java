package servlet.boardreply;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.boardreply.BoardReplyDAO;
import model.boardreply.BoardReplyDTO;

/**
 * Servlet implementation class BoardReplyWriteServlet
 */
@WebServlet("/BoardReply/board_write.do")
public class BoardReplyWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReplyWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		RequestDispatcher rd = request.getRequestDispatcher("board_write.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		BoardReplyDTO board = new BoardReplyDTO();
		
		board.setName(request.getParameter("name"));
		board.setEmail(request.getParameter("email"));
		board.setSubject(request.getParameter("subject"));
		board.setContents(request.getParameter("contents"));
		board.setPass(request.getParameter("pass"));
		board.setParent(Integer.parseInt(request.getParameter("parent")));
		board.setRealparent(Integer.parseInt(request.getParameter("realparent")));
		board.setDepth(Integer.parseInt(request.getParameter("depth")));
		board.setIndent(Integer.parseInt(request.getParameter("indent")));
		
		
		BoardReplyDAO dao = BoardReplyDAO.getInstance();
		
		int row = dao.boardWrite(board);
		
		request.setAttribute("row", row);

		RequestDispatcher rd = request.getRequestDispatcher("board_write_pro.jsp");
		rd.forward(request, response);

	}

}
