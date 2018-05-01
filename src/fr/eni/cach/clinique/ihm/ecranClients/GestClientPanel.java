package fr.eni.cach.clinique.ihm.ecranClients;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import fr.eni.cach.clinique.bo.Client;
import fr.eni.cach.clinique.ihm.UtilsIHM;
import fr.eni.cach.clinique.ihm.cliniqueVeto.CliniqueVetoFrame2;
import fr.eni.cach.clinique.ihm.ecranAnimaux.AnimauxPanel;

public class GestClientPanel extends JPanel {
	
	
	private static final long serialVersionUID = -84150586310550769L;

	private UtilsIHM utilsIHM = UtilsIHM.getInstance();

	private JButton bttRechercher;
	private JButton bttAjouterClient;
	private JButton bttSupprimerClient;
	private JButton bttEditerClient;
	private JButton bttAnnuler;
	
	private JLabel lblCodeClient;
	private JLabel lblNom;
	private JLabel lblPrenom;
	private JLabel lblAdresse;
	private JLabel lblCodePostal;
	private JLabel lblVille;
	private JLabel lblNumTel;
	private JLabel lblEmail;
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
	private JTextField tfEmail;
	private JTextField tfAssurance;
	private JTextField tfRemarques;
	
	private JTable tablAnimaux;
	private TableAnimalModel modelTablAnimaux;
	private JButton bttAjouterAnimal;
	private JButton bttSupprimerAnimal;
	private JButton bttEditerAnimal;
	
	private JPanel panelBttHaut;
	private JPanel panelInfos;
	private JPanel panelBttBas;
	private JScrollPane panelTablAnimaux;
	private JPanel panelCentral;
	
//	private JInternalFrame jifRechercher;	
	
	
	
	public GestClientPanel() {
		
		//Initialisation des champs
		
			// Les Labels
		
				this.createLblCodeClient();
				this.createLblNom();
				this.createLblPrenom();
				this.createLblAdresse();
				this.createLblCodePostal();
				this.createLblVille();
				this.createLblNumTel();
				this.createLblEmail();
				this.createLblAssurance();
				this.createLblRemarques();
				
			// Les TextField	
				
				this.createTfCodeClient();
				this.createTfNom();
				this.createTfPrenom();
				this.createTfAdresse1();
				this.createTfAdresse2();
				this.createTfCodePostal();
				this.createTfVille();
				this.createTfNumTel();
				this.createTfEmail();
				this.createTfAssurance();
				this.createTfRemarques();
		
			// Les Boutons
				
				this.createBttRechercher();
				this.createBttAjouterClient();
				this.createBttSupprimerClient();
				this.createBttEditerClient();
				this.createBttAnnuler();
				
				this.createBttAjouterAnimal();
				this.createBttSupprimerAnimal();
				this.createBttEditerAnimal();
				
			// La JTable	
		
				this.createTablAnimaux();
				
		// Création des panels
				
				this.createPanelBttHaut();
				this.createPanelBttBas();
				this.createPanelInfos();
				this.createPanelTablAnimaux();
				this.createPanelCentral();
		
		// Panel global
				
				BorderLayout layoutGlobal = new BorderLayout();
				this.setLayout(layoutGlobal);
				
				this.add(getPanelBttHaut(), BorderLayout.NORTH);
				this.add(getPanelCentral(), BorderLayout.CENTER);	
				this.add(getPanelBttBas(), BorderLayout.SOUTH);				

	}
	
	

	// ------ Méthodes de refresh des affichages --------
	
	/**
	 * Permet de rafraichir l'affichage du client courant 
	 * @param client
	 */
	public void refreshAffichageClient(Client client) {
		 this.tfCodeClient.setText(String.valueOf(client.getCodeClient()));
		 this.tflNom.setText(client.getNomClient());
		 this.tfPrenom.setText(client.getPrenomClient());
		 this.tfAdresse1.setText(client.getAdresse1());
		 this.tfAdresse2.setText(client.getAdresse2());
		 this.tfCodePostal.setText(client.getCodePostal());
		 this.tfVille.setText(client.getVille());
		 this.tfNumTel.setText(client.getNumTel());
		 this.tfEmail.setText(client.getEmail());
		 this.tfAssurance.setText(client.getAssurance());
		 this.tfRemarques.setText(client.getRemarque());
		 
		 this.rerefreshAffichageAnimaux(client);
		 
	}
	
	
	public void rerefreshAffichageAnimaux(Client client) {
		tablAnimaux.removeAll();
		TableAnimalModel.getInstance().chargementDonnees(client.getCodeClient());
	}

	//--------------------CREATION DES PANELS-----------------------------
	

	private void createPanelBttBas() {
		//permet de définir l'orientation de l'écriture dans le panel 
				setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
				panelBttBas = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				//panelBoutons.setBackground(Color.GRAY);
				
				// pour mettre une bordure sur le panel boutons : faire appel à la BorderFactory
				panelBttBas.setBorder(BorderFactory.createEtchedBorder());
				
				// -> Récupère la taille de l'écran
				//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				//panelBoutons.setSize(screenSize.width-10, 4);
				
				
				panelBttBas.add(getBttAjouterAnimal());
				panelBttBas.add(getBttSupprimerAnimal());
				panelBttBas.add(getBttEditerAnimal());
				
		
	}

	private void createPanelTablAnimaux() {
		
		panelTablAnimaux = new JScrollPane();
		panelTablAnimaux.setViewportView(getTablAnimaux());
		
		
	}

	private void createPanelInfos() {
			panelInfos = new JPanel (new GridBagLayout());
			
			// Même code que dans la frame AjouterUnClient
			
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
			
			utilsIHM.addComponentTo(getLblEmail(), panelInfos, 0, 8, 1, 1, 0.2, false);
			utilsIHM.addComponentTo(getTfEmail(), panelInfos, 1, 8, 1, 1, 0.8, true);
			
			utilsIHM.addComponentTo(getLblAssurance() , panelInfos, 0, 9, 1, 1, 0.2, false);
			utilsIHM.addComponentTo(getTfAssurance() , panelInfos, 1, 9, 1, 1, 0.8, true);
			
			utilsIHM.addComponentTo(getLblRemarques() , panelInfos, 0, 10, 1, 1, 0.2, false);
			utilsIHM.addComponentTo(getTfRemarques() , panelInfos, 1, 10, 1, 1, 0.8, true);
		
	}

	private void createPanelBttHaut() {
		
				
				panelBttHaut = new JPanel(new GridBagLayout());
				//panelBoutons.setBackground(Color.GRAY);
				
				// pour mettre une bordure sur le panel boutons : faire appel à la BorderFactory
				//panelBttHaut.setBorder(BorderFactory.createEtchedBorder());
				
				// -> Récupère la taille de l'écran
				//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				//panelBoutons.setSize(screenSize.width-10, 4);
				
				utilsIHM.addComponentTo(getBttRechercher() , panelBttHaut, 0, 0, 1, 1, 1, true);
				utilsIHM.addComponentTo(getBttAjouterClient() , panelBttHaut, 3, 0, 1, 1, 1, true);
				utilsIHM.addComponentTo(getBttSupprimerClient() , panelBttHaut, 4, 0, 1, 1, 1, true);
				utilsIHM.addComponentTo(getBttValider() , panelBttHaut, 7, 0, 1, 1, 1, true);
				utilsIHM.addComponentTo(getBttAnnuler() , panelBttHaut, 8, 0, 1, 1, 1, true);
				
		
	}

	private void createPanelCentral(){
		panelCentral = new JPanel(new GridBagLayout());
		utilsIHM.addComponentTo(getPanelInfos(), panelCentral, 0, 0, 1, 1, 0.8, true);
		utilsIHM.addComponentTo(getPanelTablAnimaux(), panelCentral, 1, 0, 1, 1, 0.8, true);
	}
	

	//-------------CREATION DES COMPONENTS----------------------------------
	
	private void createTfRemarques() {
		tfRemarques = new JTextField("", 50);
		
	}

	private void createTfAssurance() {
		tfAssurance = new JTextField("", 30);
		
	}

	private void createTfNumTel() {
		tfNumTel = new JTextField("", 15);
		
	}
	
	private void createTfEmail() {
		tfEmail = new JTextField("", 15);
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
		lblNumTel = new JLabel ("N° téléphone");
		
	}
	
	private void createLblEmail() {
		lblEmail = new JLabel("Email");
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
	
	private void createTablAnimaux() {
		modelTablAnimaux = TableAnimalModel.getInstance();
		tablAnimaux = new JTable (modelTablAnimaux);
		tablAnimaux.setBorder(BorderFactory.createEtchedBorder());
		tablAnimaux.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablAnimaux.setRowHeight(30);
		tablAnimaux.getSelectionModel().setSelectionInterval(0, 0);
		
	}

	//TODO
	private void createBttEditerAnimal() {
		bttEditerAnimal = new JButton ("Éditer");
		bttEditerAnimal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnimauxPanel panelAnimal = new AnimauxPanel(new Client());
				JInternalFrame jifAjoutAnimal = utilsIHM.createJIF("Animal", panelAnimal);
				jifAjoutAnimal.setSize(500, 350);
				jifAjoutAnimal.setVisible(true);
				CliniqueVetoFrame2.getInstance("").getDesktop().add(jifAjoutAnimal);
				try {
					jifAjoutAnimal.setSelected(true);
		        } catch (java.beans.PropertyVetoException eAjoutCli) {}
			}
		});
	}

	private void createBttSupprimerAnimal() {
		bttSupprimerAnimal = new JButton ("Supprimer");
		
	}

	private void createBttAjouterAnimal() {
		bttAjouterAnimal = new JButton ("Ajouter");
		bttAjouterAnimal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnimauxPanel panelAnimal = new AnimauxPanel(new Client());
				JInternalFrame jifAjoutAnimal = utilsIHM.createJIF("Animal", panelAnimal);
				jifAjoutAnimal.setSize(500, 350);
				jifAjoutAnimal.setVisible(true);
				CliniqueVetoFrame2.getInstance("").getDesktop().add(jifAjoutAnimal);
				try {
					jifAjoutAnimal.setSelected(true);
		        } catch (java.beans.PropertyVetoException eAjoutCli) {}
			}
		});
		
	}

	private void createBttAnnuler() {
		bttAnnuler = new JButton ("Annuler");
		
	}

	private void createBttEditerClient() {
		bttEditerClient = new JButton ("Éditer");
		
	}

	private void createBttSupprimerClient() {
		bttSupprimerClient = new JButton ("Supprimer");
		
	}

	private void createBttAjouterClient() {
		bttAjouterClient = new JButton ("Ajouter");
		bttAjouterClient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AjoutClientPanel panelAjout = new AjoutClientPanel();
				JInternalFrame jifAjoutClient = utilsIHM.createJIF("Nouveau Client", panelAjout);
				jifAjoutClient.setVisible(true);
				CliniqueVetoFrame2.getInstance("").getDesktop().add(jifAjoutClient);
				try {
					jifAjoutClient.setSelected(true);
		        } catch (java.beans.PropertyVetoException eAjoutCli) {}
			}
		});
		
	}

	private void createBttRechercher() {
		bttRechercher = new JButton ("Rechercher");
		bttRechercher.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RechercherPanel panelRecherche = new RechercherPanel(GestClientPanel.this);
				JInternalFrame jifRechercher = UtilsIHM.getInstance().createJIF("Recherche de Client", panelRecherche);
				jifRechercher.setVisible(true);
				CliniqueVetoFrame2.getInstance("").getDesktop().add(jifRechercher);
				try {
					jifRechercher.setSelected(true);
		        } catch (java.beans.PropertyVetoException eAjoutCli) {}
				
			}
		});
	}



	//*************************GETTERS***************************************************
	

	public JButton getBttRechercher() {
		return bttRechercher;
	}

	public JButton getBttAjouterClient() {
		return bttAjouterClient;
	}

	public JButton getBttSupprimerClient() {
		return bttSupprimerClient;
	}

	public JButton getBttValider() {
		return bttEditerClient;
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

	public JLabel getLblEmail(){
		return lblEmail;
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

	public JTextField getTfEmail(){
		return tfEmail;
	}

	public JTextField getTfAssurance() {
		return tfAssurance;
	}

	public JTextField getTfRemarques() {
		return tfRemarques;
	}

	public JTable getTablAnimaux() {
		return tablAnimaux;
	}

	public TableAnimalModel getModelTablAnimaux() {
		return modelTablAnimaux;
	}

	public JButton getBttAjouterAnimal() {
		return bttAjouterAnimal;
	}

	public JButton getBttSupprimerAnimal() {
		return bttSupprimerAnimal;
	}

	public JButton getBttEditerAnimal() {
		return bttEditerAnimal;
	}
	
	public JPanel getPanelBttHaut() {
		return panelBttHaut;
	}

	public JPanel getPanelInfos() {
		return panelInfos;
	}

	public JPanel getPanelBttBas() {
		return panelBttBas;
	}

	public JScrollPane getPanelTablAnimaux() {
		return panelTablAnimaux;
	}
	
	public JPanel getPanelCentral(){
		return panelCentral;
	}
	
	
	
}
