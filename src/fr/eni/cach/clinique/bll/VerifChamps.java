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

	public static VerifChamps getInstance() {
		if (instance == null) {
			instance = new VerifChamps();
		}
		return instance;
	}

	/* ***************************************** */

	public boolean isNameValid(String text) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isPwdValid(String text) {
		// TODO Auto-generated method stub
		return true;
	}


	
	
}
