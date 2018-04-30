package fr.eni.cach.clinique.bll.Utilitaires;

/**
 * 
 * Classe (singleton) regroupant les m�thodes de v�rification des champs
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
	 * V�rifie que le nom entr� est au bon format
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
	 * V�rifie que le mot de passe entr� est au bon format
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
