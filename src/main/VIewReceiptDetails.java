package main;


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
import model.Receipt;

public class VIewReceiptDetails extends VBox{
	VIewReceiptDetails vrd;
	private ObservableList<Receipt> Receipts = FXCollections.observableArrayList();
	TableView<Receipt> table;
	Button backBtn;
	TextField name = new TextField();
	TextField price = new TextField();
	TextField quantity = new TextField();
	int receiptid, receiptOrderId, receiptPaymentAmount; 
	Date receiptPaymentDate;
	String receiptPaymentType;
	
	
	VBox vb;
	
	
	public VIewReceiptDetails(int receiptid) {
		// TODO Auto-generated constructor stub
		
		this.receiptid = receiptid;
		
		backBtn = new Button("Back");
		HBox titleBox = new HBox();
        titleBox.getChildren().add(new Label("View Receipt Details"));
        titleBox.setAlignment(Pos.CENTER);
		
        VBox info = createReceiptInfo();
        
        table = createReceiptTable();
        HBox data = new HBox();
        data.getChildren().addAll(table);
        
        GridPane form = ReceiptDetailForm();
        VBox.setMargin(form, new Insets(20));
        this.getChildren().addAll(titleBox, info, data, form);
        Scene vrdScene = new Scene(this, 800, 600);
        Main.nextScene(vrdScene);
        
	}
	private VBox createReceiptInfo() {
		vb = new VBox();
		VBox.setMargin(vb, new Insets(20));
				
		return vb;
	}
	private TableView<Receipt> createReceiptTable() {
		TableView<Receipt> table = new TableView<>();
		TableColumn<Receipt, Number> receiptId = new TableColumn<>("Receipt Id");
		receiptId.setCellValueFactory(new PropertyValueFactory<>("receiptId"));

		TableColumn<Receipt, Number> menuItemId = new TableColumn<>("Menu Item ID");
		menuItemId.setCellValueFactory(new PropertyValueFactory<>("menuItemId"));

		TableColumn<Receipt, Number> quantity = new TableColumn<>("Quantity");
		quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

		table.getColumns().add(receiptId);
		table.getColumns().add(menuItemId);
		table.getColumns().add(quantity);

		return table;
	}
	private GridPane ReceiptDetailForm() {
		GridPane form = new GridPane();
		form.setVgap(20);
		form.setHgap(10);

		form.add(new Label("Item name"), 0, 0);
		form.add(name, 1, 0);
		form.add(new Label("Item Price"), 0, 1);
		form.add(price, 1, 1);
		form.add(new Label("Quantity"), 0, 2);
		form.add(quantity, 1, 2);
		form.add(backBtn, 1, 3);

		return form;
	}
	public VIewReceiptDetails getVrd() {
		return vrd;
	}
	public void setVrd(VIewReceiptDetails vrd) {
		this.vrd = vrd;
	}
	public ObservableList<Receipt> getReceipts() {
		return Receipts;
	}
	public void setReceipts(ObservableList<Receipt> receipts) {
		Receipts = receipts;
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
	public TableView<Receipt> getTable() {
		return table;
	}
	public void setTable(TableView<Receipt> table) {
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
