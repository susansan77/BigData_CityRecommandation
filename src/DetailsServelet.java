

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
 * Servlet implementation class DetailsServelet
 */
@WebServlet("/details")
public class DetailsServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String city=request.getParameter("cityName");
		String keyWord=request.getParameter("filter");
		Query query=new Query();
		List<Business> result=query.getBusinessByCity(city, "restaurant", keyWord);
		String json=new Gson().toJson(result);
		response.setContentType("application/json");
		response.getWriter().print(json);
		//RequestDispatcher dispatcher=request.getRequestDispatcher("filteredrestaurant.jsp");
	}    
  
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String city=request.getParameter("cityName");	
//		String keyWord=request.getParameter("filter");
//		Query query=new Query();
//		List<Business> result=query.getBusinessByCity(city, "restaurant", keyWord);
//		request.setAttribute("filteredResult", result);
//		request.setAttribute("cityName", city);
//		RequestDispatcher dispatcher=request.getRequestDispatcher("filteredrestaurant.jsp");
//		dispatcher.forward(request, response);
	}

}
