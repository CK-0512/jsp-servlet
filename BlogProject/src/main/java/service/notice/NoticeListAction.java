package service.notice;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Rq.Rq;
import model.notice.NoticeDAO;
import model.notice.NoticeDTO;
import model.util.PageIndex;
import service.Action;

public class NoticeListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Rq rq = new Rq(request, response);
		NoticeDAO dao = NoticeDAO.getInstance();

		String url = "notice_list.jsp";
		int totcount = 0;// 게시글 총수
		totcount = dao.noticeCount(rq.getLoginedMemberId());


		int nowpage = 1;// 현재페이지 초기화
		int maxlist = 10;// 페이지당 글수
		int totpage = 1;// 전체페이지

		// 총 페이지수 계산
		if (totcount % maxlist == 0) {
			totpage = totcount / maxlist;
		} else {
			totpage = totcount / maxlist + 1;
		}

		// 페이지번호가 넘어온 경우
		if (request.getParameter("page") != null)
			nowpage = Integer.parseInt(request.getParameter("page"));

		// 페이지별 출력될 시작, 끝번호
		int startpage = (nowpage - 1) * maxlist + 1;
		int endpage = nowpage * maxlist;
		int listcount = totcount - ((nowpage - 1) * maxlist);// 웹에서 번호출력용

		List<NoticeDTO> list = null;
		list = dao.noticeList(startpage, endpage, rq.getLoginedMemberId());


		// 페이지 처리
		String pageSkip = "";
		pageSkip = PageIndex.pageList(nowpage, totpage, url, "");
		
		// jsp에서 사용될 값을 request 내장객체에 담기
		request.setAttribute("page", nowpage);
		request.setAttribute("totpage", totpage);
		request.setAttribute("totcount", totcount);
		request.setAttribute("listcount", listcount);
		request.setAttribute("list", list);
		request.setAttribute("pageSkip", pageSkip);

		RequestDispatcher rd = request.getRequestDispatcher("/Notice/notice_list.jsp");
		rd.forward(request, response);

	}

}
