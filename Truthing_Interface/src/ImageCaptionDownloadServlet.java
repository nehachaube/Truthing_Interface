//Neha Chaube
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;


public class ImageCaptionDownloadServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		
		ImageCaptionBean bean= new ImageCaptionBean(); 
		 bean.setImgurl(request.getParameter("imgcaptionstring"));
		 System.out.println("Images from front end "+bean.getImgurl());
		 try {
			 System.out.println("Entering to download");
			List<ImageCaptionBean> download= ImageCaptionDownloadDAO.imagecaptiondownload(bean);
			System.out.println("Printing the bean for download"+download);
			Gson gson = new Gson();
			PrintWriter outPrintWriter = response.getWriter();
			outPrintWriter.write(gson.toJson(download));
		} catch (SQLException e) {
			request.setAttribute("errorindb","Could not download as CSV!");
			PrintWriter outPrintWriter = response.getWriter();
			 outPrintWriter.write("errorindb");
			e.printStackTrace();
		}
	}

}
