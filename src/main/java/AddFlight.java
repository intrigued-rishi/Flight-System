

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;


/**
 * Servlet implementation class AddFlight
 */
@WebServlet("/addFlight")
public class AddFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFlight() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		RequestDispatcher rd=request.getRequestDispatcher("booking.jsp");
//		rd.forward(request, response);
		PrintWriter pw=response.getWriter();
		pw.println();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext sc=request.getServletContext();
		String x1= request.getParameter("src");
		String x2= request.getParameter("dest");
		int fees = Integer.parseInt(request.getParameter("fees"));
		String time = request.getParameter("time");
		
		AdminModel am=new AdminModel(request,response);
		am.addFlight(x1,x2,fees,time);
		
//	    PrintWriter pw=response.getWriter();
//		response.setContentType("application/json");
//	    response.setCharacterEncoding("UTF-8");
//	    pw.print(test);
//	    pw.flush();  
	}

}
