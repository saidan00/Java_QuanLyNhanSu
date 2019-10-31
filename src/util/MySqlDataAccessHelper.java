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
		this.DataBase = "quanlynhansu";

		this.connect = null;
		this.stmt = null;
		this.result = null;
	}

	protected void driverTest() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected Connection getConnect() {
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
				displayError(e);
			}
		}
		return this.connect;
	}

	// Create statement to execute query
	protected Statement getStatement() {
		if (this.stmt == null) {
			try {
				this.stmt = this.getConnect().createStatement();
			} catch (SQLException e) {
				displayError(e);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return stmt;
	}

	public ResultSet executeQuery(String Query) {
			try {
				this.result = this.getStatement().executeQuery(Query);
			} catch (SQLException e) {
				displayError(e);
			}
		return this.result;
	}

	public int executeUpdate(String Query) {
		int res = Integer.MIN_VALUE;
		try {
			res = this.getStatement().executeUpdate(Query);
		} catch (SQLException e) {
			displayError(e);
		} finally {
			this.Close();
		}

		return res;
	}

	public void Close() {
		try {
			if (this.result != null) {
				this.result.close();
				this.result = null;
			}
	
			if (this.stmt != null) {
				this.stmt.close();
				this.stmt = null;
			}
	
			if (this.connect != null) {
				this.connect.close();
				this.connect = null;
			}
		} catch (SQLException e) {
			displayError(e);
		}
	}

	public void displayError(SQLException ex) {
		System.out.println(" Error Message:" + ex.getMessage());
		System.out.println(" SQL State:" + ex.getSQLState());
		System.out.println(" Error Code:" + ex.getErrorCode());
	}
}