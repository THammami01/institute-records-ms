package main;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import main.models.DB;
import main.models.Document;
import main.models.Etudiant;

import java.io.File;
import java.net.URL;
import java.util.*;

// TODO: ARABIC/ENGLISH LANGUAGE SUPPORT
// TODO: CUSTOM SHOW ALL (DO NOT USE WINDOWS EXPLORER)
// TODO: IN SETTINGS: CHANGE LANGUAGE, CHANGE docsDir LOCATION, BACKUP, EXPORT ALL DATA TO IT, ABOUT ME

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
	private Label lblSupprimerTousDocs05;

	@FXML
	private VBox allDocuments05;
	@FXML
	private Label lblModifier05;
	@FXML
	private Label lblSupprimer05;
	@FXML
	private Label lblRetourner05;
	@FXML
	private Label lblMsg05;

	@FXML
	private ImageView img1;
	@FXML
	private ImageView img2;
//	@FXML
//	private ImageView enactus;

	private static String img1URL;
	private static String img2URL;
//	public static String iconURL;
	ArrayList<String> classes;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initFields();

		btnContinuer01.setOnMouseClicked(e -> show(paneMain));

		btnRechercher02.setOnMouseClicked(e -> show(paneRechercher));

		btnAjouter02.setOnMouseClicked(e -> show(paneAjouter));

		lblRetourner03.setOnMouseClicked(e -> show(paneMain));

		lblRetourner04.setOnMouseClicked(e -> show(paneMain));

		lblRetourner05.setOnMouseClicked(e -> {
			if (lblModifier05.getText().equals("Modifier")) {
				show(paneRechercher);
			} else {
				Etudiant e1 = DB.getEtudiant(Integer.parseInt(txtCIN03.getText()));
				show(paneResultat);
				if (e1 != null) {
					txtCIN05.setText(String.format("%08d", e1.getCin()));
					txtArchive05.setText(e1.getArchive());
					txtNom05.setText(e1.getNom());
					txtPrenom05.setText(e1.getPrenom());
					cbClasse05.setValue(e1.getClasse());
					txtCond05.setText(e1.getCond());
				}
				lblModifier05.setText("Modifier");
				lblRetourner05.setText("Retourner");
				setDocs();
			}
		});

		// STARTING LOGIC
		lblValider03.setOnMouseClicked(e -> {
			Etudiant e1;

			if (txtCIN03.getText().isEmpty()) {
				lblMsg03.setText("Entrer un numéro de CIN d'abord.");
				return;
			}

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
					setDocs();
				}
			} catch (Exception e2) {
				lblMsg03.setText("Erreur lors de la recherche.");
			}
		});

		lblAjouter04.setOnMouseClicked(e -> {
			if (txtCIN04.getText().isEmpty() || txtArchive04.getText().isEmpty() || txtNom04.getText().isEmpty() ||
					txtPrenom04.getText().isEmpty() || txtCond04.getText().isEmpty()) {
				lblMsg04.setText("Tous les champs doivent être remplis.");
				return;
			}

			if (txtCIN04.getText().length() != 8) {
				lblMsg04.setText("Numéro de CIN invalide.");
				return;
			}

			int cin;
			try {
				cin = Integer.parseInt(txtCIN04.getText());
				if (cin < 0 || cin > 99999999) {
					lblMsg04.setText("Numéro de CIN invalide.");
					return;
				}
			} catch (Exception e1) {
				lblMsg04.setText("Numéro de CIN invalide.");
				return;
			}

			if (!classes.contains(cbClasse04.getValue().toUpperCase())) {
				lblMsg04.setText("La classe saisie n'existe pas.");
				return;
			}

			try {
				Etudiant e1 = new Etudiant(Integer.parseInt(txtCIN04.getText()), txtArchive04.getText(),
						txtNom04.getText(), txtPrenom04.getText(),
						cbClasse04.getValue().toUpperCase(), txtCond04.getText());
				if (DB.addEtudiant(e1)) {
					lblMsg04.setText("Ajouté avec succès.");
				} else {
					lblMsg04.setText("Erreur lors de l'ajout.");
				}
			} catch (Exception e2) {
				lblMsg04.setText("Erreur lors de l'ajout.");
			}
		});

		lblAjouterDoc05.setOnMouseClicked(e -> {
			lblMsg05.setText("");
			addDoc();
			setDocs();
		});

		lblVoirTousDocs05.setOnMouseClicked(e -> openDocsDir());

		lblSupprimerTousDocs05.setOnMouseClicked(e -> delAllDocs());

		lblModifier05.setOnMouseClicked(e -> {
			if (lblModifier05.getText().equals("Modifier")) {
				txtArchive05.setEditable(true);
				txtNom05.setEditable(true);
				txtPrenom05.setEditable(true);
//				cbClasse05.setEditable(true);
				txtCond05.setEditable(true);
				lblModifier05.setText("Enregistrer");
				lblRetourner05.setText("Annuler");
				lblMsg05.setText("");

//				lblAjouterDoc05.setDisable(false);
			} else {
				if (txtArchive05.getText().isEmpty() || txtNom05.getText().isEmpty() ||
						txtPrenom05.getText().isEmpty() || txtCond05.getText().isEmpty()) {
					lblMsg05.setText("Tous les champs doivent être remplis.");
					return;
				}

				if (!classes.contains(cbClasse05.getValue().toUpperCase())) {
					lblMsg05.setText("La classe saisie n'existe pas.");
					return;
				}

				try {
					Etudiant e1 = new Etudiant(
							Integer.parseInt(txtCIN05.getText()),
							txtArchive05.getText(),
							txtNom05.getText(),
							txtPrenom05.getText(),
							cbClasse05.getValue().toUpperCase(),
							txtCond05.getText()
					);

					if (DB.modifyEtudiant(e1)) {
						lblMsg05.setText("Modifié avec succès.");
					} else {
						lblMsg05.setText("Erreur lors de la modification.");
					}
				} catch (Exception e2) {
					lblMsg05.setText("Erreur lors de la modification.");
				}

				txtArchive05.setEditable(false);
				txtNom05.setEditable(false);
				txtPrenom05.setEditable(false);
//				cbClasse05.setEditable(false);
				txtCond05.setEditable(false);
//				lblAjouterDoc05.setDisable(true);
				lblModifier05.setText("Modifier");
				lblRetourner05.setText("Retourner");
			}
		});

		lblSupprimer05.setOnMouseClicked(e -> {
			lblMsg05.setText("");
			if (!ConfirmationBox.show("Supprimer Étudiant", "Voulez-vous vraiment supprimer cet étudiant ?")) {
				return;
			}

			try {
				if (DB.deleteEtudiant(Integer.parseInt(txtCIN05.getText()))) {
					lblMsg03.setText("Supprimé avec succès.");
					show(paneRechercher);
				} else lblMsg05.setText("Erreur lors de la suppression");
			} catch (Exception e1) {
				lblMsg05.setText("Erreur lors de la suppression");
			}
		});
	}

	private void initFields() {
		img1URL = img1.getImage().getUrl();
		img2URL = img2.getImage().getUrl();
//		iconURL = enactus.getImage().getUrl();

		classes = DB.getClasses();
		if (classes != null) {
//			cbClasse05.getItems().clear();
			cbClasse04.getItems().addAll(classes);
//			cbClasse05.getItems().removeAll();
			cbClasse05.getItems().addAll(classes);
		}

		lblEnactusNYear.setText("Enactus ISLAI Béja - " + Calendar.getInstance().get(Calendar.YEAR));

		lblBienvenue01.setText(getWelcomeMsg());
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
//		cbClasse05.setEditable(false);
		txtCond05.setEditable(false);
//		lblAjouterDoc05.setDisable(true);
		initializeDocs();
		lblModifier05.setText("Modifier");

		pane.setVisible(true);
	}

	public void addDoc() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choisir Document");
		List<File> files = fileChooser.showOpenMultipleDialog(Main.primaryStage);

		if (files == null || files.size() == 0)
			lblMsg05.setText("Erreur lors de l'importation des documents.");
		else {
			for (File file : files) {
				Document doc = new Document(Integer.parseInt(txtCIN05.getText()), file.getName());
				if (!DB.addDoc(doc)) {
					lblMsg05.setText("Erreur lors de l'ajout des documents.");
					return;
				}
			}

			for (File file : files) {
				String command = String.format("copy \"%s\" \"%s%s\"", file.getPath(), Main.docsDir, txtCIN05.getText());
				try {
					Runtime.getRuntime().exec("cmd /c " + command);
				} catch (Exception e) {
					lblMsg05.setText("Erreur lors de l'ajout des documents.");
					return;
				}
			}

			String wordEnd = files.size() == 0 || files.size() == 1 ? "" : "s";
			lblMsg05.setText(files.size() + " document" + wordEnd + " ajouté" + wordEnd + " avec succès.");
		}
	}

	public void openDocsDir() {
		lblMsg05.setText("");
		String command = String.format("start %s\"%s\"", Main.docsDir, txtCIN05.getText());
		try {
			Runtime.getRuntime().exec("cmd /c " + command);
			lblMsg05.setText("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initializeDocs() {
		allDocuments05.getChildren().clear();
		allDocuments05.getChildren().add(new DocumentHBox("Aucun document."));
		lblSupprimerTousDocs05.setDisable(true);
	}

	public void setDocs() {
		ArrayList<String> currDocs;
		try {
			currDocs = DB.getDocs(Integer.parseInt(txtCIN05.getText()));
		} catch (Exception e) {
			return;
		}

		allDocuments05.getChildren().clear();

		if (currDocs == null || currDocs.size() == 0) {
			lblSupprimerTousDocs05.setDisable(true);
			allDocuments05.getChildren().add(new DocumentHBox("Aucun document."));
		} else {
			for (String d : currDocs) {
				lblSupprimerTousDocs05.setDisable(false);
				allDocuments05.getChildren().add(new DocumentHBox(new Document(Integer.parseInt(txtCIN05.getText()), d)));
			}
		}
	}

	public void delAllDocs() {
		lblMsg05.setText("");
		if (!ConfirmationBox.show("Supprimer Tous Documents", "Voulez-vous vraiment supprimer tous les documents de cet étudiant ?"))
			return;

		if (DB.delDocs(Integer.parseInt(txtCIN05.getText()))) {
			lblMsg05.setText("Tous les documents sont supprimés avec succès.");
			setDocs();
		} else {
			lblMsg05.setText("Erreur lors de la suppression de tous les documents.");
		}
	}

	private class DocumentHBox extends HBox {
		public DocumentHBox(String nomDoc) {
			Label singleDocLabel = new Label(nomDoc);
			singleDocLabel.getStyleClass().add("singleDocLabel");

			setPrefWidth(USE_COMPUTED_SIZE);
			setMaxWidth(USE_PREF_SIZE);
			singleDocLabel.getStyleClass().add("singleDocHBox");

			setPrefHeight(25);
			getChildren().addAll(singleDocLabel);
		}

		public DocumentHBox(Document doc) {
			this(doc.getNomDoc());

			ImageView viewSingleDocImg = new ImageView(img1URL);
			viewSingleDocImg.setFitWidth(25);
			viewSingleDocImg.setFitHeight(25);
			viewSingleDocImg.getStyleClass().add("viewSingleDocImg");
			viewSingleDocImg.setOnMouseClicked(e -> openDocFile(doc));

			ImageView delSingleDocImg = new ImageView(img2URL);
			delSingleDocImg.setFitWidth(25);
			delSingleDocImg.setFitHeight(25);
			delSingleDocImg.getStyleClass().add("delSingleDocImg");
			delSingleDocImg.setOnMouseClicked(e -> delDocFile(doc));

			getChildren().addAll(viewSingleDocImg, delSingleDocImg);
		}

		public void openDocFile(Document doc) {
			lblMsg05.setText("");
			String command = String.format("start %s\"%08d\"\\\"%s\"", Main.docsDir, doc.getCinDoc(), doc.getNomDoc());
			try {
				Runtime.getRuntime().exec("cmd /c " + command);
				lblMsg05.setText("");
			} catch (Exception e) {
				lblMsg05.setText("Erreur lors de l'ouverture du document.");
				e.printStackTrace();
			}
			setDocs();
		}

		public void delDocFile(Document doc) {
			lblMsg05.setText("");
			if (!ConfirmationBox.show("Supprimer Document", "Voulez-vous vraiment supprimer cet document ?")) {
				return;
			}

			String command = String.format("del \"%s%08d\\%s\"", Main.docsDir, doc.getCinDoc(), doc.getNomDoc());
			try {
				Runtime.getRuntime().exec("cmd /c " + command);
				if (DB.delDoc(doc)) lblMsg05.setText("1 document supprimé avec succès.");
				else lblMsg05.setText("Erreur lors de la suppression du document.");
			} catch (Exception e) {
				lblMsg05.setText("Erreur lors de la suppression du document.");
				e.printStackTrace();
			}
			setDocs();
		}
	}
}