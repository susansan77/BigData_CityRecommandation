

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IndexServelet
 */
@WebServlet("/index")
public class IndexServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String restaurant=request.getParameter("restaurant").toLowerCase();
		String pointsOfInterests=request.getParameter("pointsOfInterests").toLowerCase();
		String hotels=request.getParameter("hotels").toLowerCase();
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("customer.jsp");

		request.setAttribute("restaurantSelection",restaurant);
		request.setAttribute("pointsOfInterestsSelection", pointsOfInterests);
		request.setAttribute("hotelsSelection", hotels);
		//request.s
		System.out.println(request.getAttribute("restaurantSelection"));
		System.out.println(request.getAttribute("pointsOfInterestsSelection"));
		System.out.println(request.getAttribute("hotelsSelection"));
		dispatcher.forward(request, response);
		return;
	
	
	}
}
