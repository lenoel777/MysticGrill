package controller;

import view.Login;
import view.Register;
import database.Connect;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;

public class Register_Control {

    private Register view;
    private Connect connection;
    private User user;

    public Register_Control(Register view, Connect connection) {
        this.view = view;
        this.connection = connection;
        this.user = new User();
        attachEventHandlers();
    }

    private void attachEventHandlers() {
        view.getRegisterButton().setOnAction(e -> registerAction());
    }

    public void registerAction() {
        String nama = view.getName().getText();
        String userEmail = view.getEmail().getText();
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
            User user = new User();
            if (user.insertUser(nama, userEmail, pass)) {
                moveToLoginPage();
            } else {
                view.getLbl().setText("ERROR TO REGISTER USER. Please Try Again");
            }
        }
    }

    private void moveToLoginPage() {
        Login loginPage = new Login(connection);
        LoginControl loginController = new LoginControl(loginPage, connection);
        Stage primaryStage = (Stage)view.getScene().getWindow();
        primaryStage.setScene(loginPage.createScene());
        
    }

    private boolean isValidMail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
}
