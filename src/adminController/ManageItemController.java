package adminController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.MenuItem;
import adminView.ViewManageItem;
import database.Connect;


public class ManageItemController {
	private ObservableList<MenuItem> menuItems = FXCollections.observableArrayList();;

	Connect connect;
	ViewManageItem vmi;
	MenuItem selectedItem;

    public ManageItemController(ViewManageItem vmi) {
    	this.vmi = vmi;
        connect = Connect.getConnection();
        
        refreshTable();
        setupTableSelectionListener();
    	buttonClickListener();
    }
    
    public void refreshTable() {
        menuItems.clear();
        
        menuItems.addAll(MenuItem.loadMenuItems());
        if (menuItems != null && !menuItems.isEmpty()) {
    	    vmi.getTable().setItems(menuItems);
   		}
    }
        
    //listener for update item
    public void setupTableSelectionListener() {
            vmi.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    
                    selectedItem = newSelection;
         
                    vmi.getIdField().setText(String.valueOf(selectedItem.getMenuItemId()));
                    vmi.getNameField().setText(selectedItem.getMenuItemName());
                    vmi.getDescriptionField().setText(selectedItem.getMenuItemDescription());
                    vmi.getPriceField().setText(String.valueOf(selectedItem.getMenuItemPrice()));
                } else {
                	vmi.getIdField().clear();
                	vmi.getNameField().clear();
                	vmi.getDescriptionField().clear();
                	vmi.getPriceField().clear();
                }
            });
    }
    
    //checking is the name for new item unique
    private boolean isItemNameUnique(String itemName) {
        // Loop through existing menu items to check for uniqueness
        for (MenuItem item : menuItems) {
            // Compare the new itemName with existing menuItemNames
            if (item.getMenuItemName().equals(itemName)) {
            	Alert alert = new Alert(AlertType.ERROR);
            	alert.setTitle("Invalid");
            	alert.setHeaderText(null);
            	alert.setContentText("Name is not unique. Try another name!");
            	alert.showAndWait();
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
        return !itemName.isEmpty() &&
               (itemDescription.isEmpty() || itemDescription.length() > 10) &&
               (itemPrice > 0); 
    }
    
    //add menu
    public void addMenuItem(String itemName, String itemDescription, int itemPrice) {
    	if (!isValidMenuItem(itemName, itemDescription, itemPrice)) {
            displayInvalidInputAlert();
            return;
        }
        
    	MenuItem.insertMenuItem(itemName, itemDescription, itemPrice);
    }
    
    //delete Menu
    public void deleteMenuItem(int menuItemId) {
	    if (menuItemId <= 0) {
	        // Handle invalid ID
	        System.out.println("Invalid menu item ID");
	        return;
	    }

	    MenuItem.deleteMenuItem(menuItemId);
	}
    
    //update menu
    public void updateMenuItem(int menuItemId, String itemName, String itemDescription, int itemPrice) {
        if (!isValidMenuItem(itemName, itemDescription, itemPrice)) {
            displayInvalidInputAlert();
            return;
        }

        MenuItem.updateMenuItem(menuItemId, itemName, itemDescription, itemPrice);
    }
    
    private void buttonClickListener() {
        vmi.getAddButton().setOnAction(event -> {
           	handleInsert();
			refreshTable();
        });
        
        vmi.getDeleteButton().setOnAction(event -> {
            if (selectedItem != null) {
                int menuItemId = selectedItem.getMenuItemId(); // Get the menuItemId from the selected item
                deleteMenuItem(menuItemId); // Pass the menuItemId to the delete method in your controller
                refreshTable(); // Refresh the table after deletion
            }
        });
        
        vmi.getUpdateButton().setOnAction(event -> {
            handleUpdate();
        });
    }
    
	private void handleInsert() {
		String name = vmi.getNameField().getText();
		String desc = vmi.getDescriptionField().getText();
		int price = Integer.parseInt(vmi.getPriceField().getText());
		
		if(!isItemNameUnique(name)) {
			return;
		};
		
		addMenuItem(name, desc, price);
		refreshTable();
	}
	
	private void handleUpdate() {
		int id = Integer.parseInt(vmi.getIdField().getText());
		String name = vmi.getNameField().getText();
		String desc = vmi.getDescriptionField().getText();
		int price = Integer.parseInt(vmi.getPriceField().getText());
		
		updateMenuItem(id, name, desc, price);
		refreshTable();
	}
}
