package cashierView;


import java.sql.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import main.Main;
import model.OrderItem;
import model.Receipt;

public class ViewReceiptDetails extends VBox{
	ViewReceiptDetails vrd;
	TableView<OrderItem> table;
	Button backBtn;
	TextField name = new TextField();
	TextField price = new TextField();
	TextField quantity = new TextField();
	int receiptid, receiptOrderId, receiptPaymentAmount; 
	Date receiptPaymentDate;
	String receiptPaymentType;
	
	VBox vb;
	
	public ViewReceiptDetails(int receiptid) {
		// TODO Auto-generated constructor stub
		this.receiptid = receiptid;
		
		backBtn = new Button("Back");
		HBox titleBox = new HBox();
        titleBox.getChildren().add(new Label("View Receipt Details"));
        titleBox.setAlignment(Pos.CENTER);
		
        VBox info = createReceiptInfo();
        
        table = createOrderItemTable();
        HBox data = new HBox();
        data.getChildren().addAll(table);
        data.setPrefHeight(300);
        
        this.getChildren().addAll(titleBox, info, data, backBtn);       
	}
	
	private VBox createReceiptInfo() {
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

	public ViewReceiptDetails getVrd() {
		return vrd;
	}
	public void setVrd(ViewReceiptDetails vrd) {
		this.vrd = vrd;
	}
	public Button getBackBtn() {
		return backBtn;
	}
	public void setBackBtn(Button backBtn) {
		this.backBtn = backBtn;
	}
	public TextField getName() {
		return name;
	}
	public void setName(TextField name) {
		this.name = name;
	}
	public TextField getPrice() {
		return price;
	}
	public void setPrice(TextField price) {
		this.price = price;
	}
	public TextField getQuantity() {
		return quantity;
	}
	public void setQuantity(TextField quantity) {
		this.quantity = quantity;
	}
	public int getReceiptid() {
		return receiptid;
	}
	public void setReceiptid(int receiptid) {
		this.receiptid = receiptid;
	}
	public int getReceiptOrderId() {
		return receiptOrderId;
	}
	public void setReceiptOrderId(int receiptOrderId) {
		this.receiptOrderId = receiptOrderId;
	}
	public VBox getVb() {
		return vb;
	}
	public void setVb(VBox vb) {
		this.vb = vb;
	}
	public TableView<OrderItem> getTable() {
		return table;
	}
	public void setTable(TableView<OrderItem> table) {
		this.table = table;
	}
	public int getReceiptPaymentAmount() {
		return receiptPaymentAmount;
	}
	public void setReceiptPaymentAmount(int receiptPaymentAmount) {
		this.receiptPaymentAmount = receiptPaymentAmount;
	}
	public Date getReceiptPaymentDate() {
		return receiptPaymentDate;
	}
	public void setReceiptPaymentDate(Date receiptPaymentDate) {
		this.receiptPaymentDate = receiptPaymentDate;
	}
	public String getReceiptPaymentType() {
		return receiptPaymentType;
	}
	public void setReceiptPaymentType(String receiptPaymentType) {
		this.receiptPaymentType = receiptPaymentType;
	}
	
}
