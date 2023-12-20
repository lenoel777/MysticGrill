package customerView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.MenuItem;

public class ViewMenuItem extends VBox {

	private TableView<MenuItem> table;

	private TextField idMenu = new TextField();
	private TextField nameMenu = new TextField();
	private TextField deskripsiMenu = new TextField();
	private TextField priceMenu = new TextField();
	private TextField quantity = new TextField();

	private Button addButton = new Button("Add Order");

	public ViewMenuItem() {
		table = createProductTable(); // Initialize the table
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		GridPane form = createProductForm(table);
		VBox.setMargin(form, new Insets(20));
		this.getChildren().addAll(table, form); // Use getChildren() directly on VBox
	}

	private TableView<MenuItem> createProductTable() {
		TableView<MenuItem> table = new TableView<>();
		TableColumn<MenuItem, Number> idColumn = new TableColumn<>("Product ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemId"));

		TableColumn<MenuItem, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemName"));

		TableColumn<MenuItem, String> descriptionColumn = new TableColumn<>("Description Menu:");
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemDescription"));

		TableColumn<MenuItem, Number> priceColumn = new TableColumn<>("Price:");
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("menuItemPrice"));

		table.getColumns().add(idColumn);
		table.getColumns().add(nameColumn);
		table.getColumns().add(descriptionColumn);
		table.getColumns().add(priceColumn);

		return table;
	}

	private GridPane createProductForm(TableView<MenuItem> table) {
		GridPane form = new GridPane();
		form.setVgap(20);
		form.setHgap(10);

		form.add(new Label("Product ID:"), 0, 0);
		idMenu.setDisable(true);
		form.add(idMenu, 1, 0);
		form.add(new Label("Name Menu:"), 0, 1);
		nameMenu.setDisable(true);
		form.add(nameMenu, 1, 1);
		form.add(new Label("Description Menu:"), 0, 2);
		deskripsiMenu.setDisable(true);
		form.add(deskripsiMenu, 1, 2);
		form.add(new Label("Price:"), 0, 3);
		priceMenu.setDisable(true);
		form.add(priceMenu, 1, 3);
		form.add(new Label("Quantity"), 0, 4);
		form.add(quantity, 1, 4);
		form.add(addButton, 0, 5);

		return form;
	}

	public TextField getIdMenu() {
		return idMenu;
	}

	public TextField getNameMenu() {
		return nameMenu;
	}

	public TextField getDeskripsiMenu() {
		return deskripsiMenu;
	}

	public TextField getPriceMenu() {
		return priceMenu;
	}

	public TextField getQuantity() {
		return quantity;
	}

	public Button getAddButton() {
		return addButton;
	}

	public TableView<MenuItem> getTable() {
		return table;
	}
}
