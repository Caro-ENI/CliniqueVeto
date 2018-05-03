package fr.eni.cach.clinique.test;

import javax.swing.SwingUtilities;

import fr.eni.cach.clinique.ihm.connexion.ConnexionFrame;

public class AppliTestIHM {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				/* **** OUVERTUR DE L'APPLI **** */
				ConnexionFrame maConnection = new ConnexionFrame();
				maConnection.setVisible(true);

			}
		});

	}

}
