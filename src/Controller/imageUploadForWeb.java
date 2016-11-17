package Controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Model.FriendRequest;
import Model.LoginDetails;

/**
 * Servlet implementation class imageUploadForWeb
 */
@WebServlet("/imageUploadForWeb")
public class imageUploadForWeb extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public imageUploadForWeb() {
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
		LoginDetails cont = (LoginDetails)hs.getAttribute("LoggerObj");
		boolean []forward = new boolean[3];
		String []name = new String[3];
		String fileName = "";
		String UPLOAD_DIRECTORY = "C:\\Users\\Vaibhav\\workspace\\SmartHealthWeb\\WebContent\\uploads";
		//if(request.getParameter("btn_image_1") != null){
			if(ServletFileUpload.isMultipartContent(request)){
				
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				try{
					List items = upload.parseRequest(request);
					Iterator iterator = items.iterator();
					while (iterator.hasNext()) {
	                     FileItem item = (FileItem) iterator.next();
	                     if (!item.isFormField()) {
	                         fileName = item.getName();  
	                         String root = getServletContext().getRealPath("/");
	                         File path = new File(root + "/" + cont.get_user_name());
	                         if (!path.exists()) {
	                             boolean status = path.mkdirs();
	                         }
	  
	                         File uploadedFile = new File(root + "/" + cont.get_user_name() + "/" + fileName);
	                         System.out.println(uploadedFile.getAbsolutePath());
	                         item.write(uploadedFile);
	                         forward[0] = true; name[0] = fileName;
	             			 forward[1] = false; name[1] = "";
	             			 forward[2] = false; name[2] = "";
	             			 cont.set_image_urls(forward, name);
	                     }
	                 }
				}catch (FileUploadException e) {
	                 e.printStackTrace();
	             } catch (Exception e) {
	                 e.printStackTrace();
	             }
			}
		//}
		response.sendRedirect("Home.jsp");
	}
}
