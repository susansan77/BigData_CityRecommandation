

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import queries.Business;
import queries.Query;

/**
 * Servlet implementation class CityServelet
 */
@WebServlet("/cityList")
public class CityServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("Susan find you!");
	}    
  
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String city=request.getParameter("city_name");
		RequestDispatcher dispatcher=request.getRequestDispatcher("details.jsp");
		
		Query query=new Query();
		List<Business> restaurants=query.getBusinessByCity(city.toLowerCase(), "restaurant", "");
		List<Business> interests=query.getBusinessByCity(city.toLowerCase(), "pointsofinterest", "");
		List<Business> hotels=query.getBusinessByCity(city.toLowerCase(), "lodging", "");
		
		request.setAttribute("city_restaurant", restaurants);
		request.setAttribute("city_interest",interests );
		request.setAttribute("city_hotels", hotels);
		request.setAttribute("city_name", city);
		dispatcher.forward(request, response);
		
	}

}
