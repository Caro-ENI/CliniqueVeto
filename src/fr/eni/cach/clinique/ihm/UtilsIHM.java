package fr.eni.cach.clinique.ihm;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
 * 
 * Classe Utilitaire (singleton) de l'IHM de Clinique Vétérinaire
 *
 */
public class UtilsIHM {


	private static UtilsIHM instance = null;
	 
	// xOffset et yOffset fixent les valeurs qui seront utilisées pour placer les JInternalFrames 
	private static final int xOffset = 6; 
	private static final int yOffset = 160;
	
	//Compte le nombre de JInternalFrames Ouvertes
	private static int comptFenetres = 0;

	
	private UtilsIHM() {
	}
	
	/**
	 * Permet de récupérer l'instance de la classe UtilsIHM
	 * @return le singleton utilsIHM
	 */
	public static UtilsIHM getInstance(){
		if (instance == null){
			instance = new UtilsIHM();
		}
		return instance;
	}
	
	/**
	 * Place un composant (component) dans un panel (panel) en utilisant un GridBagLayout
	 * @param component Le composant à placer
	 * @param panel Le panel où on place le composant
	 * @param x la colonne
	 * @param y la ligne
	 * @param width colspan (nb de colonnes occupées)
	 * @param height rowspan (nb de lignes occupées)
	 * @param weightX Proportion en X (par rapport à la largeur totale du panel)
	 * @param fillHorizontal true si on veut remplir la colonne, false sinon.
	 */
	public void addComponentTo(JComponent component, JPanel panel,
							  int x, int y, int width, int height,
							  double weightX, boolean fillHorizontal) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		gbc.weightx = weightX;
		if(fillHorizontal) {
			gbc.fill = GridBagConstraints.BOTH; //horizontal
		}
		gbc.insets = new Insets(7, 10, 5, 10);
		panel.add(component, gbc);
	}
	
	/**
	 * Créateur de JInternalFrames
	 * 
	 * @param titre le titre que l'on veut donner à cette JIF
	 * @param panelGlobal le panel global qu'affichera la JIF
	 * @return une JInternalFrame
	 */
	public JInternalFrame createJIF(String titre, JPanel panelGlobal){
		JInternalFrame maFrame = new JInternalFrame(titre, true, true, true);
		
	
		
		//création du IHM
		maFrame.setContentPane(panelGlobal);
		
		//définition de la taille:
		maFrame.setSize(500,500);
		
		
		// définition de la localisation:
		if (comptFenetres <= 1){
		maFrame.setLocation(xOffset*comptFenetres, yOffset*comptFenetres);			
		} else {
		maFrame.setLocation(xOffset+(comptFenetres*10), yOffset+(comptFenetres*10));
		}
		
		//on compte le nombre de fenêtres pour les décaler ensuite les unes par rapport aux autres 
		comptFenetres++;
		
		return maFrame;
	}
	
	/**
	 * Permet d'appliquer un LookandFeel à une fenêtre
	 * @param maFrame
	 */
	public void applyLAF(JFrame maFrame) {
		String look="javax.swing.plaf.metal.MetalLookAndFeel";
		
		try {
			
			UIManager.setLookAndFeel(look);
			SwingUtilities.updateComponentTreeUI(maFrame);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


}
