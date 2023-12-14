package view;

import database.Connect;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import model.User;
import Controller.Register_Control;

public class Register extends VBox {

    private TextField name;
    private TextField email;
    private PasswordField password;
    private PasswordField confirm;
    private Button registerButton;
    private Label lbl;
    private Connect connection;
    private User user;
    private Register_Control registerControl;

    public Register(Connect connection) {
        this.connection = connection;
        initialize();
    }

    private void initialize() {
        name = new TextField();
        name.setPromptText("Name");

        email = new TextField();
        email.setPromptText("Email");

        password = new PasswordField();
        password.setPromptText("Password");

        confirm = new PasswordField();
        confirm.setPromptText("Confirm Password");

        registerButton = new Button("Register");
        registerButton.setOnAction(e -> registerControl.registerAction());

        lbl = new Label();
        lbl.setPadding(new Insets(10));

        this.getChildren().addAll(titleLabel(), name, email, password, confirm, registerButton, lbl);
        this.setPadding(new Insets(10));
        this.setAlignment(Pos.CENTER);

         
    }

    private Label titleLabel() {
        Label title = new Label("Welcome to Mystic Grill");
        title.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        title.setPadding(new Insets(10, 0, 0, 0));
        return title;
    }
    
    public Scene createScene() {
    	return new Scene(this, 400, 400);
    }

    public TextField getName() {
        return name;
    }

    public TextField getEmail() {
        return email;
    }

    public PasswordField getPassword() {
        return password;
    }

    public PasswordField getConfirm() {
        return confirm;
    }

    public Button getRegisterButton() {
        return registerButton;
    }

    public Label getLbl() {
        return lbl;
    }
    public Register_Control getRegisterControl() {
        return registerControl;
    }
}