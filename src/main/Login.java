package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Login extends VBox {

    private TextField username;
    private TextField uPassword;
    private Button tmblLogin;
    private Button tmblRegister;

    public Login() {
    	username = new TextField();
    	username.setPromptText("Username");
    	
    	uPassword = new TextField();
    	uPassword.setPromptText("Password");
    	
    	tmblLogin = new Button("Login");
    	tmblRegister = new Button("Register"); // Tombol Register
    	
    	HBox titleBox = new HBox(); // HBox untuk menempatkan judul di tengah
    	titleBox.getChildren().add(titleLabel());
    	titleBox.setAlignment(Pos.CENTER);
    	
    	this.setPadding(new Insets(10));
    	this.setAlignment(Pos.CENTER);
    	this.getChildren().addAll(titleBox, createLayout(username, uPassword, tmblLogin, tmblRegister));
    	
    	Scene loginScene = new Scene(this, 400, 300);
    	Main.setSceneTo(loginScene);
    }

    private Label titleLabel() {
        Label title = new Label("Welcome to Mystic Grill");
        title.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        title.setPadding(new Insets(10, 0, 0, 0));
        return title;
    }

    public static VBox createLayout(TextField username, TextField uPassword, Button tmblLogin, Button tmblRegister) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(username, uPassword, tmblLogin, tmblRegister);
        return layout;
    }

	public TextField getUsername() {
		return username;
	}

	public TextField getuPassword() {
		return uPassword;
	}

	public Button getTmblLogin() {
		return tmblLogin;
	}

	public Button getTmblRegister() {
		return tmblRegister;
	}
}