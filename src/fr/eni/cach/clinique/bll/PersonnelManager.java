package fr.eni.cach.clinique.bll;

import java.util.ArrayList;
import java.util.List;


import fr.eni.cach.clinique.bo.Personnel;
import fr.eni.cach.clinique.bo.Veterinaire;
import fr.eni.cach.clinique.dal.DAOFactory;
import fr.eni.cach.clinique.dal.DalException;
import fr.eni.cach.clinique.dal.PersonnelDAO;

public class PersonnelManager {

	private PersonnelDAO daoPers;
	// private VerifChamps verif = VerifChamps.getInstance();

	/* ******* SINGLETON ******* */
	private static PersonnelManager instance = null;

	private PersonnelManager() {
		daoPers = DAOFactory.getPersonelDAO();

	}

	public static PersonnelManager getInstance() {
		if (instance == null) {
			instance = new PersonnelManager();
		}
		return instance;
	}

	/* ******* METHODES DU MANAGER ******* */

	public void addPersonnel(Personnel pers) throws BLLException {

		validerPersonnel(pers);

		try {
			daoPers.insert(pers);
		} catch (DalException e) {
			throw new BLLException("Erreur lors de l'insertion en BDD du personnel.");
		}

	}
	
	public void updatePersonnel (Personnel pers) throws BLLException {
		
		validerPersonnel(pers);
		
		try {
			daoPers.update(pers);
		} catch (DalException e) {
			throw new BLLException("Erreur lors de la mise à jour en BDD du personnel.");
		}
		
		
	}
	
	public List <Personnel> getListePersonnel() throws BLLException {
		
		List <Personnel> listePersonnels = new ArrayList <> ();
		
		try {
			listePersonnels = daoPers.selectAll();
		} catch (DalException e) {
			throw new BLLException ("Erreur accès données : la liste du personnel n'est pas récupérable");
		}
		
		
		return listePersonnels;
		
		
	}
	
	public Personnel getPersonnel(int codePers) throws BLLException {
		
		Personnel pers = null;
		
		try {
			pers = daoPers.selectById(codePers);
		} catch (DalException e) {
			throw new BLLException ("Erreur accès données : la liste du personnel n'est pas récupérable");
		}
	
		return pers;	
	}
	
	public List <Veterinaire> getListeVeto () throws BLLException {
		List <Veterinaire> listeVetos = new ArrayList <> ();
		List <Personnel> listePersonnels = getListePersonnel();
		
		for (Personnel personnel : listePersonnels) {
			if (personnel instanceof Veterinaire){
				listeVetos.add((Veterinaire) personnel);
				
			}
			
		}
	
		return listeVetos;
		
	}
	
		
	
	
	

	// *********** METHODES DE VERIFICATION ******************

	private void validerPersonnel(Personnel pers) throws BLLException {

		verificationNomPersonnel(pers);

		verificationMotPasse(pers);

		verificationRole(pers);

	}

	private void verificationRole(Personnel pers) throws BLLException {
		if (pers.getRole() == null || pers.getRole().trim().isEmpty()) {
			throw new BLLException("Il est obligatoire d'attribuer un rôle au nouveau salarié : sec, vet ou adm.");
		}

		else if (!(pers.getRole().equals("sec") || pers.getRole().equals("adm") || pers.getRole().equals("vet"))) {

			throw new BLLException("Le rôle d'un nouveau salarié doit forcément être : sec, vet ou adm.");
		}
	}

	private void verificationMotPasse(Personnel pers) throws BLLException {
		if (pers.getMotPasse() == null || pers.getMotPasse().trim().isEmpty()) {
			throw new BLLException("Le mot de passe est obligatoire");
		} else if (pers.getMotPasse().length() > 10) {
			throw new BLLException("Le mot de passe doit comporter 10 caractères maximum");
		}

	}

	private void verificationNomPersonnel(Personnel pers) throws BLLException {
		if (pers.getNom() == null || pers.getNom().trim().isEmpty()) {
			throw new BLLException("Le nom est obligatoire");
		} else if (pers.getNom().length() > 30) {
			throw new BLLException("Le nom doit comporter 30 caractères maximum");
		}

	}

}
