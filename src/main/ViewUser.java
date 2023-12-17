package main;

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
import javafx.scene.layout.VBox;
import model.User;

public class ViewUser extends VBox{

	TableView<User> table;
	TextField userId = new TextField();
	TextField userRole = new TextField();
	TextField userName = new TextField();
	TextField userEmail = new TextField();
	TextField userPassword = new TextField();
	Button updatebtn;
	public ViewUser() {
		table = UserTable();
		// TODO Auto-generated constructor stub
		HBox titleBox = new HBox();
        titleBox.getChildren().add(new Label("User List"));
        titleBox.setAlignment(Pos.CENTER);
        
        GridPane form = UserForm(table);
        VBox.setMargin(form, new Insets(20));
        this.getChildren().addAll(titleBox, table, form);

        Scene receiptOrderScene = new Scene(this, 800, 600);
        Main.nextScene(receiptOrderScene);
	}

	private TableView<User> UserTable() {
		TableView<User> table = new TableView<>();
		TableColumn<User, Number> userId = new TableColumn<>("User ID");
		userId.setCellValueFactory(new PropertyValueFactory<>("userId"));

		TableColumn<User, Number> userRole = new TableColumn<>("Receipt order ID");
		userRole.setCellValueFactory(new PropertyValueFactory<>("userRole"));

		TableColumn<User, String> userName = new TableColumn<>("receipt payment amount");
		userName.setCellValueFactory(new PropertyValueFactory<>("userName"));

		TableColumn<User, String> userEmail = new TableColumn<>("Receipt Payment Date");
		userEmail.setCellValueFactory(new PropertyValueFactory<>("userEmail"));
		
		TableColumn<User, Number> userPassword = new TableColumn<>("Receipt Payment Type");
		userPassword.setCellValueFactory(new PropertyValueFactory<>("userPassword"));

		table.getColumns().add(userId);
		table.getColumns().add(userRole);
		table.getColumns().add(userName);
		table.getColumns().add(userEmail);
		table.getColumns().add(userPassword);

		return table;
	}
	private GridPane UserForm(TableView<User> table) {
		GridPane form = new GridPane();
		form.setVgap(20);
		form.setHgap(10);

		form.add(new Label("User ID:"), 0, 0);
		userId.setDisable(true);
		form.add(userId, 1, 0);
		form.add(new Label("User Role:"), 0, 1);
		userRole.setDisable(true);
		form.add(userRole , 1, 1);
		form.add(new Label("User Name"), 0, 2);
		userName.setDisable(true);
		form.add(userName, 1, 2);
		form.add(new Label("User Email"), 0, 3);
		userEmail.setDisable(true);
		form.add(userEmail, 1, 3);
		form.add(new Label("User Password"), 0, 4);
		userPassword.setDisable(true);
		form.add(userPassword, 1, 4);
		return form;
	}

	public TableView<User> getTable() {
		return table;
	}

	public TextField getUserId() {
		return userId;
	}

	public TextField getUserRole() {
		return userRole;
	}

	public TextField getUserName() {
		return userName;
	}

	public TextField getUserEmail() {
		return userEmail;
	}

	public TextField getUserPassword() {
		return userPassword;
	}

	public Button getUpdatebtn() {
		return updatebtn;
	}
	
}
