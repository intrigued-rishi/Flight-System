import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminModel {
	HttpServletRequest req;
	HttpServletResponse res;
	public AdminModel(HttpServletRequest request, HttpServletResponse response) {
		req=request;
		res=response;
	}
	void addFlight(String x1,String x2,int fees,String time) {
		ServletContext sc=req.getServletContext();
		System.out.println(x1+" to "+x2);
		Connection con=(Connection)sc.getAttribute("conn");
		Statement stmt;
		try {
			stmt = con.createStatement();
			String sql="INSERT INTO flights VALUES('"+x1+"','"+x2+"','"+time+"',"+fees+")";
			int rs=stmt.executeUpdate(sql);  
			System.out.println(rs);  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}
}
