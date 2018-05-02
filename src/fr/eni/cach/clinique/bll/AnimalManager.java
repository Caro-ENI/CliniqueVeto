package fr.eni.cach.clinique.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.cach.clinique.bo.Animal;
import fr.eni.cach.clinique.dal.AnimalDAO;
import fr.eni.cach.clinique.dal.DAOFactory;
import fr.eni.cach.clinique.dal.DalException;

public class AnimalManager {

	/* ******* SINGLETON ******* */
	
	private AnimalDAO daoAnimal;
	
	private static AnimalManager instance = null;
	
	private AnimalManager() {
		daoAnimal = DAOFactory.getAnimalDAO();
	}
	
	public static AnimalManager getInstance(){
		if (instance == null) {
			instance = new AnimalManager();
		}
		return instance;
	}

	/* ******* METHODES DU MANAGER ******* */

	/**
	 * M�thode qui permet d'ajouter un animal dans la base 
	 * si l'ensemble de ses informations sont correctes
	 * @param animal
	 * @throws BLLException
	 */
	public void addAnimal(Animal animal) throws BLLException{
		this.validerAnimal(animal);
		
		try {
			daoAnimal.insert(animal);
		} catch (DalException e) {
			e.printStackTrace();
			throw new BLLException("L'Animal n'a pas pu �tre ajout�");
		}
		
	}
	
	/**
	 * M�thode qui renvoie la liste des animaux appartenant � un client 
	 * @param codeClient
	 * @throws BLLException 
	 */
	public List<Animal> selectAnimaux(int codeClient) throws BLLException {
		List<Animal> animauxDuClient = new ArrayList<>();
		
		try {
			animauxDuClient = daoAnimal.selectAnimauxByClient(codeClient);
		} catch (DalException e) {
			e.printStackTrace();
			throw new BLLException("Erreur d'acc�s aux donn�es.");
		}
		
		return animauxDuClient;
	}
	
	/**
	 * M�thode qui met � jour un animal
	 * @param animal
	 * @throws BLLException 
	 */
	public void updateAnimal(Animal animal) throws BLLException {
		
		this.validerAnimal(animal);
		
		try {
			daoAnimal.update(animal);
		} catch (DalException e) {
			e.printStackTrace();
			throw new BLLException("Les informations de l'animal n'ont pas pu �tre mises � jour.");
		}
	}
	
	/* ******* METHODE DE VERIFICATION ******* */

	/**
	 * V�rifie que les attributs de l'animal pass� en param�tre sont valides
	 * 
	 * @param animal
	 * @throws BLLException 
	 */
 	private void validerAnimal(Animal animal) throws BLLException {
		verificationNomAnimal(animal);
		verificationCouleur(animal);
		verificationSexe(animal);
		verificationEspece(animal);
		verificationRace(animal);
		verificationTatouage(animal);		
	}

	/* ******* SOUS-METHODES DE VERIFICATION ******* */

 	private void verificationNomAnimal(Animal animal) throws BLLException{
 		if (animal.getNomAnimal() == null || animal.getNomAnimal().trim().isEmpty()) {
			throw new BLLException("Le Nom de l'animal doit �tre renseign�.");
		} else if (animal.getNomAnimal().length() > 30) {
			throw new BLLException("Le Nom de l'animal ne doit pas exc�der 30 caract�res");
		}
 	}
 	private void verificationCouleur(Animal animal) throws BLLException{
 		if (animal.getCouleur().length() > 20) {
			throw new BLLException("La couleur de l'animal ne doit pas exc�der 20 caract�res");
		}
 	}
 	private void verificationSexe(Animal animal) throws BLLException{
 		if (animal.getSexe() == null || animal.getSexe().trim().isEmpty()) {
			throw new BLLException("Le sexe de l'animal doit �tre renseign�.");
		} else if ((animal.getSexe().length() > 1)){
			throw new BLLException("A.Le sexe de l'animal doit �tre M pour un m�le, F pour une femelle et H pour un hermaphrodite.");
		} else if ( ('H' != animal.getSexe().charAt(0)) && ('M' != animal.getSexe().charAt(0)) && ('F' != animal.getSexe().charAt(0)) ) {
		
			throw new BLLException("B.Le sexe de l'animal doit �tre M pour un m�le, F pour une femelle et H pour un hermaphrodite.");
		}
 	}
 	private void verificationEspece(Animal animal) throws BLLException{
 		if (animal.getEspece() == null || animal.getEspece().trim().isEmpty()) {
			throw new BLLException("L'esp�ce de l'animal doit �tre renseign�e.");
		} else if (animal.getEspece().length() > 20) {
			throw new BLLException("L'esp�ce de l'animal ne doit pas exc�der 20 caract�res");
		}
 	}
 	private void verificationRace(Animal animal) throws BLLException{
 		if (animal.getRace() == null || animal.getRace().trim().isEmpty()) {
			throw new BLLException("La race de l'animal doit �tre renseign�e.");
		} else if (animal.getRace().length() > 20) {
			throw new BLLException("La race de l'animal ne doit pas exc�der 20 caract�res");
		}
 	}
 	private void verificationTatouage(Animal animal) throws BLLException{
 		if (animal.getTatouage() != null && animal.getTatouage().length() > 10) {
			throw new BLLException("Le tatouage de l'animal ne doit pas exc�der 10 caract�res");
		}
 	}
 	
}
