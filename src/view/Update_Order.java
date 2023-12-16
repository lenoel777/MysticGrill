package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Controller.OrderItemController;
import model.OrderItem;

public class Update_Order extends VBox {

    private TableView<OrderItem> table;
    private ObservableList<OrderItem> data;

    private TextField orderItemId = new TextField();
    private TextField orderId = new TextField();
    private TextField menuItemId = new TextField();
    private TextField quantity = new TextField();

    private Button updateButton = new Button("Update Order");

    public Update_Order(Stage viewStage) {
        table = createProductTable(); // Initialize the table
        GridPane form = createProductForm();
        VBox.setMargin(form, new Insets(20));
        getChildren().addAll(table, form);

        data = FXCollections.observableArrayList(OrderItem.loadOrderItems());
        table.setItems(data);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Scene scene = new Scene(this, 600, 600);
        viewStage.setScene(scene);
        viewStage.setTitle("Order Management");
        viewStage.show();

        table.setOnMouseClicked(event -> {
            OrderItem selectedItem = table.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                displaySelectedItem(selectedItem);
            }
        });

        OrderItemController controller = new OrderItemController(this);
        updateButton.setOnAction(e -> controller.controlUpdateOrderItemButton());
    }

    private TableView<OrderItem> createProductTable() {
        TableView<OrderItem> table = new TableView<>();
        TableColumn<OrderItem, Number> idColumn = new TableColumn<>("Order Item ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("orderItemId"));

        TableColumn<OrderItem, Number> orderIdColumn = new TableColumn<>("Order ID");
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));

        TableColumn<OrderItem, Number> menuItemIdColumn = new TableColumn<>("Menu Item ID");
        menuItemIdColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemId"));

        TableColumn<OrderItem, Number> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        table.getColumns().add(idColumn);
        table.getColumns().add(orderIdColumn);
        table.getColumns().add(menuItemIdColumn);
        table.getColumns().add(quantityColumn);

        return table;
    }

    private GridPane createProductForm() {
        GridPane form = new GridPane();
        form.setVgap(20);
        form.setHgap(10);

        form.add(new Label("Order Item ID:"), 0, 0);
        orderItemId.setDisable(true);
        form.add(orderItemId, 1, 0);

        form.add(new Label("Order ID:"), 0, 1);
        orderId.setDisable(true);
        form.add(orderId, 1, 1);

        form.add(new Label("Menu Item ID:"), 0, 2);
        form.add(menuItemId, 1, 2);

        form.add(new Label("Quantity:"), 0, 3);
        form.add(quantity, 1, 3);

        form.add(updateButton, 0, 4);

        return form;
    }

    private void displaySelectedItem(OrderItem selectedItem) {
        orderItemId.setText(String.valueOf(selectedItem.getOrderItemId()));
        orderId.setText(String.valueOf(selectedItem.getOrderId()));
        menuItemId.setText(String.valueOf(selectedItem.getMenuItemId()));
        quantity.setText(String.valueOf(selectedItem.getQuantity()));
    }

    public TableView<OrderItem> getTable() {
        return table;
    }

    public ObservableList<OrderItem> getData() {
        return data;
    }

    public TextField getMenuItemId() {
        return menuItemId;
    }

    public TextField getQuantity() {
        return quantity;
    }
}
