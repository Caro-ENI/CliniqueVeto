package fr.eni.cach.clinique.bo;

import java.util.Date;

public class Rdv {
	
	private Veterinaire veterinaire;
	private Animal animal;
	private Date dateRdv;
	
	
	
	public Veterinaire getVeterinaire() {
		return veterinaire;
	}
	public void setVeterinaire(Veterinaire veterinaire) {
		this.veterinaire = veterinaire;
	}
	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	public Date getDateRdv() {
		return dateRdv;
	}
	public void setDateRdv(Date dateRdv) {
		this.dateRdv = dateRdv;
	}
	
	public Rdv() {
		// TODO Auto-generated constructor stub
	}
	public Rdv(Veterinaire veterinaire, Animal animal, Date dateRdv) {
		super();
		this.veterinaire = veterinaire;
		this.animal = animal;
		this.dateRdv = dateRdv;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rdv [veterinaire=");
		builder.append(veterinaire);
		builder.append(", animal=");
		builder.append(animal);
		builder.append(", dateRdv=");
		builder.append(dateRdv);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	

}
