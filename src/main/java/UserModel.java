import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

class Flight{
	public String from;
	public String to;
	public int fees;
	public Time time;
	Flight(String x1,String x2,int f,Time time2){
		from=x1;
		to=x2;
		f=fees;
		time=time2;
	}
};

public class UserModel {
	HttpServletRequest req;
	HttpServletResponse res;
	public UserModel(HttpServletRequest request, HttpServletResponse response) {
		req=request;
		res=response;
	}
	void getFlight(String x1,String x2) throws IOException {
		ServletContext sc=req.getServletContext();
		Connection con = (Connection)sc.getAttribute("conn");
		
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM flights WHERE src='"+x1+"' AND dest='"+x2+"';";
	        ResultSet rs = stmt.executeQuery(sql);
	        ArrayList<Flight> arr=new ArrayList<Flight>();
	        while(rs.next()){
	            //Display values
	        	Flight f=new Flight(rs.getString("src"),rs.getString("dest") , rs.getInt("fees"), rs.getTime("time"));
//	            System.out.print("From: " + );
//	            System.out.print(", To: " + rs.getString("dest"));
//	            System.out.print(", Fees: " + rs.getInt("fees"));
//	            System.out.println(", Time: " + rs.getTime("time"));
	        	arr.add(f);
	         }
	        String json = new Gson().toJson(arr);
		    PrintWriter pw=res.getWriter();
			res.setContentType("application/json");
		    res.setCharacterEncoding("UTF-8");
		    pw.print(json);
		    pw.flush(); 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		
	}
}
