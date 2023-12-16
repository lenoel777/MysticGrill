package main;

import java.util.Stack;

import controller.Controller_ManageItem;
import controller.Controller_PendingOrder;
import controller.ViewOrderController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import view.View_ManageItem;
import view.View_Prepare;
import javafx.scene.layout.VBox;

public class Main extends Application{

	private static Stage stage;
	private static VBox root;
	private static Stack<Scene> sceneStack = new Stack<>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("Mystic Grill");
		
		
//		ViewOrder vo = new ViewOrder();
//		root.getChildren().add(vo);
		
//		Register register = new Register();
//		register.start(primaryStage);
		
//		Login login = new Login(Connect.getConnection());
//		login.start(primaryStage);

//        Scene scene = new Scene(root, 400, 200);
//        primaryStage.setScene(scene);
//        primaryStage.show();
		
		stage.show();
		
		View_Prepare vp = new View_Prepare();
		Controller_PendingOrder vpc = new Controller_PendingOrder(vp);
    }
	
	public static void nextScene(Scene newScene) {
		sceneStack.push(newScene);
        stage.setScene(newScene);
	}
	
	//dengan load lagi
	public static void previousScene(Scene newScene) {
		sceneStack.pop();
		sceneStack.pop();
		sceneStack.push(newScene);
		stage.setScene(newScene);
	}
	
    public static void goToPreviousPage() {
    	//tanpa load lagi
        if (!sceneStack.isEmpty()) {
            sceneStack.pop(); // Buang scene saat ini
            if (!sceneStack.isEmpty()) {
                stage.setScene(sceneStack.peek()); // Tampilkan scene sebelumnya
            }
        }
    }

	public static Stack<Scene> getSceneStack() {
		return sceneStack;
	}

	
	
}
