package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import Model.*;

/**
 * Servlet implementation class loginForWeb
 */
@WebServlet("/loginForWeb")
public class loginForWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String calling_URL = request.getRequestURL().toString();
		String redirect_URL = calling_URL.substring(0, calling_URL.lastIndexOf('/'));
		PrintWriter output = response.getWriter();
		if(request.getParameter("btn_signin") != null){
			
			String email = request.getParameter("email");
			String pass = request.getParameter("password");
			LoginDetails contr = new LoginDetails(email, pass);
			HttpSession hs = request.getSession();;
			if(contr.if_login_successful() && !contr.get_running_status().equals("Deactivated")){
				hs.setAttribute("LoggerObj", contr);
				response.sendRedirect("Home.jsp");
				
			}
			else{
				if(contr.get_running_status().equals("Deactivated")){
					hs.setAttribute("LoggerObj", contr);
					contr.reactivate_account();
					contr.update_data();
					response.sendRedirect("Reactivate.jsp");
				}
				else{
					output.println("<script>");
					output.println("location.href='" + redirect_URL + "';");
					output.println("alert('Wrong login credentials. Try once more !');");
					output.println("document.getElementById('btn_problem_hidden').click();");
					output.println("</script>");
				}
			}
			
		}
		else{
			
			//getting the user registered
			Register cont = new Register();
			final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"; //Taken from www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
			Pattern r = Pattern.compile(EMAIL_PATTERN);
			Matcher m, n;
			String user_id = request.getParameter("tb_user_id").toString();
			String pwd = request.getParameter("tb_pwd").toString();
			String pwd_c = request.getParameter("tb_pwd_c").toString();
			String first_name = request.getParameter("tb_first_name").toString();
			String last_name = request.getParameter("tb_last_name").toString();
			String email_signup = request.getParameter("tb_email_signup").toString();
			String email_signup_sec = request.getParameter("tb_email_signup_sec").toString();
			String address = request.getParameter("tb_address").toString();
			String emergency_contact = request.getParameter("tb_emergency_contact").toString();
			String user_type = request.getParameter("user_type_selector").toString();
			String about = request.getParameter("tb_about").toString();
			
			if(cont.exists_user_id(user_id)){
				output.print("<script>");
				output.println("location.href='" + redirect_URL + "';");
				output.println("alert('UserID already exists');");
				output.print("</script>");
				return;
			}
			
			if(pwd.equals("")){
				output.print("<script>");
				output.println("location.href='" + redirect_URL + "';");
				output.println("alert('The password fields can't be left blank');");
				output.print("</script>");
				return;
			}
			
			if(!pwd.equals(pwd_c)){
				output.print("<script>");
				output.println("location.href='" + redirect_URL + "';");
				output.println("alert('Password and confirm password do not match');");
				output.print("</script>");
				return;
			}
			
			if(!(r.matcher(email_signup).find() && r.matcher(email_signup_sec).find())){
				output.print("<script>");
				output.println("location.href='" + redirect_URL + "';");
				output.println("alert('Check that the email if provided is valid');");
				output.print("</script>");
				return;
			}
			
			if(!cont.is_unique_email1(email_signup)){
				output.print("<script>");
				output.println("location.href='" + redirect_URL + "';");
				output.println("alert('This email id is already registered by us. Please use some other email id!');");
				output.print("</script>");
				return;
			}
			
			if(emergency_contact.length() == 0 && (user_type.equalsIgnoreCase("Moderator") || user_type.equalsIgnoreCase("administrator"))){
				output.print("<script>");
				output.println("location.href='" + redirect_URL + "';");
				output.println("alert('The Emergency contact field can be left blank only in the case of EndUser');");
				output.print("</script>");
				return;
			}
			
			if(!(user_type.equalsIgnoreCase("enduser") || user_type.equalsIgnoreCase("moderator") || user_type.equalsIgnoreCase("Administrator"))){
				output.print("<script>");
				output.println("location.href='" + redirect_URL + "';");
				output.println("alert('User Type can only be enduser/moderator/Administrator');");
				output.print("</script>");
				return;
			}
			int usertype = -1;
			
			if(user_type.equalsIgnoreCase("enduser")){
				usertype = 0;
				user_type = "user";
			}
			else if(user_type.equalsIgnoreCase("moderator")){
				usertype = 1;
				user_type = "mod";
			}
			else if(user_type.equalsIgnoreCase("administrator")){
				usertype = 2;
				user_type = "admin";
			}
			cont.set_user_type_as(user_type, user_id, emergency_contact);
			cont.register_user(user_id, pwd, email_signup, 
					email_signup_sec, first_name, last_name, 
					about, "", "", "", address, "", "", "", "", usertype, 1);
			
			output.print("<script>");
			output.println("location.href='" + redirect_URL + "';");
			output.println("alert('You have been registered successfully. You can now log in !');");
			output.print("</script>");
		}
	}

}
