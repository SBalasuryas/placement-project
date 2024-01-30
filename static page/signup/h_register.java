package signup;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class register
 */
@WebServlet("/h_register")
public class h_register extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String username = request.getParameter("username");
		String useremail = request.getParameter("useremail");
		String usernumber = request.getParameter("usernumber");
		String useraddress = request.getParameter("useraddress");
		RequestDispatcher dispatcher = null;
		Connection con=null;
		
		
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?useSSL=false", "root", "");
		PreparedStatement pst = con
		.prepareStatement("insert into user (username, useremail, usernumber, useraddress) values (?,?,?,?) ");
		pst.setString(1, username);
		pst.setString(2, useremail);
		pst.setString(3, usernumber); 
		pst.setString(4, useraddress);
		int rowCount = pst.executeUpdate();
		dispatcher = request.getRequestDispatcher("registration.jsp");
		if (rowCount > 0) {
		request.setAttribute("status", "success");
		}else {
		request.setAttribute("status", "failed");
		}
		dispatcher.forward(request, response);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
		    try {
		        if (con != null) {
		            con.close();
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}
	}
}

