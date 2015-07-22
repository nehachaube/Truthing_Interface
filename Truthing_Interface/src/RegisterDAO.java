import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class RegisterDAO {
	 static Connection con=null;
     static ResultSet rs = null;
     public static boolean register(String username,String email,String password,String confirmpassword) throws Exception{
     Statement stmt=null;
     PreparedStatement ps=null;
     String url="jdbc:mysql://localhost:3306/truthinginterface";
     String sqluserid="sqluser";
	 String sqlpassword="sqluserpw";
	 System.out.println(username+" "+email+" "+password+" "+confirmpassword);
	 try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException error)
		{
			System.err.println("unable to connect-class not found");
		}
		try
		{
			con=DriverManager.getConnection(url,sqluserid,sqlpassword);
		}
		catch(SQLException error)
		{
			System.err.println("Cannot connect to database");
		}
		try{
		 String insertQuery="insert into truthinginterface.register values('"+username+"','"+email+"','"+password+"','"+confirmpassword+"');";
		 System.out.println(insertQuery);
		 stmt = con.createStatement();
		 ps = con.prepareStatement("insert into  truthinginterface.register values (default, ?, ?, ?, ? )");
     // Parameters start with 1
     ps.setString(1, username);
     ps.setString(2, email);
     ps.setString(3, password);
     ps.setString(4, confirmpassword);
     ps.executeUpdate();
     return true;
		}
		catch (Exception e) {
		      throw e;
		    } finally {
		      con.close();
		    }
     }
}
