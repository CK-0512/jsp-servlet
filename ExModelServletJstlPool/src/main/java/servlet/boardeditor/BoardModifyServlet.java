package servlet.boardeditor;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardDAO;
import model.board.BoardDTO;
import model.boardeditor.BoardEditorDAO;
import model.boardeditor.BoardEditorDTO;

/**
 * Servlet implementation class BoardModifyServlet
 */
@WebServlet("/BoardEditor/board_modify.do")
public class BoardModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int nowpage = Integer.parseInt(request.getParameter("page"));
		
		BoardEditorDAO dao = BoardEditorDAO.getInstance();
		
		BoardEditorDTO board = dao.boardSelect(idx);
		
		request.setAttribute("page", nowpage);
		request.setAttribute("board", board);
	
		RequestDispatcher rd = request.getRequestDispatcher("board_modify.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int nowpage = Integer.parseInt(request.getParameter("page"));
		
		BoardEditorDTO board = new BoardEditorDTO();
		board.setIdx(Integer.parseInt(request.getParameter("idx")));
		board.setSubject(request.getParameter("subject"));
		board.setContents(request.getParameter("contents"));
		board.setPass(request.getParameter("pass"));

		BoardEditorDAO dao = BoardEditorDAO.getInstance();
		
		int row = dao.boardModify(board);
		//System.out.println("idx : " + board.getIdx());
		//System.out.println("pass : " + board.getPass());
		//System.out.println("row : " + row);
		
		response.sendRedirect("/BoardEditor/board_list.do?page="+nowpage);
	}

}
