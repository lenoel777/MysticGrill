package cashierController;

import cashierView.ViewOrder;
import cashierView.ViewOrderDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import main.Home;
import main.Main;
import model.Order;

public class ViewOrderController {
	private ViewOrder viewOrder;
	private ObservableList<Order> orders = FXCollections.observableArrayList();
	private int selectedOrderId;
	private boolean isEmpty;

	public ViewOrderController(ViewOrder view) {
		this.viewOrder = view;
		
		loadTableData();
		setupTableSelectionListener();
		viewOrder.getVd().setOnAction(e -> goToDetails());
	}
	
	private void goToDetails() {
		if(!isEmpty) {
			ViewOrderDetails vod;
			Home.setPage(vod = new ViewOrderDetails(selectedOrderId), "View Order Details");
			new ViewOrderDetailsController(vod);
		}
	}
	
	private void setupTableSelectionListener() {
		isEmpty = true;
		viewOrder.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				viewOrder.getOrderId().setText(String.valueOf(newSelection.getOrderId()));
				viewOrder.getOrderUserId().setText(String.valueOf(newSelection.getOrderUserId()));
				viewOrder.getOrderStatus().setText(newSelection.getOrderStatus());
				viewOrder.getOrderDate().setText(String.valueOf(newSelection.getOrderDate()));
				viewOrder.getOrderTotal().setText(String.valueOf(newSelection.getOrderTotal()));
				
				selectedOrderId = newSelection.getOrderId();
				isEmpty = false;
			}
		});
	}

	private void loadTableData() {
		orders.clear();
		orders.addAll(Order.loadOrders());
		if (orders != null && !orders.isEmpty()) {
		    viewOrder.getTable().setItems(orders);
		}
	}

	public ObservableList<Order> getOrders() {
		return orders;
	}
	
	
	
//	void handleInsert() {
//		String name = viewOrder.getNameInput().getText();
//		int price = Integer.parseInt(viewOrder.getPriceInput().getText());
//		int quantity = Integer.parseInt(viewOrder.getQuantityInput().getText());
//		for (Order order : orders) {
//			if (name.equals(order.getProductName())) {
//				System.out.println("Name is not unique");
//				return;
//			}
//		}
//		Order.insertProduct(name, price, quantity);
//		loadTableData();
//	}
//	
//	void handleDelete() {
//		int id = Integer.valueOf(viewOrder.getIdInput().getText());
//		Order.deleteProduct(id);
//		loadTableData();
//	}
//
//	void handleUpdate() {
//		int id = Integer.valueOf(viewOrder.getIdInput().getText());
//		String name = viewOrder.getNameInput().getText();
//		int price = Integer.parseInt(viewOrder.getPriceInput().getText());
//		int quantity = Integer.parseInt(viewOrder.getQuantityInput().getText());
//		Order.updateProduct(id, name, price, quantity);
//		loadTableData();
//	}
}
