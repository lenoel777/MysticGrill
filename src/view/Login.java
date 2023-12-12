package view;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.Connect;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Application {

    private TextField username;
    private TextField uPassword;
    private Button tmblLogin;
    private Button tmblRegister; // Tombol Register baru
    private Connect connection;

    public Login(Connect connection) {
        this.connection = connection.getConnection();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        username = new TextField();
        username.setPromptText("Username");

        uPassword = new TextField();
        uPassword.setPromptText("Password");

        tmblLogin = new Button("Login");
        tmblLogin.setOnAction(e -> loginAction(primaryStage));

        tmblRegister = new Button("Register"); // Tombol Register
        tmblRegister.setOnAction(e -> moveToRegisterPage(primaryStage));

        HBox titleBox = new HBox(); // HBox untuk menempatkan judul di tengah
        titleBox.getChildren().add(titleLabel());
        titleBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(5);
        vbox.getChildren().addAll(titleBox, createLayout(username, uPassword, tmblLogin, tmblRegister));

        Scene loginScene = new Scene(vbox, 300, 200);
        primaryStage.setScene(loginScene);
        primaryStage.setTitle("Login Page");
        primaryStage.show();
    }

    private Label titleLabel() {
        Label title = new Label("Welcome to Mystic Grill");
        title.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        title.setPadding(new Insets(10, 0, 0, 0));
        return title;
    }

    private void loginAction(Stage primaryStage) {
        String enterUsername = username.getText();
        String enterPassword = uPassword.getText();

        if (isValidationLogin(enterUsername, enterPassword)) {
//            primaryStage.setScene(Home.createScene());
        } else {
            showAlert("LOGIN FAILED", "Username & Password Not Found. Please Register");
        }
    }

    private Boolean isValidationLogin(String enterUsername, String enterPassword) {
        String qry = "SELECT * FROM user WHERE userName = ? AND userPassword = ?";
        
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(qry);
            preparedStatement.setString(1, enterUsername);
            preparedStatement.setString(2, enterPassword);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Menggunakan resultSet.next() untuk mengecek apakah ada hasil dari query
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void moveToRegisterPage(Stage primaryStage) {
        Register registerPage = new Register();
        try {
            registerPage.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static VBox createLayout(TextField username, TextField uPassword, Button tmblLogin, Button tmblRegister) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(username, uPassword, tmblLogin, tmblRegister);
        return layout;
    }
}