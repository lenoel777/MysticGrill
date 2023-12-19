package main;

import java.util.Stack;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class Main extends Application{

	private static Stage stage;
	public static Stack<VBox> layoutStack = new Stack<>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("Mystic Grill");
		stage.show();
		
		Login login = new Login();
		new LoginController(login);
    }
	
	public static void setSceneTo(Scene newScene) {
        stage.setScene(newScene);
	}
	
//	public static void setPageTo(VBox newPage) {
//		Scene newScene = new Scene(newPage, 800, 600);
//		setSceneTo(newScene);
//	}
	
}
