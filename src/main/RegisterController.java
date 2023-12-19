package main;

import database.Connect;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.User;

public class RegisterController {

    private Register view;

    public RegisterController(Register view) {
        this.view = view;
        
    	view.getTmblRegis().setOnAction(e -> registerAction());
    }

    public void registerAction() {
        String nama = view.getName().getText();
        String userEmail = view.getTfemail().getText();
        String pass = view.getPassword().getText();
        String validate = view.getConfirm().getText();

        if (nama.isEmpty() || userEmail.isEmpty() || pass.isEmpty() || validate.isEmpty()) {
            view.getLbl().setText("Please fill in all fields.");
        } else if (!pass.equals(validate)) {
            view.getLbl().setText("Passwords do not match. Please make sure your passwords match.");
        } else if (!isValidMail(userEmail)) {
            view.getLbl().setText("Invalid email format. Please use a valid email address.");
        } else if (pass.length() < 6) {
            view.getLbl().setText("Password should be at least 6 characters long.");
        } else {
        	User.insertUser(nama, userEmail, pass);
        	Alert successMsg = new Alert(Alert.AlertType.INFORMATION);
        	successMsg.setTitle("Registration Succeed!");
        	successMsg.setContentText("User data has been stored. Please proceed to login.");
        	successMsg.showAndWait();
            moveToLoginPage();
        }
    }

    private void moveToLoginPage() {
        Login login = new Login();
        new LoginController(login);
    }

    private boolean isValidMail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
}
