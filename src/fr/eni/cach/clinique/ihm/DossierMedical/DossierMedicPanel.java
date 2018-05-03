package fr.eni.cach.clinique.ihm.DossierMedical;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import fr.eni.cach.clinique.bll.AnimalManager;
import fr.eni.cach.clinique.bll.BLLException;
import fr.eni.cach.clinique.bo.Animal;
import fr.eni.cach.clinique.bo.Client;
import fr.eni.cach.clinique.bo.Rdv;
import fr.eni.cach.clinique.ihm.UtilsIHM;

public class DossierMedicPanel extends JPanel {
	/*
	 * ORGANISATION DE LA CLASSE : 
	 * - déclaration des attributs 
	 * - constructeur principal 
	 * - création des panels 
	 * - creation des boutons & zone de texte
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
	
	private Client clientCourant;
	private Animal animalCourant;
	
	
	// *********** CONSTRUCTEUR ***************
	
	public DossierMedicPanel(Rdv rdv) {
		
		//créateur de l'animalCourant :
		this.createAnimalCourant(rdv);
		
		//créateur du clientCourant :
		this.createClientCourant(rdv);

		//créa boutons et zones de texte :
		this.createBttOK();
		this.createBttAnnuler();
		this.createTAAntecedents();
		
		//créa labels 
		this.createLblClient();
		this.createLblAnimal();
		this.createLblCodeAnimal();
		this.createLblNom();
		this.createLblCouleur();
		this.createLblSexe();
		this.createLblEspece();
		this.createLblTatouage();
		
		//créa des panels : 
		this.createPanelBtts();
		this.createPanelClient();
		this.createPanelAnimal();
		this.createPanelAntecedents();
		this.createPanelInfos();
		
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
	
	
	// *********** CREATE BOUTONS & ZONE DE TEXTE ***************
	private void createBttOK() {
		bttOK = new JButton("Valider");		
		bttOK.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				animalCourant.setAntecedents(taAntecedents.getText());
					AnimalManager.getInstance().updateAnimal(animalCourant);
				} catch (BLLException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(DossierMedicPanel.this, e1.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
	}
	
	private void createBttAnnuler() {
		bttAnnuler = new JButton("Annuler");	
		bttAnnuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (animalCourant.getAntecedents() != null) {
				taAntecedents.setText(animalCourant.getAntecedents());
				} else {
					taAntecedents.setText("");
				}
				
			}
		});
	}
	
	private void createTAAntecedents() {
		taAntecedents = new JTextArea("");
		//si l'animal a déjà des antécédents, ils sont affichés dans la zone de texte
		if (animalCourant.getAntecedents() != null) {
		taAntecedents.setText(animalCourant.getAntecedents());
		}
	}

	// *********** CREATE JLabels ***************
	
	private void createLblClient() {
		lblClient = new JLabel();
		lblClient.setText(clientCourant.getNomClient()+" "+clientCourant.getPrenomClient());
	}
	
	private void createLblAnimal() {
		lblAnimal = new JLabel("Animal :");
	}
	
	private void createLblCodeAnimal() {
		lblCodeAnimal = new JLabel(String.valueOf(animalCourant.getCodeAnimal()));
	}
	
	private void createLblNom() {
		lblNom = new JLabel(animalCourant.getNomAnimal());
	}
	
	private void createLblCouleur() {
		lblCouleur = new JLabel(animalCourant.getCouleur());
	}
	
	private void createLblSexe() {
		lblSexe= new JLabel();
		//en fonction du sexe on affiche différents libeles :
		switch (animalCourant.getSexe()) {
		case "M" :
			lblSexe.setText("Mâle");
			break;
		case "F" :
			lblSexe.setText("Femelle");
			break;
		case "H" : 
			lblSexe.setText("Hermaphrodite");
			break;
		}
	}
	
	private void createLblEspece() {
		//on affiche sous ce label l'espèce et la race
		lblEspece = new JLabel(animalCourant.getEspece()+" "+animalCourant.getRace());
	}
	
	private void createLblTatouage() {
		lblTatouage = new JLabel();
		
		// si l'animal n'est pas tatouté on affiche "non-tatoué"
		if (animalCourant.getTatouage() == null) {
			lblTatouage.setText("Non tatoué");
		}
		lblTatouage.setText(animalCourant.getTatouage());
	}
	
	// *********** METHODES ANNEXES ***************
	
	/**
	 * Permet de créer un animal courant à partir du rdv sélectionné
	 */
	private void createAnimalCourant(Rdv rdv) {
		animalCourant = new Animal();
	}
	
	/**
	 * Permet de créer un client courant à partir du rdv sélectionné
	 */
	private void createClientCourant(Rdv rdv) {
		clientCourant = rdv.getClient();
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
