package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.FriendRequest;
import Model.LoginDetails;

/**
 * Servlet implementation class seeFriendWeb
 */
@WebServlet("/seeFriendWeb")
public class seeFriendWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public seeFriendWeb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		String calling_URL = request.getRequestURL().toString();
		String redirect_URL = calling_URL.substring(0, calling_URL.lastIndexOf('/'));
		FriendRequest fr = (FriendRequest)hs.getAttribute("RequestAttrib");
		LoginDetails cont = (LoginDetails)hs.getAttribute("LoggerObj");
		String []friends = fr.get_all_friends_list(cont.get_user_name());
		for(int i = 0 ; friends != null && i < friends.length ; i++){
			if(request.getParameter(""+i) != null){
				fr.unfriend_person(cont.get_user_name(), friends[i]);
			}
		}
		PrintWriter output = response.getWriter();
		output.println("<script>");
		output.println("location.href='" + redirect_URL + "/SeeFriend.jsp';");
		output.println("alert('Unfriended !');");
		output.println("</script>");
	}

}
