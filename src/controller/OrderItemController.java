package controller;

import javafx.scene.control.TableView;
import model.OrderItem;
import view.Update_Order;

public class OrderItemController {

    private Update_Order view;

    public OrderItemController(Update_Order view) {
        this.view = view;
    }

    public void controlUpdateOrderItemButton() {
        TableView<OrderItem> table = view.getTable();

        OrderItem selectedItem = table.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            int updatedMenuItemId = Integer.parseInt(view.getMenuItemId().getText());
            int updatedQuantity = Integer.parseInt(view.getQuantity().getText());

            // Update data in orderitem table
            OrderItem.updateOrderItem(selectedItem.getOrderItemId(), updatedMenuItemId, updatedQuantity);

            view.getData().clear();
            view.getData().addAll(OrderItem.loadOrderItems());
        }
    }
}
