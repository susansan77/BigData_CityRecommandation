package dataimport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import required packages
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class Main {

	public static void main(String[] args){
		//new LocationTable().insert();
		new AllTable().insert();;
	}
	
}

class GetConnection {
	private String user;
	private String pass;
	private String database;
	
	public GetConnection(String user, String pass, String database) {
		this.user = user;
		this.pass = pass;
		this.database = database;
	}
	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.user);
		connectionProps.put("password", this.pass);
		conn = DriverManager.getConnection("jdbc:" + "mysql" + "://" + "localhost" + ":" + "3306/" + database, connectionProps);
		System.out.println("get connected to database " + database);
		return conn;
	}
}

class LocationTable {
	private Connection conn;
	
	private void getConnection() {
		String user = "root";
		String password = "Cc2042266";
		String database = "testdatabase";
		try {
			conn = new GetConnection(user, password, database).getConnection();
		} catch (SQLException se) {
			System.err.println("connection failed");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	protected void insert() {
		getConnection();
		try {
			String line = "";
			Statement st = conn.createStatement();
			BufferedReader buff = new BufferedReader(new FileReader("CityLatitudeLongitudeBudget.txt"));
			while ((line = buff.readLine()) != null) {
				String[] tokens = line.split(",");
				String query = "insert into location values (";
				query += "\"" + tokens[0].toLowerCase() + "\", " + Float.parseFloat(tokens[1].substring(0, tokens[1].length() - 1)) 
						+ ", " + Float.parseFloat(tokens[2].substring(0, tokens[2].length() - 1)) + ", " + Float.parseFloat(tokens[3]) + ");";
				System.out.println(query);
				st.executeUpdate(query);
			}
			buff.close();
			st.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (FileNotFoundException ex) {
			System.err.println("file not found");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class WeatherTable {
	private Connection conn;
	
	private void getConnection() {
		String user = "root";
		String password = "Cc2042266";
		String database = "testdatabase";
		try {
			conn = new GetConnection(user, password, database).getConnection();
		} catch (SQLException se) {
			System.err.println("connection failed");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	public void insert() {
		getConnection();
		try {
			String line = "";
			Statement st = conn.createStatement();
			BufferedReader buff = new BufferedReader(new FileReader("CityWeather.txt"));
			while ((line = buff.readLine()) != null) {
				String[] tokens = line.split(",");
				String query = "insert into weather values (" + "\"" + tokens[0].toLowerCase() + "\",";
				query += getWeatherData(tokens);
				query = query.substring(0, query.length() - 1) + ");";
				System.out.println(query);
				st.executeUpdate(query);
			}
			buff.close();
			st.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (FileNotFoundException ex) {
			System.err.println("file not found");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String getWeatherData(String[] tokens) {
		String res = "";
		for (int i = 1; i < tokens.length; i++) {
			res += Float.parseFloat(tokens[i]) + ",";
		}
		return res;
	}
}

class AllTable {
	private Connection conn;
	private HashMap<String, String> map = new HashMap<String, String>();
	
	private void getConnection() {
		String user = "root";
		String password = "Cc2042266";
		String database = "testdatabase";
		try {
			conn = new GetConnection(user, password, database).getConnection();
		} catch (SQLException se) {
			System.err.println("connection failed");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	protected void insert() {
		getConnection();
		Utility.loadHashMap(map, "KeyWords-2.txt");
		try {
			String line = "";
			Statement st = conn.createStatement();
			BufferedReader buff = new BufferedReader(new FileReader("CommaFile.txt"));
			HashSet<String> set = new HashSet<String>();
			while ((line = buff.readLine()) != null) {
				String[] tokens = line.split(",");
				String cat = map.get(tokens[1]);
				if (cat.equals("restaurant")) {
					String query = "insert into restaurant values (";
					String name = Utility.processString(tokens[7]);
					String address = Utility.processString(tokens[9]);
					String review = Utility.processString(tokens[6]);
					if (tokens.length == 10) {
//						query += "\"" + tokens[8] + "\",\"" + name + "\",\"" + tokens[0].toLowerCase() + "\",\"" + tokens[2] + "\",\"" + 
//								address + "\",\"" + tokens[5] + "\",\"" + tokens[1] + "\"," + Float.parseFloat(tokens[3]) + ",\""
//								+ review + "\");";
//						//System.out.println(query);
//						st.executeUpdate(query);
					} else if (tokens.length == 11) {
						String[] cats = tokens[10].split(":");
						String firstQuery = "insert into restaurant values (" + "\"" + tokens[8] + "\",\"" + name + "\",\"" + tokens[0].toLowerCase() + "\",\"" + tokens[2] + "\",\"" + 
								address + "\",\"" + tokens[5] + "\",\"";
						String secondQuery = "\"," + Float.parseFloat(tokens[3]) + ",\"" + review + "\");";
						for (String s: cats) {
							query = firstQuery + s + secondQuery;
							//System.out.println(query);
							st.executeUpdate(query);
							
						}
					}
				
//				} else if (cat.equals("hotel")) {
//					String name = Utility.processString(tokens[7]);
//					String review = Utility.processString(tokens[9]);
//					String address = Utility.processString(tokens[6]);
//					String query = "insert into lodging values (";
//					query += "\"" + tokens[8] + "\",\"" + name + "\",\"" +tokens[0].toLowerCase() + 
//							"\",\"" + tokens[2] + "\",\"" + address +
//							"\",\"" + tokens[5] + "\"," + Float.parseFloat(tokens[3]) + ",\"" + review + "\");";
//					System.out.println(query);
//					break;
					//st.executeUpdate(query);
				}
			}
			buff.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("file was not found. please check path.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Investigation {
	private Connection conn;
	private HashMap<String, String> map = new HashMap<String, String>();
	
	private void getConnection() {
		String user = "root";
		String password = "Cc2042266";
		String database = "testdatabase";
		try {
			conn = new GetConnection(user, password, database).getConnection();
		} catch (SQLException se) {
			System.err.println("connection failed");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	protected void investigate() {
		getConnection();
		Utility.loadHashMap(map, "KeyWords-2.txt");
		HashSet<Integer> foodSet = new HashSet<Integer>();
		HashSet<Integer> lodgingSet = new HashSet<Integer>();
		HashSet<Integer> pointsSet = new HashSet<Integer>();
		try {
			String line = "";
			Statement st = conn.createStatement();
			BufferedReader buff = new BufferedReader(new FileReader("CommaFile.txt"));
			//HashSet<Integer> set = new HashSet<Integer>();
			while ((line = buff.readLine()) != null) {
				 String[] tokens = line.split(",");
				 if (tokens.length == 13) {
					 System.out.println(tokens[8]);
				 }
			}
//			System.out.println("Lengths for food are: " + foodSet.size());
//			System.out.println("Lengths for points are: " + pointsSet.size());
//			System.out.println("Lengths for hotels are: " + lodgingSet.size());
			buff.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("file was not found. please check path.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class MasterTable {
	private Connection conn;
	private HashMap<String, String> map = new HashMap<String, String>();
	private void getConnection() {
		String user = "root";
		String password = "Cc2042266";
		String database = "testdatabase";
		try {
			conn = new GetConnection(user, password, database).getConnection();
		} catch (SQLException se) {
			System.err.println("connection failed");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	protected void insert(){
		getConnection();
		Utility.loadHashMap(map, "KeyWords-2.txt");
		//System.out.println(map.size());
		try {
			String line = "";
			Statement st = conn.createStatement();
			BufferedReader buff = new BufferedReader(new FileReader("CommaFile.txt"));
			//HashSet<Integer> set = new HashSet<Integer>();
			while ((line = buff.readLine()) != null) {
				 String[] tokens = line.split(",");
				 String query = "insert into master values (";
				 query += "\"" + tokens[8] + "\",\"" + tokens[0].toLowerCase() + "\",\"" + map.get(tokens[1]) + "\");";
				 //System.out.println(query);
				 st.executeUpdate(query);
			}
			buff.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("file was not found. please check path.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Utility {
	public static void loadHashMap(HashMap<String, String> map, String table) {
		try {
			BufferedReader buff = new BufferedReader(new FileReader(table));
			String line = "";
			while ((line = buff.readLine()) != null) {
				String[] tokens = line.split(",");
				map.put(tokens[0], tokens[1]);
			}
			System.out.println("the size of map is: " + map.size());
			buff.close();
		} catch (IOException ex){
			ex.printStackTrace();
		}
	}
	
	//public static void get
	
	public static String processString(String s) {
		StringBuilder sb = new StringBuilder(s);
		for (int i = 0; i < sb.length();) {
			if (sb.charAt(i) != '"') {
				i++;
			} else {
				sb.insert(i, "\\");
				i = i + 2;
			}
		}
		return sb.toString();
	}
}