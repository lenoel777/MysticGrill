package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.Connect;

public class Order {
	private int orderId, orderUserId, orderTotal;
	private String orderStatus;
	private Date orderDate;
	private static ArrayList<Order> orders = new ArrayList<>();
	
	public Order(int orderId, int orderUserId, String orderStatus, Date orderDate, int orderTotal) {
		super();
		this.orderId = orderId;
		this.orderUserId = orderUserId;
		this.orderStatus = orderStatus;
		this.orderDate = orderDate;
		this.orderTotal = orderTotal;
	}
	
	public static ArrayList<Order> loadOrders() {
		orders.clear();
		String query = "SELECT * FROM `order`";
		ResultSet rs = Connect.getConnection().executeQuery(query);
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				int userId = rs.getInt(2);
				String status = rs.getString(3);
				Date date = rs.getDate(4);
				int total = rs.getInt(5);

				orders.add(new Order(id, userId, status, date, total));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return orders;
	}
	
	public static ArrayList<Order> loadPendingOrders() {
		orders.clear();
	    ArrayList<Order> pendingOrders = new ArrayList<>();
	    String query = "SELECT * FROM `order` WHERE orderStatus = 'Pending'";
	    try {
	        ResultSet rs = Connect.getConnection().executeQuery(query);
	        while (rs.next()) {
	            int id = rs.getInt(1);
	            int userId = rs.getInt(2);
	            String status = rs.getString(3);
	            Date date = rs.getDate(4);
	            int total = rs.getInt(5);

	            pendingOrders.add(new Order(id, userId, status, date, total));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return pendingOrders;
	}
	
	public static ArrayList<Order> loadPreparedOrders() {
		orders.clear();
	    ArrayList<Order> pendingOrders = new ArrayList<>();
	    String query = "SELECT * FROM `order` WHERE orderStatus = 'Prepared'";
	    try {
	        ResultSet rs = Connect.getConnection().executeQuery(query);
	        while (rs.next()) {
	            int id = rs.getInt(1);
	            int userId = rs.getInt(2);
	            String status = rs.getString(3);
	            Date date = rs.getDate(4);
	            int total = rs.getInt(5);

	            pendingOrders.add(new Order(id, userId, status, date, total));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return pendingOrders;
	}

	public static void insertOrder(int orderUserId, String orderStatus, Date orderDate, int orderTotal) {
		String query = String.format(
				"INSERT INTO `order` (orderUserId, orderStatus, orderDate, orderTotal) VALUES (%d, '%s', '%s', %d)", 
				orderUserId, orderStatus, orderDate, orderTotal);
		Connect.getConnection().executeUpdate(query);
	}
	
	public static int insertOrderAndGetID(int orderUserId, String orderStatus, Date orderDate, int orderTotal) {
        String query = String.format(
                "INSERT INTO `order` (orderUserId, orderStatus, orderDate, orderTotal) VALUES (%d, '%s', '%s', %d)",
                orderUserId, orderStatus, orderDate, orderTotal);

        // Use PreparedStatement to get generated keys
        try (PreparedStatement ps = Connect.getConnection().prepareStatementGetKey(query, Statement.RETURN_GENERATED_KEYS)) {
        	ps.executeUpdate();

            // Retrieve generated keys
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1); // Return the generated order ID
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // Return -1 if insertion fails
    }

//	public static void deleteOrder(int id) {
//		String query = "DELETE FROM order WHERE orderId = ?";
//		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
//
//		try {
//			ps.setInt(1, id);
//			ps.execute();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	public static void updateOrder(int id, int orderUserId, String orderStatus, Date orderDate, int orderTotal) {
//		String query = "UPDATE order SET orderUserId = ?, orderStatus = ?, orderDate = ?, orderTotal = ? WHERE orderId = ?";
//
//		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
//
//		try {
//			ps.setInt(1, orderUserId);
//			ps.setString(2, orderStatus);
//			ps.setDate(3, orderDate);
//			ps.setInt(4, orderTotal);
//			ps.setInt(5, id);
//			ps.execute();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
//	public static ResultSet getOrderById(int ids) {
//		ResultSet rs = null;
//		String query = "SELECT * FROM `order` WHERE orderId = ?";
//		PreparedStatement ps = Connect.getConnection().prepareStatement(query);
//		
//		try {
//			ps.setInt(1, ids);
//			ps.execute();
//			rs = ps.getResultSet();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println("tes");
//		
//		return rs;
//	}
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderUserId() {
		return orderUserId;
	}

	public void setOrderUserId(int orderUserId) {
		this.orderUserId = orderUserId;
	}

	public int getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public static ArrayList<Order> getOrders() {
		return orders;
	}
	
}
