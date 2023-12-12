package main;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.MenuItem;
import model.Order;
import model.OrderItem;

public class ViewOrderDetails extends VBox{
	
	Button proceedBtn, backBtn;
	TableView<OrderItem> table;
	TableView<MenuItem> table2;
	TextField amount = new TextField();
	TextField date = new TextField();
	TextField type = new TextField();
	
	public ViewOrderDetails(int orderId) {
		proceedBtn = new Button("Proceed");
		backBtn = new Button("Back");
        
        HBox titleBox = new HBox();
        titleBox.getChildren().add(new Label("View Order Details"));
        titleBox.setAlignment(Pos.CENTER);
        
        //order info
        VBox info = createOrderInfo(orderId);
        
        //table data
        table = createOrderItemTable();
        table2 = createMenuItemTable();
        HBox data = new HBox();
        data.getChildren().addAll(table, table2);
        
        //order item
        GridPane form = createPaymentForm();
        VBox.setMargin(form, new Insets(20));
        this.getChildren().addAll(titleBox, info, data, form);

        Scene viewOrderScene = new Scene(this, 800, 600);
        Main.getSceneStack().add(viewOrderScene);
	}
	
	private VBox createOrderInfo(int ids) {
		VBox vb = new VBox();
		
		ResultSet rs = Order.getOrderById(ids);
		try {
			Label id = new Label("Order ID: " + rs.getInt(1));
			Label userId = new Label("User ID: " + rs.getInt(2));
			Label status = new Label("Status: " + rs.getString(3));
			Label date = new Label("Order Date: " + rs.getDate(4));
			Label total = new Label("Total: " + rs.getInt(5));
			
			vb.getChildren().addAll(id, userId, status, date, total);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vb;
	}
	
	private TableView<OrderItem> createOrderItemTable() {
		TableView<OrderItem> table = new TableView<>();
		TableColumn<OrderItem, Number> orderId = new TableColumn<>("Order ID");
		orderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));

		TableColumn<OrderItem, Number> orderUserId = new TableColumn<>("User ID");
		orderUserId.setCellValueFactory(new PropertyValueFactory<>("orderUserId"));

		TableColumn<OrderItem, String> orderStatus = new TableColumn<>("Status");
		orderStatus.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

		TableColumn<OrderItem, String> orderDate = new TableColumn<>("Date");
		orderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		
		TableColumn<OrderItem, Number> orderTotal = new TableColumn<>("Total");
		orderTotal.setCellValueFactory(new PropertyValueFactory<>("orderTotal"));

		table.getColumns().add(orderId);
		table.getColumns().add(orderUserId);
		table.getColumns().add(orderStatus);
		table.getColumns().add(orderDate);
		table.getColumns().add(orderTotal);

		return table;
	}
	
	private TableView<MenuItem> createMenuItemTable() {
		TableView<MenuItem> table = new TableView<>();
		TableColumn<MenuItem, String> menuItemName = new TableColumn<>("Menu Item Name");
		menuItemName.setCellValueFactory(new PropertyValueFactory<>("menuItemName"));

		TableColumn<MenuItem, String> menuItemDescription = new TableColumn<>("Description");
		menuItemDescription.setCellValueFactory(new PropertyValueFactory<>("menuItemDescription"));
		
		TableColumn<MenuItem, Number> menuItemPrice = new TableColumn<>("Total");
		menuItemPrice.setCellValueFactory(new PropertyValueFactory<>("menuItemPrice"));

		table.getColumns().add(menuItemName);
		table.getColumns().add(menuItemDescription);
		table.getColumns().add(menuItemPrice);

		return table;
	}

	private GridPane createPaymentForm() {
		GridPane form = new GridPane();
		form.setVgap(20);
		form.setHgap(10);

		form.add(new Label("Payment Amount:"), 0, 0);
		form.add(amount, 1, 0);
		form.add(new Label("Payment Date:"), 0, 1);
		form.add(date, 1, 1);
		form.add(new Label("Payment Type:"), 0, 2);
		form.add(type, 1, 2);
		form.add(proceedBtn, 0, 3);
		form.add(backBtn, 1, 3);

		return form;
	}
}
