package fr.eni.cach.clinique.ihm.Personnels;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fr.eni.cach.clinique.bll.BLLException;
import fr.eni.cach.clinique.bll.PersonnelManager;
import fr.eni.cach.clinique.bo.Admin;
import fr.eni.cach.clinique.bo.Personnel;
import fr.eni.cach.clinique.bo.Secretaire;
import fr.eni.cach.clinique.bo.Veterinaire;
import fr.eni.cach.clinique.ihm.UtilsIHM;

public class AjouterPersonnelPanel extends JPanel {

	/*
	 * ORGANISATION DE LA CLASSE : - Attributs 
	 * - Constructeur principal 
	 * - Cr�ation des panels 
	 * - Creation des boutons 
	 * - Cr�ation de Libell�s & zones de textes & liste d�roulante
	 * - M�thodes annexes 
	 * - Getters
	 */

	// *********** ATTRIBUTS ****************************

	private static final long serialVersionUID = 1526419773788020856L;

	private UtilsIHM utilsIHM = UtilsIHM.getInstance();

	private JPanel panelBoutons;
	private JPanel panelInfos;

	private JButton bttValider;
	private JButton bttAnnuler;

	private JLabel lblNom;
	private JLabel lblMotPasse;
	private JLabel lblRole;

	private JTextField tfNom;
	private JPasswordField tfMotPasse;
	private JTextField tfRole;
	private JComboBox<String> cbRole;

	// *********** CONSTRUCTEUR PRINCIPAL ***************

	public AjouterPersonnelPanel() {

		// Initialisation des components
		this.createLblNom();
		this.createLblMotPasse();
		this.createLblRole();
		this.createTfNom();
		this.createTfMotPasse();
		this.createTfRole();
		this.createCbRole();
		this.createBttValider();
		this.createBttAnnuler();

		// Initialisation des panels

		this.createPanelBoutons();
		this.createPanelInfos();

		// Panel global

		BorderLayout layoutGlobal = new BorderLayout();
		this.setLayout(layoutGlobal);

		this.add(getPanelBoutons(), BorderLayout.NORTH);
		this.add(getPanelInfos(), BorderLayout.CENTER);

	}

	// *********** CREATION PANELS **********************

	private void createPanelInfos() {

		panelInfos = new JPanel(new GridBagLayout());

		utilsIHM.addComponentTo(getLblNom(), panelInfos, 0, 0, 1, 1, 0.3, true);
		utilsIHM.addComponentTo(getTfNom(), panelInfos, 1, 0, 1, 1, 0.7, true);

		utilsIHM.addComponentTo(getLblMotPasse(), panelInfos, 0, 1, 1, 1, 0.3, true);
		utilsIHM.addComponentTo(getTfMotPasse(), panelInfos, 1, 1, 1, 1, 0.7, true);

		utilsIHM.addComponentTo(getLblRole(), panelInfos, 0, 2, 1, 1, 0.3, true);
		utilsIHM.addComponentTo(getCbRole(), panelInfos, 1, 2, 1, 1, 0.7, true);

	}

	private void createPanelBoutons() {
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		panelBoutons = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		// pour mettre une bordure sur le panel boutons : faire appel � la BorderFactory
		panelBoutons.setBorder(BorderFactory.createEtchedBorder());

		panelBoutons.add(getBttValider());
		panelBoutons.add(getBttAnnuler());

	}

	// *********** CREATION BOUTONS *********************
	
	private void createBttAnnuler() {
		bttAnnuler = utilsIHM.createBttAvecIcon("Annuler", UtilsIHM.IconesEnum.ANNULER);
		bttAnnuler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				getTfNom().setText("");
				getTfMotPasse().setText("");
				getTfRole().setText("");

			}
		});

	}

	private void createBttValider() {
		bttValider = utilsIHM.createBttAvecIcon("Valider", UtilsIHM.IconesEnum.VALIDER);
		bttValider.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					Personnel persAAjouter = null;

					// switch (getTfRole().getText()) {
					switch (getCbRole().getSelectedItem().toString()) {
					case "Secr�taire":
						persAAjouter = new Secretaire();
						persAAjouter.setRole("sec");
						break;
					case "Administrateur":
						persAAjouter = new Admin();
						persAAjouter.setRole("adm");
						break;
					case "V�t�rinaire":
						persAAjouter = new Veterinaire();
						persAAjouter.setRole("vet");
						break;

					default:
						persAAjouter = new Veterinaire();
						persAAjouter.setRole("vet");
						break;
					}
					persAAjouter.setNom(getTfNom().getText());
					persAAjouter.setMotPasse(String.valueOf(getTfMotPasse().getPassword()));

					PersonnelManager.getInstance().addPersonnel(persAAjouter);
					// Permet de rafraichir la JTable
					TablePersonnelModel.getInstance().chargementDonnees();
					AjouterPersonnelPanel.this.getParent().getParent().getParent().setVisible(false);
				} catch (BLLException e1) {

					JOptionPane.showMessageDialog(AjouterPersonnelPanel.this, e1.getMessage(),
							"Ajout de Personnel Impossible", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}

	// *********** CREATION LIBELLES ********************
	
	private void createLblRole() {
		lblRole = new JLabel("R�le");

	}

	private void createLblMotPasse() {
		lblMotPasse = new JLabel("Mot de passe");

	}

	private void createLblNom() {
		lblNom = new JLabel("Nom");

	}
	// *********** CREATION ZONES DE TEXTE **************
	
	private void createTfRole() {
		tfRole = new JTextField("");

	}

	private void createTfMotPasse() {
		tfMotPasse = new JPasswordField("");

	}

	private void createTfNom() {
		tfNom = new JTextField("");

	}

	// *********** CREATION LISTES DEROULANTES **********
	
	private void createCbRole() {
		String[] roles = { "Administrateur", "Secr�taire", "V�t�rinaire" };
		cbRole = new JComboBox<>(roles);
		cbRole.setSelectedItem(null);
	}

	// *********** GETTERS ******************************
	
	public JPanel getPanelBoutons() {
		return panelBoutons;
	}

	public JPanel getPanelInfos() {
		return panelInfos;
	}

	public JButton getBttValider() {
		return bttValider;
	}

	public JButton getBttAnnuler() {
		return bttAnnuler;
	}

	public JLabel getLblNom() {
		return lblNom;
	}

	public JLabel getLblMotPasse() {
		return lblMotPasse;
	}

	public JLabel getLblRole() {
		return lblRole;
	}

	public JTextField getTfNom() {
		return tfNom;
	}

	public JPasswordField getTfMotPasse() {
		return tfMotPasse;
	}

	public JTextField getTfRole() {
		return tfRole;
	}

	public JComboBox<String> getCbRole() {
		return cbRole;
	}

}
