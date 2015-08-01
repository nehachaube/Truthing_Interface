/*Created By:Neha Chaube*/

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ImageCaptionUserServlet
 */
@WebServlet("/ImageCaptionUserServlet")
public class ImageCaptionUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		ImageCaptionBean bean= new ImageCaptionBean(); 
		HttpSession session = request.getSession();
		 String username= (String) session.getAttribute("currentSessionUser");
		 System.out.println("Username"+username);
		 int userid= (int) session.getAttribute("currentSessionUserId");
		 bean.setCurrentUserId(userid);
		 bean.setCurrentUserName(username);
		 bean.setImgurl(request.getParameter("imgcaptionstring"));
		 System.out.println(bean.getImgurl());
		 try {
			List<ImageCaptionBean> returncaption= ImageCaptionUserDAO.imagecaptionuserdata(bean);
			request.setAttribute("returncaptions", returncaption);
		} catch (SQLException e) {
			request.setAttribute("errorindb","Could not upload!");
			e.printStackTrace();
		}
	}

}
