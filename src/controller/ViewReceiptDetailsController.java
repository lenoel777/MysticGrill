package controller;

import java.sql.Date;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import main.Main;
import main.VIewReceiptDetails;
import model.Receipt;

public class ViewReceiptDetailsController {
	VIewReceiptDetails vrd;
	private ObservableList<Receipt> receipts = FXCollections.observableArrayList();
	int price, quantity;
	String name;
	Date date;
	public ViewReceiptDetailsController(VIewReceiptDetails vrd) {
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
		receipts.clear();
		receipts.addAll(Receipt.loadReceipts());
		if(receipts !=null && !receipts.isEmpty())
		{
			vrd.getTable().setItems(receipts);
		}
		else {
			System.out.println("Receipt list is null or empty");
		}
	}
	private void buttonOnClick() {
		vrd.getBackBtn().setOnAction(e -> {
			Main.goToPreviousPage();
		});
	}
}
