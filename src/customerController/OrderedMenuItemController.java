package customerController;

import java.util.ArrayList;

import customerView.ViewOrderedMenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import model.Order;
import model.OrderItem;
import model.User;

public class OrderedMenuItemController {
	private ObservableList<OrderItem> data = FXCollections.observableArrayList();
    private ViewOrderedMenuItem view;

    public OrderedMenuItemController(ViewOrderedMenuItem view) {
        this.view = view;
        
        loadTableData();
        buttonOnClick();
    }
    
    private void buttonOnClick() {
    	view.getUpdateButton().setOnAction(e -> controlUpdateOrderItemButton());
        
    	view.getTable().setOnMouseClicked(event -> {
            OrderItem selectedItem = view.getTable().getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                displaySelectedItem(selectedItem);
            }
        });    
    }
    
    public void controlUpdateOrderItemButton() {
        TableView<OrderItem> table = view.getTable();

        OrderItem selectedItem = table.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            int updatedMenuItemId = Integer.parseInt(view.getMenuItemId().getText());
            int updatedQuantity = Integer.parseInt(view.getQuantity().getText());

            // Update data in orderitem table
            OrderItem.updateOrderItem(selectedItem.getOrderItemId(), updatedMenuItemId, updatedQuantity);

            loadTableData();
        }
    }
    
    private void displaySelectedItem(OrderItem selectedItem) {
        view.getOrderItemId().setText(String.valueOf(selectedItem.getOrderItemId()));
        view.getOrderId().setText(String.valueOf(selectedItem.getOrderId()));
        view.getMenuItemId().setText(String.valueOf(selectedItem.getMenuItemId()));
        view.getQuantity().setText(String.valueOf(selectedItem.getQuantity()));
    }
    
    private void loadTableData() {
    	Order.loadOrders();
		data.clear();
		data.addAll(getUsersOrderItems());
		if (data != null && !data.isEmpty()) {
		    view.getTable().setItems(data);
		}
	}
    
    private ArrayList<OrderItem> getUsersOrderItems(){
    	ArrayList<OrderItem> orderItem = new ArrayList<OrderItem>();
    	
    	for(Order o: Order.getOrders()) {
    		if(o.getOrderUserId() == User.getCurrUser().getUserId() && o.getOrderStatus().equals("Pending")) {
    			for(OrderItem oi: OrderItem.loadOrderItems(o.getOrderId())) {
    				orderItem.add(oi);
    			}
    		}
    	}
    	return orderItem;
    }
}
