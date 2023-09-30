package controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Action;
import service.article.ArticleDeleteAction;
import service.article.ArticleDeleteProAction;
import service.article.ArticleListAction;
import service.article.ArticleModifyAction;
import service.article.ArticleModifyProAction;
import service.article.ArticleViewAction;
import service.article.ArticleWriteAction;
import service.article.ArticleWriteProAction;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/Article")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		System.out.println("BoardController에서 요청을 받음 :" + cmd);
		
		Action action = null;
		
		if(cmd.equals("article_list.do")) {
			action = new ArticleListAction();
		}else if(cmd.equals("article_write.do")) {//입력 폼
			action = new ArticleWriteAction();
		}else if(cmd.equals("article_write_pro.do")) {//입력 처리
			action = new ArticleWriteProAction();
		}else if(cmd.equals("article_view.do")) {
			action = new ArticleViewAction();
		}else if(cmd.equals("article_modify.do")) {
			action = new ArticleModifyAction();
		}else if(cmd.equals("article_modify_pro.do")) {
			action = new ArticleModifyProAction();
		}else if(cmd.equals("article_delete.do")) {
			action = new ArticleDeleteAction();
		}else if(cmd.equals("article_delete_pro.do")) {
			action = new ArticleDeleteProAction();
		}
		
		action.execute(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
