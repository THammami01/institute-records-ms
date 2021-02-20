package main;

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

public class ConfirmationBox {
	static Stage stage;
	static boolean btnYesClicked;

	public static boolean show(String title, String msg) {
//		stage.getIcons().add(new Image(Controller.iconURL));
		btnYesClicked = false;

		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(title);
		stage.setMinWidth(250);

		Label lbl = new Label();
		lbl.setText(msg);

		Button btnYes = new Button(Lang.getEquiv("Oui"));
		btnYes.setOnAction(e -> btnYes_Clicked());

		Button btnNo = new Button(Lang.getEquiv("Non"));
		btnNo.setOnAction(e -> btnNo_Clicked());

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

	private static void btnYes_Clicked() {
		stage.close();
		btnYesClicked = true;
	}

	private static void btnNo_Clicked() {
		stage.close();
		btnYesClicked = false;
	}
}