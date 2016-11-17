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
 * Servlet implementation class forumAdminForWeb
 */
@WebServlet("/forumAdminForWeb")
public class forumAdminForWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public forumAdminForWeb() {
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
		
		String calling_URL = request.getRequestURL().toString();
		String redirect_URL = calling_URL.substring(0, calling_URL.lastIndexOf('/'));
		HttpSession hs = request.getSession();
		LoginDetails cont = (LoginDetails)hs.getAttribute("LoggerObj");
		PrintWriter output = response.getWriter();
		if(request.getParameter("btn_create_forum") != null){
			String forum_name = request.getParameter("tb_forum_name").toString();
			String details = request.getParameter("tb_forum_desc").toString();
			cont.create_forum(forum_name, details);
			output.println("<script>");
			output.println("location.href='" + redirect_URL + "/ForumAdmin.jsp';");
			output.println("alert('Forum Created !');");
			output.println("</script>");
			return;
		}
		else{
			//delete a forum
			String[][] all_forums = cont.get_all_forum_list();
			for(int ol = 0 ; ol < all_forums.length ; ol++){
				if(request.getParameter(""+all_forums[ol][0]) != null){
					cont.delete_forum(Integer.parseInt(all_forums[ol][0]));
					output.println("<script>");
					output.println("location.href='" + redirect_URL + "/ForumAdmin.jsp';");
					output.println("alert('Forum Closed !');");
					output.println("</script>");
					return;
				}
			}
		}
	}

}
