package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.models.DB;

public class Main extends Application {
	public static Stage primaryStage;
	public static final String docsDir = "C:\\SGRN\\";

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
		Main.primaryStage = primaryStage;
		primaryStage.setTitle("SG de Relevés de Notes");
		primaryStage.getIcons().add(new Image("icon.png"));
		primaryStage.setScene(new Scene(root, 800, 600));
		primaryStage.setMinWidth(700);
		primaryStage.setMinHeight(600);
		primaryStage.show();

		primaryStage.setOnCloseRequest(e -> {
			DB.close();
			primaryStage.close();
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}