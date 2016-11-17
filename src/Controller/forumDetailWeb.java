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
 * Servlet implementation class forumDetailWeb
 */
@WebServlet("/forumDetailWeb")
public class forumDetailWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public forumDetailWeb() {
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
		int f_id = Integer.parseInt((String)hs.getAttribute("Forum"));
		String[][] all_post = cont.fetch_all_posts(f_id);
		
		if(request.getParameter("btn_new_post") != null){
			
			String post = ((String)request.getParameter("tb_new_post")).toString();
			if(post != ""){
				cont.insert_post(f_id, post, cont.get_user_name());
				output.println("<script>");
				output.println("location.href='" + redirect_URL + "/ForumDetail.jsp';");
				output.println("</script>");
				return;
			}
			else{
				output.println("<script>");
				output.println("location.href='" + redirect_URL + "/ForumDetail.jsp';");
				output.println("alert('Post can't be left blank !');");
				output.println("</script>");
				response.sendRedirect("ForumDetail.jsp");
				return;
			}
		}
		
		else{
			//the comment or rating button was pressed
			for(int it = 0 ; it < all_post.length ; it++){
				if(request.getParameter("btn_new_comment"+it) != null){
					String comment = ((String)request.getParameter("tb_new_comment"+it)).toString();
					if(comment != null){
						cont.insert_comment_on_post(it, comment, cont.get_user_name());
					}
				}
				else if(request.getParameter("btn_rating_save"+it) != null){
					int rating = Integer.parseInt(request.getParameter("rating_selector"+it).toString());
					if(rating != 0){
						cont.insert_rating(rating, all_post[it][0], all_post[it][1]);
					}
				}
			}
			response.sendRedirect("ForumDetail.jsp");
		}
	}
}
