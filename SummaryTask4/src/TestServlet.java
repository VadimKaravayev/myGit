

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import ua.karavayev.beans.Route;
import ua.karavayev.databasemanager.DBmanager;
import ua.karavayev.myExceptions.NotInDataBaseException;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DBmanager dbManager;

	@Resource(name = "jdbc/truckingdepot")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		dbManager = new DBmanager(dataSource);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		request.setAttribute("pack", "a plate of spaghetti");
		//request.getRequestDispatcher("/test-page.jsp").forward(request, response);
		request.getRequestDispatcher("/test-page.jsp").include(request, response);
		out.print("<p>P.S. enjoy your meal!</p>");
		request.getRequestDispatcher("/app/dispatcherHeader.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
