import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ImageCaptionDAO {
	static Connection con=null;
    static ResultSet rs = null;

    public static ImageCaptionBean captionupdate(ImageCaptionBean bean) throws SQLException {

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
		
		String insUpQuery="IF EXISTS(select * from truthinginterface.caption where userid="+userid+" and imgurl="+imgurl+") update truthinginterface.caption set caption="+imgcaption+" where "
				+ "userid="+userid+" and imgurl="+imgurl+" ELSE insert into truthinginterface.caption(imgurl,caption,userid,username) values('"+imgurl+"','"+imgcaption+"','"+userid+"','"+username+"'";
		ps = con.prepareStatement(insUpQuery);
		ps.executeUpdate();
		}
		catch (Exception e) {
		      throw e;
		    } finally {
		      con.close();
		    }
		return bean;
    }
   
}
