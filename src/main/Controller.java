package main;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import models.DB;
import models.Document;
import models.Etudiant;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;

// TODO: SHOW ERROR IF ENTERED CIN, ARCHIVE OR CONDITION INVALID OR CLASS NOT IN CLASSES LIST (LOWERCASE)

public class Controller implements Initializable {
	@FXML
	private VBox paneWelcome;
	@FXML
	private HBox paneMain;
	@FXML
	private VBox paneRechercher;
	@FXML
	private VBox paneAjouter;
	@FXML
	private VBox paneResultat;
	@FXML
	private Label lblEnactusNYear;

	@FXML
	private Label lblBienvenue01;
	@FXML
	private Button btnContinuer01;

	@FXML
	private Label btnRechercher02;
	@FXML
	private Label btnAjouter02;

	@FXML
	private TextField txtCIN03;
	@FXML
	private Label lblValider03;
	@FXML
	private Label lblRetourner03;
	@FXML
	private Label lblMsg03;

	@FXML
	private TextField txtCIN04;
	@FXML
	private TextField txtArchive04;
	@FXML
	private TextField txtNom04;
	@FXML
	private TextField txtPrenom04;
	@FXML
	private ComboBox<String> cbClasse04;
	@FXML
	private TextField txtCond04;
	@FXML
	private Label lblAjouter04;
	@FXML
	private Label lblRetourner04;
	@FXML
	private Label lblMsg04;

	@FXML
	private TextField txtCIN05;
	@FXML
	private TextField txtArchive05;
	@FXML
	private TextField txtNom05;
	@FXML
	private TextField txtPrenom05;
	@FXML
	private ComboBox<String> cbClasse05;
	@FXML
	private TextField txtCond05;
	@FXML
	private Label lblAjouterDoc05;
	@FXML
	private Label lblVoirTousDocs05;
	@FXML
	private TextArea txtDocs05;
	@FXML
	private Label lblModifier05;
	@FXML
	private Label lblSupprimer05;
	@FXML
	private Label lblRetourner05;
	@FXML
	private Label lblMsg05;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<String> classes = DB.getClasses();
		if (classes != null) {
//			cbClasse05.getItems().removeAll();
			cbClasse04.getItems().addAll(classes);
//			cbClasse05.getItems().removeAll();
			cbClasse05.getItems().addAll(classes);
		}

		lblEnactusNYear.setText("Enactus ISLAI Béja - " + Calendar.getInstance().get(Calendar.YEAR));

		lblBienvenue01.setText(getWelcomeMsg());

		btnContinuer01.setOnMouseClicked(e -> show(paneMain));

		btnRechercher02.setOnMouseClicked(e -> show(paneRechercher));

		btnAjouter02.setOnMouseClicked(e -> show(paneAjouter));

		lblRetourner03.setOnMouseClicked(e -> show(paneMain));

		lblRetourner04.setOnMouseClicked(e -> show(paneMain));

		lblRetourner05.setOnMouseClicked(e -> show(paneMain));

		// STARTING LOGIC
		lblValider03.setOnMouseClicked(e -> {
			Etudiant e1;

			try {
				e1 = DB.getEtudiant(Integer.parseInt(txtCIN03.getText()));
				if (e1 == null) {
					lblMsg03.setText("Aucun étudiant enregistré avec ce numéro de CIN.");
				} else {
					show(paneResultat);
					txtCIN05.setText(String.format("%08d", e1.getCin()));
					txtArchive05.setText(e1.getArchive());
					txtNom05.setText(e1.getNom());
					txtPrenom05.setText(e1.getPrenom());
					cbClasse05.setValue(e1.getClasse());
					txtCond05.setText(e1.getCond());
					txtDocs05.setText(getDocsString());
				}
			} catch (Exception e2) {
				lblMsg03.setText("Erreur lors de la recherche.");
			}
		});

		lblAjouter04.setOnMouseClicked(e -> {
			if (txtCIN04.getText().isEmpty() || txtArchive04.getText().isEmpty() ||
					txtNom04.getText().isEmpty() || txtPrenom04.getText().isEmpty() ||
					txtCond04.getText().isEmpty()
			) {
				lblMsg04.setText("Tous les champs doivent être remplis.");
			} else {
				try {
					Etudiant e1 = new Etudiant(Integer.parseInt(txtCIN04.getText()), txtArchive04.getText(),
							txtNom04.getText(), txtPrenom04.getText(),
							cbClasse04.getValue(), txtCond04.getText());
					if (DB.addEtudiant(e1)) {
						lblMsg04.setText("Ajouté avec succès.");
					} else {
						lblMsg04.setText("Error lors de l'ajout.");
					}
				} catch (Exception e2) {
					lblMsg04.setText("Error lors de l'ajout.");
				}
			}
		});

		lblAjouterDoc05.setOnMouseClicked(e -> {
			addDoc();
			txtDocs05.setText(getDocsString());
		});

		lblVoirTousDocs05.setOnMouseClicked(e -> openDocsDir());

		lblModifier05.setOnMouseClicked(e -> {
			if (lblModifier05.getText().equals("Modifier")) {
				txtArchive05.setEditable(true);
				txtNom05.setEditable(true);
				txtPrenom05.setEditable(true);
				cbClasse05.setEditable(true);
				txtCond05.setEditable(true);
				lblModifier05.setText("Enregistrer");

				lblAjouterDoc05.setDisable(false);
			} else {
				try {
					Etudiant e1 = new Etudiant(
							Integer.parseInt(txtCIN05.getText()),
							txtArchive05.getText(),
							txtNom05.getText(),
							txtPrenom05.getText(),
							cbClasse05.getValue(),
							txtCond05.getText()
					);

					if (DB.modifyEtudiant(e1)) {
						lblMsg05.setText("Modifié avec succès.");
					} else {
						lblMsg05.setText("Error lors de la modification.");
					}
				} catch (Exception e2) {
					lblMsg05.setText("Error lors de la modification.");
				}

				txtArchive05.setEditable(false);
				txtNom05.setEditable(false);
				txtPrenom05.setEditable(false);
				cbClasse05.setEditable(false);
				txtCond05.setEditable(false);
				lblAjouterDoc05.setDisable(true);
				lblModifier05.setText("Modifier");
			}
		});

		lblSupprimer05.setOnMouseClicked(e -> {
			try {
				if (DB.deleteEtudiant(Integer.parseInt(txtCIN05.getText())))
					lblMsg05.setText("Supprimé avec succès.");
				else
					lblMsg05.setText("Error lors de la suppression");
			} catch (Exception e1) {
				lblMsg05.setText("Error lors de la suppression");
			}
		});
	}

	private String getWelcomeMsg() {
		int h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		if (h > 3 && h < 12) return "Bonjour !";
		else if (h >= 12 && h < 18) return "Bon après-midi !";
		else return "Bonsoir !";
	}

	public void show(Pane pane) {
		paneWelcome.setVisible(false);
		paneMain.setVisible(false);
		paneRechercher.setVisible(false);
		paneAjouter.setVisible(false);
		paneResultat.setVisible(false);

		lblMsg03.setText("");
		lblMsg04.setText("");
		lblMsg05.setText("");

		txtArchive05.setEditable(false);
		txtNom05.setEditable(false);
		txtPrenom05.setEditable(false);
		cbClasse05.setEditable(false);
		txtCond05.setEditable(false);
		lblAjouterDoc05.setDisable(true);
		txtDocs05.setText("Aucun document.");
		lblModifier05.setText("Modifier");

		pane.setVisible(true);
	}

	// TODO: Add Doc Name to DB
	public void addDoc() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir Document");
		File file = fileChooser.showOpenDialog(Main.primaryStage);
		if (file == null)
			lblMsg05.setText("Erreur lors de l'importation du document.");
		else {
			String command = String.format("copy \"%s\" \"C:\\SGRN\\%s\"", file.getPath(), txtCIN05.getText());
			try {
				Runtime.getRuntime().exec("cmd /c " + command);
				Document doc = new Document(Integer.parseInt(txtCIN05.getText()), file.getName());
				if (DB.addDoc(doc))
					lblMsg05.setText("Document ajouté avec succès.");
				else
					lblMsg05.setText("Erreur lors de l'ajout du document.");
			} catch (Exception e) {
				lblMsg05.setText("Erreur lors de l'ajout du document.");
			}
		}
	}

	public void openDocsDir() {
		String command = "start C:\\SGRN\\" + txtCIN05.getText();
		try {
			Runtime.getRuntime().exec("cmd /c " + command);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getDocsString() {
		ArrayList<String> currDocs = DB.getDocs(Integer.parseInt(txtCIN05.getText()));
		if (currDocs == null || currDocs.size() == 0) return "Aucun document.";

		StringBuilder currDocsString = new StringBuilder();
		for (String d : currDocs)
			currDocsString.append(d).append("\n");
		return currDocsString.toString();
	}

//	public void openDocFile(String loc) {
//		String command = "start " + loc;
//		try {
//			Runtime.getRuntime().exec("cmd /c " + command);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}