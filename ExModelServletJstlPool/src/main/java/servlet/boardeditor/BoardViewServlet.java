package servlet.boardeditor;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.boardeditor.BoardEditorDAO;
import model.boardeditor.BoardEditorDTO;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/BoardEditor/board_view.do")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewServlet() {
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
		//쿠키검사 및 설정
		boolean bool = false;
		Cookie info = null;
		Cookie[] cookies = request.getCookies();//클라이언트 쿠키 가져오기
		for(int i=0; i<cookies.length ; i++) {
			info = cookies[i];
			if(info.getName().equals("BoardEditor"+idx)) {
				bool = true;
				break;
			}
		}
		
		String newValue=""+System.currentTimeMillis();//쿠키값으로 사용할 변수
		if(!bool) {//쿠키가 존재하지 않으면
			dao.boardHits(idx);
			info = new Cookie("BoardEditor"+idx, newValue);//쿠키생성
			info.setMaxAge(60*60);// 쿠키유효시간:한시간
			response.addCookie(info);//쿠키전송
		}
				
		BoardEditorDTO board = dao.boardSelect(idx);
		//board.setContents(board.getContents().replace("\n","<br>"));
		
		request.setAttribute("page", nowpage);
		request.setAttribute("board", board);
		//forword
		RequestDispatcher rd = request.getRequestDispatcher("board_view.jsp");
		rd.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
