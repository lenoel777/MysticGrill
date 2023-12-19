package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Connect;

public class OrderItem {
	private int orderItemId, orderId, menuItemId, quantity;
	private static ArrayList<OrderItem> orderItems = new ArrayList<>();

	public OrderItem(int orderItemId, int orderId, int menuItemId, int quantity) {
		super();
		this.orderItemId = orderItemId;
		this.orderId = orderId;
		this.menuItemId = menuItemId;
		this.quantity = quantity;
	}
	
	public static ArrayList<OrderItem> loadOrderItems(int ids) {
		orderItems.clear();
		String query = "SELECT * FROM orderitem WHERE orderId = " + ids;
		ResultSet rs = Connect.getConnection().executeQuery(query);
		try {
			while (rs.next()) {
				int id = rs.getInt(1);
				int orderId = rs.getInt(2);
				int menuItemId = rs.getInt(3);
				int quantity = rs.getInt(4);

				orderItems.add(new OrderItem(id, orderId, menuItemId, quantity));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return orderItems;
	}

    public static void insertOrderItem(int orderId, int menuItemId, int quantity) {
        String query = String.format(
                "INSERT INTO orderitem (orderId, menuItemId, quantity) VALUES (%d, %d, %d)",
                orderId, menuItemId, quantity);

        Connect.getConnection().executeUpdate(query);
    }
    
    public static void updateOrderItem(int orderItemId, int menuItemId, int quantity) {
        String query = "UPDATE orderitem SET menuItemId = ?, quantity = ? WHERE orderItemId = ?";

        try (PreparedStatement ps = Connect.getConnection().prepareStatement(query)) {
            ps.setInt(1, menuItemId);
            ps.setInt(2, quantity);
            ps.setInt(3, orderItemId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//	public static void deleteOrderItem(int id) {
//		String query = "DELETE FROM orderitem WHERE orderItemId = ?";
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

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(int menuItemId) {
		this.menuItemId = menuItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
