package fr.eni.cach.clinique.bo;

import java.util.ArrayList;
import java.util.List;

public class Client {

	// Variables membres
	private int codeClient;
	private String nomClient;
	private String prenomClient;
	private String adresse1;
	private String adresse2;
	private String codePostal;
	private String ville;
	private String numTel;
	private String assurance;
	private String email;
	private String remarque;
	private boolean archive;

	private List<Animal> listeAnimaux;

	// Getters/setters

	public List<Animal> getListeAnimaux() {
		if (listeAnimaux == null)
		listeAnimaux = new ArrayList<Animal>();
		return listeAnimaux;
	}

	public void setListeAnimaux(List<Animal> listeAnimaux) {
		this.listeAnimaux = listeAnimaux;
	}

	public int getCodeClient() {
		return codeClient;
	}

	public void setCodeClient(int codeClient) {
		this.codeClient = codeClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getPrenomClient() {
		return prenomClient;
	}

	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}

	public String getAdresse1() {
		return adresse1;
	}

	public void setAdresse1(String adresse1) {
		this.adresse1 = adresse1;
	}

	public String getAdresse2() {
		return adresse2;
	}

	public void setAdresse2(String adresse2) {
		this.adresse2 = adresse2;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String getAssurance() {
		return assurance;
	}

	public void setAssurance(String assurance) {
		this.assurance = assurance;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}
	
	

	/**
	 * 
	 */
	public void addAnimal(Animal animal) {
		
		getListeAnimaux().add(animal);
		
	}
	
	

	// Constructeur

	public Client() {
		// TODO Auto-generated constructor stub
	}



	public Client(String nomClient, String prenomClient, String adresse1, String adresse2, String codePostal,
			String ville, String numTel, String assurance, String email, boolean archive) {
		super();
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.codePostal = codePostal;
		this.ville = ville;
		this.numTel = numTel;
		this.assurance = assurance;
		this.email = email;
		this.archive = archive;
	}
	
	
	

	public Client(String nomClient, String prenomClient, String adresse1, String adresse2, String codePostal,
			String ville, String numTel, String assurance, String email, boolean archive, List<Animal> listeAnimaux) {
		super();
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.codePostal = codePostal;
		this.ville = ville;
		this.numTel = numTel;
		this.assurance = assurance;
		this.email = email;
		this.archive = archive;
		this.listeAnimaux = listeAnimaux;
	}
	
	
	public Client(int codeClient, String nomClient, String prenomClient, String adresse1, String adresse2,
			String codePostal, String ville, String numTel, String assurance, String email, boolean archive,
			List<Animal> listeAnimaux) {
		super();
		this.codeClient = codeClient;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.adresse1 = adresse1;
		this.adresse2 = adresse2;
		this.codePostal = codePostal;
		this.ville = ville;
		this.numTel = numTel;
		this.assurance = assurance;
		this.email = email;
		this.archive = archive;
		this.listeAnimaux = listeAnimaux;
	}

	
	// Pour avoir les bons affichages dans les JComboBox
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
//		builder.append("Client [codeClient=");
//		builder.append(codeClient);
//		builder.append(", nomClient=");
		builder.append(nomClient);
		builder.append(" ");
//		builder.append(", prenomClient=");
		builder.append(prenomClient);
//		builder.append(", adresse1=");
//		builder.append(adresse1);
//		builder.append(", adresse2=");
//		builder.append(adresse2);
//		builder.append(", codePostal=");
//		builder.append(codePostal);
//		builder.append(", ville=");
//		builder.append(ville);
//		builder.append(", numTel=");
//		builder.append(numTel);
//		builder.append(", assurance=");
//		builder.append(assurance);
//		builder.append(", email=");
//		builder.append(email);
//		builder.append(", archive=");
//		builder.append(archive);
//		builder.append(", \rlisteAnimaux=");
//		builder.append(listeAnimaux);
//		builder.append("]");
		return builder.toString();
	}

}
