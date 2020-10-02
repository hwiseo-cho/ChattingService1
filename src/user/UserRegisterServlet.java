package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String userId = request.getParameter("userId");
		String userPwd1 = request.getParameter("userPwd1");
		String userPwd2 = request.getParameter("userPwd2");
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String profile = request.getParameter("profile");
		if(userId == null || userPwd1 == null || userPwd2 == null || name == null || age == null ||
				gender == null || email == null) {
			request.getSession().setAttribute("messageTpye", "오류");
			request.getSession().setAttribute("messageContent", "모든 내용을 입력하시오");
			response.sendRedirect("join.jsp");
			return;
		}
		if(!userPwd1.equals(userPwd2)) {
			request.getSession().setAttribute("messageTpye", "오류");
			request.getSession().setAttribute("messageContent", "비밀번호가 다름");
			response.sendRedirect("join.jsp");
			return;
		}
		int result = new UserDao().register(userId, userPwd1, name, age, gender, email, profile);
		System.out.println(result);
		if(result == 1) {
			request.getSession().setAttribute("userId", userId);
			request.getSession().setAttribute("messageTpye", "성공");
			request.getSession().setAttribute("messageContent", "회원가입성공");
			response.sendRedirect("index.jsp");
		} else {
			request.getSession().setAttribute("messageTpye", "오류 ");
			request.getSession().setAttribute("messageContent", "이미 존재함");
			response.sendRedirect("join.jsp");
		}
				
	}

}
