package main.models;

import main.Controller;

public class Etudiant {
	private int cin;
	private String archive;
	private String nom;
	private String prenom;
	private String classe;
	private String cond;

	public Etudiant() {

	}

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

	public void setCin(int cin) {
		this.cin = cin;
	}

	public void setArchive(String archive) {
		this.archive = archive;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

	@Override
	public String toString() {
		switch (Controller.lang) {
			case "arabic":
				return String.format("رقم بطاقة التعريف: %08d", cin) +
						"\nالأرشيف: " + archive +
						"\nاللقب: " + nom +
						"\nالإسم: " + prenom +
						"\nالقسم: " + classe +
						"\nالحالة: " + cond +
						"\nعدد الوثائق: " + DB.getNbDocs(cin);
			case "english":
				return String.format("ID Card Number: %08d", cin) +
						"\nArchive: " + archive +
						"\nLast Name: " + nom +
						"\nFirst Name: " + prenom +
						"\nClass: " + classe +
						"\nCondition: " + cond +
						"\nNumber of documents: " + DB.getNbDocs(cin);
			default:
				return String.format("CIN: %08d", cin) +
						"\nArchive: " + archive +
						"\nNom: " + nom +
						"\nPrénom: " + prenom +
						"\nClasse: " + classe +
						"\nCondition: " + cond +
						"\nNombre de documents: " + DB.getNbDocs(cin);
		}
	}

//	@Override
//	public String toString() {
//		return "Etudiant{" +
//				"cin=" + cin +
//				", archive='" + archive + '\'' +
//				", nom='" + nom + '\'' +
//				", prenom='" + prenom + '\'' +
//				", classe='" + classe + '\'' +
//				", cond='" + cond + '\'' +
//				'}';
//	}
}