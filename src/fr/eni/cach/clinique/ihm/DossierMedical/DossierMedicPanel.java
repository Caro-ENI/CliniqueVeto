package fr.eni.cach.clinique.ihm.DossierMedical;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import fr.eni.cach.clinique.ihm.UtilsIHM;

public class DossierMedicPanel extends JPanel {
	/*
	 * ORGANISATION DE LA CLASSE : 
	 * - déclaration des attributs 
	 * - constructeur principal 
	 * - création des panels 
	 * - creation des boutons 
	 * - méthodes annexes 
	 * - getters
	 */

	// *********** ATTRIBUTS ***************
	private static final long serialVersionUID = 4810147909187792134L;
	
	
	private JButton bttOK;
	private JButton bttAnnuler;
	private JPanel panelBtts;
	
	private JPanel panelInfos;
	private JPanel panelAnimal;
	private JPanel panelAntecedents;
	private JTextArea taAntecedents;
	
	private JPanel panelClient;
	private JLabel lblClient;
	
	private JLabel lblAnimal;
	private JLabel lblCodeAnimal;
	private JLabel lblNom;
	private JLabel lblCouleur;
	private JLabel lblSexe;
	private JLabel lblEspece;
	private JLabel lblTatouage;
	
	
	// *********** CONSTRUCTEUR ***************
	
	public DossierMedicPanel() {

		//créa boutons et zones de texte :
		createBttOK();
		createBttAnnuler();
		createTAAntecedents();
		
		//créa labels 
		createLblClient();
		createLblAnimal();
		createLblCodeAnimal();
		createLblNom();
		createLblCouleur();
		createLblSexe();
		createLblEspece();
		createLblTatouage();
		
		//créa des panels : 
		createPanelBtts();
		createPanelClient();
		createPanelAnimal();
		createPanelAntecedents();
		createPanelInfos();
		
		//ajout des panels au panel principal :
		BorderLayout layoutGlobal = new BorderLayout();
		this.setLayout(layoutGlobal);
		
		this.add(getPanelBtts(), BorderLayout.NORTH);
		this.add(getPanelInfos(), BorderLayout.CENTER);
		
		
	}
	
	// *********** CREATE PANEL ***************
	private void createPanelBtts() {
		//permet de définir l'orientation de l'écriture dans le panel (à reformuler si meilleure explication)
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		panelBtts = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		//l'ordre des .add détermine la position des boutons 
		panelBtts.add(getBttOK());
		panelBtts.add(getBttAnnuler());
		panelBtts.setBorder(new TitledBorder(""));
	}
	
	private void createPanelInfos() {
		panelInfos = new JPanel(new GridBagLayout());
		
		UtilsIHM.getInstance().addComponentTo(getPanelAnimal(), panelInfos, 0, 0, 1, 1, 0.5, true);
		UtilsIHM.getInstance().addComponentTo(getPanelAntecedents(), panelInfos, 1, 0, 3, 1, 0.5, true);
	}
	
	private void createPanelAntecedents() {
		// Ajout d'un GridBagLayout pour que le TexteArea prenne toute la place.
		panelAntecedents = new JPanel(new GridBagLayout());
		panelAntecedents.setBorder(new TitledBorder("Antécédents de Consultation : "));
		UtilsIHM.getInstance().addComponentTo(getTAAntecedents(), panelAntecedents, 0, 0, 1, 1, 1, true);
	}
	
	private void createPanelClient() {
		panelClient = new JPanel();
		panelClient.setBorder(new TitledBorder("Client : "));
		panelClient.add(getLblClient());
	}
	
	private void createPanelAnimal() {
		panelAnimal = new JPanel( new GridBagLayout());
		
		UtilsIHM.getInstance().addComponentTo(getPanelClient(), panelAnimal, 0, 0, 3, 1, 0.8, true);
		UtilsIHM.getInstance().addComponentTo(getLblAnimal(), panelAnimal, 0, 1, 1, 1, 0.2, true);
		UtilsIHM.getInstance().addComponentTo(getLblCodeAnimal(), panelAnimal, 1, 1, 1, 1, 0.2, true);
		
		UtilsIHM.getInstance().addComponentTo(getLblNom(), panelAnimal, 1, 2, 1, 1, 0.2, true);
		
		UtilsIHM.getInstance().addComponentTo(getLblCouleur(), panelAnimal, 1, 3, 1, 1, 0.2, true);
		UtilsIHM.getInstance().addComponentTo(getLblSexe(), panelAnimal, 2, 3, 1, 1, 0.2, true);
		
		UtilsIHM.getInstance().addComponentTo(getLblEspece(), panelAnimal, 1, 4, 1, 1, 0.2, true);
		UtilsIHM.getInstance().addComponentTo(getLblTatouage(), panelAnimal, 1, 5, 1, 1, 0.2, true);
	}
	
	
	// *********** CREATE BOUTONS & ZOne de texte ***************
	private void createBttOK() {
		bttOK = new JButton("Valider");		
	}
	
	private void createBttAnnuler() {
		bttAnnuler = new JButton("Annuler");		
	}
	
	private void createTAAntecedents() {
		taAntecedents = new JTextArea("Antécédents & Détails de Consultation :");
		
	}

	// *********** CREATE JLabels ***************
	private void createLblClient() {
		lblClient = new JLabel("Je suis le propriétaire de l'animal");
	}
	
	private void createLblAnimal() {
		lblAnimal = new JLabel("Animal :");
	}
	
	private void createLblCodeAnimal() {
		lblCodeAnimal = new JLabel("CodeAnimal");
	}
	
	private void createLblNom() {
		lblNom = new JLabel("Nom Animal");
	}
	
	private void createLblCouleur() {
		lblCouleur = new JLabel("Couleur");
	}
	
	private void createLblSexe() {
		lblSexe= new JLabel("Sexe");
	}
	
	private void createLblEspece() {
		lblEspece = new JLabel("Espèce");
	}
	
	private void createLblTatouage() {
		lblTatouage = new JLabel("Tatouage");
	}
	
	
	// *********** GETTERS ***************
	public JButton getBttOK() {
		return bttOK;
	}
	
	public JButton getBttAnnuler() {
		return bttAnnuler;
	}
	
	public JPanel getPanelBtts() {
		return panelBtts;
	}
	
	public JPanel getPanelInfos() {
		return panelInfos;
	}
	
	public JPanel getPanelAnimal() {
		return panelAnimal;
	}
	
	public JPanel getPanelAntecedents() {
		return panelAntecedents;
	}
	
	public JTextArea getTAAntecedents() {
		return taAntecedents;
	}
	
	public JPanel getPanelClient() {
		return panelClient;
	}
	
	public JLabel getLblClient() {
		return lblClient;
	}
	
	public JLabel getLblAnimal() {
		return lblAnimal;
	}
	public JLabel getLblCodeAnimal() {
		return lblCodeAnimal;
	}
	public JLabel getLblNom() {
		return lblNom;
	}
	public JLabel getLblCouleur() {
		return lblCouleur;
	}
	public JLabel getLblSexe() {
		return lblSexe;
	}
	public JLabel getLblEspece() {
		return lblEspece;
	}
	public JLabel getLblTatouage() {
		return lblTatouage;
	}
}
