package controller;

import java.sql.Date;

import view.Add_Order;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import model.MenuItem;

public class AddOrderController {
	
	private Add_Order view;

	public AddOrderController(Add_Order view) {
		super();
		this.view = view;
	}
	
	public void controlAddOrderButton() {
		TableView<MenuItem>table = view.getTable();
		
        MenuItem selectedItem = table.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            int orderQuantity = Integer.parseInt(view.getQuantity().getText());
            int orderTotal = orderQuantity * selectedItem.getMenuItemPrice();
            Date orderDate = new Date(System.currentTimeMillis());
            int orderUserId = 1; // Buat bikin ID sesuai ama user

            // Masukin data kedalam Tabel Order
            int orderId = model.Order.insertOrder(orderUserId, "Pending", orderDate, orderTotal);

            // Masukin data kedalam Tabel OrderItem
            model.OrderItem.insertOrderItem(orderId, selectedItem.getMenuItemId(), orderQuantity);

            view.getData().clear();
            view.getData().addAll(MenuItem.loadMenuItems());

            showAlert("Order Success", "Order has been added successfully.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        
        alert.setContentText(content);
        alert.showAndWait();
    }
}
