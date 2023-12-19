package main;

import javafx.scene.control.Alert;
import model.User;

public class LoginController {
    private Login view;

    public LoginController(Login view) {
        this.view = view;

        view.getTmblLogin().setOnAction(e -> loginAction());
        view.getTmblRegister().setOnAction(e -> moveToRegisterPage());
    }

    public void loginAction() {
        String enterUsername = view.getUsername().getText();
        String enterPassword = view.getuPassword().getText();
        
        if (isValidationLogin(enterUsername, enterPassword)) {
            new Home();
        } else {
            showAlert("LOGIN FAILED", "Username & Password Not Found. Please Register");
        }
    }

    private void moveToRegisterPage() {
        Register reg = new Register();
        new RegisterController(reg);
    }

    private boolean isValidationLogin(String enterUsername, String enterPassword) {
        for(User u: User.loadUsers()) {
        	if(u.getUserName().equals(enterUsername) && u.getUserPassword().equals(enterPassword)) {
        		User.setCurrUser(u);
        		return true;
        	}
        }
        return false;
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
