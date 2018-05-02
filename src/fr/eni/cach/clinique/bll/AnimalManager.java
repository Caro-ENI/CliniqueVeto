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
	 * Méthode qui permet d'ajouter un animal dans la base 
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
			throw new BLLException("L'Animal n'a pas pu être ajouté");
		}
		
	}
	
	/**
	 * Méthode qui renvoie la liste des animaux appartenant à un client 
	 * @param codeClient
	 * @throws BLLException 
	 */
	public List<Animal> selectAnimaux(int codeClient) throws BLLException {
		List<Animal> animauxDuClient = new ArrayList<>();
		
		try {
			animauxDuClient = daoAnimal.selectAnimauxByClient(codeClient);
		} catch (DalException e) {
			e.printStackTrace();
			throw new BLLException("Erreur d'accès aux données.");
		}
		
		return animauxDuClient;
	}
	
	/**
	 * Méthode qui met à jour un animal
	 * @param animal
	 * @throws BLLException 
	 */
	public void updateAnimal(Animal animal) throws BLLException {
		
		this.validerAnimal(animal);
		
		try {
			daoAnimal.update(animal);
		} catch (DalException e) {
			e.printStackTrace();
			throw new BLLException("Les informations de l'animal n'ont pas pu être mises à jour.");
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
	}

	/* ******* SOUS-METHODES DE VERIFICATION ******* */

 	private void verificationNomAnimal(Animal animal) throws BLLException{
 		if (animal.getNomAnimal() == null || animal.getNomAnimal().trim().isEmpty()) {
			throw new BLLException("Le Nom de l'animal doit être renseigné.");
		} else if (animal.getNomAnimal().length() > 30) {
			throw new BLLException("Le Nom de l'animal ne doit pas excéder 30 caractères");
		}
 	}
 	private void verificationCouleur(Animal animal) throws BLLException{
 		if (animal.getCouleur().length() > 20) {
			throw new BLLException("La couleur de l'animal ne doit pas excéder 20 caractères");
		}
 	}
 	private void verificationSexe(Animal animal) throws BLLException{
 		if (animal.getSexe() == null || animal.getSexe().trim().isEmpty()) {
			throw new BLLException("Le sexe de l'animal doit être renseigné.");
		} else if ((animal.getSexe().length() > 1)){
			throw new BLLException("A.Le sexe de l'animal doit être M pour un mâle, F pour une femelle et H pour un hermaphrodite.");
		} else if ( ('H' != animal.getSexe().charAt(0)) && ('M' != animal.getSexe().charAt(0)) && ('F' != animal.getSexe().charAt(0)) ) {
		
			throw new BLLException("B.Le sexe de l'animal doit être M pour un mâle, F pour une femelle et H pour un hermaphrodite.");
		}
 	}
 	private void verificationEspece(Animal animal) throws BLLException{
 		if (animal.getEspece() == null || animal.getEspece().trim().isEmpty()) {
			throw new BLLException("L'espèce de l'animal doit être renseignée.");
		} else if (animal.getEspece().length() > 20) {
			throw new BLLException("L'espèce de l'animal ne doit pas excéder 20 caractères");
		}
 	}
 	private void verificationRace(Animal animal) throws BLLException{
 		if (animal.getRace() == null || animal.getRace().trim().isEmpty()) {
			throw new BLLException("La race de l'animal doit être renseignée.");
		} else if (animal.getRace().length() > 20) {
			throw new BLLException("La race de l'animal ne doit pas excéder 20 caractères");
		}
 	}
 	private void verificationTatouage(Animal animal) throws BLLException{
 		if (animal.getTatouage() != null && animal.getTatouage().length() > 10) {
			throw new BLLException("Le tatouage de l'animal ne doit pas excéder 10 caractères");
		}
 	}
 	
}
