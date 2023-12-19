package main;

import adminController.ManageItemController;
import adminController.ViewUserController;
import adminView.ViewManageItem;
import adminView.ViewUser;
import cashierController.ViewOrderController;
import cashierController.ViewReceiptController;
import cashierView.ViewOrder;
import cashierView.ViewReceipt;
import chefController.PrepareController;
import chefView.ViewPrepare;
import customerController.MenuItemController;
import customerController.OrderedMenuItemController;
import customerView.ViewMenuItem;
import customerView.ViewOrderedMenuItem;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.User;
import waiterController.ServeController;
import waiterView.ViewServe;

public class Home extends VBox {
	
	static Scene scene;

    public Home() {
        BorderPane layout = new BorderPane();

        // pesan selamat datang di tengah menuBar
        Label welcomeLabel = new Label("WELCOME TO MYSTIC GRILL\nSilahkan pesan makan yang Anda mau");
        welcomeLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        VBox welcomeBox = new VBox(welcomeLabel);
        welcomeBox.setAlignment(javafx.geometry.Pos.CENTER);
        welcomeBox.setPadding(new Insets(20));
        layout.setCenter(welcomeBox);
        
        this.getChildren().add(layout);
        setPage(this, "Home");
    }

    private static MenuBar createMenuBar() {
        // Untuk Tab Bar View
        MenuBar menuBar = new MenuBar();
        
        // Home
        Label homeLbl = new Label("Home");
        Menu menuHome = new Menu("", homeLbl);
        homeLbl.setOnMouseClicked(e -> {
        	moveToHome();
        });
        
        // ADMIN
        Label manageUserLbl = new Label("Manage User");
        Menu menuManageUser = new Menu("", manageUserLbl);
        manageUserLbl.setOnMouseClicked(e -> {
        	moveToManageUser();
        });
        
        Label manageMILbl = new Label("Manage MenuItem");
        Menu menuManageMI = new Menu("", manageMILbl);
        manageMILbl.setOnMouseClicked(e -> {
        	moveToManageMI();
        });
        
        // CASHIER
        Label viewOrderLbl = new Label("View Order");
        Menu menuViewOrder = new Menu("", viewOrderLbl);
        viewOrderLbl.setOnMouseClicked(e -> {
        	moveToViewOrder();
        });
        
        Label viewReceiptLbl = new Label("View Receipt");
        Menu menuViewReceipt = new Menu("", viewReceiptLbl);
        viewReceiptLbl.setOnMouseClicked(e -> {
        	moveToViewReceipt();
        });
        
        // CUSTOMER
        Label viewMILbl = new Label("View MenuItems");
        Menu menuViewMI = new Menu("", viewMILbl);
        viewMILbl.setOnMouseClicked(e -> {
        	moveToViewMI();
        });
        
        Label viewOrderedMILbl = new Label("View Ordered MenuItems");
        Menu menuViewOrderedMI = new Menu("", viewOrderedMILbl);
        viewOrderedMILbl.setOnMouseClicked(e -> {
        	moveToViewOrderedMI();
        });
        
        // CHEF
        Label viewPrepareLbl = new Label("View Pending Order");
        Menu menuViewPrepare = new Menu("", viewPrepareLbl);
        viewPrepareLbl.setOnMouseClicked(e -> {
        	moveToViewPrepare();
        });
        
        // WAITER
        Label viewServeLbl = new Label("View Prepared Order");
        Menu menuViewServe = new Menu("", viewServeLbl);
        viewServeLbl.setOnMouseClicked(e -> {
        	moveToViewServe();
        });
        
        // Buat Munculin Menu Bar dari Home
        String role = User.getCurrUser().getUserRole();
        if(role.equals("Admin")) {
//        	menuBar.getMenus().addAll(menuHome, menuManageUser, menuManageMI);
        }else if(role.equals("Cashier")){
        	menuBar.getMenus().addAll(menuHome, menuViewOrder, menuViewReceipt);
        }else if(role.equals("Customer")) {
        	menuBar.getMenus().addAll(menuHome, menuViewMI, menuViewOrderedMI);
        }else if(role.equals("Chef")) {
        	menuBar.getMenus().addAll(menuHome, menuViewPrepare);
        }else if(role.equals("Waiter")) {
        	menuBar.getMenus().addAll(menuHome, menuViewServe);
        }
        
        menuBar.getMenus().addAll(menuHome, menuManageUser, menuManageMI, menuViewOrder, menuViewReceipt, menuViewMI, 
        		menuViewOrderedMI, menuViewPrepare, menuViewServe);
        
        return menuBar;
    }
    
    private static void moveToHome() {
    	setPage(new Home(), "home");
    }
    
    private static void moveToManageUser() {
    	ViewUser vu;
    	setPage(vu = new ViewUser(), "User Management");
    	new ViewUserController(vu);
    }
    
    private static void moveToManageMI() {
    	ViewManageItem vmi;
    	setPage(vmi = new ViewManageItem(), "MenuItem Management");
    	new ManageItemController(vmi);
    }

    private static void moveToViewOrder() {
    	ViewOrder vo;
    	setPage(vo = new ViewOrder(), "View Order");
    	new ViewOrderController(vo);
    }
    
    private static void moveToViewReceipt() {
    	ViewReceipt vr;
    	setPage(vr = new ViewReceipt(), "View Receipt");
    	new ViewReceiptController(vr);
    }
    
    private static void moveToViewMI() {
    	ViewMenuItem vmi;
    	setPage(vmi = new ViewMenuItem(), "View Menu Items");
    	new MenuItemController(vmi);
    }
    
    private static void moveToViewOrderedMI() {
    	ViewOrderedMenuItem vomi;
    	setPage(vomi = new ViewOrderedMenuItem(), "View Ordered MenuItems");
    	new OrderedMenuItemController(vomi);
    }
    
    private static void moveToViewPrepare() {
    	ViewPrepare vp;
    	setPage(vp = new ViewPrepare(), "View Pending Orders");
    	new PrepareController(vp);
    }
    
    private static void moveToViewServe() {
    	ViewServe vs;
    	setPage(vs = new ViewServe(), "View Prepared Orders");
    	new ServeController(vs);
    }

    public static void setPage(javafx.scene.Node content, String pageTitle) {
        BorderPane layout = new BorderPane();

        MenuBar menuBar = createMenuBar();
        layout.setTop(menuBar);

        layout.setCenter(content);

        scene = new Scene(layout, 800, 600);
        Main.setSceneTo(scene);
    }
}
