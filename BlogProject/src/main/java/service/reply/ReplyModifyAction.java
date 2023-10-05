package service.reply;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.reply.ReplyDAO;
import model.reply.ReplyDTO;
import service.Action;

public class ReplyModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int nowPage = Integer.parseInt(request.getParameter("page"));
		
		ReplyDAO dao = ReplyDAO.getInstance();
		
		ReplyDTO reply = dao.getReplyById(id);
		
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

		String jsonResponse = "{\"id\":" + reply.getId() + ",\"body\":\"" + reply.getBody() + "\",\"page\":\"" + nowPage + "\"}";

		response.getWriter().write(jsonResponse);

	}

}
