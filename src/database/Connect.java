package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class Connect {
	private final String USERNAME = "root";
	private final String PASSWORD = "";
	private final String DATABASE = "mysticgrill";
	private final String HOST = "localhost:3306";
	private final String CONECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);

	private Connection con;
	private Statement st;
	private static Connect connect;
	private ResultSet rs;

	private Connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(CONECTION, USERNAME, PASSWORD);
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to connect the database, the system is terminated!");
			System.exit(0);
		}
	}

	public static synchronized Connect getConnection() {
		return connect = (connect == null) ? new Connect() : connect;
	}

	public ResultSet executeQuery(String query) {
			try {
				st = con.createStatement();
				rs = st.executeQuery(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return rs;
	}
	
	public ResultSet getResultSet() {
        return rs;
	}

	public void executeUpdate(String query) {
			try {
				st = con.createStatement();
				st.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	public PreparedStatement prepareStatement(String query) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ps;
	}
	
	public void close() throws Exception {

		if(con != null) {
			con.close();
		}
		
	}
}
