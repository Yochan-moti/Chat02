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
import model.PostMutterLogic;
import model.User;

/**
 * Servlet implementation class Many
 */
@WebServlet("/Many")
public class Many extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//つぶやきリストをアプリケーションスコープから取得
		ServletContext application = this.getServletContext();
		List<Mutter> mutterList =
				(List<Mutter>) application.getAttribute("mutterList");
		//取得できなかった場合はつぶやきリストを新規作成して
		//アプリケーションスコープに保存
		if (mutterList == null) {
			mutterList = new ArrayList<>();
			application.setAttribute("mutterList", mutterList);
		}
		
		//ログインしているか確認するため
		//セッションスコープからユーザ情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");
		
		if(loginUser == null) { //ログインしていない場合
			//リダイレクト
			response.sendRedirect("/Chat03/");
		} else { //ログイン済みの場合
			//フォワード
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/WEB-INF/jsp/many.jsp");
			dispatcher.forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String text = request.getParameter("text");
		
		if (text != null && text.length() != 0) {
			ServletContext application = this.getServletContext();
			List<Mutter> mutterList = 
					(List<Mutter>) application.getAttribute("mutterList");
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");
			
			Mutter mutter = new Mutter(loginUser.getName(), text);
			PostMutterLogic postMutterLogic = new PostMutterLogic();
			postMutterLogic.execute(mutter, mutterList);
			application.setAttribute("mutterList", mutterList);
		} else {
			request.setAttribute("errorMsg", "つぶやきが入力されていません");
 		}
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("/WEB-INF/jsp/many.jsp");
		dispatcher.forward(request,response);
	}
    /**
     * Default constructor. 
     */
    public Many() {
    	super();
        // TODO Auto-generated constructor stub
    }
	
}
