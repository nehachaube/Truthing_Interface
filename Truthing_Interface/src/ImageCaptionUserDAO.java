import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ImageCaptionUserDAO {
	static Connection con=null;
    static ResultSet rs = null;

    public static List<ImageCaptionBean> imagecaptionuserdata(ImageCaptionBean bean) throws SQLException {

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
		try
		{
		int userid=bean.getCurrentUserId();
		String username=bean.getCurrentUserName();
		String imgurl=bean.getImgurl();
		String imgcaption=bean.getImgcaption();
		 String searchQuery = "select * from truthinginterface.caption where username='"+username+"' AND imgurl IN("+imgurl+") AND userid="+userid;
		 stmt=con.createStatement();
         rs = stmt.executeQuery(searchQuery);
         List<ImageCaptionBean> img = new ArrayList<ImageCaptionBean>();
         while(rs.next())
         {
        	 ImageCaptionBean bean1= new ImageCaptionBean();
        	 bean1.setCurrentUserId(rs.getInt("userid"));
             bean1.setImgurl(rs.getString("imgurl"));
             bean1.setImgcaption(rs.getString("imgcaption"));
             img.add(bean1);
         }
		return img;
		}
		catch (Exception e) {
		      throw e;
		    } finally {
		      rs.close();
		      stmt.close();
		      con.close();
		    }
    }
   

}
