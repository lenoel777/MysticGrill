package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Order;
import view.View_Prepare;
import view.View_Served;

public class Controller_PreparedOrder {
	private View_Served viewPrepared;
	private Connect connect;
	private ObservableList<Order> orders = FXCollections.observableArrayList();
	private int selectedUserId;
	private boolean isEmpty;

	
	
	public Controller_PreparedOrder(View_Served view) {
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
	            int selectedOrderId = selectedOrder.getOrderId();
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
	            int selectedOrderId = selectedOrder.getOrderId();
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
	            int selectedOrderId = selectedOrder.getOrderId();
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
	    isEmpty = true;
	    viewPrepared.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	        if (newSelection != null) {
	        	viewPrepared.getOrderId().setText(String.valueOf(newSelection.getOrderId()));
	        	viewPrepared.getOrderUserId().setText(String.valueOf(newSelection.getOrderUserId()));
	        	viewPrepared.getOrderStatus().setText(newSelection.getOrderStatus());
	        	viewPrepared.getOrderDate().setText(String.valueOf(newSelection.getOrderDate()));
	        	viewPrepared.getOrderTotal().setText(String.valueOf(newSelection.getOrderTotal()));

	            int selectedOrderId = newSelection.getOrderId();
	          
	            isEmpty = false;
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
