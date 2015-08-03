//Neha Chaube
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ImageCaptionDownloadDAO {
	static Connection con=null;
    static ResultSet rs = null;

    public static List<ImageCaptionBean> imagecaptiondownload(ImageCaptionBean bean) throws SQLException {

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
		//int userid=bean.getCurrentUserId();
		//String username=bean.getCurrentUserName();
		String imgurl=bean.getImgurl();
		//String imgcaption=bean.getImgcaption();
		 String searchQueryDownload = "select * from truthinginterface.caption where imgurl IN("+imgurl+") ORDER BY imgurl";
		 System.out.println("Query "+searchQueryDownload);
		 stmt=con.createStatement();
         rs = stmt.executeQuery(searchQueryDownload);
         List<ImageCaptionBean> img = new ArrayList<ImageCaptionBean>();
         while(rs.next())
         {
        	 ImageCaptionBean bean1= new ImageCaptionBean();
        	 bean1.setCurrentUserId(rs.getInt("userid"));
             bean1.setImgurl(rs.getString("imgurl"));
             bean1.setImgcaption(rs.getString("caption"));
             bean1.setCurrentUserName(rs.getString("username"));
             img.add(bean1);
             System.out.println(bean1.getImgcaption());
             System.out.println(bean1.getImgurl());
             
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
