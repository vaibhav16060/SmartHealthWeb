package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Model.*;

/**
 * Servlet implementation class sendRequestWeb
 */
@WebServlet("/sendRequestWeb")
public class sendRequestWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sendRequestWeb() {
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
		String[][] all_people = fr.all_available_people(cont.get_user_name());
		for(int i = 0 ; all_people != null & i < all_people.length ; i++){
			if(request.getParameter(""+i) != null){
				fr.send_request(cont.get_user_name(), all_people[i][0]);
			}
		}
		PrintWriter output = response.getWriter();
		output.println("<script>");
		output.println("location.href='" + redirect_URL + "/SendFriendRequest.jsp';");
		output.println("alert('Request has been sent !');");
		output.println("</script>");
	}

}
