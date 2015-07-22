import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Login extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {
	{
		System.out.println("Connecting to db from login");
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		String url="jdbc:msql://localhost/truthinginterface";
		String userid="sqluser";
		String password="sqluserpw";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException error)
		{
			System.out.println("unable");
		}
		try
		{
			con=DriverManager.getConnection(url,userid,password);
		}
		catch(SQLException error)
		{
			System.err.println("Cannot connect to database");
		}
		try
		{
			//st = con.createStatement();
			ps = con.prepareStatement("select email,password from truthinginterface.register");
			rs = ps.executeQuery();
			 while (rs.next()) {
			      String dbemail = rs.getString("email");
			      String dbpassword = rs.getString("password");
		    String username=req.getParameter("un");
		    String userpass=req.getParameter("pw");
		    if(username.equals(dbemail) && userpass.equals(dbpassword))
		    {
		    	System.out.println("Login Success");
		    }
		       
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	}
}
