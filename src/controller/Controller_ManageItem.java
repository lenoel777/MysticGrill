package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.MenuItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Connect;


public class Controller_ManageItem {
	private ObservableList<MenuItem> menuItems;
    private Connect connect;
	private TableView<MenuItem> tableView;
	private TextField idField;
	private TextField nameField;
	private TextField descriptionField;
	private TextField priceField; 
    private static Controller_ManageItem instance;
    

    public Controller_ManageItem() {
        this.menuItems = FXCollections.observableArrayList();
        this.connect = Connect.getConnection(); 
    }

    public void fetchMenuItemsFromDatabase() {
        String query = "SELECT menuItemId, menuItemName, menuItemDescription, menuItemPrice FROM menuitem";

        try {
            ResultSet resultSet = connect.executeQuery(query);

            while (resultSet.next()) {
                int itemId = resultSet.getInt("menuItemId");
                String itemName = resultSet.getString("menuItemName");
                String itemDescription = resultSet.getString("menuItemDescription");
                int itemPrice = resultSet.getInt("menuItemPrice");

                MenuItem item = new MenuItem(itemId, itemName, itemDescription, itemPrice);
                menuItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void refreshTable() {
        menuItems.clear(); 
        fetchMenuItemsFromDatabase(); 
    }
    
    //singleton	
    public static synchronized Controller_ManageItem getInstance() {
        if (instance == null) {
            instance = new Controller_ManageItem();
        }
        return instance;
    }
    
    

        
    //listener for update item
    public void initialize(TableView<MenuItem> tableView, TextField idField, TextField nameField,
                               TextField descriptionField, TextField priceField) {
            this.tableView = tableView;
            this.idField = idField;
            this.nameField = nameField;
            this.descriptionField = descriptionField;
            this.priceField = priceField;

            
            tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    
                    MenuItem selectedItem = tableView.getSelectionModel().getSelectedItem();

                    
                    idField.setText(String.valueOf(selectedItem.getMenuItemId()));
                    nameField.setText(selectedItem.getMenuItemName());
                    descriptionField.setText(selectedItem.getMenuItemDescription());
                    priceField.setText(String.valueOf(selectedItem.getMenuItemPrice()));
                } else {
                    idField.clear();
                    nameField.clear();
                    descriptionField.clear();
                    priceField.clear();
                }
            });
        }

        
    

    
    //checking is the name for new item unique
    private boolean isItemNameUnique(String itemName) {
        // Loop through existing menu items to check for uniqueness
        for (MenuItem item : menuItems) {
            // Compare the new itemName with existing menuItemNames
            if (item.getMenuItemName().equals(itemName)) {
                return false; // Name is not unique
            }
        }
        return true; // Name is unique
    }
    
    
    //alert for invalid add input
    private void displayInvalidInputAlert() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText("Please check your input:\n"
                + "- Menu Item Name cannot be empty and must be unique.\n"
                + "- Menu Item Description must be more than 10 characters.");

        // Display the alert dialog
        alert.showAndWait();
    }
    
    //checking constraint
    public boolean isValidMenuItem(String itemName, String itemDescription, int itemPrice) {
        // Check for empty itemName, insufficient itemDescription length, and itemPrice constraints
        return !itemName.isEmpty() &&
               (itemDescription.isEmpty() || itemDescription.length() > 10) &&
               (itemPrice > 0); // Assuming a positive price is a constraint
    }
    
    
    //add menu
    public void addMenuItem(String itemName, String itemDescription, int itemPrice) {
    	if (!isValidMenuItem(itemName, itemDescription, itemPrice)) {
            displayInvalidInputAlert();
            return;
        }
        
        String query = "INSERT INTO menuitem (menuItemName, menuItemDescription, menuItemPrice) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, itemName);
            statement.setString(2, itemDescription);
            statement.setDouble(3, itemPrice);

            statement.executeUpdate();
            System.out.println("Menu item added to the database!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to add menu item to the database.");
        }
    }
    
    //delete Menu
    public void deleteMenuItem(int menuItemId) {
	    if (menuItemId <= 0) {
	        // Handle invalid ID
	        System.out.println("Invalid menu item ID");
	        return;
	    }

	    String query = "DELETE FROM menuitem WHERE menuItemId = ?";

	    try (PreparedStatement statement = connect.prepareStatement(query)) {
	        statement.setInt(1, menuItemId);
	        int rowsAffected = statement.executeUpdate();

	        if (rowsAffected > 0) {
	            System.out.println("Menu item deleted from the database!");
	        } else {
	            System.out.println("Menu item with ID " + menuItemId + " not found in the database.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.err.println("Failed to delete menu item from the database.");
	    }
	}
    
    //update menu
    public void updateMenuItem(int menuItemId, String itemName, String itemDescription, int itemPrice) {
        if (!isValidMenuItem(itemName, itemDescription, itemPrice)) {
            displayInvalidInputAlert();
            return;
        }

        String query = "UPDATE menuitem SET menuItemName = ?, menuItemDescription = ?, menuItemPrice = ? WHERE menuItemId = ?";

        try (PreparedStatement statement = connect.prepareStatement(query)) {
            statement.setString(1, itemName);
            statement.setString(2, itemDescription);
            statement.setDouble(3, itemPrice);
            statement.setInt(4, menuItemId);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Menu item updated in the database!");
            } else {
                System.out.println("Menu item not found or no update executed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to update menu item in the database.");
        }
    }
    
    
    //getting the list from database
    public ObservableList<MenuItem> getMenuItems() {
        return menuItems;
    }

}
