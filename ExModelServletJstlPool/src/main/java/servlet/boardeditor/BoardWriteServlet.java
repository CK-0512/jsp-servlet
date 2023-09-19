package servlet.boardeditor;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.boardeditor.BoardEditorDAO;
import model.boardeditor.BoardEditorDTO;

/**
 * Servlet implementation class BoardWriteServlet
 */
@WebServlet("/BoardEditor/board_write.do")
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardWriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int nowpage = 1;
		if(request.getParameter("page") != null && !request.getParameter("page").equals("")) {
		    nowpage=Integer.parseInt(request.getParameter("page"));
		}
		
		request.setAttribute("page", nowpage);
		
		RequestDispatcher rd = request.getRequestDispatcher("board_write.jsp");
		rd.forward(request, response);
				
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		BoardEditorDAO dao = BoardEditorDAO.getInstance();
		BoardEditorDTO board = new BoardEditorDTO();
		
		int nowpage = Integer.parseInt(request.getParameter("page"));
		
		board.setName(request.getParameter("name"));
		board.setPass(request.getParameter("pass"));
		board.setSubject(request.getParameter("subject"));
		board.setContents(request.getParameter("contents"));
		
		int row = dao.boardWrite(board);
	
		request.setAttribute("page", nowpage);
		request.setAttribute("row", row);
		
		RequestDispatcher rd = request.getRequestDispatcher("board_write_pro.jsp");
		rd.forward(request, response);

	}

}
