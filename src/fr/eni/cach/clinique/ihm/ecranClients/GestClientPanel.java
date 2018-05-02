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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import fr.eni.cach.clinique.bll.AnimalManager;
import fr.eni.cach.clinique.bll.BLLException;
import fr.eni.cach.clinique.bll.ClientManager;
import fr.eni.cach.clinique.bo.Animal;
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

	private Client clientCourant = null;

	public GestClientPanel() {

		// Initialisation des champs

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

	// ------ METHODES INTERNES --------

	/**
	 * Permet de rafraichir l'affichage du client courant
	 * 
	 * @param client
	 */
	public void refreshAffichageClient(Client client) {
		if (client == null) {
			this.tfCodeClient.setText("");
			this.tflNom.setText("");
			this.tfPrenom.setText("");
			this.tfAdresse1.setText("");
			this.tfAdresse2.setText("");
			this.tfCodePostal.setText("");
			this.tfVille.setText("");
			this.tfNumTel.setText("");
			this.tfEmail.setText("");
			this.tfAssurance.setText("");
			this.tfRemarques.setText("");

		} else {

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

		this.clientCourant = client;

	}

	/**
	 * Permet de raflaichir l'affichage de la liste des animaux
	 * 
	 * @param client
	 */
	public void rerefreshAffichageAnimaux(Client client) {
		tablAnimaux.removeAll();
		TableAnimalModel.getInstance().chargementDonnees(client.getCodeClient());
	}

	/**
	 * Construit le client courant selon les champs éditables/
	 * 
	 * @return
	 */
	public Client createClientCourant() {
		clientCourant.setCodeClient(Integer.parseInt((getTfCodeClient().getText())));
		clientCourant.setNomClient(getTflNom().getText());
		clientCourant.setPrenomClient(getTfPrenom().getText());
		clientCourant.setAdresse1(getTfAdresse1().getText());
		clientCourant.setAdresse2(getTfAdresse2().getText());
		clientCourant.setCodePostal(getTfCodePostal().getText());
		clientCourant.setVille(getTfVille().getText());
		clientCourant.setNumTel(getTfNumTel().getText());
		clientCourant.setEmail(getTfEmail().getText());
		clientCourant.setAssurance(getTfAssurance().getText());
		clientCourant.setRemarque(getTfRemarques().getText());
		return clientCourant;
	}

	// --------------------CREATION DES PANELS-----------------------------

	private void createPanelBttBas() {
		// permet de définir l'orientation de l'écriture dans le panel
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		panelBttBas = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		// panelBoutons.setBackground(Color.GRAY);

		// pour mettre une bordure sur le panel boutons : faire appel à la
		// BorderFactory
		panelBttBas.setBorder(BorderFactory.createEtchedBorder());

		// -> Récupère la taille de l'écran
		// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// panelBoutons.setSize(screenSize.width-10, 4);

		panelBttBas.add(getBttAjouterAnimal());
		panelBttBas.add(getBttSupprimerAnimal());
		panelBttBas.add(getBttEditerAnimal());

	}

	private void createPanelTablAnimaux() {

		panelTablAnimaux = new JScrollPane();
		panelTablAnimaux.setViewportView(getTablAnimaux());

	}

	private void createPanelInfos() {
		panelInfos = new JPanel(new GridBagLayout());

		// Même code que dans la frame AjouterUnClient

		utilsIHM.addComponentTo(getLblCodeClient(), panelInfos, 0, 0, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTfCodeClient(), panelInfos, 1, 0, 1, 1, 0.8, true);

		utilsIHM.addComponentTo(getLblNom(), panelInfos, 0, 1, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTflNom(), panelInfos, 1, 1, 1, 1, 0.8, true);

		utilsIHM.addComponentTo(getLblPrenom(), panelInfos, 0, 2, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTfPrenom(), panelInfos, 1, 2, 1, 1, 0.8, true);

		utilsIHM.addComponentTo(getLblAdresse(), panelInfos, 0, 3, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTfAdresse1(), panelInfos, 1, 3, 1, 1, 0.8, true);
		utilsIHM.addComponentTo(getTfAdresse2(), panelInfos, 1, 4, 1, 1, 0.8, true);

		utilsIHM.addComponentTo(getLblCodePostal(), panelInfos, 0, 5, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTfCodePostal(), panelInfos, 1, 5, 1, 1, 0.8, true);

		utilsIHM.addComponentTo(getLblVille(), panelInfos, 0, 6, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTfVille(), panelInfos, 1, 6, 1, 1, 0.8, true);

		utilsIHM.addComponentTo(getLblNumTel(), panelInfos, 0, 7, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTfNumTel(), panelInfos, 1, 7, 1, 1, 0.8, true);

		utilsIHM.addComponentTo(getLblEmail(), panelInfos, 0, 8, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTfEmail(), panelInfos, 1, 8, 1, 1, 0.8, true);

		utilsIHM.addComponentTo(getLblAssurance(), panelInfos, 0, 9, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTfAssurance(), panelInfos, 1, 9, 1, 1, 0.8, true);

		utilsIHM.addComponentTo(getLblRemarques(), panelInfos, 0, 10, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getTfRemarques(), panelInfos, 1, 10, 1, 1, 0.8, true);

	}

	private void createPanelBttHaut() {

		panelBttHaut = new JPanel(new GridBagLayout());
		// panelBoutons.setBackground(Color.GRAY);

		// pour mettre une bordure sur le panel boutons : faire appel à la
		// BorderFactory
		// panelBttHaut.setBorder(BorderFactory.createEtchedBorder());

		// -> Récupère la taille de l'écran
		// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// panelBoutons.setSize(screenSize.width-10, 4);

		utilsIHM.addComponentTo(getBttRechercher(), panelBttHaut, 0, 0, 1, 1, 1, true);
		utilsIHM.addComponentTo(getBttAjouterClient(), panelBttHaut, 3, 0, 1, 1, 1, true);
		utilsIHM.addComponentTo(getBttSupprimerClient(), panelBttHaut, 4, 0, 1, 1, 1, true);
		utilsIHM.addComponentTo(getBttValider(), panelBttHaut, 7, 0, 1, 1, 1, true);
		utilsIHM.addComponentTo(getBttAnnuler(), panelBttHaut, 8, 0, 1, 1, 1, true);

	}

	private void createPanelCentral() {
		panelCentral = new JPanel(new GridBagLayout());
		utilsIHM.addComponentTo(getPanelInfos(), panelCentral, 0, 0, 1, 1, 0.8, true);
		utilsIHM.addComponentTo(getPanelTablAnimaux(), panelCentral, 1, 0, 1, 1, 0.8, true);
	}

	// -------------CREATION DES COMPONENTS----------------------------------

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
		// rend la zone de texte non éditable par l'utilisateur
		tfCodeClient.setEditable(false);

	}

	private void createLblRemarques() {
		lblRemarques = new JLabel("Remarques");

	}

	private void createLblAssurance() {
		lblAssurance = new JLabel("Assurance");

	}

	private void createLblNumTel() {
		lblNumTel = new JLabel("N° téléphone");

	}

	private void createLblEmail() {
		lblEmail = new JLabel("Email");
	}

	private void createLblVille() {
		lblVille = new JLabel("Ville");

	}

	private void createLblCodePostal() {
		lblCodePostal = new JLabel("Code postal");

	}

	private void createLblAdresse() {
		lblAdresse = new JLabel("Adresse");

	}

	private void createLblPrenom() {
		lblPrenom = new JLabel("Prenom");

	}

	private void createLblNom() {
		lblNom = new JLabel("Nom");

	}

	private void createLblCodeClient() {
		lblCodeClient = new JLabel("Code");

	}

	private void createTablAnimaux() {
		modelTablAnimaux = TableAnimalModel.getInstance();
		tablAnimaux = new JTable(modelTablAnimaux);
		tablAnimaux.setBorder(BorderFactory.createEtchedBorder());
		tablAnimaux.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablAnimaux.setRowHeight(30);
		tablAnimaux.getSelectionModel().setSelectionInterval(0, 0);

	}

	private void createBttEditerAnimal() {
		bttEditerAnimal = new JButton("Éditer");
		bttEditerAnimal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (clientCourant == null) {
					JOptionPane.showMessageDialog(GestClientPanel.this,
							"Vous ne pouvez pas éditer un animal sans avoir un client affiché.", "Attention",
							JOptionPane.WARNING_MESSAGE);
				} else if (tablAnimaux.getSelectedRow() < 1) {
					JOptionPane.showMessageDialog(GestClientPanel.this,
							"Vous devez d'abord sélectionner un animal dans le tableau.", "Attention",
							JOptionPane.WARNING_MESSAGE);
				} else {
					AnimauxPanel panelAnimal = new AnimauxPanel(clientCourant);
					JInternalFrame jifAjoutAnimal = utilsIHM.createJIF("Animal", panelAnimal);
					jifAjoutAnimal.setSize(500, 350);
					jifAjoutAnimal.setVisible(true);
					CliniqueVetoFrame2.getInstance("").getDesktop().add(jifAjoutAnimal);
					try {
						jifAjoutAnimal.setSelected(true);
					} catch (java.beans.PropertyVetoException eAjoutCli) {
					}
				}
			}
		});
	}

	private void createBttSupprimerAnimal() {
		bttSupprimerAnimal = new JButton("Supprimer");
		bttSupprimerAnimal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tablAnimaux.getSelectedRow() < 1) {
					JOptionPane.showMessageDialog(GestClientPanel.this,
							"Vous devez d'avoir avoir sélectionné un animal dans la liste avant de le supprimer.",
							"Attention", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						// on sélectionne l'animal à supprimer à partir de la
						// ligne sélectionnée
						Animal animalASuppr = TableAnimalModel.getInstance().getValueAt(tablAnimaux.getSelectedRow());
						// on archive l'animal
						animalASuppr.setArchive(true);

						AnimalManager.getInstance().updateAnimal(animalASuppr);

						JOptionPane.showMessageDialog(GestClientPanel.this, "L'Animal a bien été supprimé.",
								"Infomation", JOptionPane.INFORMATION_MESSAGE);
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					TableAnimalModel.getInstance().chargementDonnees(clientCourant.getCodeClient());
				}
			}
		});
	}

	private void createBttAjouterAnimal() {
		bttAjouterAnimal = new JButton("Ajouter");
		bttAjouterAnimal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (clientCourant == null) {
					JOptionPane.showMessageDialog(GestClientPanel.this,
							"Vous ne pouvez pas créer un animal sans avoir un client affiché.", "Attention",
							JOptionPane.WARNING_MESSAGE);
				} else {
					AnimauxPanel panelAnimal = new AnimauxPanel(clientCourant);
					JInternalFrame jifAjoutAnimal = utilsIHM.createJIF("Animal", panelAnimal);
					jifAjoutAnimal.setSize(500, 350);
					jifAjoutAnimal.setVisible(true);
					CliniqueVetoFrame2.getInstance("").getDesktop().add(jifAjoutAnimal);
					try {
						jifAjoutAnimal.setSelected(true);
					} catch (java.beans.PropertyVetoException eAjoutCli) {
					}
				}
			}
		});

	}

	private void createBttAnnuler() {
		bttAnnuler = new JButton("Annuler");
		bttAnnuler.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GestClientPanel.this.refreshAffichageClient(clientCourant);
			}
		});

	}

	private void createBttEditerClient() {
		bttEditerClient = new JButton("Éditer");
		bttEditerClient.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// on vérifie qu'il y a bien un client courant :
				if (clientCourant == null) {
					JOptionPane.showMessageDialog(GestClientPanel.this,
							"Vous devez d'abord avoir affiché un client avant de le modifier. Pour créer un nouveau client utilisez le bouton Ajouter",
							"Attention", JOptionPane.WARNING_MESSAGE);
				} else {
					// le client à modifier est le client courant, on récupère
					// donc ses informations
					Client cliAModif = GestClientPanel.this.createClientCourant();
					try {
						ClientManager.getInstance().updateClient(cliAModif);
						JOptionPane.showMessageDialog(GestClientPanel.this, "Le Client a bien été édité.",
								"Information", JOptionPane.INFORMATION_MESSAGE);
					} catch (BLLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}

	private void createBttSupprimerClient() {
		bttSupprimerClient = new JButton("Supprimer");
		bttSupprimerClient.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// on vérifie qu'il y a bien un client courant :
				if (clientCourant == null) {
					JOptionPane.showMessageDialog(GestClientPanel.this,
							"Vous devez d'abord avoir affiché un client avant de le supprimer.", "Attention",
							JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						// on modifie la valeur Archive du client courant afin
						// qu'il s'archive bien
						clientCourant.setArchive(true);
						ClientManager.getInstance().updateClient(clientCourant);
						// on informe l'utilisateur
						JOptionPane.showMessageDialog(GestClientPanel.this, "Le Client a bien été supprimé.",
								"Information", JOptionPane.INFORMATION_MESSAGE);

						// on fait un refresh de l'affichage
						clientCourant = null;
						refreshAffichageClient(clientCourant);
					} catch (BLLException e1) {
						e1.printStackTrace();
					}

				}

			}
		});
	}

	private void createBttAjouterClient() {
		bttAjouterClient = new JButton("Ajouter");
		bttAjouterClient.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AjoutClientPanel panelAjout = new AjoutClientPanel(GestClientPanel.this);
				JInternalFrame jifAjoutClient = utilsIHM.createJIF("Nouveau Client", panelAjout);
				jifAjoutClient.setVisible(true);
				CliniqueVetoFrame2.getInstance("").getDesktop().add(jifAjoutClient);
				try {
					jifAjoutClient.setSelected(true);
				} catch (java.beans.PropertyVetoException eAjoutCli) {
				}
			}
		});

	}

	private void createBttRechercher() {
		bttRechercher = new JButton("Rechercher");
		bttRechercher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RechercherPanel panelRecherche = new RechercherPanel(GestClientPanel.this);
				TableClientModel.getInstance().dechargementDonnees();
				JInternalFrame jifRechercher = UtilsIHM.getInstance().createJIF("Recherche de Client", panelRecherche);
				jifRechercher.setVisible(true);
				CliniqueVetoFrame2.getInstance("").getDesktop().add(jifRechercher);
				try {
					jifRechercher.setSelected(true);
				} catch (java.beans.PropertyVetoException eAjoutCli) {
				}

			}
		});
	}

	// *************************GETTERS***************************************************

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

	public JLabel getLblEmail() {
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

	public JTextField getTfEmail() {
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

	public JPanel getPanelCentral() {
		return panelCentral;
	}

}
