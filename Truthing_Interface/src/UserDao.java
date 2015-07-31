import java.text.*;
import java.util.*;
import java.sql.*;
 
   public class UserDao
   {
	   static Connection con=null;
      static ResultSet rs = null;
 
      public static UserBean login(UserBean bean) {
 
         //preparing some objects for connection
    	Connection con=null;
  		Statement stmt=null;
  		//ResultSet rs=null;
  		PreparedStatement ps=null;
  		String url="jdbc:mysql://localhost:3306/truthinginterface";
  		String sqluserid="sqluser";
  		String sqlpassword="sqluserpw";
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
 
         String username = bean.getUserName();
         String password = bean.getPassword();
 
         String searchQuery = "select * from truthinginterface.register where name='"+username+"' AND password='"+password+"'";
 
      // "System.out.println" prints in the console; Normally used to trace the process
      System.out.println("Your user name is " + username);
      //System.out.println("Your password is " + password);
      System.out.println("Query: "+searchQuery);
 
      try
      {
         stmt=con.createStatement();
         rs = stmt.executeQuery(searchQuery);
         boolean more = rs.next();
 
         // if user does not exist set the isValid variable to false
         if (!more)
         {
            System.out.println("Sorry, you are not a registered user! Please sign up first");
            bean.setValid(false);
         }
 
         //if user exists set the isValid variable to true
         else if (more)
         {
            String name = rs.getString("name");
            int userid=rs.getInt("id");
            System.out.println("Welcome " + name);
            bean.setUserName(name);
            bean.setUserId(userid);
            bean.setValid(true);
         }
      }
 
      catch (Exception ex)
      {
         System.out.println("Log In failed: An Exception has occurred! " + ex);
      }
 
      //some exception handling
      finally
      {
         if (rs != null)    {
            try {
               rs.close();
            } catch (Exception e) {}
               rs = null;
            }
 
         if (stmt != null) {
            try {
               stmt.close();
            } catch (Exception e) {}
               stmt = null;
            }
 
         if (con != null) {
            try {
               con.close();
            } catch (Exception e) {
            }
 
            con = null;
         }
      }
 
return bean;
 
      }
   }