package fr.eni.cach.clinique.ihm.connexion;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import fr.eni.cach.clinique.bll.Utilitaires.VerifChamps;
import fr.eni.cach.clinique.ihm.UtilsIHM;
import fr.eni.cach.clinique.ihm.cliniqueVeto.CliniqueVetoFrame2;

public class ConnexionFrame extends JFrame {
	
	// *********** ATTRIBUTS ****************************

	private static final long serialVersionUID = -4761827877265050036L;

	private UtilsIHM utilsIHM = UtilsIHM.getInstance();

	private JButton bttOK;
	
	private JLabel lblNom;
	private JLabel lblMdp;
	
	private JTextField tfNom;
	private JPasswordField tfMdp;

	private JPanel panelGlobal;
	
	// *********** CONSTRUCTEUR PRINCIPAL ***************

	/**
	 * Constructeur de la fenêtre de Connexion (1)
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

		// définition du panel général
		this.setContentPane(getPanelGlobal());

	}
	
	// *********** CREATION PANELS **********************

	/**
	 * Crée le panel global
	 */
	private void createPanelGlobal() {
		panelGlobal = new JPanel(new GridBagLayout());

		utilsIHM.addComponentTo(getLblNom(), panelGlobal, 0, 0, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTfNom(), panelGlobal, 1, 0, 1, 1, 0.8, true);

		utilsIHM.addComponentTo(getLblMdp(), panelGlobal, 0, 1, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTfMdp(), panelGlobal, 1, 1, 1, 1, 0.8, true);

		utilsIHM.addComponentTo(getBttOK(), panelGlobal, 2, 2, 1, 1, 0.2, false);

	}

	// *********** CREATION BOUTONS *********************
	
	/**
	 * crée le bouton Valider
	 */
	private void createBttOK() {
		bttOK = new JButton("Valider");
		bttOK.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ouvertureClinique();
			}
		});
	}

	// *********** CREATION LIBELLES ********************

	/**
	 * crée le label MDP
	 */
	private void createLblMdp() {
		lblMdp = new JLabel("Mot de passe");
	}

	/**
	 * Crée le label Nom
	 */
	private void createLblNom() {
		lblNom = new JLabel("Nom");
	}

	// *********** CREATION ZONES DE TEXTE **************

	/**
	 * crée le champ de MDP
	 */
	private void createTfMdp() {
		tfMdp = new JPasswordField("", 10);
		tfMdp.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if ('\n' == (e.getKeyChar())) {
					ouvertureClinique();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

		});
	}

	/**
	 * crée le champ de nom
	 */
	private void createTfNom() {
		tfNom = new JTextField("", 30);
		// s'active si on appuie sur ENTRER quand on est dans le champ
		tfNom.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if ('\n' == (e.getKeyChar())) {
					ouvertureClinique();
				}
			}

			// méthodes non ytilisées ici
			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

		});

	}

	// *********** METHODES *****************************

	/**
	 * Ouvre la fenêtre principale de l'appication ainsi que celle des
	 * actualités permet également de masquer la fenêtre de connexion
	 */
	private void ouvertureClinique() {

		// Vérification que les entrées utilisateurs sont au bon format
		if (VerifChamps.getInstance().isNamePersonelValid(getTfNom().getText())
				&& VerifChamps.getInstance().isPwdValid(getTfMdp().getText())) {

			// validation que l'utilisateur est trouvé en BDD avec
			// VerifMetier....
			if (true) {

				// TODO changer avec le couple nom/mdp de l'utilisateur
				// au clic du bttOK ouvre la fenêtre cliniqueVeto
				// avec le rôle rentré dans le champ nom

				// ouverture de Clinique Veto
				CliniqueVetoFrame2 maCliniqueVeto2 = CliniqueVetoFrame2.getInstance(getTfNom().getText());
				maCliniqueVeto2.setVisible(true);
//				// Ouvertue d'Actualité
//				ActualiteFrame monActu = new ActualiteFrame(getTfNom().getText());
//				monActu.setVisible(true);
//				monActu.setLocationRelativeTo(null);
				// Masque la fenêtre Connexion
				this.setVisible(false);
			}

		} else {
			JOptionPane.showMessageDialog(this, "Vos Identifiants de connexion ne sont pas valides",
					"Connexion Impossible", JOptionPane.WARNING_MESSAGE);
		}

	}


	// *********** GETTERS ******************************

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
