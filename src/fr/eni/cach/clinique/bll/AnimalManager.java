package fr.eni.cach.clinique.bll;

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

	public void addAnimal(Animal animal) throws BLLException{
		this.validerAnimal(animal);
		
		try {
			daoAnimal.insert(animal);
		} catch (DalException e) {
			e.printStackTrace();
			throw new BLLException("L'Animal n'a pas pu être ajouté");
		}
		
	}
	
	/* ******* METHODE DE VERIFICATION ******* */

	/**
	 * Vérifie que les attributs de l'animal passé en paramètre sont valides
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
		verificationAntecedents(animal);
		
	}

	/* ******* SOUS-METHODES DE VERIFICATION ******* */

 	private void verificationNomAnimal(Animal animal){
 		
 	}
 	private void verificationCouleur(Animal animal){
 		
 	}
 	private void verificationSexe(Animal animal){
 		
 	}
 	private void verificationEspece(Animal animal){
 		
 	}
 	private void verificationRace(Animal animal){
 		
 	}
 	private void verificationTatouage(Animal animal){
 		
 	}
 	private void verificationAntecedents(Animal animal){
 		
 	}
}
