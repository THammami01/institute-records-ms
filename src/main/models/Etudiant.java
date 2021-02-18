package main.models;

public class Etudiant {
	private int cin;
	private String archive;
	private String nom;
	private String prenom;
	private String classe;
	private String cond;

	public Etudiant(int cin, String archive, String nom, String prenom, String classe, String cond) {
		this.cin = cin;
		this.archive = archive;
		this.nom = nom;
		this.prenom = prenom;
		this.classe = classe;
		this.cond = cond;
	}

	public int getCin() {
		return cin;
	}

	public String getArchive() {
		return archive;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getClasse() {
		return classe;
	}

	public String getCond() {
		return cond;
	}
}