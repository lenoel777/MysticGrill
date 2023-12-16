package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;

public class User {
	private int userId;
	private String userRole, userName, userEmail, userPassword;
	
	public User(int userId, String userRole, String userName, String userEmail, String userPassword) {
		super();
		this.userId = userId;
		this.userRole = userRole;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}
	
	public static ArrayList<User> loadUsers() {
		ArrayList<User> users = new ArrayList<>();
		String query = "SELECT * FROM user";
		ResultSet rs = Connect.getConnection().executeQuery(query);
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				String role = rs.getString(2);
				String name = rs.getString(3);
				String email = rs.getString(4);
				String password = rs.getString(5);

				users.add(new User(id, role, name, email, password));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return users;
	}

	public static void insertUser(String name, String email, String password) {
		String query = String.format(
				"INSERT INTO user (userRole, userName, userEmail, userPassword) VALUES ('Customer', '%s', '%s', '%s')", 
				name, email, password);
		Connect.getConnection().executeUpdate(query);
	}

	public static void deleteUser(int id) {
		String query = "DELETE FROM user WHERE userId = ?";
		PreparedStatement ps = Connect.getConnection().prepareStatement(query);

		try {
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateUser(int id, String role, String name, String email, String password) {
		String query = "UPDATE user SET userRole = ?, userName = ?, userEmail = ?, userPassword = ? WHERE userId = ?";

		PreparedStatement ps = Connect.getConnection().prepareStatement(query);

		try {
			ps.setString(1, role);
			ps.setString(2, name);
			ps.setString(3, email);
			ps.setString(4, password);
			ps.setInt(5, id);
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
}
