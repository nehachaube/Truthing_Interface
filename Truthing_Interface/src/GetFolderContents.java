import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetFolderContents extends HttpServlet{
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
		try
		{
			//String folder = request.getParameter("path");
			final File folder = new File("C:/Users/Divya/Desktop/art");
			String s = "";
			for (File fileEntry : folder.listFiles()) {
				s += folder+fileEntry.getName() + "\t";
		    }
			PrintWriter out = response.getWriter();
		    out.write(s);
		}
		catch (Throwable e)
		{
		     System.out.println(e);
		}
	}
}


