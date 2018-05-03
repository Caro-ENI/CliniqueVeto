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
 * Classe Utilitaire (singleton) de l'IHM de Clinique Vétérinaire
 *
 */
public class UtilsIHM {

	/*
	 * ORGANISATION DE LA CLASSE : - Singleton - Attributs - Méthodes Annexes
	 */

	/* ************** SINGLETON ************** */
	private static UtilsIHM instance = null;

	private UtilsIHM() {
	}

	/**
	 * Permet de récupérer l'instance de la classe UtilsIHM
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

	// xOffset et yOffset fixent les valeurs qui seront utilisées pour placer les
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
	 *            Le composant à placer
	 * @param panel
	 *            Le panel où on place le composant
	 * @param x
	 *            la colonne
	 * @param y
	 *            la ligne
	 * @param width
	 *            colspan (nb de colonnes occupées)
	 * @param height
	 *            rowspan (nb de lignes occupées)
	 * @param weightX
	 *            Proportion en X (par rapport à la largeur totale du panel)
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
	 * Créateur de JInternalFrames
	 * 
	 * @param titre
	 *            le titre que l'on veut donner à cette JIF
	 * @param panelGlobal
	 *            le panel global qu'affichera la JIF
	 * @return une JInternalFrame
	 */
	public JInternalFrame createJIF(String titre, JPanel panelGlobal) {
		JInternalFrame maFrame = new JInternalFrame(titre, true, true, true);

		// création du IHM
		maFrame.setContentPane(panelGlobal);

		// définition de la taille:
		maFrame.setSize(500, 500);

		// définition de la localisation:
		if (comptFenetres <= 1) {
			maFrame.setLocation(xOffset * comptFenetres, yOffset * comptFenetres);
		} else {
			maFrame.setLocation(xOffset + (comptFenetres * 10), yOffset + (comptFenetres * 10));
		}

		// on compte le nombre de fenêtres pour les décaler ensuite les unes par rapport
		// aux autres
		comptFenetres++;

		return maFrame;
	}

	/**
	 * Permet d'appliquer un LookandFeel à une fenêtre
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

		JButton bttAvecIcone = new JButton(textBtt);
		bttAvecIcone.setVerticalTextPosition(SwingConstants.BOTTOM);
		bttAvecIcone.setHorizontalTextPosition(SwingConstants.CENTER);
		bttAvecIcone.setPreferredSize(new Dimension(105, 60));

		switch (icone) {
		case AJOUTER:
			try {
				Image ajouter = ImageIO
						.read((this.getClass().getResource("/fr/eni/cach/clinique/Ressources/ajouter.png")));
				ajouter = ajouter.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				bttAvecIcone.setIcon(new ImageIcon(ajouter));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case ANNULER:
			try {
				Image annuler = ImageIO
						.read((this.getClass().getResource("/fr/eni/cach/clinique/Ressources/annuler.png")));
				annuler = annuler.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				bttAvecIcone.setIcon(new ImageIcon(annuler));
				
				} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case DOSSIER:
			try {
				Image dossier = ImageIO
						.read((this.getClass().getResource("/fr/eni/cach/clinique/Ressources/dossier.png")));
				dossier = dossier.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				bttAvecIcone.setIcon(new ImageIcon(dossier));
				//on le redimessionne ici car il est très long
				bttAvecIcone.setPreferredSize(new Dimension(130, 60));
				
				} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case RECHERCHER:
			try {
				Image rechercher = ImageIO
						.read((this.getClass().getResource("/fr/eni/cach/clinique/Ressources/rechercher.png")));
				rechercher = rechercher.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				bttAvecIcone.setIcon(new ImageIcon(rechercher));
				
				} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case REINIT:
			try {
				Image reinit = ImageIO
						.read((this.getClass().getResource("/fr/eni/cach/clinique/Ressources/reinit.png")));
				reinit = reinit.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				bttAvecIcone.setIcon(new ImageIcon(reinit));
				
				} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case VALIDER:
			try {
				Image valider = ImageIO
						.read((this.getClass().getResource("/fr/eni/cach/clinique/Ressources/valider.png")));
				valider = valider.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				bttAvecIcone.setIcon(new ImageIcon(valider));
				
				} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case SUPPRIMER:
			try {
				Image supprimer = ImageIO
						.read((this.getClass().getResource("/fr/eni/cach/clinique/Ressources/supprimer.png")));
				supprimer = supprimer.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				bttAvecIcone.setIcon(new ImageIcon(supprimer));
				
				} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case EDITER:
			try {
				Image supprimer = ImageIO
						.read((this.getClass().getResource("/fr/eni/cach/clinique/Ressources/editer.png")));
				supprimer = supprimer.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH);
				bttAvecIcone.setIcon(new ImageIcon(supprimer));
				
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
		AJOUTER, ANNULER, DOSSIER, RECHERCHER, REINIT, VALIDER, SUPPRIMER, EDITER;
	}
}
