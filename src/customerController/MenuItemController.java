package customerController;

import java.sql.Date;

import customerView.ViewMenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.User;

public class MenuItemController {

	private ObservableList<MenuItem> data = FXCollections.observableArrayList();
	private ViewMenuItem view;

	public MenuItemController(ViewMenuItem view) {
		super();
		this.view = view;
		
		loadTableData();
		view.getAddButton().setOnAction(e -> controlAddOrderButton());
		
		view.getTable().setOnMouseClicked(event -> {
			MenuItem selectedItem = view.getTable().getSelectionModel().getSelectedItem();
			if (selectedItem != null) {
				displaySelectedItem(selectedItem);
			}
		});
	}
	
	public void loadTableData() {
		data = FXCollections.observableArrayList(MenuItem.loadMenuItems());
		view.getTable().setItems(data);
	}
	
	public void controlAddOrderButton() {
		TableView<MenuItem>table = view.getTable();
		
        MenuItem selectedItem = table.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            int orderQuantity = Integer.parseInt(view.getQuantity().getText());
            int orderTotal = orderQuantity * selectedItem.getMenuItemPrice();
            Date orderDate = new Date(System.currentTimeMillis());
            int orderUserId = User.getCurrUser().getUserId(); // Buat bikin ID sesuai ama user

            // Masukin data kedalam Tabel Order
            int orderId = Order.insertOrderAndGetID(orderUserId, "Pending", orderDate, orderTotal);

            // Masukin data kedalam Tabel OrderItem
            OrderItem.insertOrderItem(orderId, selectedItem.getMenuItemId(), orderQuantity);

            data.clear();
            data.addAll(MenuItem.loadMenuItems());

            showAlert("Order Success", "Order has been added successfully.");
        }
    }
	
	private void displaySelectedItem(MenuItem selectedItem) {
		view.getIdMenu().setText(String.valueOf(selectedItem.getMenuItemId()));
		view.getNameMenu().setText(selectedItem.getMenuItemName());
		view.getDeskripsiMenu().setText(selectedItem.getMenuItemDescription());
		view.getPriceMenu().setText(String.valueOf(selectedItem.getMenuItemPrice()));
	}

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        
        alert.setContentText(content);
        alert.showAndWait();
    }
}
