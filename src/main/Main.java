package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.DB;

import java.io.File;

public class Main extends Application {
	public static Stage primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
		Main.primaryStage = primaryStage;
		primaryStage.setTitle("SG de Relevés de Notes");
		primaryStage.setScene(new Scene(root, 800, 600));
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