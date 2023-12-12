package main;

import java.util.Stack;

import controller.ViewOrderController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application{

	private static Stage stage;
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
		
		ViewOrder vo = new ViewOrder(stage);
		ViewOrderController voc = new ViewOrderController(vo);
    }

	public static void changeScene(Scene newScene) {
		if (stage != null) {
			sceneStack.push(newScene);
            stage.setScene(newScene);
        }
	}
	
    public static void goToPreviousPage() {
        if (!sceneStack.isEmpty()) {
            sceneStack.pop(); // Buang scene saat ini
            if (!sceneStack.isEmpty()) {
                Scene previousScene = sceneStack.peek(); // Ambil scene sebelumnya
                stage.setScene(previousScene); // Tampilkan scene sebelumnya
            }
        }
    }

	public static Stack<Scene> getSceneStack() {
		return sceneStack;
	}
	
}
