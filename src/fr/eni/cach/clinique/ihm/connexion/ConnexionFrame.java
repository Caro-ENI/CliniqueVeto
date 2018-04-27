package fr.eni.cach.clinique.ihm.connexion;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import fr.eni.cach.clinique.ihm.UtilsIHM;
import fr.eni.cach.clinique.ihm.cliniqueVeto.ActualiteFrame;
import fr.eni.cach.clinique.ihm.cliniqueVeto.CliniqueVetoFrame2;

public class ConnexionFrame extends JFrame {

	private static final long serialVersionUID = -4761827877265050036L;

	private UtilsIHM utilsIHM = UtilsIHM.getInstance();

	private JButton bttOK;
	private JLabel lblNom;
	private JLabel lblMdp;
	private JTextField tfNom;
	private JPasswordField tfMdp;

	private JPanel panelGlobal;

	/**
	 * Constructeur de la fen�tre de Connexion (1)
	 */
	public ConnexionFrame() {

		this.setSize(400, 175);
		this.setTitle("Connexion");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		// Initialisation des champs
		this.createLblNom();
		this.createLblMdp();
		this.createTfNom();
		this.createTfMdp();
		this.createBttOK();

		// Initialisation du panel
		this.createPanelGlobal();

		// d�finition du panel g�n�ral
		this.setContentPane(getPanelGlobal());

	}

	// *********** CREATION PANELS ***************

	/**
	 * Cr�e le panel global
	 */
	private void createPanelGlobal() {
		panelGlobal = new JPanel(new GridBagLayout());

		utilsIHM.addComponentTo(getLblNom(), panelGlobal, 0, 0, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTfNom(), panelGlobal, 1, 0, 1, 1, 0.8, true);

		utilsIHM.addComponentTo(getLblMdp(), panelGlobal, 0, 1, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTfMdp(), panelGlobal, 1, 1, 1, 1, 0.8, true);

		utilsIHM.addComponentTo(getBttOK(), panelGlobal, 2, 2, 1, 1, 0.2, false);

	}

	// *********** CREATION BOUTONS **************

	/**
	 * cr�e le bouton Valider
	 */
	private void createBttOK() {
		bttOK = new JButton("Valider");
		bttOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO changer avec le couple nom/mdp de l'utilisateur
				// au clic du bttOK ouvre la fen�tre cliniqueVeto
				// avec le r�le rentr� dans le champ nom

				CliniqueVetoFrame2 maCliniqueVeto2 = CliniqueVetoFrame2.getInstance(getTfNom().getText());
				maCliniqueVeto2.setVisible(true);
				ConnexionFrame.this.setVisible(false);
				
				ActualiteFrame monActu = new ActualiteFrame(getTfNom().getText());
				monActu.setVisible(true);
				monActu.setLocationRelativeTo(null);

			}
		});
	}

	/**
	 * cr�e le champ de MDP
	 */
	private void createTfMdp() {
		tfMdp = new JPasswordField("", 10);
	}

	/**
	 * cr�e le champ de nom
	 */
	private void createTfNom() {
		tfNom = new JTextField("", 30);
	}

	/**
	 * cr�e le label MDP
	 */
	private void createLblMdp() {
		lblMdp = new JLabel("Mot de passe");
	}

	/**
	 * Cr�e le label Nom
	 */
	private void createLblNom() {
		lblNom = new JLabel("Nom");
	}

	// *********** GETTERS **************

	/**
	 * Renvoie le bouton valider
	 * 
	 * @return
	 */
	private JButton getBttOK() {
		return bttOK;
	}

	/**
	 * Renvoie le champ de MDP
	 * 
	 * @return
	 */
	private JTextField getTfMdp() {
		return tfMdp;
	}

	/**
	 * Renvoie le libele MDP
	 * 
	 * @return
	 */
	private JLabel getLblMdp() {
		return lblMdp;
	}

	/**
	 * Renvoie le champ de nom
	 * 
	 * @return
	 */
	private JTextField getTfNom() {
		return tfNom;
	}

	/**
	 * Renvoie le label NOM
	 * 
	 * @return
	 */
	private JLabel getLblNom() {
		return lblNom;
	}

	/**
	 * Revoie le panel Global
	 * 
	 * @return
	 */
	private JPanel getPanelGlobal() {
		return panelGlobal;
	}

}
