package queries;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Query {
	private Connection conn;

	private void getConnection() {
		String user = "root";
		String password = "";
		String database = "yelp";
		try {
			conn = new GetConnection(user, password, database).getConnection();
		} catch (SQLException se) {
			System.err.println("connection failed");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	public List<Business> getBusinessByCity(String city, String tablename, String keyword) {
		getConnection();
		String query="";
		if(tablename.equals("restaurant")){
			 query = "select * from " + tablename.toLowerCase() + " where city = '" + city.toLowerCase()
			+ "' and category like '%" + keyword.toLowerCase() + "%';";
	// System.out.println(query);
		}else{
			 query = "select * from " + tablename.toLowerCase() + " where city = '" + city.toLowerCase()
				+  "';";
		}
		
		// System.out.println(query);
		List<Business> list = new ArrayList<Business>();
		HashSet<String> set = new HashSet<String>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				String id = rs.getString("id");
				if (!set.contains(id)) {
					set.add(id);
					Business business = new Business();
					list.add(business);
					business.setName(rs.getString("name"));
					business.setAddress(rs.getString("address"));
					business.setPhone(rs.getString("phone"));
					business.setRating("" + rs.getFloat("rating"));
					business.setUrl(rs.getString("url"));
					if(tablename.equals("restaurant")){
						business.setCategory(rs.getString("category"));
					}
					
					business.setReview(rs.getString("review"));
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		}
		return list;
	}

	public List<CityWithScore> getCities(SearchCritiria critiria) {
		getConnection();
		// first load cities from file
		String path = "/Users/zishanqin/Documents/webWorkspace/CityRecommandation/WebContent/queryResult/part-r-"
				+ critiria.getRestaurantRating() + "" + critiria.getLodgingRating() + "" + critiria.getPointsRating()
				+ ".txt";
		// String path = "part-r-" + critiria.getRestaurantRating() +
		// critiria.getLodgingRating() + critiria.getPointsRating() + ".txt";
		System.out.println(path);
		try {
			BufferedReader buff = new BufferedReader(new FileReader(path));
			ArrayList<CityWithScore> list = new ArrayList<CityWithScore>();
			String line = "";
			while ((line = buff.readLine()) != null) {
				// System.out.println(line);
				String[] tokens = line.split("\t");
				list.add(new CityWithScore(tokens[0], tokens[1]));
			}
			// System.out.println(list.size());
			Statement st = conn.createStatement();

			// first filter out cities by budget
			if (!critiria.getLowBudget().equals("-1")) {
				// query from database to get all budget matching
				String budgetQuery = "select city from location where budget >= " + critiria.getLowBudget()
						+ " and budget < " + critiria.getHighBudget() + ";";
				// System.out.println(budgetQuery);
				ResultSet budgetResult = st.executeQuery(budgetQuery);
				HashSet<String> cityBudget = new HashSet<String>();
				while (budgetResult.next()) {
					cityBudget.add(budgetResult.getString("city"));
				}
				System.out.println(cityBudget.size());
				// remove cities that does not in city budget set
				int index = 0;
				while (index < list.size()) {
					// System.out.println(cityBudget.contains("austin"));
					// System.out.println(list.get(index).getCity());
					if (cityBudget.contains(list.get(index).getCity().toLowerCase())) {
						// System.out.println(list.get(index).getCity());
						index++;
					} else {
						list.remove(index);
					}
				}
				System.out.println("size after budget is: " + list.size());
			}

			// second filter out cities by temperature
			if (!critiria.getLowTemperature().equals("-1")) {
				System.out.println("program goes into temperature");
				String temperatureQuery = "select city from weather where " + critiria.getDepatureMonth().toLowerCase()
						+ " >= " + critiria.getLowTemperature() + " and " + critiria.getDepatureMonth().toLowerCase()
						+ " < " + critiria.getHighTemperature() + ";";
				System.out.println(temperatureQuery);
				ResultSet temperatureResult = st.executeQuery(temperatureQuery);
				HashSet<String> cityTemperature = new HashSet<String>();
				while (temperatureResult.next()) {
					cityTemperature.add(temperatureResult.getString("city"));
				}
				// remove cities that does not in city budget set
				int index = 0;
				while (index < list.size()) {
					if (cityTemperature.contains(list.get(index).getCity().toLowerCase())) {
						index++;
					} else {
						list.remove(index);
					}
				}
			}

			// finally filter out cities by radius
			if (!critiria.getComeFrom().equals("-1")) {
				int index = 0;
				String comeFrom = critiria.getComeFrom().toLowerCase();
				double baseLatitude = getLatitude(conn, comeFrom);
				double baseLongitude = getLongitude(conn, comeFrom);
				double distanceLimit = Double.parseDouble(critiria.getRadius());
				while (index < list.size()) {
					String cityName = list.get(index).getCity().toLowerCase();
					double cityLatitude = getLatitude(conn, cityName);
					double cityLongitude = getLongitude(conn, cityName);
					if (distFrom(cityLatitude, cityLongitude, baseLatitude, baseLongitude) <= distanceLimit) {
						index++;
					} else {
						list.remove(index);
					}
				}
			}
			return list;
		} catch (FileNotFoundException ex) {
			System.err.println("file not found");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			// return null;
		}
		return null;
	}

	private double getLongitude(Connection conn, String city) {
		String query = "select longitude from location where city = '" + city.toLowerCase() + "';";
		// System.out.println(query);
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				return rs.getFloat("longitude");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	private double getLatitude(Connection conn, String city) {
		String query = "select latitude from location where city = '" + city.toLowerCase() + "';";
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				return rs.getFloat("latitude");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	private double distFrom(double lat1, double lng1, double lat2, double lng2) {
		double earthRadius = 6731000;
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double dist = (double) (earthRadius * c) / 1609.34; // convert meter to
															// mile

		return dist;
	}

}
