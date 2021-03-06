import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
 
 
    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, java.io.IOException {
	 
		try
		{
		 
		     UserBean user = new UserBean();
		     user.setUserName(request.getParameter("un"));
		     user.setPassword(request.getParameter("pw"));
		 
		     user = UserDao.login(user);
		 
		     if (user.isValid())
		     {
		 
		          HttpSession session = request.getSession(true);
		          session.setAttribute("currentSessionUser",user.getUserName());
		          session.setAttribute("currentSessionUserId",user.getUserId());
		          response.sendRedirect("src/jsp/UserLogged.jsp"); //logged-in page
		     }
		 
		     else
		     {
		          //response.sendRedirect("invalidLogin.jsp"); //error page
		          System.err.println("Could not login");
		          request.setAttribute("errorMessage", "Login Failed!");
		          RequestDispatcher rd = request.getRequestDispatcher("src/jsp/UserLogin.jsp");
		          rd.forward(request, response); 
		     }
		}
		catch (Throwable theException)
		{
		     System.out.println(theException);
		}
	}
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, java.io.IOException {
    	HttpSession session  = request.getSession();
    	try
    	{      
    	    session.removeAttribute("currentSessionUser");
    	    session.removeAttribute("currentSessionUserId");
    	    session.invalidate();                               
    	    RequestDispatcher rd = request.getRequestDispatcher("src/jsp/UserLogin.jsp");
	        rd.forward(request, response);
    	}
    	catch (Exception sqle)
    	{
    	    System.out.println("error UserValidateServlet message : " + sqle.getMessage());
    	    System.out.println("error UserValidateServlet exception : " + sqle);
    	}
    }
}