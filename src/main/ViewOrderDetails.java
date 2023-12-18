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
	TextField amount = new TextField();
	TextField date = new TextField();
	TextField type = new TextField();
	int orderId, orderUserId, orderTotal;
	String orderStatus;
	Date orderDate;
	VBox vb;
	
	public ViewOrderDetails(int orderId) {
		this.orderId = orderId;

		proceedBtn = new Button("Proceed");
		backBtn = new Button("Back");
        
        HBox titleBox = new HBox();
        titleBox.getChildren().add(new Label("View Order Details"));
        titleBox.setAlignment(Pos.CENTER);
        
        //order info
        VBox info = createOrderInfo();
        
        //table data
        table = createOrderItemTable();
        HBox data = new HBox();
        data.getChildren().addAll(table);
        
        //order item
        GridPane form = createPaymentForm();
        VBox.setMargin(form, new Insets(20));
        this.getChildren().addAll(titleBox, info, data, form);

        Scene vodScene = new Scene(this, 800, 600);
        Main.nextScene(vodScene);
	}
	
	private VBox createOrderInfo() {
		vb = new VBox();
		VBox.setMargin(vb, new Insets(20));
				
		return vb;
	}
	
	private TableView<OrderItem> createOrderItemTable() {
		TableView<OrderItem> table = new TableView<>();
		TableColumn<OrderItem, Number> orderItemId = new TableColumn<>("Order Item ID");
		orderItemId.setCellValueFactory(new PropertyValueFactory<>("orderItemId"));

		TableColumn<OrderItem, Number> menuItemId = new TableColumn<>("Menu Item ID");
		menuItemId.setCellValueFactory(new PropertyValueFactory<>("menuItemId"));

		TableColumn<OrderItem, String> quantity = new TableColumn<>("Quantity");
		quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

		table.getColumns().add(orderItemId);
		table.getColumns().add(menuItemId);
		table.getColumns().add(quantity);

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
		date.setDisable(true);
		form.add(new Label("Payment Type:"), 0, 2);
		form.add(type, 1, 2);
		form.add(proceedBtn, 0, 3);
		form.add(backBtn, 1, 3);

		return form;
	}

	public Button getProceedBtn() {
		return proceedBtn;
	}

	public Button getBackBtn() {
		return backBtn;
	}

	public TableView<OrderItem> getTable() {
		return table;
	}
	
	public TextField getAmount() {
		return amount;
	}

	public TextField getDate() {
		return date;
	}

	public TextField getType() {
		return type;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getOrderUserId() {
		return orderUserId;
	}

	public void setOrderUserId(int orderUserId) {
		this.orderUserId = orderUserId;
	}

	public int getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public VBox getVb() {
		return vb;
	}
	
}
