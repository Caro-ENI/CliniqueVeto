package fr.eni.cach.clinique.bo;

public abstract class Personnel {
	
	// Variables membres
	
	private int codePersonnel;
	private String nom;
	private String motPasse;
	private String role;
	private boolean archive;
	
	// Getters/Setters
	
	public int getCodePersonnel() {
		return codePersonnel;
	}
	public void setCodePersonnel(int codePersonnel) {
		this.codePersonnel = codePersonnel;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getMotPasse() {
		return motPasse;
	}
	public void setMotPasse(String motPasse) {
		this.motPasse = motPasse;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isArchive() {
		return archive;
	}
	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	// Constructeur
	
	public Personnel() {
		// TODO Auto-generated constructor stub
	}
	
	public Personnel(int codePersonnel, String nom, String motPasse, String role, boolean archive) {
		super();
		this.codePersonnel = codePersonnel;
		this.nom = nom;
		this.motPasse = motPasse;
		this.role = role;
		this.archive = archive;
	}
	
	
	public Personnel(String nom, String motPasse, String role, boolean archive) {
		super();
		this.nom = nom;
		this.motPasse = motPasse;
		this.role = role;
		this.archive = archive;
	}
	
	// Pour avoir les bons affichages dans les JComboBox
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
//		builder.append("Personnel [codePersonnel=");
//		builder.append(codePersonnel);
//		builder.append(", nom=");
		builder.append(nom);
//		builder.append(", motPasse=");
//		builder.append(motPasse);
//		builder.append(", role=");
//		builder.append(role);
//		builder.append(", archive=");
//		builder.append(archive);
//		builder.append("]\r");
		return builder.toString();
	}
	
	

	
	
	
	
	

}
