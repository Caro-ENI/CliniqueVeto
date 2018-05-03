package fr.eni.cach.clinique.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 
 * Classe Utilitaire (singleton) de l'IHM de Clinique V�t�rinaire
 *
 */
public class UtilsIHM {

	/*
	 * ORGANISATION DE LA CLASSE : - Singleton - Attributs - M�thodes Annexes
	 */

	/* ************** SINGLETON ************** */
	private static UtilsIHM instance = null;

	private UtilsIHM() {
	}

	/**
	 * Permet de r�cup�rer l'instance de la classe UtilsIHM
	 * 
	 * @return le singleton utilsIHM
	 */
	public static UtilsIHM getInstance() {
		if (instance == null) {
			instance = new UtilsIHM();
		}
		return instance;
	}

	/* *************************************** */

	/* ************** ATTRIBUTS ************** */

	// xOffset et yOffset fixent les valeurs qui seront utilis�es pour placer les
	// JInternalFrames
	private static final int xOffset = 6;
	private static final int yOffset = 160;

	// Compte le nombre de JInternalFrames Ouvertes
	private static int comptFenetres = 0;

	/**
	 * Place un composant (component) dans un panel (panel) en utilisant un
	 * GridBagLayout
	 * 
	 * @param component
	 *            Le composant � placer
	 * @param panel
	 *            Le panel o� on place le composant
	 * @param x
	 *            la colonne
	 * @param y
	 *            la ligne
	 * @param width
	 *            colspan (nb de colonnes occup�es)
	 * @param height
	 *            rowspan (nb de lignes occup�es)
	 * @param weightX
	 *            Proportion en X (par rapport � la largeur totale du panel)
	 * @param fillHorizontal
	 *            true si on veut remplir la colonne, false sinon.
	 */
	public void addComponentTo(JComponent component, JPanel panel, int x, int y, int width, int height, double weightX,
			boolean fillHorizontal) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		gbc.weightx = weightX;
		if (fillHorizontal) {
			gbc.fill = GridBagConstraints.BOTH; // horizontal
		}
		gbc.insets = new Insets(7, 10, 5, 10);
		panel.add(component, gbc);
	}

	/**
	 * Cr�ateur de JInternalFrames
	 * 
	 * @param titre
	 *            le titre que l'on veut donner � cette JIF
	 * @param panelGlobal
	 *            le panel global qu'affichera la JIF
	 * @return une JInternalFrame
	 */
	public JInternalFrame createJIF(String titre, JPanel panelGlobal) {
		JInternalFrame maFrame = new JInternalFrame(titre, true, true, true);

		// cr�ation du IHM
		maFrame.setContentPane(panelGlobal);

		// d�finition de la taille:
		maFrame.setSize(500, 500);

		// d�finition de la localisation:
		if (comptFenetres <= 1) {
			maFrame.setLocation(xOffset * comptFenetres, yOffset * comptFenetres);
		} else {
			maFrame.setLocation(xOffset + (comptFenetres * 10), yOffset + (comptFenetres * 10));
		}

		// on compte le nombre de fen�tres pour les d�caler ensuite les unes par rapport
		// aux autres
		comptFenetres++;

		return maFrame;
	}

	/**
	 * Permet d'appliquer un LookandFeel � une fen�tre
	 * 
	 * @param maFrame
	 */
	public void applyLAF(JFrame maFrame) {
		String look = "javax.swing.plaf.metal.MetalLookAndFeel";

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

	public JButton createBttAvecIcon(String textBtt, IconesEnum icone) {

		JButton bttAvecIcone = null;

		switch (icone) {
		case AJOUTER:
			try {
				Image ajouter = ImageIO
						.read((this.getClass().getResource("/fr/eni/cach/clinique/Ressources/ajouter.png")));
				ajouter = ajouter.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				bttAvecIcone = new JButton(textBtt, new ImageIcon(ajouter));
				bttAvecIcone.setVerticalTextPosition(SwingConstants.BOTTOM);
				bttAvecIcone.setHorizontalTextPosition(SwingConstants.CENTER);
				bttAvecIcone.setSize(50, 45);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case ANNULER:
			try {
				Image ajouter = ImageIO
						.read((this.getClass().getResource("/fr/eni/cach/clinique/Ressources/annuler.png")));
				ajouter = ajouter.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				bttAvecIcone = new JButton(textBtt, new ImageIcon(ajouter));
				bttAvecIcone.setVerticalTextPosition(SwingConstants.BOTTOM);
				bttAvecIcone.setHorizontalTextPosition(SwingConstants.CENTER);
				bttAvecIcone.setSize(50, 45);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case DOSSIER:
			try {
				Image ajouter = ImageIO
						.read((this.getClass().getResource("/fr/eni/cach/clinique/Ressources/dossier.png")));
				ajouter = ajouter.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				bttAvecIcone = new JButton(textBtt, new ImageIcon(ajouter));
				bttAvecIcone.setVerticalTextPosition(SwingConstants.BOTTOM);
				bttAvecIcone.setHorizontalTextPosition(SwingConstants.CENTER);
				bttAvecIcone.setSize(50, 45);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case RECHERCHER:
			try {
				Image ajouter = ImageIO
						.read((this.getClass().getResource("/fr/eni/cach/clinique/Ressources/rechercher.png")));
				ajouter = ajouter.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				bttAvecIcone = new JButton(textBtt, new ImageIcon(ajouter));
				bttAvecIcone.setVerticalTextPosition(SwingConstants.BOTTOM);
				bttAvecIcone.setHorizontalTextPosition(SwingConstants.CENTER);
				bttAvecIcone.setSize(50, 45);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case REINIT:
			try {
				Image ajouter = ImageIO
						.read((this.getClass().getResource("/fr/eni/cach/clinique/Ressources/reinit.png")));
				ajouter = ajouter.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				bttAvecIcone = new JButton(textBtt, new ImageIcon(ajouter));
				bttAvecIcone.setVerticalTextPosition(SwingConstants.BOTTOM);
				bttAvecIcone.setHorizontalTextPosition(SwingConstants.CENTER);
				bttAvecIcone.setSize(50, 45);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case VALIDER:
			try {
				Image ajouter = ImageIO
						.read((this.getClass().getResource("/fr/eni/cach/clinique/Ressources/valider.png")));
				ajouter = ajouter.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				bttAvecIcone = new JButton(textBtt, new ImageIcon(ajouter));
				bttAvecIcone.setVerticalTextPosition(SwingConstants.BOTTOM);
				bttAvecIcone.setHorizontalTextPosition(SwingConstants.CENTER);
				bttAvecIcone.setSize(50, 45);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case SUPPRIMER:
			try {
				Image ajouter = ImageIO
						.read((this.getClass().getResource("/fr/eni/cach/clinique/Ressources/supprimer.png")));
				ajouter = ajouter.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				bttAvecIcone = new JButton(textBtt, new ImageIcon(ajouter));
				bttAvecIcone.setVerticalTextPosition(SwingConstants.BOTTOM);
				bttAvecIcone.setHorizontalTextPosition(SwingConstants.CENTER);
				bttAvecIcone.setPreferredSize(new Dimension(100, 60));
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			bttAvecIcone = new JButton(textBtt);
			break;
		}

		return bttAvecIcone;
	}

	// *************** CLASSE INTERNE ENUMERATION ***************

	public enum IconesEnum {
		AJOUTER, ANNULER, DOSSIER, RECHERCHER, REINIT, VALIDER, SUPPRIMER;
	}
}
