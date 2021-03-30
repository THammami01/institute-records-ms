package main;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import main.models.DB;
import main.models.Document;
import main.models.Etudiant;
import main.models.Setting;
import main.useful.Dialog;
import main.useful.Lang;

import java.io.File;
import java.net.URL;
import java.util.*;

// TODO: CUSTOM SHOW ALL (DO NOT USE WINDOWS EXPLORER)
// TODO: IN SETTINGS: ACTIVATE/DEACTIVATE DELETE, BACKUP, EXPORT ALL DATA, ABOUT ME

public class Controller implements Initializable {
	@FXML
	private Label lblInst01;
	@FXML
	private Label lblMinis01;
	@FXML
	private Label lblUniv01;
	@FXML
	private Label lblSG01;
	@FXML
	private Label lblCIN03;
	@FXML
	private HBox firstRow04;
	@FXML
	private HBox secondRow04;
	@FXML
	private HBox thirdRow04;
	@FXML
	private Label lblCIN04;
	@FXML
	private Label lblArchive04;
	@FXML
	private Label lblNom04;
	@FXML
	private Label lblPrenom04;
	@FXML
	private Label lblClasse04;
	@FXML
	private Label lblCond04;
	@FXML
	private HBox firstRow05;
	@FXML
	private HBox secondRow05;
	@FXML
	private VBox thirdRow05;
	@FXML
	private HBox fourthRow05;
	@FXML
	private Label lblCIN05;
	@FXML
	private Label lblArchive05;
	@FXML
	private Label lblNom05;
	@FXML
	private Label lblPrenom05;
	@FXML
	private Label lblClasse05;
	@FXML
	private Label lblCond05;
	@FXML
	private Label lblDocuments05;
	@FXML
	private Label lblAucunDoc05;
	@FXML
	private Label lblLangue06;

	// PANE 04 & 05 | BOURSIER
	@FXML
	private Label lblBoursier04;
	@FXML
	private RadioButton rbBoursierOui04;
	@FXML
	private RadioButton rbBoursierNon04;
	@FXML
	private Label lblBoursier05;
	@FXML
	private RadioButton rbBoursierOui05;
	@FXML
	private RadioButton rbBoursierNon05;

	private enum Boursier {
		OUI, NON, NONE
	}

	// PANE 07
	@FXML
	private Label lblClasse07;
	@FXML
	private Label lblSelClasse07;
	@FXML
	private VBox allEtudiants07;
	@FXML
	private HBox firstRow07;
	@FXML
	private ScrollPane secondRow07;
	@FXML
	private HBox thirdRow07;
	@FXML
	private Label lblSupprimer07;
	@FXML
	private Label lblMsg07;
	@FXML
	private ComboBox cbClasse07;
	@FXML
	private Label lblRetourner07;

	@FXML
	private VBox vboxCIN04;
	@FXML
	private VBox vboxNom04;
	@FXML
	private VBox vboxPrenom04;
	@FXML
	private VBox vboxClasse04;
	@FXML
	private VBox vboxCond04;
	@FXML
	private VBox vboxCIN05;
	@FXML
	private VBox vboxNom05;
	@FXML
	private VBox vboxPrenom05;
	@FXML
	private VBox vboxClasse05;
	@FXML
	private VBox vboxCond05;
	@FXML
	private HBox innerHBoxDocs05;

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
	private VBox paneSettings;
	@FXML
	private VBox paneClasses;
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
	private Label lblAfficherClasses03;

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
	private ComboBox<String> cbCond04;
	@FXML
	private Label lblAjouter04;
	@FXML
	private Label lblRetourner04;
	@FXML
	private Label lblMsg04;

	@FXML
	private HBox fourthRow04;
	@FXML
	private VBox vboxAC04;
	@FXML
	private Label lblNomClasse04;
	@FXML
	private TextField txtNomClasse04;
	@FXML
	private Label lblAjouterClasse04;
	@FXML
	private Label lblMsgAC04;

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
	private ComboBox<String> cbCond05;
	@FXML
	private Label lblAjouterDoc05;
//	@FXML
//	private Label lblVoirTousDocs05;
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
	private ImageView imgSettings01;
	@FXML
	private RadioButton rbArabic06;
	@FXML
	private RadioButton rbFrench06;
	@FXML
	private RadioButton rbEnglish06;
	@FXML
	private Label lblEnregistrer06;
	@FXML
	private Label lblRetourner06;
	@FXML
	private Label lblMsg06;

	@FXML
	private ImageView img1;
	@FXML
	private ImageView img2;

	private boolean isFileChooserBusy;

	EventHandler modifier05EventHandler = (EventHandler<KeyEvent>) e -> {
		if (isFileChooserBusy) {
			isFileChooserBusy = false;
			return;
		}

		if (e.getCode() == KeyCode.ENTER && !(e.getSource() instanceof ComboBox))
			editStudent();
//			else if (e.getCode() == KeyCode.DELETE)
//				deleteStudent();
		else if (e.getCode() == KeyCode.ESCAPE)
			return05();
	};

//	@FXML
//	private ImageView enactus;

	private static String img1URL;
	private static String img2URL;
	//	public static String iconURL;
	ArrayList<String> classes;
	public static String lang;
	public Pane lastPane;
	public Pane currPane;
	public Pane correctCurrPane;
	public boolean isLastClassesPane = false;

	private String lastLang;
	private String currCond04;
	private String currCond05;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initLang();
		setUpLang();
		initThings();
		setOnToUpdateMsgs();

		btnContinuer01.setOnMouseClicked(e -> proceedToMain());

		btnRechercher02.setOnMouseClicked(e -> show(paneRechercher));

		btnAjouter02.setOnMouseClicked(e -> show(paneAjouter));

		lblRetourner03.setOnMouseClicked(e -> return03());

		lblRetourner04.setOnMouseClicked(e -> return04());

		lblRetourner05.setOnMouseClicked(e -> return05());

		// STARTING LOGIC
		lblValider03.setOnMouseClicked(e -> search());

		lblAjouter04.setOnMouseClicked(e -> addStudent());

		lblAjouterDoc05.setOnMouseClicked(e -> {
			lblMsg05.setText("");
			addDoc();
			setDocs();
		});

//		lblVoirTousDocs05.setOnMouseClicked(e -> openDocsDir());

		lblSupprimerTousDocs05.setOnMouseClicked(e -> delAllDocs());

		lblModifier05.setOnMouseClicked(e -> editStudent());

		lblSupprimer05.setOnMouseClicked(e -> deleteStudent());

		imgSettings01.setOnMouseClicked(e -> {
			if (currPane == paneSettings)
				return06();
			else {
				lastPane = currPane;
				show(paneSettings);
			}
		});

		lblEnregistrer06.setOnMouseClicked(e -> saveSettings());

		lblRetourner06.setOnMouseClicked(e -> return06());

		lblRetourner07.setOnMouseClicked(e -> return07());

		lblAfficherClasses03.setOnMouseClicked(e -> show(paneClasses));

		cbClasse07.setOnAction(e -> {
			lblMsg07.setText("");
			setStudentsByClass();
		});

		lblSupprimer07.setOnMouseClicked(e -> deleteClass());

		lblAjouterClasse04.setOnMouseClicked(e -> addClass());

		btnContinuer01.setOnKeyReleased(e -> {
			if (e.getCode() == KeyCode.ENTER)
				proceedToMain();
		});

		txtCIN03.setOnKeyReleased((e) -> {
			if (e.getCode() == KeyCode.ENTER)
				search();
			else if (e.getCode() == KeyCode.ESCAPE)
				return03();
		});

		txtCIN04.setOnKeyReleased((e) -> {
			if (e.getCode() == KeyCode.ENTER)
				addStudent();
			else if (e.getCode() == KeyCode.ESCAPE)
				return04();
		});
		txtArchive04.setOnKeyReleased((e) -> {
			if (e.getCode() == KeyCode.ENTER)
				addStudent();
			else if (e.getCode() == KeyCode.ESCAPE)
				return04();
		});
		txtNom04.setOnKeyReleased((e) -> {
			if (e.getCode() == KeyCode.ENTER)
				addStudent();
			else if (e.getCode() == KeyCode.ESCAPE)
				return04();
		});
		txtPrenom04.setOnKeyReleased((e) -> {
			if (e.getCode() == KeyCode.ENTER)
				addStudent();
			else if (e.getCode() == KeyCode.ESCAPE)
				return04();
		});
		cbClasse04.setOnKeyReleased((e) -> {
//			if (e.getCode() == KeyCode.ENTER)
//				addStudent();
//			else
			if (e.getCode() == KeyCode.ESCAPE)
				return04();
		});
		cbCond04.setOnKeyReleased((e) -> {
//			if (e.getCode() == KeyCode.ENTER)
//				addStudent();
//			else
			if (e.getCode() == KeyCode.ESCAPE)
				return04();
		});
		txtNomClasse04.setOnKeyReleased((e) -> {
			if (e.getCode() == KeyCode.ENTER)
				addClass();
			else if (e.getCode() == KeyCode.ESCAPE)
				return04();
		});

		txtCIN05.setOnKeyReleased(modifier05EventHandler);
		txtArchive05.setOnKeyReleased(modifier05EventHandler);
		txtNom05.setOnKeyReleased(modifier05EventHandler);
		txtPrenom05.setOnKeyReleased(modifier05EventHandler);
		cbClasse05.setOnKeyReleased(modifier05EventHandler);
		cbCond05.setOnKeyReleased(modifier05EventHandler);

		rbArabic06.setOnKeyReleased((e) -> {
			if (e.getCode() == KeyCode.ENTER)
				saveSettings();
			else if (e.getCode() == KeyCode.ESCAPE)
				return06();
		});
		rbFrench06.setOnKeyReleased((e) -> {
			if (e.getCode() == KeyCode.ENTER)
				saveSettings();
			else if (e.getCode() == KeyCode.ESCAPE)
				return06();
		});
		rbEnglish06.setOnKeyReleased((e) -> {
			if (e.getCode() == KeyCode.ENTER)
				saveSettings();
			else if (e.getCode() == KeyCode.ESCAPE)
				return06();
		});

		cbClasse07.setOnKeyReleased((e) -> {
//			if (e.getCode() == KeyCode.DELETE)
//				deleteClass();
//			else
			if (e.getCode() == KeyCode.ESCAPE)
				return07();
		});
		secondRow07.setOnKeyReleased((e) -> {
//			if (e.getCode() == KeyCode.DELETE)
//				deleteClass();
//			else
			if (e.getCode() == KeyCode.ESCAPE)
				return07();
		});
	}

	private void initThings() {
		lastPane = paneWelcome;
		currPane = paneWelcome;

		img1URL = img1.getImage().getUrl();
		img2URL = img2.getImage().getUrl();
//		iconURL = enactus.getImage().getUrl();

		setClasses();

		lblEnactusNYear.setText("ISLAI Béja - " + Calendar.getInstance().get(Calendar.YEAR));

		lblBienvenue01.setText(getWelcomeMsg());

		ToggleGroup boursier04 = new ToggleGroup();
		rbBoursierOui04.setToggleGroup(boursier04);
		rbBoursierNon04.setToggleGroup(boursier04);

		rbBoursierOui04.setSelected(false);
		rbBoursierNon04.setSelected(false);

		ToggleGroup boursier05 = new ToggleGroup();
		rbBoursierOui05.setToggleGroup(boursier05);
		rbBoursierNon05.setToggleGroup(boursier05);

		// CONDITIONS
		ArrayList<String> conditions = getConditions();
		cbCond04.getItems().clear();
		cbCond04.getItems().addAll(conditions);
		cbCond05.getItems().clear();
		cbCond05.getItems().addAll(conditions);

		ToggleGroup language = new ToggleGroup();
		rbArabic06.setToggleGroup(language);
		rbFrench06.setToggleGroup(language);
		rbEnglish06.setToggleGroup(language);
	}

	private String getWelcomeMsg() {
		int h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		if (h > 3 && h < 12) return Lang.getEquiv("Bonjour !");
		else if (h >= 12 && h < 18) return Lang.getEquiv("Bon après-midi !");
		else return Lang.getEquiv("Bonsoir !");
	}

	public void show(Pane pane) {
		currPane = pane;

		paneWelcome.setVisible(false);
		paneMain.setVisible(false);
		paneRechercher.setVisible(false);
		paneAjouter.setVisible(false);
		paneResultat.setVisible(false);
		paneSettings.setVisible(false);
		paneClasses.setVisible(false);

		lblMsg03.setText("");
		lblMsg04.setText("");
		lblMsgAC04.setText("");
		lblMsg05.setText("");
		lblMsg06.setText("");
		lblMsg07.setText("");

		txtArchive05.setEditable(false);
		txtNom05.setEditable(false);
		txtPrenom05.setEditable(false);
//		cbClasse05.setEditable(false);
//		txtCond05.setEditable(false);
//		lblAjouterDoc05.setDisable(true);
		initDocs();
		lblModifier05.setText(Lang.getEquiv("Modifier"));
		disableInputs05(true);

		if (pane == paneSettings)
			initLang();
		if (pane == paneClasses && cbClasse07.getValue() != null)
			setStudentsByClass();
		if (pane != paneSettings && pane != paneResultat)
			isLastClassesPane = false;

		requestFocus(txtCIN04);
		requestFocus(txtCIN05);

		isFileChooserBusy = false;

		pane.setVisible(true);
		if (pane != paneSettings)
			correctCurrPane = pane;
	}

	public void addDoc() {
//		txtCIN05.removeEventHandler(KeyEvent.KEY_RELEASED, pane05EventHandler);
//		txtArchive05.removeEventHandler(KeyEvent.KEY_RELEASED, pane05EventHandler);
//		txtNom05.removeEventHandler(KeyEvent.KEY_RELEASED, pane05EventHandler);
//		txtPrenom05.removeEventHandler(KeyEvent.KEY_RELEASED, pane05EventHandler);
//		cbClasse05.removeEventHandler(KeyEvent.KEY_RELEASED, pane05EventHandler);
//		txtCond05.removeEventHandler(KeyEvent.KEY_RELEASED, pane05EventHandler);

		isFileChooserBusy = true;
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(Lang.getEquiv("Choisir Document"));
		List<File> files = fileChooser.showOpenMultipleDialog(Main.primaryStage);

		if (files == null || files.size() == 0)
			lblMsg05.setText(Lang.getEquiv("Aucun fichier selectionné."));
		else {
			for (File file : files) {
				Document doc = new Document(Integer.parseInt(txtCIN05.getText()), file.getName());
				if (!DB.addDoc(doc)) {
					lblMsg05.setText(Lang.getEquiv("Erreur lors de l'ajout des documents."));
					return;
				}
			}

			for (File file : files) {
				String command = String.format("copy \"%s\" \"%s%s\"", file.getPath(), Main.docsDir, txtCIN05.getText());
				try {
					Runtime.getRuntime().exec("cmd /c " + command);
				} catch (Exception e) {
					lblMsg05.setText(Lang.getEquiv("Erreur lors de l'ajout des documents."));
					return;
				}
			}

			String wordEnd = files.size() == 0 || files.size() == 1 ? "" : "s";
			lblMsg05.setText(files.size() + Lang.getEquiv(" document" + wordEnd + " ajouté" + wordEnd + " avec succès."));
		}

//		txtCIN05.setOnKeyReleased(pane05EventHandler);
//		txtArchive05.setOnKeyReleased(pane05EventHandler);
//		txtNom05.setOnKeyReleased(pane05EventHandler);
//		txtPrenom05.setOnKeyReleased(pane05EventHandler);
//		cbClasse05.setOnKeyReleased(pane05EventHandler);
//		txtCond05.setOnKeyReleased(pane05EventHandler);
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

	public void initDocs() {
		allDocuments05.getChildren().clear();
		allDocuments05.getChildren().add(new DocumentHBox(Lang.getEquiv("Aucun document.")));
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
			allDocuments05.getChildren().add(new DocumentHBox(Lang.getEquiv("Aucun document.")));
		} else {
			for (String d : currDocs) {
				lblSupprimerTousDocs05.setDisable(false);
				allDocuments05.getChildren().add(new DocumentHBox(new Document(Integer.parseInt(txtCIN05.getText()), d)));
			}
		}
	}

	public void delAllDocs() {
		lblMsg05.setText("");
		if (!main.useful.Dialog.confirm(Lang.getEquiv("Supprimer Tous Documents"), Lang.getEquiv("Voulez-vous vraiment supprimer tous les documents de cet étudiant ?")))
			return;

		if (DB.delDocs(Integer.parseInt(txtCIN05.getText()))) {
			lblMsg05.setText(Lang.getEquiv("Tous les documents sont supprimés avec succès."));
			setDocs();
		} else {
			lblMsg05.setText(Lang.getEquiv("Erreur lors de la suppression de tous les documents."));
		}
	}

	public void setOnToUpdateMsgs() {
		txtCIN03.setOnKeyTyped(e -> lblMsg03.setText(""));
		txtCIN04.setOnKeyTyped(e -> lblMsg04.setText(""));
		txtArchive04.setOnKeyTyped(e -> {
			lblMsg04.setText("");
			lblMsgAC04.setText("");
		});
		txtNom04.setOnKeyTyped(e -> {
			lblMsg04.setText("");
			lblMsgAC04.setText("");
		});
		txtPrenom04.setOnKeyTyped(e -> {
			lblMsg04.setText("");
			lblMsgAC04.setText("");
		});
		cbClasse04.setOnAction(e -> {
			lblMsg04.setText("");
			lblMsgAC04.setText("");
		});
		cbCond04.setOnAction(e -> {
			lblMsg04.setText("");
			lblMsgAC04.setText("");
		});
		rbBoursierOui04.setOnAction(e -> {
			lblMsg04.setText("");
			lblMsgAC04.setText("");
		});
		rbBoursierNon04.setOnAction(e -> {
			lblMsg04.setText("");
			lblMsgAC04.setText("");
		});
		txtNomClasse04.setOnKeyTyped(e -> {
			lblMsg04.setText("");
			lblMsgAC04.setText("");
		});
		txtCIN05.setOnKeyTyped(e -> lblMsg05.setText(""));
		txtArchive05.setOnKeyTyped(e -> lblMsg05.setText(""));
		txtNom05.setOnKeyTyped(e -> lblMsg05.setText(""));
		txtPrenom05.setOnKeyTyped(e -> lblMsg05.setText(""));
		cbClasse05.setOnAction(e -> lblMsg05.setText(""));
		cbCond05.setOnAction(e -> lblMsg05.setText(""));
		rbBoursierOui05.setOnAction(e -> lblMsg05.setText(""));
		rbBoursierNon05.setOnAction(e -> lblMsg05.setText(""));
		rbArabic06.setOnMouseClicked(e -> lblMsg06.setText(""));
		rbFrench06.setOnMouseClicked(e -> lblMsg06.setText(""));
		rbEnglish06.setOnMouseClicked(e -> lblMsg06.setText(""));
	}

	public void initLang() {
		Setting langSetting = DB.getSetting("language");
		if (langSetting == null) {
			rbFrench06.setSelected(true);
			lang = "french";
		} else {
			switch (langSetting.getValue()) {
				case "arabic":
					rbArabic06.setSelected(true);
					lang = "arabic";
					break;
				case "french":
					rbFrench06.setSelected(true);
					lang = "french";
					break;
				case "english":
					rbEnglish06.setSelected(true);
					lang = "english";
					break;
			}
		}
	}

	public void setUpLang() {
		Main.primaryStage.setTitle(Lang.getEquiv("SG des Relevés de Notes"));
		setDocs();
		setStudentsByClass();

//		DocumentHBox etudiantsHBox = (DocumentHBox) allDocuments05.getChildren().get(0);
//		Label lbl = (Label) dhbox.getChildren().get(0);
//		lbl.setAlignment(Pos.TOP_RIGHT);

		if (lang.equals("arabic")) {
			firstRow04.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
			secondRow04.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
			firstRow05.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
			secondRow05.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
			firstRow07.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

//			firstRow04.setAlignment(Pos.TOP_RIGHT);
			secondRow04.setAlignment(Pos.TOP_RIGHT);
			thirdRow04.setAlignment(Pos.BOTTOM_RIGHT);
//			firstRow05.setAlignment(Pos.TOP_RIGHT);
			secondRow05.setAlignment(Pos.TOP_RIGHT);
			thirdRow05.setAlignment(Pos.TOP_RIGHT);
			fourthRow05.setAlignment(Pos.BOTTOM_RIGHT);

//			lblCIN04.setAlignment(Pos.TOP_RIGHT);
//			lblArchive04.setAlignment(Pos.TOP_RIGHT);
//			lblNom04.setAlignment(Pos.TOP_RIGHT);
//			lblPrenom04.setAlignment(Pos.TOP_RIGHT);
//			lblClasse04.setAlignment(Pos.TOP_RIGHT);
//			lblCond04.setAlignment(Pos.TOP_RIGHT);
//			lblCIN05.setAlignment(Pos.TOP_RIGHT);
//			lblArchive05.setAlignment(Pos.TOP_RIGHT);
//			lblNom05.setAlignment(Pos.TOP_RIGHT);
//			lblPrenom05.setAlignment(Pos.TOP_RIGHT);
//			lblClasse05.setAlignment(Pos.TOP_RIGHT);
//			lblCond05.setAlignment(Pos.TOP_RIGHT);

			btnRechercher02.toFront();

			lblValider03.toFront();

			lblLangue06.toFront();
			lblEnregistrer06.toFront();

//			vboxCIN04.toFront();
//			vboxCIN05.toFront();

//			vboxClasse04.toFront();
//			vboxPrenom04.toFront();
//			vboxNom04.toFront();

//			vboxClasse05.toFront();
//			vboxPrenom05.toFront();
//			vboxNom05.toFront();

			lblRetourner04.toFront();
			lblAjouter04.toFront();

			lblRetourner05.toFront();
			lblSupprimer05.toFront();
			lblModifier05.toFront();

			// PANE 07
			lblRetourner07.toFront();
			lblSupprimer07.toFront();

//			lblVoirTousDocs05.toFront();
			lblAjouterDoc05.toFront();
			lblDocuments05.toFront();
			lblDocuments05.setPadding(new Insets(0, 0, 0, 15));

//			for (Node d : allDocuments05.getChildren()) {
//				Label lbl = (Label) ((HBox) d).getChildren().get(0);
//				lbl.setAlignment(Pos.TOP_RIGHT);
//				System.out.println(lbl.getText());
//			}

//			txtCIN04.setAlignment(Pos.TOP_RIGHT);
//			txtArchive04.setAlignment(Pos.TOP_RIGHT);
//			txtNom04.setAlignment(Pos.TOP_RIGHT);
//			txtPrenom04.setAlignment(Pos.TOP_RIGHT);
//			cbClasse04.setStyle("-fx-alignment: right;");
//			txtCond04.setAlignment(Pos.TOP_RIGHT);

//			txtCIN05.setAlignment(Pos.TOP_RIGHT);
//			txtArchive05.setAlignment(Pos.TOP_RIGHT);
//			txtNom05.setAlignment(Pos.TOP_RIGHT);
//			txtPrenom05.setAlignment(Pos.TOP_RIGHT);
//			cbClasse05.setStyle("-fx-alignment: right;");
//			txtCond05.setAlignment(Pos.TOP_RIGHT);


			// PANE 07
//			firstRow07.setAlignment(Pos.TOP_RIGHT);
			thirdRow07.setAlignment(Pos.BOTTOM_RIGHT);
//			lblClasse07.setAlignment(Pos.TOP_RIGHT);
			allEtudiants07.setAlignment(Pos.TOP_RIGHT);


			// PANE 04: ADD CLASS
			fourthRow04.setAlignment(Pos.BOTTOM_RIGHT);
			lblNomClasse04.setAlignment(Pos.TOP_RIGHT);
			lblAjouterClasse04.toFront();
			vboxAC04.toFront();
			txtNomClasse04.setAlignment(Pos.TOP_RIGHT);


		} else {
			firstRow04.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
			secondRow04.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
			firstRow05.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
			secondRow05.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
			firstRow07.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

//			firstRow04.setAlignment(Pos.TOP_LEFT);
			secondRow04.setAlignment(Pos.TOP_LEFT);
			thirdRow04.setAlignment(Pos.BOTTOM_LEFT);
//			firstRow05.setAlignment(Pos.TOP_LEFT);
			secondRow05.setAlignment(Pos.TOP_LEFT);
			thirdRow05.setAlignment(Pos.TOP_LEFT);
			fourthRow05.setAlignment(Pos.BOTTOM_LEFT);

//			lblCIN04.setAlignment(Pos.TOP_LEFT);
//			lblArchive04.setAlignment(Pos.TOP_LEFT);
//			lblNom04.setAlignment(Pos.TOP_LEFT);
//			lblPrenom04.setAlignment(Pos.TOP_LEFT);
//			lblClasse04.setAlignment(Pos.TOP_LEFT);
//			lblCond04.setAlignment(Pos.TOP_LEFT);
//			lblCIN05.setAlignment(Pos.TOP_LEFT);
//			lblArchive05.setAlignment(Pos.TOP_LEFT);
//			lblNom05.setAlignment(Pos.TOP_LEFT);
//			lblPrenom05.setAlignment(Pos.TOP_LEFT);
//			lblClasse05.setAlignment(Pos.TOP_LEFT);
//			lblCond05.setAlignment(Pos.TOP_LEFT);

			btnRechercher02.toBack();

			lblValider03.toBack();

			lblLangue06.toBack();
			lblEnregistrer06.toBack();

//			vboxCIN04.toBack();
//			vboxCIN05.toBack();

//			vboxPrenom04.toFront();
//			vboxClasse04.toFront();
//			vboxCond04.toFront();

//			vboxPrenom05.toFront();
//			vboxClasse05.toFront();
//			vboxCond05.toFront();

			lblRetourner04.toFront();
			lblMsg04.toFront();

			lblRetourner05.toFront();
			lblSupprimer05.toFront();
			lblModifier05.toFront();

			lblSupprimer05.toFront();
			lblRetourner05.toFront();
			lblMsg05.toFront();


			// PANE 07
			lblRetourner07.toFront();
			lblMsg07.toFront();


			// PANE 04: ADD CLASS
			fourthRow04.setAlignment(Pos.BOTTOM_LEFT);
			lblNomClasse04.setAlignment(Pos.TOP_LEFT);
			lblAjouterClasse04.toFront();
			lblMsgAC04.toFront();
			txtNomClasse04.setAlignment(Pos.TOP_LEFT);


			lblAjouterDoc05.toFront();
//			lblVoirTousDocs05.toFront();
			lblSupprimerTousDocs05.toFront();
			lblDocuments05.setPadding(new Insets(0, 15, 0, 0));

//			for (Node d : allDocuments05.getChildren()) {
//				Label lbl = (Label) ((HBox) d).getChildren().get(0);
//				lbl.setAlignment(Pos.TOP_LEFT);
//				System.out.println(lbl.getText());
//			}

//			txtCIN04.setAlignment(Pos.TOP_LEFT);
//			txtArchive04.setAlignment(Pos.TOP_LEFT);
//			txtNom04.setAlignment(Pos.TOP_LEFT);
//			txtPrenom04.setAlignment(Pos.TOP_LEFT);
//			cbClasse04.setStyle("-fx-alignment: left;");
//			txtCond04.setAlignment(Pos.TOP_LEFT);

//			txtCIN05.setAlignment(Pos.TOP_LEFT);
//			txtArchive05.setAlignment(Pos.TOP_LEFT);
//			txtNom05.setAlignment(Pos.TOP_LEFT);
//			txtPrenom05.setAlignment(Pos.TOP_LEFT);
//			cbClasse05.setStyle("-fx-alignment: left;");
//			txtCond05.setAlignment(Pos.TOP_LEFT);


			// PANE 07
//			firstRow07.setAlignment(Pos.TOP_LEFT);
			thirdRow07.setAlignment(Pos.BOTTOM_LEFT);
//			lblClasse07.setAlignment(Pos.TOP_LEFT);
			allEtudiants07.setAlignment(Pos.TOP_LEFT);
		}

		lblSG01.setText(Lang.getEquiv("SG des Relevés de Notes") + " (v1.0.0)");

		switch (lang) { // THE USE OF OVERLOADED Lang.getEquiv(staticLang: true) MIGHT BE BETTER
			case "arabic":
				lblInst01.setText("المعهد العالي للّغات التطبيقيّة والإعلاميّة بباجة");
				lblMinis01.setText("وزارة التعليم العالي والبحث العلمي");
				lblUniv01.setText("جامعة جندوبة");
				btnContinuer01.setText("واصل");

				btnRechercher02.setText("البحث");
				btnAjouter02.setText("المزيد");

				lblCIN03.setText("رقم بطاقة التعريف:");
				lblValider03.setText("بحث");
				lblRetourner03.setText("رجوع");
				lblAfficherClasses03.setText("عرض الطلاب حسب القسم");

				lblCIN04.setText("رقم بطاقة التعريف:");
				lblArchive04.setText("الأرشيف:");
				lblNom04.setText("اللقب:");
				lblPrenom04.setText("الإسم:");
				lblClasse04.setText("القسم:");
				lblCond04.setText("الحالة:");
				lblAjouter04.setText("إضافة");
				lblRetourner04.setText("رجوع");

				// PANE 04: ADD CLASS
				lblNomClasse04.setText("إسم القسم:");
				lblAjouterClasse04.setText("إضافة");

				lblCIN05.setText("رقم بطاقة التعريف:");
				lblArchive05.setText("الأرشيف:");
				lblNom05.setText("اللقب:");
				lblPrenom05.setText("الإسم:");
				lblClasse05.setText("القسم:");
				lblCond05.setText("الحالة:");
				lblDocuments05.setText("الوثائق:");
				lblAucunDoc05.setText("لا يوجد أي وثيقة.");
				lblAjouterDoc05.setText("إضافة وثائق");
//				lblVoirTousDocs05.setText("رؤية جميع الوثائق");
				lblSupprimerTousDocs05.setText("حذف جميع الوثائق");
				lblSupprimer05.setText("حذف");
				lblRetourner05.setText("رجوع");

				lblLangue06.setText("اللغة:");
				lblEnregistrer06.setText("تسجيل");
				lblRetourner06.setText("رجوع");

				// PANE 07
				lblClasse07.setText("القسم:");
				lblSelClasse07.setText("إختر القسم.");
				lblSupprimer07.setText("حذف");
				lblRetourner07.setText("رجوع");

				// PANE 04 & 05 | BOURSIER
				lblBoursier04.setText("متحصّل على منحة:");
				rbBoursierOui04.setText("نعم");
				rbBoursierNon04.setText("لا");
				lblBoursier05.setText("متحصّل على منحة:");
				rbBoursierOui05.setText("نعم");
				rbBoursierNon05.setText("لا");
				break;

			case "french":
				lblInst01.setText("Institut Supérieur des Langues Appliquées et Informatique de Béja");
				lblMinis01.setText("Ministère de l'Enseignement Supérieur et de la Recherche Scientifique");
				lblUniv01.setText("Université de Jendouba");
				btnContinuer01.setText("Continuer");

				btnRechercher02.setText("Rechercher");
				btnAjouter02.setText("Plus");

				lblCIN03.setText("CIN:");
				lblValider03.setText("Valider");
				lblRetourner03.setText("Retourner");
				lblAfficherClasses03.setText("Afficher étudiants par classe");

				lblCIN04.setText("CIN:");
				lblArchive04.setText("Archive:");
				lblNom04.setText("Nom:");
				lblPrenom04.setText("Prénom:");
				lblClasse04.setText("Classe:");
				lblCond04.setText("Condition:");
				lblAjouter04.setText("Ajouter");
				lblRetourner04.setText("Retourner");

				// PANE 04: ADD CLASS
				lblNomClasse04.setText("Nom Classe :");
				lblAjouterClasse04.setText("Ajouter");

				lblCIN05.setText("CIN:");
				lblArchive05.setText("Archive:");
				lblNom05.setText("Nom:");
				lblPrenom05.setText("Prénom:");
				lblClasse05.setText("Classe:");
				lblCond05.setText("Condition:");
				lblDocuments05.setText("Documents:");
				lblAucunDoc05.setText("Aucun document.");
				lblAjouterDoc05.setText("Ajouter");
//				lblVoirTousDocs05.setText("Voir tous");
				lblSupprimerTousDocs05.setText("Supprimer tous");
				lblSupprimer05.setText("Supprimer");
				lblRetourner05.setText("Retourner");

				lblLangue06.setText("Langue:");
				lblEnregistrer06.setText("Enregistrer");
				lblRetourner06.setText("Retourner");


				// PANE 07
				lblClasse07.setText("Classe:");
				lblSelClasse07.setText("Sélectionner une classe.");
				lblSupprimer07.setText("Supprimer");
				lblRetourner07.setText("Retourner");

				// PANE 04 & 05 | BOURSIER
				lblBoursier04.setText("Boursier:");
				rbBoursierOui04.setText("Oui");
				rbBoursierNon04.setText("Non");
				lblBoursier05.setText("Boursier:");
				rbBoursierOui05.setText("Oui");
				rbBoursierNon05.setText("Non");

				break;

			case "english":
				lblInst01.setText("Higher Institute of Applied Languages and Computer Science of Beja");
				lblMinis01.setText("Ministry of Higher Education and Scientific Research");
				lblUniv01.setText("University of Jendouba");
				btnContinuer01.setText("Continue");

				btnRechercher02.setText("Search");
				btnAjouter02.setText("More");

				lblCIN03.setText("ID Card Number:");
				lblValider03.setText("Validate");
				lblRetourner03.setText("Return");
				lblAfficherClasses03.setText("Show Students by Class");

				lblCIN04.setText("ID Card Number:");
				lblArchive04.setText("Archive:");
				lblNom04.setText("Last Name:");
				lblPrenom04.setText("First Name:");
				lblClasse04.setText("Class:");
				lblCond04.setText("Condition:");
				lblAjouter04.setText("Add");
				lblRetourner04.setText("Return");

				// PANE 04: ADD CLASS
				lblNomClasse04.setText("Class Name :");
				lblAjouterClasse04.setText("Add");

				lblCIN05.setText("ID Card Number:");
				lblArchive05.setText("Archive:");
				lblNom05.setText("Last Name:");
				lblPrenom05.setText("First Name:");
				lblClasse05.setText("Class:");
				lblCond05.setText("Condition:");
				lblDocuments05.setText("Documents:");
				lblAucunDoc05.setText("No Document.");
				lblAjouterDoc05.setText("Add");
//				lblVoirTousDocs05.setText("View All");
				lblSupprimerTousDocs05.setText("Delete All");
				lblSupprimer05.setText("Delete");
				lblRetourner05.setText("Return");

				lblLangue06.setText("Language:");
				lblEnregistrer06.setText("Save");
				lblSupprimer07.setText("Delete");
				lblRetourner06.setText("Return");


				// PANE 07
				lblClasse07.setText("Class:");
				lblSelClasse07.setText("Select Class.");
				lblRetourner07.setText("Return");

				// PANE 04 & 05 | BOURSIER
				lblBoursier04.setText("Has Scholarship:");
				rbBoursierOui04.setText("Yes");
				rbBoursierNon04.setText("No");
				lblBoursier05.setText("Has Scholarship:");
				rbBoursierOui05.setText("Yes");
				rbBoursierNon05.setText("No");

				break;
		}

		// CONDITIONS
		ArrayList<String> conditions = getConditions();
		cbCond04.getItems().clear();
		cbCond04.getItems().addAll(conditions);
		cbCond05.getItems().clear();
		cbCond05.getItems().addAll(conditions);
	}

	public void requestFocus(Node node) {
		requestFocus(node, 3);
	}

	private void requestFocus(final Node node, final int max) {
		if (max > 0) {
			Platform.runLater(
				() -> {
					if (!node.isFocused()) {
						node.requestFocus();
						requestFocus(node, max - 1);
					}
				}
			);
		}
	}

	private void setStudentsByClass() {
		allEtudiants07.getChildren().clear();

		if (cbClasse07.getValue() == null) {
			allEtudiants07.getChildren().add(new EtudiantHBox(Lang.getEquiv("Sélectionner une classe.")));
			return;
		}

		ArrayList<Etudiant> etudiants = DB.getStudentsByClass(cbClasse07.getValue().toString());

		if (etudiants == null || etudiants.size() == 0)
			allEtudiants07.getChildren().add(new EtudiantHBox(Lang.getEquiv("Aucun étudiant.")));
		else {
			int nb = 0;
			for (Etudiant etudiant : etudiants) {
				nb++;
				allEtudiants07.getChildren().add(new EtudiantHBox(nb, etudiant));
			}
		}
	}

	private void setClasses() {
		classes = DB.getClasses();
		if (classes != null) {
			cbClasse04.getItems().clear();
			cbClasse04.getItems().addAll(classes);
			cbClasse05.getItems().clear();
			cbClasse05.getItems().addAll(classes);
			cbClasse07.getItems().clear();
			cbClasse07.getItems().addAll(classes);
		}
	}

	private void proceedToMain() {
		show(paneMain);
	}

	private void search() {
		Etudiant e1;

		if (txtCIN03.getText().isEmpty()) {
			lblMsg03.setText(Lang.getEquiv("Entrer un numéro de CIN d'abord."));
			return;
		}

		int cin;
		try {
			cin = Integer.parseInt(txtCIN03.getText());
		} catch (Exception e2) {
			lblMsg03.setText(Lang.getEquiv("Entrer un numéro de CIN valide."));
			return;
		}

		try {
			e1 = DB.getEtudiant(cin);
			if (e1 == null) {
				lblMsg03.setText(Lang.getEquiv("Aucun étudiant enregistré avec ce numéro de CIN."));
			} else {
				show(paneResultat);
				txtCIN05.setText(String.format("%08d", e1.getCin()));
				txtArchive05.setText(e1.getArchive());
				txtNom05.setText(e1.getNom());
				txtPrenom05.setText(e1.getPrenom());
				cbClasse05.setValue(e1.getClasse());
				cbCond05.setValue(e1.getCond());
				setSelectedBoursier05(e1.isBoursier() ? Boursier.OUI : Boursier.NON);
				disableInputs05(true);
				setDocs();
				System.out.println("---");
				currCond05 = cbCond05.getValue();
				System.out.println(currCond05);
				setEquivCond();
			}
		} catch (Exception e2) {
			lblMsg03.setText(Lang.getEquiv("Erreur lors de la recherche."));
		}
	}

	private void return03() {
		show(paneMain);
	}

	private void addStudent() {
		if (txtCIN04.getText().isEmpty() || txtArchive04.getText().isEmpty() || txtNom04.getText().isEmpty() ||
				txtPrenom04.getText().isEmpty()) {
			lblMsg04.setText(Lang.getEquiv("Tous les champs doivent être remplis."));
			return;
		}

		if (txtCIN04.getText().length() != 8) {
			lblMsg04.setText(Lang.getEquiv("Numéro de CIN invalide."));
			return;
		}

		int cin;
		try {
			cin = Integer.parseInt(txtCIN04.getText());
			if (cin < 0 || cin > 99999999) {
				lblMsg04.setText(Lang.getEquiv("Numéro de CIN invalide."));
				return;
			}
		} catch (Exception e1) {
			lblMsg04.setText(Lang.getEquiv("Numéro de CIN invalide."));
			return;
		}

		if (getSelectedBoursier04() == Boursier.NONE) {
			lblMsg04.setText(Lang.getEquiv("Sélectionner boursier ou non."));
			return;
		}

		if (cbClasse04.getValue() == null) {
			lblMsg04.setText(Lang.getEquiv("Aucune classe selectionnée."));
			return;
		}

		if (cbCond04.getValue() == null) {
			lblMsg04.setText(Lang.getEquiv("Aucune condition selectionnée."));
			return;
		}

//		if (!classes.contains(cbClasse04.getValue().toUpperCase())) {
//			lblMsg04.setText(Lang.getEquiv("La classe entrée n'existe pas."));
//			return;
//		}

		try {
			Etudiant e1 = new Etudiant(Integer.parseInt(txtCIN04.getText()),
					txtArchive04.getText(),
					txtNom04.getText(),
					txtPrenom04.getText(),
					cbClasse04.getValue().toUpperCase(),
					getCondInFrench(cbCond04.getValue()),
					getSelectedBoursier04() == Boursier.OUI
			);
			if (DB.addEtudiant(e1)) {
				lblMsg04.setText(Lang.getEquiv("Ajouté avec succès."));
			} else {
				lblMsg04.setText(Lang.getEquiv("Erreur lors de l'ajout."));
			}
		} catch (Exception e2) {
			lblMsg04.setText(Lang.getEquiv("Erreur lors de l'ajout."));
		}
	}

	private void addClass() {
		if (txtNomClasse04.getText().isEmpty()) {
			lblMsgAC04.setText(Lang.getEquiv("Entrer le nom de la classe d'abord."));
			return;
		}

		if (DB.addClasse(txtNomClasse04.getText().toUpperCase())) {
			setClasses();
			lblMsgAC04.setText(Lang.getEquiv("Ajouté avec succès."));
		} else {
			lblMsgAC04.setText(Lang.getEquiv("Erreur lors de l'ajout."));
		}
	}

	private void return04() {
		show(paneMain);
	}

	private void editStudent() {
		if (lblModifier05.getText().equals(Lang.getEquiv("Modifier"))) {
			txtArchive05.setEditable(true);
			txtNom05.setEditable(true);
			txtPrenom05.setEditable(true);
//			cbClasse05.setEditable(true);
//			txtCond05.setEditable(true);
			lblModifier05.setText(Lang.getEquiv("Enregistrer"));
			lblRetourner05.setText(Lang.getEquiv("Annuler"));
			lblMsg05.setText("");
			disableInputs05(false);

//				lblAjouterDoc05.setDisable(false);
		} else {
			if (txtArchive05.getText().isEmpty() || txtNom05.getText().isEmpty() ||
					txtPrenom05.getText().isEmpty()) {
				lblMsg05.setText(Lang.getEquiv("Tous les champs doivent être remplis."));
				return;
			}

			if (cbClasse05.getValue() == null) {
				lblMsg05.setText(Lang.getEquiv("Aucune classe selectionnée."));
				return;
			}

			if (cbCond05.getValue() == null) {
				lblMsg05.setText(Lang.getEquiv("Aucune condition selectionnée."));
				return;
			}


//			if (!classes.contains(cbClasse05.getValue().toUpperCase())) {
//				lblMsg05.setText(Lang.getEquiv("La classe entrée n'existe pas."));
//				return;
//			}

			try {
				Etudiant e1 = new Etudiant(
						Integer.parseInt(txtCIN05.getText()),
						txtArchive05.getText(),
						txtNom05.getText(),
						txtPrenom05.getText(),
						cbClasse05.getValue().toUpperCase(),
						getCondInFrench(cbCond05.getValue()),
						getSelectedBoursier05() == Boursier.OUI
				);

				if (DB.modifyEtudiant(e1)) {
					lblMsg05.setText(Lang.getEquiv("Modifié avec succès."));
				} else {
					lblMsg05.setText(Lang.getEquiv("Erreur lors de la modification."));
				}
			} catch (Exception e2) {
				lblMsg05.setText(Lang.getEquiv("Erreur lors de la modification."));
			}

			txtArchive05.setEditable(false);
			txtNom05.setEditable(false);
			txtPrenom05.setEditable(false);
//			cbClasse05.setEditable(false);
//			txtCond05.setEditable(false);
//			lblAjouterDoc05.setDisable(true);
			disableInputs05(true);
			lblModifier05.setText(Lang.getEquiv("Modifier"));
			lblRetourner05.setText(Lang.getEquiv("Retourner"));
		}
	}

	private void deleteStudent() {
		lblMsg05.setText("");
		if (!main.useful.Dialog.confirm(Lang.getEquiv("Supprimer Étudiant"), Lang.getEquiv("Voulez-vous vraiment supprimer cet étudiant ?"))) {
			return;
		}

		try {
			if (DB.deleteEtudiant(Integer.parseInt(txtCIN05.getText()))) {
				show(paneRechercher);
				lblMsg03.setText(Lang.getEquiv("Supprimé avec succès."));
			} else lblMsg05.setText(Lang.getEquiv("Erreur lors de la suppression."));
		} catch (Exception e1) {
			lblMsg05.setText(Lang.getEquiv("Erreur lors de la suppression."));
		}
	}

	public void return05() {
		if (lblModifier05.getText().equals(Lang.getEquiv("Modifier"))) {
			if (isLastClassesPane) show(paneClasses);
			else show(paneRechercher);
		} else {
			show(paneResultat);
			Etudiant e1 = DB.getEtudiant(Integer.parseInt(txtCIN05.getText()));
			if (e1 != null) {
				txtCIN05.setText(String.format("%08d", e1.getCin()));
				txtArchive05.setText(e1.getArchive());
				txtNom05.setText(e1.getNom());
				txtPrenom05.setText(e1.getPrenom());
				cbClasse05.setValue(e1.getClasse());
				cbCond05.setValue(e1.getCond());
				setSelectedBoursier05(e1.isBoursier() ? Boursier.OUI : Boursier.NON);
			}
			lblModifier05.setText(Lang.getEquiv("Modifier"));
			lblRetourner05.setText(Lang.getEquiv("Retourner"));
			setDocs();
			disableInputs05(true);
			System.out.println("---");
			currCond05 = cbCond05.getValue();
			System.out.println(currCond05);
			setEquivCond();
		}
	}

	public void saveSettings() {
		String currSelectedLang;
		if (rbArabic06.isSelected()) currSelectedLang = "arabic";
		else if (rbFrench06.isSelected()) currSelectedLang = "french";
		else if (rbEnglish06.isSelected()) currSelectedLang = "english";
		else {
			lblMsg06.setText(Lang.getEquiv("Aucune langue sélectionnée."));
			return;
		}

		if (DB.saveSetting(new Setting("language", currSelectedLang))) {
			lang = currSelectedLang;
			if (lastPane == paneWelcome) lblBienvenue01.setText(getWelcomeMsg());
			currCond04 = cbCond04.getValue();
			currCond05 = cbCond05.getValue();
			setUpLang();
			if (lastPane == paneResultat || lastPane == paneAjouter) {
				setEquivCond();
			}
			lblMsg06.setText(Lang.getEquiv("Langue enregistrée."));
		} else {
			lblMsg06.setText(Lang.getEquiv("Erreur lors de l'enregistrement."));
		}
	}

	public void return06() {
		show(lastPane);
		if (correctCurrPane == paneResultat) setDocs();
	}

	public void deleteClass() {
		lblMsg07.setText("");
		if (cbClasse07.getValue() == null) {
			lblMsg07.setText(Lang.getEquiv("Sélectionner une classe."));
			return;
		}

		if (!main.useful.Dialog.confirm(Lang.getEquiv("Supprimer Classe"), Lang.getEquiv("Voulez-vous vraiment supprimer cette classe avec tous ses données ?"))) {
//				lblMsg07.setText(Lang.getEquiv("Aucun classe supprimé."));
			return;
		}

		try {
			if (DB.delClasse(cbClasse07.getValue().toString())) {
				setClasses();
				setStudentsByClass();
				lblMsg07.setText(Lang.getEquiv("Supprimé avec succès."));
			} else lblMsg07.setText(Lang.getEquiv("Erreur lors de la suppression."));
		} catch (Exception e1) {
			lblMsg07.setText(Lang.getEquiv("Erreur lors de la suppression."));
		}
	}

	public void return07() {
		show(paneRechercher);
	}

	public Boursier getSelectedBoursier04() {
		if (rbBoursierNon04.isSelected()) return Boursier.NON;
		if (rbBoursierOui04.isSelected()) return Boursier.OUI;
		return Boursier.NONE;
	}

	public Boursier getSelectedBoursier05() {
		if (rbBoursierOui05.isSelected()) return Boursier.OUI;
		return Boursier.NON;
	}

	public void setSelectedBoursier05(Boursier b) {
		rbBoursierNon05.setSelected(false);
		rbBoursierOui05.setSelected(false);
		switch (b) {
			case OUI:
				rbBoursierOui05.setSelected(true);
				break;
			case NON:
				rbBoursierNon05.setSelected(true);
				break;
		}
	}

	public void disableInputs05(boolean b) {
		if (b) {
			txtCIN05.setDisable(false);
			if (getSelectedBoursier05() == Boursier.OUI) {
				rbBoursierOui05.setDisable(false);
				rbBoursierNon05.setDisable(true);
			} else {
				rbBoursierOui05.setDisable(true);
				rbBoursierNon05.setDisable(false);
			}
		} else {
			txtCIN05.setDisable(true);
			rbBoursierOui05.setDisable(false);
			rbBoursierNon05.setDisable(false);
		}
	}

	public ArrayList<String> getConditions() {
		ArrayList<String> conditions = new ArrayList<>();
		// TODO: SET CONDITIONS
		conditions.add(Lang.getEquiv("Condition 1"));
		conditions.add(Lang.getEquiv("Condition 2"));
		conditions.add(Lang.getEquiv("Condition 3"));
		conditions.add(Lang.getEquiv("Condition 4"));
		return conditions;
	}

	public String getCondInFrench(String s) {
		// TODO: SET CONDITIONS
		switch (s) {
			case "الحالة 1":
			case "Condition En 1":
				return "Condition 1";
			case "الحالة 2":
			case "Condition En 2":
				return "Condition 2";
			case "الحالة 3":
			case "Condition En 3":
				return "Condition 3";
			case "الحالة 4":
			case "Condition En 4":
				return "Condition 4";
			default:
				return s;
		}
	}

	public void setEquivCond() {
		// TODO - SET CONDITIONS
		// TODO - OPTIMIZE IT
		setCB(currCond04, cbCond04);
		setCB(currCond05, cbCond05);
	}

	private void setCB(String currCond05, ComboBox<String> cbCond05) {
		if(currCond05 == null) cbCond05.setValue(null);
		else switch (currCond05) {
				case "Condition 1":
				case "الحالة 1":
				case "Condition En 1":
					switch (lang) {
						case "french":
							cbCond05.setValue("Condition 1");
							break;
						case "arabic":
							cbCond05.setValue("الحالة 1");
							break;
						case "english":
							cbCond05.setValue("Condition En 1");
							break;
					}
					break;

				case "Condition 2":
				case "الحالة 2":
				case "Condition En 2":
					switch (lang) {
						case "french":
							cbCond05.setValue("Condition 2");
							break;
						case "arabic":
							cbCond05.setValue("الحالة 2");
							break;
						case "english":
							cbCond05.setValue("Condition En 2");
							break;
					}
					break;

				case "Condition 3":
				case "الحالة 3":
				case "Condition En 3":
					switch (lang) {
						case "french":
							cbCond05.setValue("Condition 3");
							break;
						case "arabic":
							cbCond05.setValue("الحالة 3");
							break;
						case "english":
							cbCond05.setValue("Condition En 3");
							break;
					}
					break;

				case "Condition 4":
				case "الحالة 4":
				case "Condition En 4":
					switch (lang) {
						case "french":
							cbCond05.setValue("Condition 4");
							break;
						case "arabic":
							cbCond05.setValue("الحالة 4");
							break;
						case "english":
							cbCond05.setValue("Condition En 4");
							break;
					}
					break;
			}
	}

	private class DocumentHBox extends HBox {
		public DocumentHBox(String nomDoc) {
			Label singleDocLabel = new Label(nomDoc);
			singleDocLabel.getStyleClass().add("singleDocLabel");
			singleDocLabel.setMinWidth(USE_COMPUTED_SIZE);
			singleDocLabel.setPrefWidth(USE_COMPUTED_SIZE);
			singleDocLabel.setMaxWidth(USE_COMPUTED_SIZE);
//			if(lang.equals("arabic")) singleDocLabel.setAlignment(Pos.TOP_RIGHT);
//			else singleDocLabel.setAlignment(Pos.TOP_LEFT);
			if (nomDoc.equals("لا يوجد أي وثيقة."))
				singleDocLabel.setAlignment(Pos.TOP_RIGHT);
			else
				singleDocLabel.setAlignment(Pos.TOP_LEFT);

			setMinWidth(USE_COMPUTED_SIZE);
			setPrefWidth(375);
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
				lblMsg05.setText(Lang.getEquiv("Erreur lors de l'ouverture du document."));
				e.printStackTrace();
			}
			setDocs();
		}

		public void delDocFile(Document doc) {
			lblMsg05.setText("");
			if (!Dialog.confirm(Lang.getEquiv("Supprimer Document"), Lang.getEquiv("Voulez-vous vraiment supprimer cet document ?"))) {
				return;
			}

			String command = String.format("del \"%s%08d\\%s\"", Main.docsDir, doc.getCinDoc(), doc.getNomDoc());
			try {
				Runtime.getRuntime().exec("cmd /c " + command);
				if (DB.delDoc(doc)) lblMsg05.setText(Lang.getEquiv("1 document supprimé avec succès."));
				else lblMsg05.setText(Lang.getEquiv("Erreur lors de la suppression du document."));
			} catch (Exception e) {
				lblMsg05.setText(Lang.getEquiv("Erreur lors de la suppression du document."));
				e.printStackTrace();
			}
			setDocs();
		}
	}

	private class EtudiantHBox extends HBox {
		private final Etudiant etudiant;

		public EtudiantHBox(String s) {
			init();
			etudiant = null;

			Label lblAucun = new Label(s);
			if ("arabic".equals(lang)) lblAucun.setPadding(new Insets(3, 5, 0, 0));
			else lblAucun.setPadding(new Insets(3, 0, 0, 0));

//			setAlignment(Pos.CENTER_LEFT);
//			setPrefHeight(25);
			setPrefWidth(USE_COMPUTED_SIZE);
			getChildren().add(lblAucun);
		}

		public EtudiantHBox(int nb, Etudiant e) {
			init();
			etudiant = e;

			Label lblNb = new Label(String.format(Lang.getEquiv("Étudiant") + " %02d:", nb));
			TextArea txtDetails = new TextArea(e.toString());
			txtDetails.setEditable(false);
			txtDetails.setPrefRowCount(8);
			txtDetails.setPrefWidth(350);
			txtDetails.setMinHeight(USE_PREF_SIZE);
			txtDetails.setOnKeyReleased((e1) -> {
				if (e1.getCode() == KeyCode.ESCAPE)
					return07();
				else if (e1.getCode() == KeyCode.ENTER)
					viewStudent();
			});

			ImageView imgView = new ImageView(img1URL);
			imgView.setFitWidth(25);
			imgView.setFitHeight(25);
			imgView.setOnMouseClicked(e1 -> viewStudent());
			imgView.setCursor(Cursor.HAND);

			setSpacing(5);
			setPrefWidth(USE_COMPUTED_SIZE);
			setPrefHeight(45);

			if ("arabic".equals(lang)) {
				lblNb.setPadding(new Insets(3, 5, 0, 5));
				txtDetails.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
				getChildren().addAll(imgView, txtDetails, lblNb);
			} else {
				lblNb.setPadding(new Insets(3, 5, 0, 0));
				txtDetails.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
				getChildren().addAll(lblNb, txtDetails, imgView);
			}
		}

		public void init() {
			if ("arabic".equals(lang)) setAlignment(Pos.TOP_RIGHT);
			else setAlignment(Pos.TOP_LEFT);
		}

		public Etudiant getEtudiant() {
			return etudiant;
		}

		public void viewStudent() {
			// txtCIN03.setText(String.format("%08d", e.getCin()));
			isLastClassesPane = true;
			show(paneResultat);
			txtCIN05.setText(String.format("%08d", etudiant.getCin()));
			txtArchive05.setText(etudiant.getArchive());
			txtNom05.setText(etudiant.getNom());
			txtPrenom05.setText(etudiant.getPrenom());
			cbClasse05.setValue(etudiant.getClasse());
			cbCond05.setValue(etudiant.getCond());
			setSelectedBoursier05(etudiant.isBoursier() ? Boursier.OUI : Boursier.NON);
			disableInputs05(true);
			setDocs();
			System.out.println("---");
			currCond05 = cbCond05.getValue();
			System.out.println(currCond05);
			setEquivCond();
		}
	}
}