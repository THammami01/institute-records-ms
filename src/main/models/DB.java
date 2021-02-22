package main.models;

import main.Main;

import java.sql.*;
import java.util.ArrayList;

// TODO: METHODE backup() CALLED EACH TIME WHEN USER EXISTS

public class DB {
	private static Connection connection;
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String query;

	public static boolean connected;

	static {
		try {
//			Class.forName("org.mariadb.jdbc.Driver");
//			String dburl = "jdbc:mariadb://localhost:3306/SGRN";
//			String usernameLocal = "root";
//			String passwordLocal = "";
//			connection = DriverManager.getConnection(dburl, usernameLocal, passwordLocal);
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:DB.db");
			st = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
			connected = false;
		}

		connected = true;
	}

	public static void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
				e.getCause();
			}
		}
	}

	public static ArrayList<String> getClasses() {
		ArrayList<String> classes = new ArrayList<>();
		query = "SELECT * FROM Classe ORDER BY id;";

		try {
			rs = st.executeQuery(query);
			while (rs.next())
				classes.add(rs.getString("classe"));
			return classes;
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return null;
	}

	public static Etudiant getEtudiant(int cin) {
		query = "SELECT * FROM Etudiant WHERE cin = ?;";
		try {
			pst = connection.prepareStatement(query);
			pst.setInt(1, cin);
			rs = pst.executeQuery();

			if (rs.next())
				return new Etudiant(
						cin,
						rs.getString("archive"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("classe"),
						rs.getString("cond")
				);
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return null;
	}

	public static boolean addEtudiant(Etudiant e1) {
		query = "INSERT INTO Etudiant VALUES(?, ?, ?, ?, ?, ?);";
		try {
			String command = String.format("mkdir \"%s%08d\"", Main.docsDir, e1.getCin());
			Runtime.getRuntime().exec("cmd /c " + command);

			pst = connection.prepareStatement(query);
			pst.setInt(1, e1.getCin());
			pst.setString(2, e1.getArchive());
			pst.setString(3, e1.getNom());
			pst.setString(4, e1.getPrenom());
			pst.setString(5, e1.getClasse());
			pst.setString(6, e1.getCond());
			if (pst.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return false;
	}

	public static boolean modifyEtudiant(Etudiant e1) {
		query = "UPDATE Etudiant SET archive = ?, nom = ?, prenom = ?, classe = ?, cond = ?;";
		try {
			pst = connection.prepareStatement(query);
			pst.setString(1, e1.getArchive());
			pst.setString(2, e1.getNom());
			pst.setString(3, e1.getPrenom());
			pst.setString(4, e1.getClasse());
			pst.setString(5, e1.getCond());
			if (pst.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return false;
	}

	public static boolean deleteEtudiant(int cin) {
		query = "DELETE FROM Document WHERE cinDoc = ?;";
		try {
			String command = String.format("rmdir /q /s \"%s%08d\"", Main.docsDir, cin);
			Runtime.getRuntime().exec("cmd /c " + command);

			pst = connection.prepareStatement(query);
			pst.setInt(1, cin);

			query = "DELETE FROM Etudiant WHERE cin = ?;";
			pst = connection.prepareStatement(query);
			pst.setInt(1, cin);
			if (pst.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return false;
	}

	public static boolean addDoc(Document doc) {
		query = "SELECT count(*) FROM Document WHERE cinDoc = ? AND nomDoc = ?;";
		try {
			pst = connection.prepareStatement(query);
			pst.setInt(1, doc.getCinDoc());
			pst.setString(2, doc.getNomDoc());
			rs = pst.executeQuery();

			rs.next();
			if(rs.getInt("count(*)") > 0) return false;

			query = "INSERT INTO Document(cinDoc, nomDoc) VALUES(?, ?);";
			pst = connection.prepareStatement(query);
			pst.setInt(1, doc.getCinDoc());
			pst.setString(2, doc.getNomDoc());
			if (pst.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return false;
	}

	public static ArrayList<String> getDocs(int cinDoc) {
		ArrayList<String> nomsDocs = new ArrayList<>();
		query = "SELECT nomDoc FROM Document WHERE cinDoc = " + cinDoc + " ORDER BY idDoc;";

		try {
			rs = st.executeQuery(query);
			while (rs.next())
				nomsDocs.add(rs.getString("nomDoc"));
			return nomsDocs;
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return null;
	}

	public static boolean delDoc(Document doc) {
		query = "DELETE FROM Document WHERE cinDoc = ? AND nomDoc = ?;";
		try {
			pst = connection.prepareStatement(query);
			pst.setInt(1, doc.getCinDoc());
			pst.setString(2, doc.getNomDoc());
			if (pst.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return false;
	}

	public static boolean delDocs(int cinDoc) {
		query = "DELETE FROM Document WHERE cinDoc = ?;";
		try {
			pst = connection.prepareStatement(query);
			pst.setInt(1, cinDoc);
			if (pst.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return false;
	}

	public static Setting getSetting(String label) {
		query = "SELECT * FROM Settings WHERE label = ?;";
		try {
			pst = connection.prepareStatement(query);
			pst.setString(1, label);
			rs = pst.executeQuery();

			if (rs.next())
				return new Setting(label, rs.getString("value"));
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return null;
	}

	public static boolean saveSetting(Setting setting) {
		query = "UPDATE Settings SET value = ? WHERE label = ?";
		try {
			pst = connection.prepareStatement(query);
			pst.setString(1, setting.getValue());
			pst.setString(2, setting.getLabel());
			if (pst.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return false;
	}
}