package customerController;

import java.sql.Date;

import customerView.ViewMenuItem;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import model.MenuItem;
import model.Order;
import model.OrderItem;

public class MenuItemController {
	
	private ViewMenuItem view;

	public MenuItemController(ViewMenuItem view) {
		super();
		this.view = view;
		
		view.getAddButton().setOnAction(e -> controlAddOrderButton());
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
            int orderId = Order.insertOrderAndGetID(orderUserId, "Pending", orderDate, orderTotal);

            // Masukin data kedalam Tabel OrderItem
            OrderItem.insertOrderItem(orderId, selectedItem.getMenuItemId(), orderQuantity);

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
