import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
 
 
    @Override
public void doGet(HttpServletRequest request, HttpServletResponse response)
                       throws ServletException, java.io.IOException {
 
try
{
 
     UserBean user = new UserBean();
     user.setUserName(request.getParameter("un"));
     user.setPassword(request.getParameter("pw"));
 
     user = UserDao.login(user);
 
     if (user.isValid())
     {
 
          HttpSession session = request.getSession(true);
          session.setAttribute("currentSessionUser",user);
          response.sendRedirect("UserLogged.jsp"); //logged-in page
     }
 
     else
     {
          //response.sendRedirect("invalidLogin.jsp"); //error page
          System.err.println("Could not login");
			request.setAttribute("errorMessage", "Login Failed!");
			RequestDispatcher rd = request.getRequestDispatcher("/UserLogin.jsp");
          rd.forward(request, response); 
     }
}
 
 
catch (Throwable theException)
{
     System.out.println(theException);
}
       }
    }