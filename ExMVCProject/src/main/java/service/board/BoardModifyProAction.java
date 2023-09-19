package service.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardDAO;
import model.board.BoardDTO;
import service.Action;

public class BoardModifyProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nowpage = Integer.parseInt(request.getParameter("page"));
		
		BoardDTO board = new BoardDTO();
		board.setIdx(Integer.parseInt(request.getParameter("idx")));
		board.setEmail(request.getParameter("email"));
		board.setSubject(request.getParameter("subject"));
		board.setContents(request.getParameter("contents"));
		board.setPass(request.getParameter("pass"));

		BoardDAO dao = BoardDAO.getInstance();
		
		int row = dao.boardModify(board);
		//System.out.println("idx : " + board.getIdx());
		//System.out.println("pass : " + board.getPass());
		//System.out.println("row : " + row);
		
		response.sendRedirect("/Board?cmd=board_list.do&page="+nowpage);


	}

}
