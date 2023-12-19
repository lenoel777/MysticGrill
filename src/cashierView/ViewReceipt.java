package cashierView;

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
import main.Main;
import model.Order;
import model.Receipt;

public class ViewReceipt extends VBox{
	Button rd;
	TableView<Receipt> table;
	TextField receiptId = new TextField();
	TextField receiptOrderId = new TextField();
	TextField receiptPaymentAmount = new TextField();
	TextField receiptPaymentDate = new TextField();
	TextField receiptPaymentType = new TextField();
	
	
	public ViewReceipt() {
		rd = new Button("View Detail");
        table = ReceiptTable();
        
        HBox titleBox = new HBox();
        titleBox.getChildren().add(new Label("Receipt List"));
        titleBox.setAlignment(Pos.CENTER);
        
        GridPane form = ReceiptForm(table);
        VBox.setMargin(form, new Insets(20));
        this.getChildren().addAll(titleBox, table, form);
	}
	
	private TableView<Receipt> ReceiptTable() {
		TableView<Receipt> table = new TableView<>();
		TableColumn<Receipt, Number> receiptId = new TableColumn<>("Receipt ID");
		receiptId.setCellValueFactory(new PropertyValueFactory<>("receiptId"));

		TableColumn<Receipt, Number> receiptOrderId = new TableColumn<>("Receipt order ID");
		receiptOrderId.setCellValueFactory(new PropertyValueFactory<>("receiptOrderId"));

		TableColumn<Receipt, String> receiptPaymentAmount = new TableColumn<>("receipt payment amount");
		receiptPaymentAmount.setCellValueFactory(new PropertyValueFactory<>("receiptPaymentAmount"));

		TableColumn<Receipt, String> receipPaymentDate = new TableColumn<>("Receipt Payment Date");
		receipPaymentDate.setCellValueFactory(new PropertyValueFactory<>("receiptPaymentDate"));
		
		TableColumn<Receipt, Number> receiptPaymentType = new TableColumn<>("Receipt Payment Type");
		receiptPaymentType.setCellValueFactory(new PropertyValueFactory<>("receiptPaymentType"));

		table.getColumns().add(receiptId);
		table.getColumns().add(receiptOrderId);
		table.getColumns().add(receiptPaymentAmount);
		table.getColumns().add(receipPaymentDate);
		table.getColumns().add(receiptPaymentType);

		return table;
	}
	private GridPane ReceiptForm(TableView<Receipt> table) {
		GridPane form = new GridPane();
		form.setVgap(20);
		form.setHgap(10);

		form.add(new Label("Receipt ID:"), 0, 0);
		receiptId.setDisable(true);
		form.add(receiptId, 1, 0);
		form.add(new Label("Receipt Order ID:"), 0, 1);
		receiptOrderId.setDisable(true);
		form.add(receiptOrderId , 1, 1);
		form.add(new Label("Status:"), 0, 2);
		receiptPaymentAmount.setDisable(true);
		form.add(receiptPaymentAmount, 1, 2);
		form.add(new Label("Payment ammount:"), 0, 3);
		receiptPaymentDate.setDisable(true);
		form.add(receiptPaymentDate, 1, 3);
		form.add(new Label("Payment Type:"), 0, 4);
		receiptPaymentType.setDisable(true);
		form.add(receiptPaymentType, 1, 4);
		form.add(rd, 0, 5);

		return form;
	}
	public Button getRd() {
		return rd;
	}
	public TableView<Receipt> getTable() {
		return table;
	}
	public TextField getReceiptId() {
		return receiptId;
	}
	public TextField getReceiptOrderId() {
		return receiptOrderId;
	}
	public TextField getReceiptPaymentAmount() {
		return receiptPaymentAmount;
	}
	public TextField getReceiptPaymentDate() {
		return receiptPaymentDate;
	}
	public TextField getReceiptPaymentType() {
		return receiptPaymentType;
	}
	
	
}
