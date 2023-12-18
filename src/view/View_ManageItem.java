package view;

import controller.Controller_ManageItem;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Main;
import model.MenuItem;

public class View_ManageItem{
	
	private Controller_ManageItem controller;
	
	private Stage stage; // Add a stage reference
	
	TextField idField = new TextField();
	TextField nameField = new TextField();
	TextField descriptionField = new TextField();
	TextField priceField = new TextField();

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void display() {
    	Controller_ManageItem controller = Controller_ManageItem.getInstance();
        controller.fetchMenuItemsFromDatabase(); 

        TableView<MenuItem> tableView = new TableView<>(controller.getMenuItems());

        TableColumn<MenuItem, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(data -> {
            MenuItem menuItem = data.getValue();
            return javafx.beans.binding.Bindings.createObjectBinding(() -> menuItem.getMenuItemId());
        });

        TableColumn<MenuItem, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(data -> {
            MenuItem menuItem = data.getValue();
            return javafx.beans.binding.Bindings.createStringBinding(() -> menuItem.getMenuItemName());
        });

        TableColumn<MenuItem, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(data -> {
            MenuItem menuItem = data.getValue();
            return javafx.beans.binding.Bindings.createStringBinding(() -> menuItem.getMenuItemDescription());
        });

        TableColumn<MenuItem, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(data -> {
            MenuItem menuItem = data.getValue();
            return javafx.beans.binding.Bindings.createObjectBinding(() -> menuItem.getMenuItemPrice());
        });
        
        
        
       
        Button addButton = new Button("Add");
        addButton.setOnAction(event -> {
            View_Add_Menu addMenu = new View_Add_Menu(); // Create an instance of the View_Add_Menu class
            Stage stage = new Stage();
            try {
				addMenu.start(stage);
				controller.refreshTable();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Start the View_Add_Menu in a new stage
            
        
        });
        
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> {
            MenuItem selectedItem = tableView.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                int menuItemId = selectedItem.getMenuItemId(); // Get the menuItemId from the selected item
                controller.deleteMenuItem(menuItemId); // Pass the menuItemId to the delete method in your controller
                controller.refreshTable(); // Refresh the table after deletion
            }
        });
        
        
        controller.initialize(tableView, idField, nameField, descriptionField, priceField);
        
        VBox detailsBox = new VBox(
        	    new Label("Name:"), nameField,
        	    new Label("Description:"), descriptionField,
        	    new Label("Price:"), priceField
        	);
     
        Button updateButton = new Button("Update");
        

        HBox buttonsBox = new HBox(addButton, deleteButton);
        
        tableView.getColumns().addAll(idColumn, nameColumn, descriptionColumn, priceColumn);
    

        VBox root = new VBox(tableView, buttonsBox, detailsBox, updateButton);
        Scene scene = new Scene(root, 1600, 900);
        
        


        Stage primaryStage = new Stage(); // Create a new stage for this view
        primaryStage.setTitle("Manage Menu Items");
        primaryStage.setScene(scene);
        primaryStage.show();
    
    }
}