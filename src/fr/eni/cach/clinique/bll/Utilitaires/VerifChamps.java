package fr.eni.cach.clinique.bll.Utilitaires;

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

	//***************VERIF DES CHAMPS DE CONNEXION *********************************
	
	/**
	 * Vérifie que le nom entré est au bon format
	 * @param text
	 * @return
	 */
	public boolean isNamePersonelValid(String nom) {
		boolean nameValid = true;
		if (nom.length() > 30 ){
			nameValid = false;
		}
		return nameValid;
	}

	/**
	 * Vérifie que le mot de passe entré est au bon format
	 * @param psw
	 * @return
	 */
	public boolean isPwdValid(String psw) {
		boolean pswValid = true;
		if (psw.length() > 10) {
			pswValid = false;
		}
		return pswValid;
	}



	
	
}
