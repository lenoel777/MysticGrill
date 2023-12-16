package view;

import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Main;
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
import model.Order;

public class View_Prepare extends VBox {
	Button vd;
    TableView<Order> table;
    private VBox root;
	TextField orderId = new TextField();
	TextField orderUserId = new TextField();
	TextField orderStatus = new TextField();
	TextField orderDate = new TextField();
	TextField orderTotal = new TextField();
	

    public View_Prepare() {
    	vd = new Button("Prepare");
    	
        table = createOrderTable();

        HBox titleBox = new HBox();
        titleBox.getChildren().add(new Label("View Pending"));
        titleBox.setAlignment(Pos.CENTER);
        
        
        
        this.getChildren().addAll( titleBox, table, vd);

        Scene viewPendingOrder = new Scene(this, 800, 600);
        Main.nextScene(viewPendingOrder);
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
	
	

	public Button getVd() {
		return vd;
	}
	
	public VBox getRoot() {
        return root;
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
