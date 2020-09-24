package orq.oar.EcomApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = "/ss")
public class SecondServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String pname=(String) req.getAttribute("prdnm");
		String pqty=(String) req.getAttribute("prdqty");
		int qty=Integer.parseInt(pqty);
		int totalsum=MobilePrice.mobilePrice(pname, qty);
		req.setAttribute("total", totalsum);
		PrintWriter out= resp.getWriter();
		out.println("<html><body bgcolor='green'><font color='white' size=30>"+"Your product name is "+pname+" mobile and you bought total "+qty + " numbers of mobile and you have to pay "+totalsum +"/- Rupees only </font></body></html>");
		out.flush();
		out.close();
		
		
		Connection con=null;
		PreparedStatement pstmt=null;
		String qry="insert into oejm4.product values(?,?,?)";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=root");
			pstmt=con.prepareStatement(qry);
			pstmt.setString(1, pname);
			pstmt.setInt(2, qty);
			pstmt.setDouble(3, totalsum);
			 pstmt.executeUpdate();
		
		}
		catch (ClassNotFoundException | SQLException e)
		{
			
			e.printStackTrace();
		}
		finally
		{
			if (con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt!=null)
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
		}
	}
	

}
