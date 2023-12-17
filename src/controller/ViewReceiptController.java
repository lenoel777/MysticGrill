package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.VIewReceiptDetails;
import main.ViewReceipt;
import model.Receipt;

public class ViewReceiptController {

	private ViewReceipt viewReceipt;
	private ObservableList<Receipt> receipts = FXCollections.observableArrayList();
	private boolean isEmpty;
	private int selectedId;
	
	public ViewReceiptController(ViewReceipt view) {
		this.viewReceipt = view;
		
		setupTableSelectionListener();
		loadTableData();
		viewReceipt.getRd().setOnAction(e-> goToDetails());
	}
	private void goToDetails() {
		if(!isEmpty) {
			VIewReceiptDetails vrd = new VIewReceiptDetails(selectedId);
			ViewReceiptDetailsController vrdc = new ViewReceiptDetailsController(vrd);
		}
	}

	private void setupTableSelectionListener() {
		isEmpty = true;
		viewReceipt.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				viewReceipt.getReceiptId().setText(String.valueOf(newSelection.getReceiptId()));
				viewReceipt.getReceiptOrderId().setText(String.valueOf(newSelection.getReceiptOrderId()));
				viewReceipt.getReceiptPaymentAmount().setText(String.valueOf(newSelection.getReceiptPaymentAmount()));
				viewReceipt.getReceiptPaymentDate().setText(String.valueOf(newSelection.getReceiptPaymentDate()));
				viewReceipt.getReceiptPaymentType().setText(String.valueOf(newSelection.getReceiptPaymentType()));
				
				selectedId = newSelection.getReceiptOrderId();
				isEmpty = false;
			}
		});
	}
	private void loadTableData() {
		receipts.clear();
		receipts.addAll(Receipt.loadReceipts());
		if (receipts != null && !receipts.isEmpty()) {
		    viewReceipt.getTable().setItems(receipts);
		} else {
		    System.out.println("Receipt list is null or empty");
		}
	}
}
