package fr.eni.cach.clinique.bll;

/**
 * 
 * Classe (singleton) regroupant les méthodes de vérification des champs
 * utilisateurs
 *
 */
public class VerifChamps {

	/* ******* CONSTRUCTION DU SINGLETON ******* */

	private static VerifChamps instance = null;

	private VerifChamps() {
	}

	public VerifChamps getInstance() {
		if (instance == null) {
			instance = new VerifChamps();
		}
		return instance;
	}

	/* ***************************************** */

	
	
}
