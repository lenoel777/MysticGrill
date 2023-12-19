package waiterController;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import chefView.ViewPrepare;
import database.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Order;
import waiterView.ViewServe;

public class ServeController {
	private ViewServe viewPrepared;
	private Connect connect;
	private ObservableList<Order> orders = FXCollections.observableArrayList();
	private int selectedOrderId;

	public ServeController(ViewServe view) {
		this.viewPrepared = view;
		this.connect = Connect.getConnection();
		
		loadTableData();
		setupTableSelectionListener();
		setupPrepareButtonAction();
		updateOrderButton();
		setupDeleteButtonAction();
	}
	
	private void setupPrepareButtonAction() {
		viewPrepared.getVs().setOnAction(e -> {
	        Order selectedOrder = viewPrepared.getTable().getSelectionModel().getSelectedItem();
	        if (selectedOrder != null) {
	            selectedOrderId = selectedOrder.getOrderId();
	            updateOrderStatusToServed(selectedOrderId);
	            loadTableData();
	        } else {
	        	System.err.println("Failed to update order status.");
	        }
	    });
		
	}
	
	private void setupDeleteButtonAction() {
		viewPrepared.getDel().setOnAction(e -> {
	        Order selectedOrder = viewPrepared.getTable().getSelectionModel().getSelectedItem();
	        if (selectedOrder != null) {
	            selectedOrderId = selectedOrder.getOrderId();
	            deleteUserOrder(selectedOrderId);
	            loadTableData();
	        } else {
	        	System.err.println("Failed to update order status.");
	        }
	    });
		
	}
	
	private void updateOrderButton() {
		viewPrepared.getUp().setOnAction(e -> {
	        Order selectedOrder = viewPrepared.getTable().getSelectionModel().getSelectedItem();
	        if (selectedOrder != null) {
	            selectedOrderId = selectedOrder.getOrderId();
	            String userId = viewPrepared.getOrderUserId().getText();
	            String status = viewPrepared.getOrderStatus().getText();
	            String date = viewPrepared.getOrderDate().getText();

	            updateUserOrder(selectedOrderId, userId, status, date);
	            loadTableData();
	        } else {
	            System.err.println("Failed to update order.");
	        }
	    });
	}

	private void setupTableSelectionListener() {
	    viewPrepared.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	        if (newSelection != null) {
	        	viewPrepared.getOrderId().setText(String.valueOf(newSelection.getOrderId()));
	        	viewPrepared.getOrderUserId().setText(String.valueOf(newSelection.getOrderUserId()));
	        	viewPrepared.getOrderStatus().setText(newSelection.getOrderStatus());
	        	viewPrepared.getOrderDate().setText(String.valueOf(newSelection.getOrderDate()));
	        	viewPrepared.getOrderTotal().setText(String.valueOf(newSelection.getOrderTotal()));

	            selectedOrderId = newSelection.getOrderId();
	        }
	    });
	}
	

	private void loadTableData() {
		orders.clear();
		orders.addAll(Order.loadPreparedOrders());
		if (orders != null && !orders.isEmpty()) {
			viewPrepared.getTable().setItems(orders);
		} else {
		    System.out.println("Orders list is null or empty");
		}
	}
	
	public void updateOrderStatusToServed(int orderId) {
        String query = "UPDATE `order` SET orderStatus = 'Served' WHERE orderId = ?";

        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setInt(1, orderId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order status updated to 'Served'!");
            } else {
                System.out.println("Order not found or status not updated.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to update order status.");
        }
    }
	
	public void deleteUserOrder(int orderId) {
	    if (orderId <= 0) {
	        // Handle invalid ID
	        System.out.println("Invalid order ID");
	        return;
	    }

	    String query = "DELETE FROM `order` WHERE orderId = ?";

	    try (PreparedStatement statement = connect.prepareStatement(query)) {
	        statement.setInt(1, orderId);
	        int rowsAffected = statement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Order deleted from the database!");
	        } else {
	            System.out.println("Order with ID " + orderId + " not found in the database.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.err.println("Failed to delete menu item from the database.");
	    }
	}
	
	//update order
	public void updateUserOrder(int orderId, String userId, String status, String date) {
	    String query = "UPDATE `order` SET orderUserId = ?, orderStatus = ?, orderDate = ? WHERE orderId = ?";

	    try (PreparedStatement statement = connect.prepareStatement(query)) {
	        statement.setString(1, userId);
	        statement.setString(2, status);
	        statement.setString(3, date);
	        statement.setInt(4, orderId);

	        int rowsAffected = statement.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Order updated successfully!");
	        } else {
	            System.out.println("Order not found or not updated.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.err.println("Failed to update order.");
	    }
	}
}
