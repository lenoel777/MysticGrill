package main;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Register extends VBox {

    private TextField name;
    private TextField tfemail;
    private TextField password;
    private TextField confirm;
    private Button tmblRegis;
    private Label lbl;
    
    public Register() {
    	name = new TextField();
    	name.setPromptText("Name");
    	
    	tfemail = new TextField();
    	tfemail.setPromptText("Email");
    	
    	password = new TextField();
    	password.setPromptText("Password");
    	
    	confirm = new TextField();
    	confirm.setPromptText("Confirm Password");
    	
    	tmblRegis = new Button("Register");
    	
    	lbl = new Label();
    	lbl.setPadding(new Insets(10));
    	
    	this.setPadding(new Insets(10));
    	this.setAlignment(Pos.CENTER);
    	this.getChildren().addAll(titleLabel(), name, tfemail, password, confirm, tmblRegis, lbl);
    	
    	Scene scene = new Scene(this, 400, 300);
    	Main.setSceneTo(scene);
    }
    
    private Label titleLabel() {
        Label title = new Label("Welcome to Mystic Grill");
        title.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        title.setPadding(new Insets(10, 0, 0, 0));
        return title;
    }

	public TextField getName() {
		return name;
	}

	public TextField getTfemail() {
		return tfemail;
	}

	public TextField getPassword() {
		return password;
	}

	public TextField getConfirm() {
		return confirm;
	}

	public Button getTmblRegis() {
		return tmblRegis;
	}

	public Label getLbl() {
		return lbl;
	}

}