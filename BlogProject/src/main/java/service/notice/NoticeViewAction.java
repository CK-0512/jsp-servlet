package service.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.notice.NoticeDAO;
import model.notice.NoticeDTO;
import service.Action;

public class NoticeViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		NoticeDAO dao = NoticeDAO.getInstance();
		NoticeDTO notice = dao.noticeSelect(id);
		dao.readNotice(id);

		//forword
		RequestDispatcher rd = request.getRequestDispatcher(notice.getUrl());
		rd.forward(request, response);


	}

}
