/*Created By:Neha Chaube*/
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import net.sf.json.JSONObject;
import org.json.*;


public class ImageCaptionServlet extends HttpServlet{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	                       throws ServletException, java.io.IOException {
		ImageCaptionBean bean= new ImageCaptionBean(); 
		HttpSession session = request.getSession();
		 String username= (String) session.getAttribute("currentSessionUser");
		 System.out.println("Username"+username);
		 int userid= (int) session.getAttribute("currentSessionUserId");
		 bean.setCurrentUserId(userid);
		 bean.setCurrentUserName(username);
		 String jsonString=request.getParameter("imgcaption");
		
		// JSONObject jObj    = new JSONObject();
	     //JSONObject newObj = jObj.getJSONObject(jsonString);
		try {
			 String json=jsonString.replace("[", "").replace("]", "");
			 System.out.println("Parameter "+json);
			 String[] strs = json.split("(?<=\\}),(?=\\{)");
			 for (String s : strs) {         
			    JSONObject jObj = new JSONObject(s);
			     System.out.println("jObj "+jObj);
			    // System.out.println("newObj "+newObj);
			     Iterator it = jObj.keys(); //gets all the keys
			     while(it.hasNext())
			     {
			         String key = (String) it.next(); // get key
			         String o = (String) jObj.get(key); // get value
			        // System.out.println("Key "+key);
			        // System.out.println("Value "+o);
			         if("name".equalsIgnoreCase(key))
			         {
			         bean.setImgurl(o);
			         }
			         if("caption".equalsIgnoreCase(key))
			         {
			         bean.setImgcaption(o);
			         }
			        // System.out.println(bean.getCurrentUserId());
			        // System.out.println(bean.getCurrentUserName());
			        // System.out.println(bean.getImgurl());
			         //System.out.println(bean.getImgcaption());
			     }
			     bean= ImageCaptionDAO.captionupdate(bean);
			 }
			 request.setAttribute("success", "Saved!");
		}
	 catch (SQLException | JSONException e) {
		 request.setAttribute("errorindb", "Could not Save");
			e.printStackTrace();
		}
	         
	     }
	}

