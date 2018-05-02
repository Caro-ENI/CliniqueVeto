package fr.eni.cach.clinique.ihm.ecranClients;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.TextField;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import fr.eni.cach.clinique.ihm.UtilsIHM;

public class AjoutClientFrame extends JFrame {
	
	
	private static final long serialVersionUID = 2751586841854361836L;
	
	private JPanel panelGlobal;
	private JPanel panelBoutons;
	private JPanel panelInfos;
	
	private UtilsIHM utilsIHM = UtilsIHM.getInstance();
	
	private JButton bttValider;
	private JButton bttAnnuler;
	
	private JLabel lblCodeClient;
	private JLabel lblNom;
	private JLabel lblPrenom;
	private JLabel lblAdresse;
	private JLabel lblCodePostal;
	private JLabel lblVille;
	private JLabel lblNumTel;
	private JLabel lblAssurance;
	private JLabel lblRemarques;
	
	private JTextField tfCodeClient;
	private JTextField tflNom;
	private JTextField tfPrenom;
	private JTextField tfAdresse1;
	private JTextField tfAdresse2;
	private JTextField tfCodePostal;
	private JTextField tfVille;
	private JTextField tfNumTel;
	private JTextField tfAssurance;
	private JTextField tfRemarques;
	
	
	
	
	public AjoutClientFrame() {
		
		this.setTitle("Ajouter un client");
		this.setLocation(0,142);
		this.setSize(500,500);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		//Initialisation des champs
		
		this.createLblCodeClient();
		this.createLblNom();
		this.createLblPrenom();
		this.createLblAdresse();
		this.createLblCodePostal();
		this.createLblVille();
		this.createLblNumTel();
		this.createLblAssurance();
		this.createLblRemarques();
		
		this.createTfCodeClient();
		this.createTfNom();
		this.createTfPrenom();
		this.createTfAdresse1();
		this.createTfAdresse2();
		this.createTfCodePostal();
		this.createTfVille();
		this.createTfNumTel();
		this.createTfAssurance();
		this.createTfRemarques();
		
		this.createBttValider();
		this.createBttAnnuler();
		
		// Initialisation des panels
		
	
		this.createPanelBoutons();
		this.createPanelInfos();
		
		BorderLayout layoutGlobal = new BorderLayout();
		this.setLayout(layoutGlobal);
		
		this.add(getPanelBoutons(), BorderLayout.NORTH);
		this.add(getPanelInfos(), BorderLayout.CENTER);
		
		
		
		
		
	}


	//***********CREATION PANELS********************
	
	private void createPanelInfos() {
		panelInfos = new JPanel(new GridBagLayout());
		
		utilsIHM.addComponentTo(getLblCodeClient() , panelInfos, 0, 0, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTfCodeClient() , panelInfos, 1, 0, 1, 1, 0.8, true);
		
		utilsIHM.addComponentTo(getLblNom() , panelInfos, 0, 1, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTflNom() , panelInfos, 1, 1, 1, 1, 0.8, true);
		
		utilsIHM.addComponentTo(getLblPrenom() , panelInfos, 0, 2, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTfPrenom() , panelInfos, 1, 2, 1, 1, 0.8, true);
		
		utilsIHM.addComponentTo(getLblAdresse() , panelInfos, 0, 3, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTfAdresse1() , panelInfos, 1, 3, 1, 1, 0.8, true);
		utilsIHM.addComponentTo(getTfAdresse2() , panelInfos, 1, 4, 1, 1, 0.8, true);
		
		utilsIHM.addComponentTo(getLblCodePostal() , panelInfos, 0, 5, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTfCodePostal() , panelInfos, 1, 5, 1, 1, 0.8, true);
		
		utilsIHM.addComponentTo(getLblVille() , panelInfos, 0, 6, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTfVille() , panelInfos, 1, 6, 1, 1, 0.8, true);
		
		utilsIHM.addComponentTo(getLblNumTel() , panelInfos, 0, 7, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTfNumTel() , panelInfos, 1, 7, 1, 1, 0.8, true);
		
		utilsIHM.addComponentTo(getLblAssurance() , panelInfos, 0, 8, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTfAssurance() , panelInfos, 1, 8, 1, 1, 0.8, true);
		
		utilsIHM.addComponentTo(getLblRemarques() , panelInfos, 0, 9, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTfRemarques() , panelInfos, 1, 9, 1, 1, 0.8, true);
	}



	private void createPanelBoutons() {
		//permet de d�finir l'orientation de l'�criture dans le panel 
				setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
				panelBoutons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				//panelBoutons.setBackground(Color.GRAY);
				
				// pour mettre une bordure sur le panel boutons : faire appel � la BorderFactory
				panelBoutons.setBorder(BorderFactory.createEtchedBorder());
				//l'ordre des .add d�termine la position des boutons 
				panelBoutons.add(getBttValider());
				panelBoutons.add(getBttAnnuler());
				
		
	}



	



	//**************CREATION DES COMPONENTS************************

	private void createBttAnnuler() {
		bttAnnuler = new JButton ("Annuler");
		
	}



	private void createBttValider() {
		bttValider = new JButton ("Valider");
		
	}



	private void createTfRemarques() {
		tfRemarques = new JTextField("", 50);
		
	}



	private void createTfAssurance() {
		tfAssurance = new JTextField("", 30);
		
	}



	private void createTfNumTel() {
		tfNumTel = new JTextField("", 15);
		
	}



	private void createTfVille() {
		tfVille = new JTextField("", 25);
		
	}



	private void createTfCodePostal() {
		tfCodePostal = new JTextField("", 6);
		
	}



	private void createTfAdresse2() {
		tfAdresse2 = new JTextField("", 30);
		
	}



	private void createTfAdresse1() {
		tfAdresse1 = new JTextField("", 30);
		
	}



	private void createTfPrenom() {
		tfPrenom = new JTextField("", 20);
		
	}



	private void createTfNom() {
		tflNom = new JTextField("", 20);
		
	}



	private void createTfCodeClient() {
		tfCodeClient = new JTextField("", 20);
		
	}



	private void createLblRemarques() {
		lblRemarques = new JLabel("Remarques");
		
	}



	private void createLblAssurance() {
		lblAssurance = new JLabel ("Assurance");
		
	}



	private void createLblNumTel() {
		lblNumTel = new JLabel ("N� t�l�phone");
		
	}



	private void createLblVille() {
		lblVille = new JLabel ("Ville");
		
	}



	private void createLblCodePostal() {
		lblCodePostal = new JLabel ("Code postal");
		
	}



	private void createLblAdresse() {
		lblAdresse = new JLabel ("Adresse");
		
	}



	private void createLblPrenom() {
		lblPrenom = new JLabel ("Prenom");
		
	}



	private void createLblNom() {
		lblNom = new JLabel ("Nom");
		
	}



	private void createLblCodeClient() {
		lblCodeClient = new JLabel ("Code");
		
	}

	// *********** GETTERS **************

	public JPanel getPanelGlobal() {
		return panelGlobal;
	}

	public JPanel getPanelBoutons() {
		return panelBoutons;
	}

	public JPanel getPanelInfos() {
		return panelInfos;
	}

	public UtilsIHM getUtilsIHM() {
		return utilsIHM;
	}

	public JButton getBttValider() {
		return bttValider;
	}

	public JButton getBttAnnuler() {
		return bttAnnuler;
	}

	public JLabel getLblCodeClient() {
		return lblCodeClient;
	}

	public JLabel getLblNom() {
		return lblNom;
	}

	public JLabel getLblPrenom() {
		return lblPrenom;
	}

	public JLabel getLblAdresse() {
		return lblAdresse;
	}

	public JLabel getLblCodePostal() {
		return lblCodePostal;
	}

	public JLabel getLblVille() {
		return lblVille;
	}

	public JLabel getLblNumTel() {
		return lblNumTel;
	}

	public JLabel getLblAssurance() {
		return lblAssurance;
	}

	public JLabel getLblRemarques() {
		return lblRemarques;
	}

	public JTextField getTfCodeClient() {
		return tfCodeClient;
	}

	public JTextField getTflNom() {
		return tflNom;
	}

	public JTextField getTfPrenom() {
		return tfPrenom;
	}

	public JTextField getTfAdresse1() {
		return tfAdresse1;
	}

	public JTextField getTfAdresse2() {
		return tfAdresse2;
	}

	public JTextField getTfCodePostal() {
		return tfCodePostal;
	}

	public JTextField getTfVille() {
		return tfVille;
	}

	public JTextField getTfNumTel() {
		return tfNumTel;
	}

	public JTextField getTfAssurance() {
		return tfAssurance;
	}

	public JTextField getTfRemarques() {
		return tfRemarques;
	}
	
	//*************************************************************
	
	
}
