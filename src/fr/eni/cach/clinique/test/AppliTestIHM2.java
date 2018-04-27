package fr.eni.cach.clinique.test;

import javax.swing.SwingUtilities;

import fr.eni.cach.clinique.ihm.connexion.ConnexionFrame;

public class AppliTestIHM2 {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				
				/* **** OUVERTUR DE L'APPLI **** */
				ConnexionFrame maConnection = new ConnexionFrame();
				maConnection.setVisible(true);

				/* **** AUTRES TESTS **** */
//				PriseRDVFrame mesRDV = new PriseRDVFrame();
//				mesRDV.setVisible(true);
				
//				CliniqueVetoFrame2 maCliniqueVeto2 = CliniqueVetoFrame2.getInstance("vet");
//				maCliniqueVeto2.setVisible(true);
				
				

			}
		});


	}

}
