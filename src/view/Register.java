package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import database.Connect;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

public class Register extends Application {

    private TextField name;
    private TextField tfemail;
    private TextField password;
    private TextField confirm;
    private Button tmblRegis;
    private Label lbl;

    @Override
    public void start(Stage primaryStage) throws Exception {
        name = new TextField();
        name.setPromptText("Name");

        tfemail = new TextField();
        tfemail.setPromptText("Email");

        password = new TextField();
        password.setPromptText("Password");

        confirm = new TextField();
        confirm.setPromptText("Confirm Password");

        tmblRegis = new Button("Register");
        tmblRegis.setOnAction(e -> {
			try {
				registerAction(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

        lbl = new Label();
        lbl.setPadding(new Insets(10));

        VBox vbox = new VBox(5);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(titleLabel(), name, tfemail, password, confirm, tmblRegis, lbl);

        Scene scene = new Scene(vbox, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Registration Page");
        primaryStage.show();
    }

    private Label titleLabel() {
        Label title = new Label("Welcome to Mystic Grill");
        title.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        title.setPadding(new Insets(10, 0, 0, 0));
        return title;
    }

    private void registerAction(Stage primaryStage) throws Exception {
        String nama = name.getText();
        String email = tfemail.getText();
        String pass = password.getText();
        String validate = confirm.getText();

        if (nama.isEmpty() || email.isEmpty() || pass.isEmpty() || validate.isEmpty()) {
            lbl.setText("Please fill in all fields.");
        } else if (!pass.equals(validate)) {
            lbl.setText("Passwords do not match. Please make sure your passwords match.");
        } else if (!isValidMail(email)) {
            lbl.setText("Invalid email format. Please use a valid email address.");
        } else if (pass.length() < 6) {
            lbl.setText("Password should be at least 6 characters long.");
        } else {
        	
        	//Kondisi pas berhasil login
        	if(registerUser(nama, email, pass)) {
        		
                lbl.setText("Registration Successful");
//                MoveToLoginPage(primaryStage);
                
        	}else {
        		lbl.setText("EROR TO REGISTER USER. Please Try Again");
        	}
        	
        }
    }
    
    private boolean registerUser(String name, String email, String password) throws Exception {
//        try (Connect connect = Connect.getConnection()) {
//            String sql = "INSERT INTO user (userName, userEmail, userPassword, userRole) VALUES (?, ?, ?, ?)";
//            try (PreparedStatement prepared = connect.prepareStatement(sql)) {
//                prepared.setString(1, name);
//                prepared.setString(2, email);
//                prepared.setString(3, password);
//                prepared.setString(4, "Customer");
//
//                int rowsAffected = prepared.executeUpdate();
//
//                return rowsAffected > 0;
//            } catch (SQLException e) {
//                e.printStackTrace();
//                return false;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
        
        try {
			User.insertUser(name, email, password);
			User.loadUsers();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
            return false;
		}
        
    }

    
//    private void MoveToLoginPage(Stage primaryStage) {
//        Login login = new Login(Connect.getConnection()); // Mengirimkan koneksi ke konstruktor Login
//
//        try {
//            login.start(primaryStage);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private boolean isValidMail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
}