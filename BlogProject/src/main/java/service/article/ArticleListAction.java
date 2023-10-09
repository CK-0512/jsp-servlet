package service.article;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.article.ArticleDAO;
import model.article.ArticleDTO;
import model.member.MemberDAO;
import model.member.MemberDTO;
import model.nonMember.NonMemberDAO;
import model.nonMember.NonMemberDTO;
import model.util.PageIndex;
import service.Action;

public class ArticleListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleDAO dao = ArticleDAO.getInstance();
		String search = "", key = "", url = "article_list.jsp";
		int boardId = Integer.parseInt(request.getParameter("boardId"));
		String boardName = "";
		switch (boardId) {
			case 1 :
				boardName = "일상";
				break;
			case 2 :
				boardName = "공부";
				break;
			case 3:
				boardName = "기업 정보";
				break;
			case 4 :
				boardName = "구인 정보";
				break;
			case 5 :
				boardName = "일정표";
				break;
			case 6 :
				boardName = "질의 응답";
				break;
		}
		int totcount = 0;// 게시글 총수
		// 검색 판단
		if (request.getParameter("key") != null) {
			key = request.getParameter("key");
			search = request.getParameter("search");
			totcount = dao.articleCount(search, key, boardId);
		} else {
			totcount = dao.articleCount(boardId);
		}

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

		List<ArticleDTO> list = null;
		if (key.equals("")) {
			list = dao.articleList(startpage, endpage, boardId);
		} else {
			list = dao.articleList(search, key, startpage, endpage, boardId);
		}
		
		for (ArticleDTO article : list) {
			if (article.getMemberType() == 2) {
				NonMemberDAO ndao = NonMemberDAO.getInstance();
				NonMemberDTO non = ndao.NonMemberSelectOnArticle(article.getId());
				article.setWriterName(non.getNickname());
			} else {
				MemberDAO mdao = MemberDAO.getInstance();
				MemberDTO member = mdao.memberSelect(article.getMemberId());
				article.setWriterName(member.getNickname());
			}
		}

		// 페이지 처리
		String pageSkip = "";
		if (key.equals("")) {
			pageSkip = PageIndex.pageList(nowpage, totpage, url, "", boardId);
		} else {
			pageSkip = PageIndex.pageListHan(nowpage, totpage, url, search, key, boardId);
		}
		// jsp에서 사용될 값을 request 내장객체에 담기
		request.setAttribute("page", nowpage);
		request.setAttribute("boardId", boardId);
		request.setAttribute("boardName", boardName);
		request.setAttribute("totpage", totpage);
		request.setAttribute("totcount", totcount);
		request.setAttribute("listcount", listcount);
		request.setAttribute("list", list);
		request.setAttribute("pageSkip", pageSkip);
		request.setAttribute("search", search);
		request.setAttribute("key", key);

		RequestDispatcher rd = request.getRequestDispatcher("/Article/article_list.jsp");
		rd.forward(request, response);

	}

}
