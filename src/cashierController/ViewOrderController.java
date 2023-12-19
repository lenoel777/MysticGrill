package cashierController;

import cashierView.ViewOrder;
import cashierView.ViewOrderDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.Home;
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
}
