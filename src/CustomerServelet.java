

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import queries.CityList;
import queries.CityWithScore;
import queries.Query;
import queries.SearchCritiria;

/**
 * Servlet implementation class CustomerServelet
 */
@WebServlet("/customer")
public class CustomerServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String restaurant=getSelection(request.getParameter("restaurantSelection"));
		String pointsOfInterests=getSelection(request.getParameter("pointsOfInterestsSelection"));
		String hotels=getSelection(request.getParameter("hotelsSelection"));
		System.out.println(restaurant);
		System.out.println(pointsOfInterests);
		System.out.println(hotels);
		String budget=request.getParameter("budget_id").toLowerCase();
	
		String month=request.getParameter("month_id").toLowerCase();
	
		String tempreture=request.getParameter("temp_id").toLowerCase();

		String city=request.getParameter("city_id").toLowerCase();

		String radius=request.getParameter("radius_id").toLowerCase();
	
		boolean weatherOK=isValidWeather(month,tempreture);
		if(weatherOK==false){
			 request.getRequestDispatcher("customer.jsp").forward(request, response);

			return;
		}
		boolean distanceOK=isValidDistance(city,radius);
		if(distanceOK==false){
			request.getRequestDispatcher("customer.jsp").forward(request, response);;
			return;
		}
		SearchCritiria critiria=new SearchCritiria(restaurant, pointsOfInterests, hotels);
		if(!budget.equals("select")){
			String lowB="-1";
			String HighB="-1";
			if(budget.equals("<100")){
				lowB="0";
				HighB="100";
			}
			if(budget.equals("<150")){
				lowB="100";
				HighB="150";
			}
			if(budget.equals("<200")){
				lowB="150";
				HighB="200";
			}
			if(budget.equals("<300")){
				lowB="200";
				HighB="300";
			}
			critiria.setLowBudget(lowB);
			critiria.setHighBudget(HighB);
			
		}
		
		if(!month.equals("select")){
			critiria.setDepatureMonth(month);
			String lowT="-1";
			String HighT="-1";
			if(tempreture.equals("hot(80~100)")){
				lowT="80";
				HighT="100";
			}
			if(tempreture.equals("warm(60~80)")){
				lowT="60";
				HighT="80";
			}
			if(tempreture.equals("cool(45~60)")){
				lowT="45";
				HighT="60";
			}
			if(tempreture.equals("cold(<45)")){
				lowT="0";
				HighT="45";
			}
			critiria.setLowTemperature(lowT);
			critiria.setHighTemperature(HighT);
			
		}
		
		if(!city.equals("select")){
			critiria.setComeFrom(city);
			critiria.setRadius(String.valueOf(Integer.parseInt(radius)*50));
		}
		Query query=new Query();
		List<CityWithScore> cities=query.getCities(critiria);
		CityList clist=new CityList(cities);
		request.setAttribute("cityList", clist);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("cityList.jsp");
		
		dispatcher.forward(request, response);
	}

	private boolean isValidDistance(String city, String radius) {
		// TODO Auto-generated method stub
		if(city.equals("select")&&radius.equals("select")){
			return true;
		}
		if(city.equals("select")||radius.equals("")){
			return false;
		}
		return true;
	}

	private boolean isValidWeather(String month, String tempreture) {
		// TODO Auto-generated method stub
		if(month.equals("select")&&tempreture.equals("select")){
			return true;
		}
		if(month.equals("select")||tempreture.equals("select")){
			return false;
		}
		return true;
	}
	public String getSelection(String str){
		if(str.equals("select")){
			return "2";
		}
		else if(str.equals("very important")){
			return "3";
		}
		else if(str.equals("medium")){
			return "2";
		}else{
			return "1";
		}
	}

}
