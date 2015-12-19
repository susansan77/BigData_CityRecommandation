

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import queries.Business;
import queries.Query;

/**
 * Servlet implementation class FilteredServelet
 */
@WebServlet("/filteredrestaurant")
public class FilteredServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher=request.getRequestDispatcher("filteredrestaurant.jsp");
		String city=request.getParameter("cityName");	
		String keyWord=request.getParameter("filter");
		Query query=new Query();
		List<Business> result=query.getBusinessByCity(city, "restaurant", keyWord);
		request.setAttribute("filteredResult", result);
		request.setAttribute("cityName", city);
		dispatcher.forward(request, response);
	}

}
