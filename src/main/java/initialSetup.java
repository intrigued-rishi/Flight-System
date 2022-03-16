

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class initialSetup
 *
 */
@WebListener
public class initialSetup implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public initialSetup() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	ServletContext sc = sce.getServletContext();
    	Connection con = (Connection) sc.getAttribute("conn");
    	sc.removeAttribute("conn");
    	try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    private void usersTable(Connection con) {
    	try {
    		Statement stmt;
			stmt = con.createStatement();
			String sql="CREATE TABLE IF NOT EXISTS users (userId INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,name VARCHAR(25) NOT NULL);"; 
			int rs = stmt.executeUpdate(sql);
			System.out.println(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
    }
    private void flightsTable(Connection con) {
    	try {
    		Statement stmt;
			stmt = con.createStatement();
			String sql="CREATE TABLE IF NOT EXISTS flights (src VARCHAR(25), dest VARCHAR(25), time TIME NOT NULL, fees INT NOT NULL, PRIMARY KEY ( src,dest));"; 
			int rs = stmt.executeUpdate(sql);
			System.out.println(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
    }
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	ServletContext sc = sce.getServletContext();
		String connString = sc.getInitParameter("dbstring");
		//sc.setAttribute("key2", connString);
		
		String connUser = sc.getInitParameter("dbuser");
		//sc.setAttribute("key3", connUser);
		
		String connPwd = sc.getInitParameter("dbpass");
		//sc.setAttribute("key4", connPwd);
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
				connString,connUser,connPwd); 
			System.out.println("**********************************");
			sc.setAttribute("conn",con);
			System.out.println(con);
			usersTable(con);
			flightsTable(con);
 
		}catch(Exception e){ System.out.println(e);}
    }
	
}
