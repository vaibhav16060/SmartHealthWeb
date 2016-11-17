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
 * Servlet implementation class seeRequestWeb
 */
@WebServlet("/seeRequestWeb")
public class seeRequestWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public seeRequestWeb() {
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
		String[][] recv = fr.all_recieved_requests(cont.get_user_name());
		boolean accept = false;
		for(int i = 0 ; recv != null && i < recv.length ; i++){
			if(request.getParameter("A"+i) != null){
				fr.accept_request(recv[i][0], cont.get_user_name());
				System.out.println(recv[i][0] + " " + cont.get_user_name());
				accept = true;
				break;
			}
			else  if(request.getParameter("R"+i) != null){
				fr.reject_request(recv[i][0], cont.get_user_name());
				accept = false;
				break;
			}
		}
		PrintWriter output = response.getWriter();
		output.println("<script>");
		output.println("location.href='" + redirect_URL + "/SeeFriendRequest.jsp';");
		if(accept == true)
			output.println("alert('Request has been accepted!');");
		else
			output.println("alert('Request has been rejected!');");
		output.println("</script>");
	}

}
