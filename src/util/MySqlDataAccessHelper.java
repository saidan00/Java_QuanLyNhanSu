package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlDataAccessHelper {
	String Host;
	String UserName;
	String Password;
	String DataBase;

	Connection connect;
	Statement stmt;
	ResultSet result;

	public MySqlDataAccessHelper() {
		this.Host = "localhost";
		this.UserName = "root";
		this.Password = "passne";
		this.DataBase = "4eles";

		this.connect = null;
		this.stmt = null;
		this.result = null;
	}

	protected void driverTest() throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("MySQL JDBC Driver not found");
		}
	}

	protected Connection getConnect() throws Exception {
		// If connect != null -> return
		if (this.connect == null) {
			// Check for driver
			driverTest();

			// Create URL connect to DB server
			String url = "jdbc:mysql://" + this.Host + "/" + this.DataBase
					+ "?useLegacyDatetimeCode=false&serverTimezone=UTC";
			url += "&user=" + this.UserName;
			url += "&password=" + this.Password;
			// create connect
			try {
				this.connect = DriverManager.getConnection(url);
			} catch (SQLException e) {
				throw new Exception("Can not connect to database");
			}
		}
		return this.connect;
	}

	// Create statement to execute query
	protected Statement getStatement() throws Exception {
		if (this.stmt == null || this.stmt.isClosed()) {
			this.stmt = this.getConnect().createStatement();
		}
		return stmt;
	}

	public ResultSet executeQuery(String Query) throws Exception {
		try {
			this.result = this.getStatement().executeQuery(Query);
		} catch (Exception e) {
			throw new Exception("Error: " + e.getMessage() + " - " + Query);
		}
		return this.result;
	}

	public int executeUpdate(String Query) throws Exception {
		int res = Integer.MIN_VALUE;
		try {
			res = this.getStatement().executeUpdate(Query);
		} catch (Exception e) {
			throw new Exception("Error: " + e.getMessage() + " - " + Query);
		} finally {
			this.Close();
		}

		return res;
	}

	public void Close() throws SQLException {
		if (this.result != null || !this.result.isClosed()) {
			this.result.close();
			this.result = null;
		}

		if (this.stmt != null || !this.stmt.isClosed()) {
			this.stmt.close();
			this.stmt = null;
		}

		if (this.connect != null || !this.connect.isClosed()) {
			this.connect.close();
			this.connect = null;
		}
	}

	public void displayError(SQLException ex) {
		System.out.println(" Error Message:" + ex.getMessage());
		System.out.println(" SQL State:" + ex.getSQLState());
		System.out.println(" Error Code:" + ex.getErrorCode());
	}
}