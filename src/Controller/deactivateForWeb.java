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
 * Servlet implementation class deactivateForWeb
 */
@WebServlet("/deactivateForWeb")
public class deactivateForWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deactivateForWeb() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String calling_URL = request.getRequestURL().toString();
		String redirect_URL = calling_URL.substring(0, calling_URL.lastIndexOf('/'));
		String password = request.getParameter("tb_password").toString();
		HttpSession hs = request.getSession();
		LoginDetails cont = (LoginDetails)hs.getAttribute("LoggerObj");
		PrintWriter output = response.getWriter();
		if(cont.if_password_matches(password)){
			cont.deactivate_account();
			cont.update_data(); 
			hs.setAttribute("LoggerObj", null);
			output.println("<script>");
			output.println("location.href='" + redirect_URL + "';");
			output.println("alert('You have been successfully deactivated !');");
			output.println("</script>");
			return;
		}
		else{
			output.println("<script>");
			output.println("location.href='" + redirect_URL + "/Deactivate.jsp';");
			output.println("alert('The password you entered does not match your current password.');");
			output.println("</script>");
			return;
		}
	}

}
