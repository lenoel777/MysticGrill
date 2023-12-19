package cashierController;

import java.sql.Date;
import java.time.LocalDate;

import cashierView.ViewReceipt;
import cashierView.ViewReceiptDetails;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import main.Home;
import main.Main;
import model.OrderItem;
import model.Receipt;

public class ViewReceiptDetailsController {
	ViewReceiptDetails vrd;
	private ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();
	int price, quantity;
	String name;
	Date date;
	
	public ViewReceiptDetailsController(ViewReceiptDetails vrd) {
		// TODO Auto-generated constructor stub
		this.vrd = vrd;
		date = Date.valueOf(LocalDate.now());
		
		loadReceiptInfo();
		loadTableData();
		buttonOnClick();
	}
	private void loadReceiptInfo() {
		for(Receipt receipt: Receipt.loadReceipts()) {
			if(receipt.getReceiptId() == vrd.getReceiptid()) {
				vrd.setReceiptid(receipt.getReceiptId());
				Label id = new Label("Rceipt ID: " + vrd.getReceiptid());
				vrd.setReceiptOrderId(receipt.getReceiptOrderId());
				Label orderId = new Label("Order ID: " + vrd.getReceiptOrderId());
				vrd.setReceiptPaymentAmount(receipt.getReceiptPaymentAmount());
				Label amount = new Label("Amount: " + vrd.getReceiptPaymentAmount());
				vrd.setReceiptPaymentDate(receipt.getReceiptPaymentDate());
				Label date = new Label("Receipt Date: " + vrd.getReceiptPaymentDate());
				vrd.setReceiptPaymentType(receipt.getReceiptPaymentType());
				Label type = new Label("Payment Type: " + vrd.getReceiptPaymentType());
				vrd.getVb().getChildren().addAll(id, orderId, amount, date, type);
				break;
			}
		}
	}
	private void loadTableData() {
		orderItems.clear();
		orderItems.addAll(OrderItem.loadOrderItems(vrd.getReceiptOrderId()));
		if(!orderItems.isEmpty())
		{
			vrd.getTable().setItems(orderItems);
		}
	}
		
	private void buttonOnClick() {
		vrd.getBackBtn().setOnAction(e -> {
			ViewReceipt vr;
			Home.setPage(vr = new ViewReceipt(), "View Receipt");
			new ViewReceiptController(vr);
		});
	}
}
