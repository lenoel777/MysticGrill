package main;

import java.util.Stack;

import controller.ViewOrderController;
import javafx.application.Application;
import javafx.scene.Scene;
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
		stage.show();
		
		ViewOrder vo = new ViewOrder();
		ViewOrderController voc = new ViewOrderController(vo);
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
