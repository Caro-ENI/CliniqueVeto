package fr.eni.cach.clinique.test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.security.auth.callback.LanguageCallback;

import fr.eni.cach.clinique.bo.Animal;
import fr.eni.cach.clinique.bo.Client;
import fr.eni.cach.clinique.bo.Personnel;
import fr.eni.cach.clinique.bo.Rdv;
import fr.eni.cach.clinique.bo.Veterinaire;
import fr.eni.cach.clinique.dal.AnimalDAO;
import fr.eni.cach.clinique.dal.ClientDAO;
import fr.eni.cach.clinique.dal.DAOFactory;
import fr.eni.cach.clinique.dal.DalException;
import fr.eni.cach.clinique.dal.PersonnelDAO;
import fr.eni.cach.clinique.dal.RdvDAO;

public class AppliTestDAO {

	public static void main(String[] args) {
		
		// TOUT EST OK !!!!

		AnimalDAO animalDAO = DAOFactory.getAnimalDAO();
		ClientDAO clientDAO = DAOFactory.getClientDAO();

		try {
			Animal animalId1 = animalDAO.selectById(1);
			Client clientId1 = clientDAO.selectById(1);

			List<Animal> listeAnimaux = animalDAO.selectAnimauxByClient(1);

			System.out.println("\r");
			System.out.println("Animal avec l'id1");
			System.out.println("\r");
			System.out.println(animalId1);

			System.out.println("\r");
			System.out.println("Client avec l'id1 (et sa liste d'animaux)");
			System.out.println("\r");
			System.out.println(clientId1);

			System.out.println("\r");
			System.out.println("Liste des animaux du client avec l'identifiant 1");
			System.out.println("\r");
			System.out.println(listeAnimaux);

			// Insertion d'un animal au client 1
			Animal a1 = new Animal("Tobya", "F", "noir", "Batard", "Chien", 1, null, null, false);
			animalDAO.insert(a1);
			System.out.println(a1.getCodeAnimal());
			System.out.println("\r");
			System.out.println("Nouvelle Liste des animaux du client avec l'identifiant 1 apr�s INSERTION");
			System.out.println("\r");
			listeAnimaux = animalDAO.selectAnimauxByClient(1);
			System.out.println(listeAnimaux);

			System.out.println(a1.getCodeAnimal());

			// Update d'un animal Animal a2= animalDAO.selectById(16);

			a1.setCouleur("gris");
			a1.setSexe("M");
			animalDAO.update(a1);
			System.out.println("\r");
			System.out.println("Nouvelle Liste des animaux du client avec l'identifiant 1 apr�s UPDATE");
			System.out.println("\r");
			listeAnimaux = animalDAO.selectAnimauxByClient(1);
			System.out.println(listeAnimaux);

			// Suppression d'un animal animalDAO.delete(a1);

			System.out.println("\r");
			System.out.println("Liste apr�s SUPPRESSION");
			listeAnimaux = animalDAO.selectAnimauxByClient(1);
			System.out.println(listeAnimaux);

			List<Client> listeClients = clientDAO.selectAll();

			// Ecriture de la liste des clients non archiv�s
			System.out.println("\r");
			System.out.println("Liste des clients non archiv�s");

			for (Client client : listeClients) {
				System.out.println(client);
			}

			Client clienTest = clientDAO.selectById(1);

			System.out.println("Client avec l'ID=1\r");
			System.out.println(clienTest);
			System.out.println("\r");

			List<Client> clientele = clientDAO.selectAll();

			System.out.println("Client�le (clients non archiv�s)\r");
			for (Client client : clientele) {

				System.out.println(client);

			}
			System.out.println("\r");

			Client nouveauClient = new Client();
			nouveauClient.setNomClient("Morel");
			nouveauClient.setPrenomClient("Christophe");
			nouveauClient.setArchive(false);

			clientDAO.insert(nouveauClient);
			clientele.add(nouveauClient);

			System.out.println("Client�le apr�s INSERTION (clients non archiv�s)\r ");
			for (Client client : clientele) {

				System.out.println(client);

			}
			System.out.println("\r");

			nouveauClient.setPrenomClient("Cricri");
			clientDAO.update(nouveauClient);
			System.out.println(nouveauClient.getCodeClient());
			System.out.println("Client�le apr�s UPDATE (clients non archiv�s)\r ");

			for (Client client : clientele) {

				System.out.println(client);

			}
			System.out.println("\r");

			clientDAO.delete(nouveauClient);
			clientele.remove(nouveauClient);

			System.out.println("Client�le apr�s SUPPRESSION (clients non archiv�s)\r ");

			for (Client client : clientele) {

				System.out.println(client);

			}
			System.out.println("\r");

			List<Client> clienteleAvecMotCle = clientDAO.selectByMotCle("dupo");

			System.out.println("Client�le avec mot cl� (clients non archiv�s)\r ");

			for (Client client : clienteleAvecMotCle) {
				System.out.println(client);

			}
			System.out.println("\r");

			System.out.println("Test du personnel \r");
			PersonnelDAO employeDAO = DAOFactory.getPersonelDAO();

			System.out.println("S�lection de l'employ� avec l'id 2");

			Personnel employe = employeDAO.selectById(2);
			System.out.println(employe);
			System.out.println("\r");

			System.out.println("S�lection de tous les employ�s non archiv�s");
			List<Personnel> listeEmployes = employeDAO.selectAll();
			for (Personnel personnel : listeEmployes) {
				System.out.println(personnel);
			}
			System.out.println("\r");

			Veterinaire nouveauVeto = new Veterinaire("Caroline", "Caro", "vet", false);
			employeDAO.insert(nouveauVeto);
			listeEmployes.add(nouveauVeto);
			System.out.println("Insertion de Caroline");
			for (Personnel personnel : listeEmployes) {
				System.out.println(personnel);
			}
			System.out.println("\r");

			System.out.println("Update du mot de passe de Caroline");
			nouveauVeto.setMotPasse("Roca");
			employeDAO.update(nouveauVeto);
			for (Personnel personnel : listeEmployes) {
				System.out.println(personnel);
			}
			System.out.println("\r");
			
			
			//**************************************************************************
			// JUSQU'ICI TOUT VA BIEN
			//**************************************************************************

			System.out.println("Test du RDV \r********************************************");
			RdvDAO rdvDAO = DAOFactory.getRdvDAO();
			
			 System.out.println("S�lection des rendez-vous du veto avec l'ID 2");
			 List<Rdv> listeRdv = new ArrayList<>();
			
			 try {
			 listeRdv = rdvDAO.selectRdvByVeto(2);
			 for (Rdv rdv : listeRdv) {
			 System.out.println(rdv);
			 }
			
			 } catch (DalException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
			
			System.out.println("\rS�lection des rendez-vous du veto avec l'ID 2 et une date donn�e");
			List<Rdv> rdvParVetoDate = new ArrayList<>();
			
			try {
				rdvParVetoDate = rdvDAO.selectRdvByVetoAndDate(2, "2018-04-24");
				for (Rdv rdv : rdvParVetoDate) {
					System.out.println(rdv);
				}
			} catch (DalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// Tr�s important
			Date in =new GregorianCalendar(2018, 3, 30, 14, 04).getTime();
			LocalDateTime dateTest = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
			
			Veterinaire vetoTest = (Veterinaire) employeDAO.selectById(2);
			
			System.out.println("\rTest d'insertion d'un rdv");
			
			Rdv rdvTest = new Rdv(vetoTest,animalId1, dateTest);
			
			rdvDAO.insert(rdvTest);
			System.out.println("*****************************Insertion r�ussie ************************************************");
			
			
			System.out.println("S�lection des rendez-vous du veto avec l'ID 2");
			 List<Rdv> listeRdv1 = new ArrayList<>();
			
			 try {
			 listeRdv1 = rdvDAO.selectRdvByVeto(2);
			 for (Rdv rdv : listeRdv1) {
			 System.out.println(rdv);
			 }
			
			 } catch (DalException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
			 
			 rdvDAO.delete(rdvTest);
			 System.out.println("*****************************Suppression r�ussie\r ************************************************");
			 List<Rdv> listeRdv2 = new ArrayList<>();
				
			 try {
			 listeRdv2 = rdvDAO.selectRdvByVeto(2);
			 for (Rdv rdv : listeRdv2) {
			 System.out.println(rdv);
			 }
			
			 } catch (DalException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }
			 
			
			
			
			
			

		} catch (DalException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		 
		
	}

}
