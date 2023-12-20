package chefController;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import chefView.ViewPrepare;
import database.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.Order;

public class PrepareController {
	
	private ViewPrepare viewPending;
	private Connect connect;
	private ObservableList<Order> orders = FXCollections.observableArrayList();
	private int selectedOrderId;

	public PrepareController(ViewPrepare view) {
		this.viewPending = view;
		this.connect = Connect.getConnection();
		
		loadTableData();
		setupTableSelectionListener();
		setupPrepareButtonAction();
		updateOrderButton();
		setupDeleteButtonAction();
	}

	private void setupPrepareButtonAction() {
		viewPending.getVp().setOnAction(e -> {
	        Order selectedOrder = viewPending.getTable().getSelectionModel().getSelectedItem();
	        if (selectedOrder != null) {
	            selectedOrderId = selectedOrder.getOrderId();
	            updateOrderStatusToPrepared(selectedOrderId);
	            loadTableData();
	        } else {
	        	showAlert("Failed to update order status.");
	        }
	    });
		
	}
	
	private void setupDeleteButtonAction() {
		viewPending.getDel().setOnAction(e -> {
	        Order selectedOrder = viewPending.getTable().getSelectionModel().getSelectedItem();
	        if (selectedOrder != null) {
	            selectedOrderId = selectedOrder.getOrderId();
	            deleteUserOrder(selectedOrderId);
	            loadTableData();
	        } else {
	        	showAlert("Failed to update order status.");
	        }
	    });
		
	}
	
	private void updateOrderButton() {
	    viewPending.getUp().setOnAction(e -> {
	        Order selectedOrder = viewPending.getTable().getSelectionModel().getSelectedItem();
	        if (selectedOrder != null) {
	            selectedOrderId = selectedOrder.getOrderId();
	            String userId = viewPending.getOrderUserId().getText();
	            String date = viewPending.getOrderDate().getText();
	            int total = Integer.parseInt(viewPending.getOrderTotal().getText());

	            updateUserOrder(selectedOrderId, userId, date, total);
	            loadTableData();
	        } else {
	        	showAlert("Failed to update order.");
	        }
	    });
	}

	private void setupTableSelectionListener() {
	    viewPending.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
	        if (newSelection != null) {
	            viewPending.getOrderId().setText(String.valueOf(newSelection.getOrderId()));
	            viewPending.getOrderUserId().setText(String.valueOf(newSelection.getOrderUserId()));
	            viewPending.getOrderStatus().setText(newSelection.getOrderStatus());
	            viewPending.getOrderDate().setText(String.valueOf(newSelection.getOrderDate()));
	            viewPending.getOrderTotal().setText(String.valueOf(newSelection.getOrderTotal()));

	            selectedOrderId = newSelection.getOrderId();
	        }
	    });
	}
	

	private void loadTableData() {
		orders.clear();
		orders.addAll(Order.loadPendingOrders());
		if (orders != null && !orders.isEmpty()) {
		    viewPending.getTable().setItems(orders);
		}
	}
	
	public void updateOrderStatusToPrepared(int orderId) {
        String query = "UPDATE `order` SET orderStatus = 'Prepared' WHERE orderId = ?";

        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setInt(1, orderId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
            	showAlert("Order status updated to 'Prepared'!");
            } else {
            	showAlert("Order not found or status not updated.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Failed to update order status.");
        }
        
        loadTableData();
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
	        	showAlert("Order deleted from the database!");
	        } else {
	        	showAlert("Order with ID " + orderId + " not found in the database.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        showAlert("Failed to delete menu item from the database.");
	    }
	    
	    loadTableData();
	}
	
	//update order
	public void updateUserOrder(int orderId, String userId, String date, int total) {
	    String query = "UPDATE `order` SET orderUserId = ?, orderDate = ?, orderTotal = ? WHERE orderId = ?";

	    try (PreparedStatement statement = connect.prepareStatement(query)) {
	        statement.setString(1, userId);
	        statement.setString(2, date);
	        statement.setInt(3, total);
	        statement.setInt(4, orderId);

	        int rowsAffected = statement.executeUpdate();
	        if (rowsAffected > 0) {
	        	showAlert("Order updated successfully!");
	        } else {
	        	showAlert("Order not found or not updated.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        showAlert("Failed to update order.");
	    }
	    
	    loadTableData();
	}
	
	private void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
