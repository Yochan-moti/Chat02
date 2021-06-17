package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Mutter;
import model.PostKoukan;
import model.PostKoukanLogic;
import model.PostMutterLogic;
import model.User;

/**
 * Servlet implementation class Koukan
 */
@WebServlet("/Koukan")
public class Koukan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext application = this.getServletContext();
		List<PostKoukan> koukanList = (List<PostKoukan>) application.getAttribute("koukanList");
		
		if (koukanList == null) {
		      koukanList = new ArrayList<PostKoukan>();
		      application.setAttribute("koukanList", koukanList);
		    }
		    
		    // ログインしているか確認するため
		    // セッションスコープからユーザー情報を取得
		    HttpSession session = request.getSession();
		    User loginUser = (User) session.getAttribute("loginUser");
		    if (loginUser == null) { // ログインしていない場合
		      // リダイレクト
		      response.sendRedirect("/Chat03/");
		    } else { // ログイン済みの場合
		      // フォワード
		      RequestDispatcher dispatcher =
		          request.getRequestDispatcher("/WEB-INF/jsp/koukan.jsp");
		      dispatcher.forward(request, response);
		    }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// リクエストパラメータの取得
	    request.setCharacterEncoding("UTF-8");
	    String item = request.getParameter("title");
	    String epi = request.getParameter("epi");

	    // 入力値チェック
	    if (epi != null && epi.length() != 0 && item != null && item.length() != 0) {
	      // アプリケーションスコープに保存されたつぶやきリストを取得
	      ServletContext application = this.getServletContext();
	      List<PostKoukan> koukanList =
	          (List<PostKoukan>) application.getAttribute("koukanList");

	      // セッションスコープに保存されたユーザー情報を取得
	      HttpSession session = request.getSession();
	      User loginUser = (User) session.getAttribute("loginUser");

	      // つぶやきをつぶやきリストに追加
	      PostKoukan koukan = new PostKoukan(loginUser.getName(), item, epi);
	      PostKoukanLogic postKoukanLogic = new PostKoukanLogic();
	      postKoukanLogic.butsu(koukan, koukanList);

	      // アプリケーションスコープにつぶやきリストを保存
	      application.setAttribute("koukanList", koukanList);
	    } else {
	      //エラーメッセージをリクエストスコープに保存
	      request.setAttribute("errorMsg", "入力されていません");
	    }

	    // メイン画面にフォワード

	    RequestDispatcher dispatcher =
	        request.getRequestDispatcher("/WEB-INF/jsp/koukan.jsp");

	    dispatcher.forward(request, response);
	}

}
