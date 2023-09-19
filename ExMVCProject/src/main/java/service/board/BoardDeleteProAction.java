package service.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.board.BoardDAO;
import service.Action;

public class BoardDeleteProAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int nowpage = Integer.parseInt(request.getParameter("page"));
		int idx = Integer.parseInt(request.getParameter("idx"));
		String pass = request.getParameter("pass");
		
		BoardDAO dao = BoardDAO.getInstance();
		int row = dao.boardDelete(idx, pass);
/*		
		request.setAttribute("page", nowpage);
		request.setAttribute("row", row);
		
		RequestDispatcher rd = request.getRequestDispatcher("board_delete_pro.jsp");
		rd.forward(request, response);
*/
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(row==1) {
			out.print("<script>");
			out.print("alert('글이삭제되었습니다');");
			out.print("window.opener.location.href='/Board?cmd=board_list.do&page="+ nowpage +"';");
			out.print("self.close();");
			out.print("</script>");
		}else {
			out.print("<script>");
			out.print("alert('비번오류');");
			out.print("history.back()");
			out.print("</script>");			
		}


	}

}
