package chat;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChatSubmitServlet
 */
@WebServlet("/ChatSubmitServlet")
public class ChatSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String fromId = request.getParameter("fromId");
		String toId = request.getParameter("toId");
		String chatContent = request.getParameter("chatContent");
		
		fromId = URLDecoder.decode(fromId,"UTF-8");
		toId = URLDecoder.decode(toId,"UTF-8");
		chatContent = URLDecoder.decode(chatContent,"UTF-8");
		response.getWriter().write(new ChatDao().submit(fromId, toId, chatContent));
	}

}
