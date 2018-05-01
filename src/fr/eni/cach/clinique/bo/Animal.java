package fr.eni.cach.clinique.bo;

public class Animal {
	
	// Variables membres
	
	private int codeAnimal;
	private String nomAnimal;
	private String sexe;
	private String couleur;
	private String race;
	private String espece;
	private int codeClient;
	private String tatouage;
	private String antecedents;
	private boolean archive;
	
	// Getters/setters
	
	public int getCodeAnimal() {
		return codeAnimal;
	}
	public void setCodeAnimal(int codeAnimal) {
		this.codeAnimal = codeAnimal;
	}
	public String getNomAnimal() {
		return nomAnimal;
	}
	public void setNomAnimal(String nomAnimal) {
		this.nomAnimal = nomAnimal;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getCouleur() {
		return couleur;
	}
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getEspece() {
		return espece;
	}
	public void setEspece(String espece) {
		this.espece = espece;
	}
	public int getCodeClient() {
		return codeClient;
	}
	public void setCodeClient(int codeClient) {
		this.codeClient = codeClient;
	}
	public String getTatouage() {
		return tatouage;
	}
	public void setTatouage(String tatouage) {
		this.tatouage = tatouage;
	}
	public String getAntecedents() {
		return antecedents;
	}
	public void setAntecedents(String antecedents) {
		this.antecedents = antecedents;
	}
	public boolean isArchive() {
		return archive;
	}
	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	// Constructeurs
	
	public Animal() {
		// TODO Auto-generated constructor stub
	}
	public Animal(int codeAnimal, String nomAnimal, String sexe, String couleur, String race, String espece,
			int codeClient, String tatouage, String antecedents, boolean archive) {
		super();
		this.codeAnimal = codeAnimal;
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.couleur = couleur;
		this.race = race;
		this.espece = espece;
		this.codeClient = codeClient;
		this.tatouage = tatouage;
		this.antecedents = antecedents;
		this.archive = archive;
	}
	

	public Animal(String nomAnimal, String sexe, String couleur, String race, String espece, int codeClient,
			boolean archive) {
		super();
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.couleur = couleur;
		this.race = race;
		this.espece = espece;
		this.codeClient = codeClient;
		this.archive = archive;
	}
	

	public Animal(String nomAnimal, String sexe, String couleur, String race, String espece, int codeClient,
			String tatouage, String antecedents, boolean archive) {
		super();
		this.nomAnimal = nomAnimal;
		this.sexe = sexe;
		this.couleur = couleur;
		this.race = race;
		this.espece = espece;
		this.codeClient = codeClient;
		this.tatouage = tatouage;
		this.antecedents = antecedents;
		this.archive = archive;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
//		builder.append("Animal [codeAnimal=");
//		builder.append(codeAnimal);
//		builder.append(", nomAnimal=");
		builder.append(nomAnimal);
//		builder.append(", sexe=");
//		builder.append(sexe);
//		builder.append(", couleur=");
//		builder.append(couleur);
//		builder.append(", race=");
//		builder.append(race);
//		builder.append(", espece=");
//		builder.append(espece);
//		builder.append(", codeClient=");
//		builder.append(codeClient);
//		builder.append(", tatouage=");
//		builder.append(tatouage);
//		builder.append(", antecedents=");
//		builder.append(antecedents);
//		builder.append(", archive=");
//		builder.append(archive);
//		builder.append("]\r");
		return builder.toString();
	}
	
	
	
	
	
}
