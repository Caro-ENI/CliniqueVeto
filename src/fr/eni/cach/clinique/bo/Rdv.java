package fr.eni.cach.clinique.bo;

import java.time.LocalDateTime;
import java.util.Date;

public class Rdv {
	
	private Veterinaire veterinaire;
	private Animal animal;
	private LocalDateTime dateRdv;
	private Client client;
	
	
	
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
	public LocalDateTime getDateRdv() {
		return dateRdv;
	}
	public void setDateRdv(LocalDateTime dateRdv) {
		this.dateRdv = dateRdv;
	}
	

	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Rdv() {
		// TODO Auto-generated constructor stub
	}
	public Rdv(Veterinaire veterinaire, Animal animal, LocalDateTime dateRdv) {
		super();
		this.veterinaire = veterinaire;
		this.animal = animal;
		this.dateRdv = dateRdv;
	}
	
	
	
	public Rdv(Veterinaire veterinaire, Animal animal, LocalDateTime dateRdv, Client client) {
		super();
		this.veterinaire = veterinaire;
		this.animal = animal;
		this.dateRdv = dateRdv;
		this.client = client;
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
		builder.append(", client=");
		builder.append(client);
		builder.append("]");
		return builder.toString();
	}
	
	
	

	


	

	
	
	
	
	
	

}
