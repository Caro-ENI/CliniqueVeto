package fr.eni.cach.clinique.test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import fr.eni.cach.clinique.bo.Animal;
import fr.eni.cach.clinique.bo.Client;
import fr.eni.cach.clinique.bo.Rdv;
import fr.eni.cach.clinique.bo.Veterinaire;

public class AppliTestbo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Veterinaire v1 = new Veterinaire ("BOSAPIN Edmond", "PIN", "vet", false);
		Veterinaire v2 = new Veterinaire ("DE CAJOU Benoît", "NOIX", "vet", false);
		
		System.out.println(v1);
		System.out.println("\r");
		System.out.println(v2);
		System.out.println("\r");
		
		
		Client c1 = new Client ("Dupond","Jean","20 rue de la paix","","44000","Nantes","02.40.41.42.43","MAAF","JDupont@free.fr",false);
		System.out.println(c1);
		System.out.println("\r");
		
		
		Animal a1 = new Animal ("Toby","M","noir","Chien","Batard",0,false);
		Animal a2 = new Animal ("Kiki","M","Jaune","Chien","Batard",0, false);
		
		
		
		//listeAnimaux.add(a1);
		//listeAnimaux.add(a2);
		
		c1.addAnimal(a1);
		c1.addAnimal(a2);

		System.out.println("Ajout d'une liste d'animaux au client c1");
		System.out.println(c1);
		System.out.println("\r");
		
		Date in =new GregorianCalendar(2018, 3, 23, 10, 0).getTime();
		LocalDateTime dateTest = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
		
	
		
		Rdv r1 = new Rdv(v1,a1, dateTest,c1);
		
		System.out.println(r1);
		
		
	}

}
