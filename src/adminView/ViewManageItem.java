package adminView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.MenuItem;

public class ViewManageItem extends VBox{
	
	TextField idField = new TextField();
	TextField nameField = new TextField();
	TextField descriptionField = new TextField();
	TextField priceField = new TextField();
	TableView<MenuItem> table;
	Button addButton, deleteButton, updateButton;
	
	public ViewManageItem() {
		table = setTable();
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        addButton = new Button("Add");
        deleteButton = new Button("Delete");
        updateButton = new Button("Update");
        
        VBox detailsBox = new VBox(
        		new Label("ID: "), idField,
        	    new Label("Name:"), nameField,
        	    new Label("Description:"), descriptionField,
        	    new Label("Price:"), priceField
        	);
        idField.setDisable(true);
        VBox.setMargin(detailsBox, new Insets(20));
        
        HBox buttonsBox = new HBox(updateButton, addButton, deleteButton);
        HBox.setMargin(buttonsBox, new Insets(20));
        
        VBox root = new VBox(table, detailsBox, buttonsBox);
        
        this.setAlignment(Pos.CENTER);
        this.getChildren().add(root);
    }
	
	private TableView<MenuItem> setTable(){
	     TableView<MenuItem> tableView = new TableView<>();

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
	     
	     tableView.getColumns().addAll(idColumn, nameColumn, descriptionColumn, priceColumn);
	     
	     return tableView;
	}

	public TextField getIdField() {
		return idField;
	}

	public void setIdField(TextField idField) {
		this.idField = idField;
	}

	public TextField getNameField() {
		return nameField;
	}

	public void setNameField(TextField nameField) {
		this.nameField = nameField;
	}

	public TextField getDescriptionField() {
		return descriptionField;
	}

	public void setDescriptionField(TextField descriptionField) {
		this.descriptionField = descriptionField;
	}

	public TextField getPriceField() {
		return priceField;
	}

	public void setPriceField(TextField priceField) {
		this.priceField = priceField;
	}

	public TableView<MenuItem> getTable() {
		return table;
	}

	public void setTable(TableView<MenuItem> table) {
		this.table = table;
	}

	public Button getAddButton() {
		return addButton;
	}

	public void setAddButton(Button addButton) {
		this.addButton = addButton;
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(Button deleteButton) {
		this.deleteButton = deleteButton;
	}

	public Button getUpdateButton() {
		return updateButton;
	}

	public void setUpdateButton(Button updateButton) {
		this.updateButton = updateButton;
	}
	
	
	
}