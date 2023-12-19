package chefView;

import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Order;

public class ViewPrepare extends VBox {
	Button vp;
	Button up;
	Button del;
    TableView<Order> table;
    
	TextField orderId = new TextField();
	TextField orderUserId = new TextField();
	TextField orderStatus = new TextField();
	TextField orderDate = new TextField();
	TextField orderTotal = new TextField();
	

    public ViewPrepare() {
    	vp = new Button("Prepare");
    	up = new Button ("Update");
    	del = new Button ("Delete");
    	
        table = createOrderTable();

        HBox titleBox = new HBox();
        titleBox.getChildren().add(new Label("View Pending"));
        titleBox.setAlignment(Pos.CENTER);
        
        
        GridPane form = createOrderForm(table);
        VBox.setMargin(form, new Insets(20));

        this.getChildren().addAll( titleBox, table, vp, form);
    }
    
	
	private TableView<Order> createOrderTable() {
		TableView<Order> table = new TableView<>();
		TableColumn<Order, Number> orderId = new TableColumn<>("Order ID");
		orderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));

		TableColumn<Order, Number> orderUserId = new TableColumn<>("User ID");
		orderUserId.setCellValueFactory(new PropertyValueFactory<>("orderUserId"));

		TableColumn<Order, String> orderStatus = new TableColumn<>("Status");
		orderStatus.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));

		TableColumn<Order, String> orderDate = new TableColumn<>("Date");
		orderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		
		TableColumn<Order, Number> orderTotal = new TableColumn<>("Total");
		orderTotal.setCellValueFactory(new PropertyValueFactory<>("orderTotal"));

		table.getColumns().add(orderId);
		table.getColumns().add(orderUserId);
		table.getColumns().add(orderStatus);
		table.getColumns().add(orderDate);
		table.getColumns().add(orderTotal);

		return table;
	}
	
	private GridPane createOrderForm(TableView<Order> table) {
		GridPane form = new GridPane();
		form.setVgap(20);
		form.setHgap(10);

		form.add(new Label("Order ID:"), 0, 0);
		
		form.add(orderId, 1, 0);
		orderId.setDisable(true);
		form.add(new Label("User ID:"), 0, 1);
		
		form.add(orderUserId, 1, 1);
		form.add(new Label("Status:"), 0, 2);
		orderStatus.setDisable(true);
		form.add(orderStatus, 1, 2);
		form.add(new Label("Date:"), 0, 3);
		
		form.add(orderDate, 1, 3);
		form.add(new Label("Total:"), 0, 4);
		
		form.add(orderTotal, 1, 4);
		form.add(up, 0, 5);
		form.add(del, 1, 5);

		return form;
	}
	
	public Button getVp() {
		return vp;
	}
	
	public Button getUp() {
		return up;
	}
	
	public Button getDel() {
		return del;
	}
	

	public TableView<Order> getTable() {
		return table;
	}

	public TextField getOrderId() {
		return orderId;
	}

	public TextField getOrderUserId() {
		return orderUserId;
	}

	public TextField getOrderStatus() {
		return orderStatus;
	}

	public TextField getOrderDate() {
		return orderDate;
	}

	public TextField getOrderTotal() {
		return orderTotal;
	}
	
	
}
