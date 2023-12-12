package controller;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import database.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import main.Main;
import main.ViewOrder;
import main.ViewOrderDetails;
import model.Order;
import model.OrderItem;
import model.Receipt;

public class ViewOrderDetailsController {
	ViewOrderDetails vod;
	private ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();
	int amount; String type;
	Date date;

	public ViewOrderDetailsController(ViewOrderDetails vod) {
		this.vod = vod;
		vod.getDate().setText(LocalDate.now().toString());
		date = Date.valueOf(LocalDate.now());
		
		loadOrderInfo();
		loadTableData();
		buttonOnClick();
	}
	
	private void loadOrderInfo() {
		for(Order order: Order.getOrders()) {
			if(order.getOrderId() == vod.getOrderId()) {
				vod.setOrderId(order.getOrderId());
				Label id = new Label("Order ID: " + vod.getOrderId());
				vod.setOrderUserId(order.getOrderUserId());
				Label userId = new Label("User ID: " + vod.getOrderUserId());
				vod.setOrderStatus(order.getOrderStatus());
				Label status = new Label("Status: " + vod.getOrderStatus());
				vod.setOrderDate(order.getOrderDate());
				Label date = new Label("Order Date: " + vod.getOrderDate());
				vod.setOrderTotal(order.getOrderTotal());
				Label total = new Label("Total: " + vod.getOrderTotal());
				vod.getVb().getChildren().addAll(id, userId, status, date, total);
				break;
			}
		}
	}
	
	private void loadTableData() {
		orderItems.clear();
		orderItems.addAll(OrderItem.loadOrderItems());
		if (orderItems != null && !orderItems.isEmpty()) {
		    vod.getTable().setItems(orderItems);
		} else {
		    System.out.println("Order Items list is null or empty");
		}
	}
	
	private void buttonOnClick() {
		vod.getBackBtn().setOnAction(e -> {
			Main.goToPreviousPage();
		});
		
		vod.getProceedBtn().setOnAction(e -> {
			if(checkData()) {
				updateStatus();
				makeReceipt();
				showAlert("Success", "- Order status has been updated to 'Paid'.\n"
						+ "- Receipt successfully made");
				
				ViewOrder vo = new ViewOrder();
				Scene voScene = new Scene(vo, 800, 600);
				Main.previousScene(voScene);
			};
		});
	}
	
	private boolean checkData() {
		if(vod.getOrderStatus().equals("Paid")) {
			showAlert("Error", "Order has been paid");
			return false;
		}else if(vod.getAmount().getText().isEmpty() || vod.getDate().getText().isEmpty() || vod.getType().getText().toString().isEmpty()) {
			showAlert("Error", "All fields must be filled");
			return false;
		}
		
		amount = Integer.parseInt(vod.getAmount().getText());
		type = vod.getType().getText().toString();
		
		if(amount==0 || amount < vod.getOrderTotal()) {

			showAlert("Error", "Payment amount must be greater than or equal to the total order price");
			return false;
		}else if(!type.equals("Cash") && !type.equals("Debit") && !type.equals("Credit")) {
			System.out.println("4");
			showAlert("Error", "Payment type must be 'Cash', 'Debit', or 'Credit'");
			return false;
		}
		
		return true;
	}
	
	 private void showAlert(String title, String message) {
	        Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle(title);
	        alert.setHeaderText(null);
	        alert.setContentText(message);
	        alert.showAndWait();
	    }
	
	 private void updateStatus() {
			String query = "UPDATE `order` SET orderStatus = ? WHERE orderId = ?";
	
			PreparedStatement ps = Connect.getConnection().prepareStatement(query);
	
			try {
				ps.setString(1, "Paid");
				ps.setInt(2, vod.getOrderId());
				ps.execute();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 
	 private void makeReceipt() {
		 Receipt.insertReceipt(vod.getOrderId(), amount, date, type);
	 }
	 
}
