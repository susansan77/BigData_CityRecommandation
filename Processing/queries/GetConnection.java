package queries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GetConnection {
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
