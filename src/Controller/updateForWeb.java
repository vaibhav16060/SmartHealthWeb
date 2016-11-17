package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.LoginDetails;

/**
 * Servlet implementation class updateForWeb
 */
@WebServlet("/updateForWeb")
public class updateForWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateForWeb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
		String first_name = request.getParameter("tb_first_name").toString();
		String last_name = request.getParameter("tb_last_name").toString();
		String password = request.getParameter("tb_password").toString();
		String address = request.getParameter("tb_address").toString();
		String about = request.getParameter("tb_about").toString();
		String edu_details = request.getParameter("tb_edu_details").toString();
		HttpSession hs = request.getSession();
		LoginDetails cont = (LoginDetails)hs.getAttribute("LoggerObj");
		PrintWriter output = response.getWriter();
		if(!first_name.equals("")){
			if(!last_name.equals("")){
				cont.set_name(first_name, last_name);
			}
			else{
				cont.set_name(first_name, cont.get_last_name());
			}
		}
		else{
			if(!last_name.equals("")){
				cont.set_name(cont.get_first_name(), last_name);
			}
		}
		
		if(!password.equals("")){
			cont.set_password(password);
		}
		
		if(!address.equals("")){
			cont.set_address(address);
		}
		if(!about.equals("")){
			//to be done
		}
		if(!edu_details.equals("")){
			cont.update_edu_details_moderator(edu_details);
		}
		cont.update_data();
		output.println("<script>");
		output.println("location.href='" + redirect_URL + "/Update.jsp';");
		output.println("alert('Data has been updated !');");
		output.println("</script>");
	}

}
