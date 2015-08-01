import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	                       throws ServletException, java.io.IOException {
	 try
	 {
		String name=request.getParameter("usr");
		String email=request.getParameter("email");
		String pass=request.getParameter("pwd");
		String cpass=request.getParameter("cpwd");
		System.out.println(name+email+pass+cpass);
		boolean success=RegisterDAO.register(name,email,pass,cpass);
		if(success)
		{
			request.setAttribute("successMessage", "Registration Successful");
			response.sendRedirect("UserLogin.jsp");
		}
		else
		{
			System.err.println("Could not register");
			request.setAttribute("errorMessage", "Registration Failed!");
			RequestDispatcher rd = request.getRequestDispatcher("src/jsp/RegisterBootStrap.jsp");
            rd.forward(request, response); 
		}
	 }
	 catch (Throwable theException)
	 {
	      System.out.println(theException);
	 }
	}
}
