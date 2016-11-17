package Controller;

import java.io.IOException;
import org.apache.commons.lang3.StringEscapeUtils;
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
 * Servlet implementation class seeFriendDataForWeb
 */
@WebServlet("/seeFriendDataForWeb")
public class seeFriendDataForWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public seeFriendDataForWeb() {
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
		PrintWriter output = response.getWriter();
		String []friends = fr.get_all_friends_list(cont.get_user_name());
		for(int i = 0 ; friends != null && i < friends.length ; i++){
			if(request.getParameter("btn_show_health_data"+i) != null){
				String dat[][] = cont.see_health_data(friends[i]);
				String alert_string = "";
				for(int j = 0 ; dat != null && j < dat.length ; j++){
					alert_string += dat[j][0] + " : " + dat[j][1] /*+ "\n"*/;					
				}
				output.println("<script>");
				output.println("location.href='" + redirect_URL + "/HealthData.jsp';");
				if(dat != null){
					alert_string = "alert('" + alert_string + "');";
					//String al =  StringEscapeUtils.escapeEcmaScript(alert_string);
					output.println(alert_string);
				}
				else{
					output.println("alert('No data found');");
				}
				output.println("</script>");
				return;
			}
		}
	}
}
