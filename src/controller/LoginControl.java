package controller;

import view.Home;
import view.Login;
import view.Register;
import database.Connect;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.User;

public class LoginControl {
    private final Login view;
    private final Connect connection;
    private final User user;

    public LoginControl(Login view, Connect connection) {
        this.view = view;
        this.connection = connection;
        this.user = new User();
    }

    public void loginAction() {
        String enterUsername = view.getUsername().getText();
        String enterPassword = view.getPassword().getText();
        

        if (isValidationLogin(enterUsername, enterPassword)) {
            Stage primaryStage = new Stage();
            Home home = new Home(primaryStage);
            primaryStage.setScene(new Scene(home,400,400));
            primaryStage.show();
        } else {
            showAlert("LOGIN FAILED", "Username & Password Not Found. Please Register");
        }
    }

    public void moveToRegisterPage() {
        Register registerView = new Register(connection);
        Register_Control registerControl = new Register_Control(registerView, connection);
        Stage primaryStage = (Stage) view.getScene().getWindow();
        primaryStage.setScene(registerView.createScene());
        
    }

    private boolean isValidationLogin(String enterUsername, String enterPassword) {
        return user.validateLogin(connection, enterUsername, enterPassword);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
