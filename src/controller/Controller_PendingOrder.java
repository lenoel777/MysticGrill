package controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import view.View_Prepare;
import model.Order;


public class Controller_PendingOrder {
	
	private View_Prepare viewPending;
	private Connect connect;
	private ObservableList<Order> orders = FXCollections.observableArrayList();
	private int selectedUserId;
	private boolean isEmpty;

	
	public Controller_PendingOrder(View_Prepare view) {
		this.viewPending = view;
		
		loadTableData();
	}
	
	private void setupTableSelectionListener() {
	    isEmpty = true;
	    viewPending.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	        if (newSelection != null) {
	            viewPending.getOrderId().setText(String.valueOf(newSelection.getOrderId()));
	            viewPending.getOrderUserId().setText(String.valueOf(newSelection.getOrderUserId()));
	            viewPending.getOrderStatus().setText(newSelection.getOrderStatus());
	            viewPending.getOrderDate().setText(String.valueOf(newSelection.getOrderDate()));
	            viewPending.getOrderTotal().setText(String.valueOf(newSelection.getOrderTotal()));

	            int selectedOrderId = newSelection.getOrderId();
	            updateOrderStatusToServed(selectedOrderId); // Call your update method
	            isEmpty = false;
	        }
	    });
	}
	

	private void loadTableData() {
		orders.clear();
		orders.addAll(Order.loadPendingOrders());
		if (orders != null && !orders.isEmpty()) {
		    viewPending.getTable().setItems(orders);
		} else {
		    System.out.println("Orders list is null or empty");
		}
	}
	
	public void updateOrderStatusToServed(int orderId) {
        String query = "UPDATE orders SET status = 'Served' WHERE order_id = ?";

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
	
	

}
