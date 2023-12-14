package view;

import view.Add_Order;
import view.Update_Order;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Home extends VBox {

    private Stage primaryStage;

    public Home(Stage primaryStage) {
        this.primaryStage = primaryStage;
        BorderPane layout = new BorderPane();

        MenuBar menuBar = createMenuBar();
        layout.setTop(menuBar);

        // pesan selamat datang di bawah menuBar
        Label welcomeLabel = new Label("WELCOME TO MYSTIC GRILL\nSilahkan pesan makan yang ada mau");
        welcomeLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        VBox welcomeBox = new VBox(welcomeLabel);
        welcomeBox.setAlignment(javafx.geometry.Pos.CENTER);
        welcomeBox.setPadding(new Insets(20));
        layout.setCenter(welcomeBox);

        getChildren().add(layout);
    }

    private MenuBar createMenuBar() {
        // Untuk Tab Bar View
        MenuBar menuBar = new MenuBar();

        Menu menuView = new Menu("Menu");
        MenuItem itemViewMenu = new MenuItem("View Menu");
        // Buat masukin Methode pindah Page
        itemViewMenu.setOnAction(e -> moveToViewMenuPage());

        MenuItem itemViewOrder = new MenuItem("View Order");
        itemViewOrder.setOnAction(e -> moveToViewOrderPage());

        menuView.getItems().addAll(itemViewMenu, itemViewOrder);

        // Untuk Tab Bar Process
        Menu menuProcess = new Menu("User Process");
        MenuItem itemServe = new MenuItem("Serve");
        itemServe.setOnAction(e -> moveToServePage());
        MenuItem itemProcess = new MenuItem("Process");
        itemProcess.setOnAction(e -> moveToProcessPage());

        menuProcess.getItems().addAll(itemServe, itemProcess);

        // Untuk Tab Bar Payment
        Menu menuPayment = new Menu("Managemet Process");
        MenuItem itemPayment = new MenuItem("Payment");
        itemPayment.setOnAction(e -> moveToPaymentPage());

        // Untuk Tab Bar Profile
        Menu menuProfile = new Menu("User Management");
        MenuItem itemProfile = new MenuItem("Profile");
        itemProfile.setOnAction(e -> moveToProfilePage());

        // Buat Munculin Menu Bar dari Home
        menuBar.getMenus().addAll(menuView, menuProcess, menuPayment, menuProfile);

        return menuBar;
    }

    private void moveToViewMenuPage() {
        Add_Order viewMenuPage = new Add_Order(primaryStage);
        setPage(viewMenuPage, "View Menu");
    }

    private void moveToViewOrderPage() {
        Update_Order viewUpdatePage = new Update_Order(primaryStage);
        setPage(viewUpdatePage, "Order List");
    }

    private void moveToServePage() {
        setPage(new Label("Serve Page"), "Serve Order");
    }

    private void moveToProcessPage() {
        setPage(new Label("View Process Page"), "Process");
    }

    private void moveToPaymentPage() {
        setPage(new Label("View Payment Page"), "Payment");
    }

    private void moveToProfilePage() {
        setPage(new Label("View Profile Page"), "Profile");
    }

    private void setPage(javafx.scene.Node content, String pageTitle) {
        BorderPane layout = new BorderPane();

        MenuBar menuBar = createMenuBar();
        layout.setTop(menuBar);

        layout.setCenter(content);

        Scene scene = new Scene(layout, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle(pageTitle);
        primaryStage.show();
    }
}
