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
 * Servlet implementation class forumLoginForWeb
 */
@WebServlet("/forumLoginForWeb")
public class forumLoginForWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public forumLoginForWeb() {
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
		String[][] all_forums_disp = cont.get_all_forum_list();
        
		if(all_forums_disp != null){
        	for(int ol = 0 ; ol < all_forums_disp.length ; ol++){
        		if(request.getParameter(""+all_forums_disp[ol][0]) != null){
        			
        			hs.setAttribute("Forum", all_forums_disp[ol][0]);
        			response.sendRedirect("ForumDetail.jsp");
        		}
        	}
        }
	}

}
