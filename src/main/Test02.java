//package main;
//
//import javafx.application.Application;
//import javafx.geometry.Insets;
//import javafx.scene.Scene;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.layout.StackPane;
//import javafx.scene.layout.TilePane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.stage.Stage;
//
//public class Test02 extends Application {
//	public static void main(String[] args) {
//		launch(args);
//	}
//
//	@Override
//	public void start(Stage primaryStage) {
////		TilePane tile1 = new TilePane();
////		tile1.setHgap(10);
////		tile1.setVgap(10);
////		tile1.setPrefColumns(2);
////		tile1.setPadding(new Insets(10, 10, 10, 10));
////		for (int i = 1; i < 13; i++)
////		{
////			Rectangle r = new Rectangle(100, 100);
////			r.setFill(Color.LIGHTGRAY);
////			Label l = new Label("A-" + i);
////			StackPane s = new StackPane(r, l);
////			tile1.getChildren().add(s);
////		}
//
//		ScrollPane spane = new ScrollPane();
//		spane.setMinWidth(250);
//		spane.setPrefWidth(250);
//		spane.setMaxWidth(250);
//		spane.setPrefHeight(400);
//		spane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//		StackPane stack = new StackPane(spane);
//		StackPane.setMargin(spane, new Insets(40, 40, 40, 40));
//		Scene scene = new Scene(stack);
//		primaryStage.setScene(scene);
//		primaryStage.setTitle("Scrolled Tiles");
//		primaryStage.show();
//	}
//}