package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.HealthData;

/**
 * Servlet implementation class healthDataUpdateForWeb
 */
@WebServlet("/healthDataUpdateForWeb")
public class healthDataUpdateForWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public healthDataUpdateForWeb() {
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
		PrintWriter output = response.getWriter();
		HealthData obj = new HealthData();
		if(request.getParameter("btn_new_property") != null){
		
			String new_name = request.getParameter("tb_new_name").toString();
			String description = request.getParameter("tb_description").toString();
			if(new_name != "" && description != ""){
				obj.insert_into_property(new_name, description);
				output.print("<script>");
				output.println("location.href='" + redirect_URL + "/InsertHealthData.jsp';");
				output.println("alert('The property has been added');");
				output.print("</script>");
			}
			return;
		}
		else{
			String user_id = request.getParameter("tb_user_id").toString();
			String value = request.getParameter("tb_prop_value").toString();
			int type = Integer.parseInt(request.getParameter("property_selector").toString());
			if(user_id != "" && value != ""){
				obj.insert_into_datum(user_id, type, value);
				output.print("<script>");
				output.println("location.href='" + redirect_URL + "/InsertHealthData.jsp';");
				output.println("alert('Value inserted');");
				output.print("</script>");
			}
		}
	}

}
