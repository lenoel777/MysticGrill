package view;

import controller.Controller_ManageItem;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View_Add_Menu extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Controller_ManageItem controller = Controller_ManageItem.getInstance();
		
		
        Label itemNameLabel = new Label("Menu Item Name:");
        TextField itemNameField = new TextField();
       

        Label itemDescriptionLabel = new Label("Menu Item Description:");
        TextField itemDescriptionField = new TextField();

        Label itemPriceLabel = new Label("Menu Item Price:");
        TextField itemPriceField = new TextField();

        Button addButton = new Button("Add Menu Item");
        addButton.setOnAction(event -> {
            String itemName = itemNameField.getText();
            String itemDescription = itemDescriptionField.getText();
            int itemPrice = Integer.parseInt(itemPriceField.getText());

            
            controller.addMenuItem(itemName, itemDescription, itemPrice);

            
            controller.refreshTable();
            primaryStage.close();
        });

        
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(
                itemNameLabel, itemNameField,
                itemDescriptionLabel, itemDescriptionField,
                itemPriceLabel, itemPriceField,
                addButton
        );

        
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Add Menu Item");
        primaryStage.setScene(scene);
        primaryStage.show();
		
	}

}
