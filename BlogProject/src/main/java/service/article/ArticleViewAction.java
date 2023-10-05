package service.article;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.article.ArticleDAO;
import model.article.ArticleDTO;
import model.reply.ReplyDAO;
import model.reply.ReplyDTO;
import service.Action;

public class ArticleViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int nowpage = 1;
		if (request.getParameter("page") != null) {
			nowpage = Integer.parseInt(request.getParameter("page"));
		}
		
		ArticleDAO dao = ArticleDAO.getInstance();
		//쿠키검사 및 설정
		boolean bool = false;
		Cookie info = null;
		Cookie[] cookies = request.getCookies();//클라이언트 쿠키 가져오기
		for(int i=0; i<cookies.length ; i++) {
			info = cookies[i];
			if(info.getName().equals("Article"+id)) {
				bool = true;
				break;
			}
		}
		
		String newValue=""+System.currentTimeMillis();//쿠키값으로 사용할 변수
		if(!bool) {//쿠키가 존재하지 않으면
			dao.articleHits(id);
			info = new Cookie("Article"+id, newValue);//쿠키생성
			info.setMaxAge(60*60);// 쿠키유효시간:한시간
			response.addCookie(info);//쿠키전송
		}
				
		ArticleDTO article = dao.articleSelect(id);
		ReplyDAO rDao = ReplyDAO.getInstance();
		List<ReplyDTO> replies = rDao.getReplies(id);
		
		request.setAttribute("page", nowpage);
		request.setAttribute("article", article);
		request.setAttribute("replies", replies);
		//forword
		RequestDispatcher rd = request.getRequestDispatcher("/Article/article_view.jsp");
		rd.forward(request, response);


	}

}
