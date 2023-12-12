package main;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ProcessOrderPayment extends VBox{
	
	Label judul;
	
	public ProcessOrderPayment(int orderIds) {
		judul = new Label("Process Order Payment");
		
		TextField orderIdField = new TextField();
        ComboBox<String> paymentTypeComboBox = new ComboBox<>();
        paymentTypeComboBox.getItems().addAll("Cash", "Debit", "Credit");
        TextField paymentAmountField = new TextField();
        Button processOrderButton = new Button("Process Order");

        // Create labels
        Label orderIdLabel = new Label("Order ID:");
        Label paymentTypeLabel = new Label("Payment Type:");
        Label paymentAmountLabel = new Label("Payment Amount:");

        // Create a GridPane layout
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Add components to the layout
        gridPane.add(orderIdLabel, 0, 0);
        gridPane.add(orderIdField, 1, 0);
        gridPane.add(paymentTypeLabel, 0, 1);
        gridPane.add(paymentTypeComboBox, 1, 1);
        gridPane.add(paymentAmountLabel, 0, 2);
        gridPane.add(paymentAmountField, 1, 2);
        gridPane.add(processOrderButton, 1, 3);

        // Set action for the Process Order button
        processOrderButton.setOnAction(e -> {
            // Validate fields
            String orderId = orderIdField.getText();
            String paymentType = paymentTypeComboBox.getValue();
            String paymentAmountText = paymentAmountField.getText();

            if (orderId.isEmpty() || paymentType == null || paymentAmountText.isEmpty()) {
                showAlert("Error", "Please fill in all fields.");
                return;
            }

            // Validate order ID (Check if it exists in the database - Simulated here)
            boolean orderExists = checkOrderExistsInDatabase(orderId);
            if (!orderExists) {
                showAlert("Error", "Order ID does not exist in the database.");
                return;
            }

            // Validate payment type
            if (!paymentType.equals("Cash") && !paymentType.equals("Debit") && !paymentType.equals("Credit")) {
                showAlert("Error", "Payment type must be 'Cash', 'Debit', or 'Credit'.");
                return;
            }

            // Validate payment amount
            double paymentAmount;
            try {
                paymentAmount = Double.parseDouble(paymentAmountText);
                double totalOrderPrice = getTotalOrderPriceFromDatabase(orderId); // Simulated database call
                if (paymentAmount < totalOrderPrice) {
                    showAlert("Error", "Payment amount must be greater than or equal to the total order price.");
                    return;
                }
            } catch (NumberFormatException ex) {
                showAlert("Error", "Please enter a valid payment amount.");
                return;
            }

            // If all validations pass, update order status to "Paid" (Simulated action)
            updateOrderStatusToPaid(orderId);
            showAlert("Success", "Order processed successfully. Order status updated to 'Paid'.");
        });
        
		this.getChildren().addAll(judul, gridPane);
	}
        
	private boolean checkOrderExistsInDatabase(String orderId) {
        // Perform a check in the database and return true/false based on existence
        // Simulated implementation
    return true; // Change to your actual database check
	}

    private double getTotalOrderPriceFromDatabase(String orderId) {
    	// Retrieve total order price from the database based on orderId
    	// Simulated implementation
    	return 100.0; // Change to your actual database retrieval
    }
    
    private void updateOrderStatusToPaid(String orderId) {
    	// Update the order status to "Paid" in the database
    	// Simulated implementation
    	// Update the order status here (e.g., using SQL UPDATE query)
    }

    // Helper method to display an alert dialog
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
