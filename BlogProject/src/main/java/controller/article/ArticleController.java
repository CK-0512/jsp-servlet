package controller.article;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Action;
import service.article.ArticleDeleteProAction;
import service.article.ArticleListAction;
import service.article.ArticleListAllAction;
import service.article.ArticleModifyAction;
import service.article.ArticleModifyNonAction;
import service.article.ArticleModifyNonProAction;
import service.article.ArticleModifyProAction;
import service.article.ArticleNonAction;
import service.article.ArticleViewAction;
import service.article.ArticleWriteAction;
import service.article.ArticleWriteNonAction;
import service.article.ArticleWriteNonProAction;
import service.article.ArticleWriteProAction;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/Article")
public class ArticleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleController() {
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
		
		if(cmd.equals("article_list")) {
			action = new ArticleListAction();
		}else if(cmd.equals("article_list_all")) {//입력 폼
			action = new ArticleListAllAction();
		}else if(cmd.equals("article_write")) {//입력 폼
			action = new ArticleWriteAction();
		}else if(cmd.equals("article_non")) {//입력 폼
			action = new ArticleNonAction();
		}else if(cmd.equals("article_write_non")) {//입력 폼
			action = new ArticleWriteNonAction();
		}else if(cmd.equals("article_write_non_pro")) {//입력 처리
			action = new ArticleWriteNonProAction();
		}else if(cmd.equals("article_write_pro")) {//입력 처리
			action = new ArticleWriteProAction();
		}else if(cmd.equals("article_view")) {
			action = new ArticleViewAction();
		}else if(cmd.equals("article_modify")) {
			action = new ArticleModifyAction();
		}else if(cmd.equals("article_modify_non")) {
			action = new ArticleModifyNonAction();
		}else if(cmd.equals("article_modify_non_pro")) {
			action = new ArticleModifyNonProAction();
		}else if(cmd.equals("article_modify_pro")) {
			action = new ArticleModifyProAction();
		}else if(cmd.equals("article_delete_pro")) {
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
