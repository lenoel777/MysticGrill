package view;

import view.Home;
import database.Connect;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import Controller.LoginControl;

public class Login extends VBox {

    private TextField username;
    private PasswordField password;
    private Button loginButton;
    private Hyperlink registerLink;
    private Connect connection;
    private User user;

    public Login(Connect connection) {
        this.connection = connection;
        initialize();
        attachEventHandlers();
    }

    private void initialize() {
        username = new TextField();
        username.setPromptText("Username");

        password = new PasswordField();
        password.setPromptText("Password");

        loginButton = new Button("Login");

        registerLink = new Hyperlink("Don't Have an Account Yet? Register Here");

        HBox titleBox = new HBox();
        titleBox.getChildren().add(titleLabel());
        titleBox.setAlignment(Pos.CENTER);

        this.getChildren().addAll(titleBox, createLayout(username, password, loginButton, registerLink));
        this.setAlignment(Pos.CENTER);
    }

    public TextField getUsername() {
        return username;
    }

    public PasswordField getPassword() {
        return password;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public Hyperlink getRegisterLink() {
        return registerLink;
    }

    private Label titleLabel() {
        Label title = new Label("Welcome to Mystic Grill");
        title.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        title.setPadding(new Insets(10, 0, 0, 0));
        return title;
    }

    private static VBox createLayout(TextField username, PasswordField password, Button loginButton, Hyperlink registerLink) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(username, password, loginButton, registerLink);
        return layout;
    }

    private void attachEventHandlers() {
        LoginControl loginControl = new LoginControl(this, connection);
        loginButton.setOnAction(e -> loginControl.loginAction());
        registerLink.setOnAction(e -> loginControl.moveToRegisterPage());
    }
    public Scene createScene() {
    	return new Scene(this, 400,400);
    }

}
