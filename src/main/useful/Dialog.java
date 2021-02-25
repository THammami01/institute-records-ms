package main.useful;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;

public class Dialog {
	static Stage stage;
	static boolean btnYesClicked;

	public static boolean confirm(String title, String msg) {
//		stage.getIcons().add(new Image(Controller.iconURL));
		btnYesClicked = false;

		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setMinWidth(300);

		Label lbl = new Label();
		lbl.setText(msg);

		Button btnYes = new Button(Lang.getEquiv("Oui"));
		btnYes.setOnAction(e -> {
			stage.close();
			btnYesClicked = true;
		});

		Button btnNo = new Button(Lang.getEquiv("Non"));
		btnNo.setOnAction(e -> {
			stage.close();
			btnYesClicked = false;
		});

		HBox paneBtn = new HBox(8);
		paneBtn.getChildren().addAll(btnYes, btnNo);
		paneBtn.setAlignment(Pos.CENTER);

		VBox pane = new VBox(10);
		pane.setPadding(new Insets(10, 10, 10, 10));
		pane.getChildren().addAll(lbl, paneBtn);
		pane.setAlignment(Pos.CENTER);

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.showAndWait();

		return btnYesClicked;
	}

	public static void informDBConnErrAndQuit() {
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Connection Failed | Connexion échouée | لم يتم الإتّصال");
		stage.setWidth(430);

		Label lbl1 = new Label("Cannot Connect to Database.");
		Label lbl2 = new Label("La connexion à la base de données a échoué.");
		Label lbl3 = new Label("لم يتم الإتّصال بقاعدة البيانات.");

		Button btnQuit = new Button("Quit | Quitter | الخروج");
		btnQuit.setAlignment(Pos.CENTER);
		btnQuit.setOnAction(e -> {
			stage.close();
			Main.primaryStage.close();
		});

		VBox paneLbl = new VBox();
		paneLbl.getChildren().addAll(lbl1, lbl2, lbl3);
		paneLbl.setAlignment(Pos.CENTER);

		VBox pane = new VBox(10);
		pane.setPadding(new Insets(10, 10, 10, 10));
		pane.getChildren().addAll(paneLbl, btnQuit);
		pane.setAlignment(Pos.CENTER);

		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.showAndWait();
	}
}