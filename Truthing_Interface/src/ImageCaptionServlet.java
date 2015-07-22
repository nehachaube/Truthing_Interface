import java.sql.SQLException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;


public class ImageCaptionServlet extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	                       throws ServletException, java.io.IOException {
		ImageCaptionBean bean= new ImageCaptionBean(); 
		HttpSession session = request.getSession();
		 String username= (String) session.getAttribute("currentSessionUser");
		 System.out.println(username);
		 int userid= (int) session.getAttribute("currentSessionUserId");
		 bean.setCurrentUserId(userid);
		 bean.setCurrentUserName(username);
		 JSONObject jObj    = new JSONObject();
	     JSONObject newObj = jObj.getJSONObject(request.getParameter("imgcaption"));
	     Iterator it = jObj.keys(); //gets all the keys
	     while(it.hasNext())
	     {
	         String key = (String) it.next(); // get key
	         String o = (String) jObj.get(key); // get value
	       try {
			bean= ImageCaptionDAO.captionupdate(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	         
	     }
	}
}
