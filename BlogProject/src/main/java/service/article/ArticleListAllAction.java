package service.article;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.article.ArticleDAO;
import model.article.ArticleDTO;
import model.util.PageIndex;
import service.Action;

public class ArticleListAllAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleDAO dao = ArticleDAO.getInstance();
		
		String search="", key="", url="index.jsp";
		int totcount=0;//게시글 총수
		//검색 판단
		if(request.getParameter("key") != null){
			key=request.getParameter("key");
			search = request.getParameter("search");
			totcount = dao.articleCount(search, key);
		}else {
			totcount = dao.articleCount();
		}
		
		int nowpage=1;//현재페이지 초기화
		int maxlist = 10;//페이지당 글수
		int totpage = 1;//전체페이지
		
		//총 페이지수 계산
		if(totcount % maxlist == 0) {
			totpage = totcount / maxlist;
		}else {
			totpage = totcount / maxlist + 1;
		}
		
		// 페이지번호가 넘어온 경우
		if(request.getParameter("page") != null)
			nowpage = Integer.parseInt(request.getParameter("page"));
		
		//페이지별 출력될 시작, 끝번호 
		int startpage = (nowpage-1) * maxlist + 1;
		int endpage = nowpage * maxlist;
		int listcount = totcount-((nowpage-1)*maxlist);//웹에서 번호출력용
		
		List<ArticleDTO> list = null;
		if(key.equals("")) {
			list = dao.articleList(startpage, endpage);
		}else {
			list = dao.articleList(search, key, startpage, endpage);
		}
		
		//페이지 처리
		String pageSkip = "";
		if(key.equals("")) {
			pageSkip = PageIndex.pageList(nowpage, totpage, url, "");
		}else {
			pageSkip = PageIndex.pageListHan(nowpage, totpage, url, search, key);			
		}
		
		for (ArticleDTO article : list) {
			switch (article.getBoardId()) {
				case 1 :
					article.setBoardName("일상");
					break;
				case 2 :
					article.setBoardName("공부");
					break;
				case 3:
					article.setBoardName("기업 정보");
					break;
				case 4 :
					article.setBoardName("구인 정보");
					break;
				case 5 :
					article.setBoardName("일정표");
					break;
				case 6 :
					article.setBoardName("질의 응답");
					break;
			}
		}
		
		//jsp에서 사용될 값을 request 내장객체에 담기
		request.setAttribute("page", nowpage);
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
