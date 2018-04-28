package fr.eni.cach.clinique.bll;

/**
 * 
 * Classe (singleton) regroupant les m�thodes de v�rification des donn�es
 * envoy�es � la BDD
 *
 */
public class VerfiMetier {

	/* ******* CONSTRUCTION DU SINGLETON ******* */

	private static VerfiMetier instance = null;

	private VerfiMetier() {
	}

	public VerfiMetier getInstance() {
		if (instance == null) {
			instance = new VerfiMetier();
		}
		return instance;
	}

	/* ***************************************** */

}
