package fr.eni.cach.clinique.ihm.priseRDV;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import org.jdatepicker.JDatePicker;

import fr.eni.cach.clinique.ihm.UtilsIHM;
import fr.eni.cach.clinique.ihm.cliniqueVeto.CliniqueVetoFrame2;
import fr.eni.cach.clinique.ihm.ecranAnimaux.AnimauxPanel;
import fr.eni.cach.clinique.ihm.ecranClients.AjoutClientPanel;

public class PriseRDVPanel extends JPanel {
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

	private static final long serialVersionUID = 7834116385228727053L;
	
	/**
	 * Panel contenant les informations relatives au Client
	 */
	private JPanel panelInfomations;
	
	/**
	 * Panel avec scroll contenant la Jtable des RDV
	 */
	private JScrollPane panelTablRDV;
	
	/**
	 * Panel contenant les boutons de l'édition d'un RDV
	 */
	private JPanel panelBoutons;
	
	/**
	 * Tables des RDV
	 */
	private JTable tableRDV;
	
	/**
	 * Sous panel de Panel Information
	 */
	private JPanel panelPour;

	/**
	 * Sous panel de Panel Information
	 */
	private JPanel panelPar;

	/**
	 * Sous panel de Panel Information
	 */
	private JPanel panelQuand;
	
	//Boutons de l'édition d'un rdv
	private JButton bttOK;
	private JButton bttSuppr;
	private JButton bttModif;
	private JButton bttAjoutClient;
	private JButton bttAjoutAnimal;
	
	private JLabel jlClient;
	private JLabel jlAnimal;
	private JLabel jlVeto;
	private JLabel jlDate;
	private JLabel jlHeure;
	private JLabel jlH;
		
	private JComboBox<String> jcbVeto;
	//TODO changer en Client et en Animal !! 
	private JComboBox<String> jcbClient;
	private JComboBox<String> jcbAnimal;
	
	private JComboBox<Integer> jcbHeures;
	private JComboBox<Integer> jcbMinutes;
	
	private JPanel panelHeure;
	
	private UtilsIHM utilsIHM = UtilsIHM.getInstance();
	
	/**
	 * modèle de JTable disposant les données dans la JTable
	 */
	private TableRDVModel modelTablRDV;
	
	
	// *********** CONSTRUCTEUR ***************
	
	/**
	 * Constructeur de la fenêtre Prise de RDV (3)
	 * Permet de saisir un nouveau RDV
	 * Permet de visualiser les RDV à une date donnée
	 * Permet d'éditer un RDV déjà pris
	 */
	public PriseRDVPanel() {
			
		// création de la JTable
		this.createTableRDV();
		
		// création des Boutons 
		this.createBttOK();
		this.createBttSuppr();
		this.createBttModif();
		this.createBttAjoutClient();
		this.createBttAjoutAniaml();
		
		// création des JComboBox 
		this.createJcbVeto();
		this.createJcbAnimal();
		this.createJcbClient();
		this.createJcbHeures();
		this.createJcbMinutes();
		
		// création des JLabels 
		this.createJlClient();
		this.createJlAnimal();
		this.createJlVeto();
		this.createJlDate();
		this.createJlHeure();
		this.createJlH();

		
		//création des panels
		this.createPanelHeure();
		this.createPanelPour();
		this.createPanelPar();
		this.createPanelQuand();
		
		this.createPanelInformation();
		this.createPanelTablRDV();
		this.createPanelBoutons();
	
		//Définition du Layout global 
		BorderLayout layoutGlobal = new BorderLayout();
		this.setLayout(layoutGlobal);

		//ajout des panels au panel général 
		this.add(getPanelInformations(), BorderLayout.NORTH);
		this.add(getPanelTablRDV(), BorderLayout.CENTER);
		this.add(getPanelBoutons(), BorderLayout.SOUTH);
		
	}

	
	// *********** CREATE PANELS **************
	/**
	 * Crée le panel d'informations du RDV à partir de 3 sous-panels
	 * panelPour
	 * panelPar
	 * panelQuand
	 */
	private void createPanelInformation(){
		panelInfomations = new JPanel(new GridBagLayout());
		panelInfomations.setBackground(Color.ORANGE);
		
		utilsIHM.addComponentTo(getPanelPour(), panelInfomations, 0, 0, 1, 1, 0.5, true);
		utilsIHM.addComponentTo(getPanelPar(), panelInfomations, 1, 0, 1, 1, 0.5, true);
		utilsIHM.addComponentTo(getPanelQuand(), panelInfomations, 2, 0, 1, 1, 0.5, true);
		

	}
	
	/**
	 * Crée le Panel affichant les RDVs (contient la JTable des RDV)
	 */
	private void createPanelTablRDV() {
		panelTablRDV = new JScrollPane();		
		panelTablRDV.setViewportView(getTableRDV());
	}
	
	/**
	 * crée le panel contenant les boutons d'édition des RDV
	 * MODIFIER SUPPRIMER VALIDER
	 */
	private void createPanelBoutons() {
		//permet de définir l'orientation de l'écriture dans le panel (à reformuler si meilleure explication)
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		panelBoutons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		//l'ordre des .add détermine la position des boutons 
		panelBoutons.add(getBttModif());
		panelBoutons.add(getBttSuppr());
		panelBoutons.add(getBttOK());
		
	}
	
	/**
	 * Crée le panel Pour : Renseigne les infos du client grâce à des JComboBox
	 * nom du client
	 * nom de son animal 
	 * Si l'une des données n'est pas en BDD il est possible de la créer à la volée 
	 * -> liens vers (4) et (6)
	 */
	private void createPanelPour() {
		panelPour = new JPanel(new GridBagLayout());
		
		//met une Bordure au Panel et lui donne un titre
		panelPour.setBorder(new TitledBorder("Pour : "));
		
		utilsIHM.addComponentTo(getJlClient(), panelPour, 0, 0, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getJcbClient(), panelPour, 0, 1, 1, 1, 0.8, true);
		utilsIHM.addComponentTo(getBttAjoutClient(), panelPour, 1, 1, 1, 1, 0.2, false);
		
		utilsIHM.addComponentTo(getJlAnimal(), panelPour, 0, 2, 1, 1, 0.2, false);
		utilsIHM.addComponentTo(getJcbAnimal(), panelPour, 0, 3, 1, 1, 0.8, true);
		utilsIHM.addComponentTo(getBttAjoutAnimal(), panelPour, 1, 3, 1, 1, 0.2, false);
		
	}
	
	/**
	 * Crée le panel Par : renseigne sur le véto grâce à des JComboBox
	 * par défaut donne le premier veto disponible (à la première date disponible)
	 */
	private void createPanelPar() {
		panelPar = new JPanel(new GridBagLayout());
		panelPar.setBorder(new TitledBorder("Par : "));
		
		utilsIHM.addComponentTo(getJlVeto(), panelPar, 0, 0, 1, 1, 0.2, true);
		utilsIHM.addComponentTo(getJcbVeto(), panelPar, 0, 1, 1, 1, 0.8, true);
	
	}
	
	/**
	 * Crée le panel Quand : renseigne la date du Rdv
	 * Date rentrée dans un champ ou grâce à une JDatePicker
	 * Heure avec 2 Jcombobox (heures et minutes) -> seuelement tous les 15min de dispo pour les mins
	 * par défaut donne le premier veto disponible (à la première date/heure disponible)
	 */
	private void createPanelQuand() {
		panelQuand = new JPanel(new GridBagLayout());
		panelQuand.setBorder(new TitledBorder("Quand : "));
		
		utilsIHM.addComponentTo(getJlDate(), panelQuand, 0, 0, 1, 1, 0.2, true);
		utilsIHM.addComponentTo(new JDatePicker(), panelQuand, 0, 1, 1, 1, 0.5, true);
		
		utilsIHM.addComponentTo(getJlHeure(), panelQuand, 0, 2, 1, 1, 0.2, true);
		utilsIHM.addComponentTo(getPanelHeure(), panelQuand, 0, 3, 1, 1, 0.8, true);
	}
	
	private void createPanelHeure() {
		panelHeure = new JPanel();
		BoxLayout layout = new BoxLayout(panelHeure, BoxLayout.X_AXIS);
		panelHeure.setLayout(layout);
		panelHeure.add(getJcbHeures());
		panelHeure.add(getJlH());
		panelHeure.add(getJcbMinutes());


	}


	// *********** CREATE JTABLE **************
	
	/**
	 * Crée la Table des RDV à partir d'un modèle de données
	 * doit être réactif à la date saisie dans le panelQuand
	 */
 	private void createTableRDV(){
		modelTablRDV = new TableRDVModel();
		
		tableRDV = new JTable(modelTablRDV);
		
		tableRDV.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tableRDV.setRowHeight(30);
		
		tableRDV.getSelectionModel().setSelectionInterval(0, 0);
		
		/* pour l'ajout d'un LISTENER
		 tableArticle.getSelectionModel().addListSelectionListener(new List...
		 */
	}

	
	// *********** CREATE BOUTONS **************
	
	/**
	 * Crée le bouton valider 
	 * (au clic) enregistre le RDV saisi dans le panel Information
	 */
	private void createBttOK(){
		bttOK = new JButton("Valider");
	}

	/**
	 * Crée le bouton supprimer 
	 * (au clic) supprime le RDV sélectionné dans la Table des RDV
	 */
	private void createBttSuppr() {
		bttSuppr = new JButton("Supprimer");
	}

	/**
	 * Crée le bouton valider 
	 * (au clic) modifie le RDV sélectionné dans la Table des RDV
	 *  avec les informations saisies dans le panel Information
	 */
	private void createBttModif() {
		bttModif = new JButton("Modifier");
	}

	private void createBttAjoutClient(){
		bttAjoutClient = new JButton("Ajout");
		bttAjoutClient.addActionListener(new ActionListener() {
			
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
	
	private void createBttAjoutAniaml(){
		bttAjoutAnimal = new JButton("Ajout");
		bttAjoutAnimal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AnimauxPanel panelAnimal = new AnimauxPanel();
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
	


	
	// *********** CREATE JLABELS **************
	private void createJlAnimal(){
		jlAnimal = new JLabel("Animal");
	}
	
	private void createJlVeto(){
		jlVeto = new JLabel("Vétérinaire");
	}
	
	private void createJlDate(){
		jlDate = new JLabel("Date");
	}
	
	private void createJlHeure() {
		jlHeure = new JLabel("Heure");
	}
	
	private void createJlH(){
		jlH = new JLabel("h"); 
	}
	
	private void createJlClient(){
		jlClient = new JLabel("Client");
	}

	// *********** CREATE JCOMBOBOX **************	
		
	private void createJcbVeto(){
		//TODO remplir avec la liste des veto courante
		String[] veto = {"Veto1", "Veto2", "Veto3", "Veto4", "Veto5"};
		jcbVeto = new JComboBox<>(veto);
		//permet de ne pas sélectionner par défaut le nom d'un véto
		jcbVeto.setSelectedItem(null);
	}
	
	private void createJcbClient(){
		//TODO remplir avec la liste des veto courante
		String[] veto = {"Client1", "Client2", "Client3", "Client4", "Client5"};
		jcbClient = new JComboBox<>(veto);
		//permet de ne pas sélectionner par défaut le nom d'un véto
		jcbClient.setSelectedItem(null);
	}
	
	private void createJcbAnimal(){
		//TODO remplir avec la liste des veto courante
		String[] veto = {"Animal1", "Animal2", "Animal3", "Animal4", "Animal5"};
		jcbAnimal = new JComboBox<>(veto);
		//permet de ne pas sélectionner par défaut le nom d'un véto
		jcbAnimal.setSelectedItem(null);
	}

	
	private void createJcbHeures() {
		Integer[] heures = {8,9,10,11,12,13,14,15,16,17,18,19};
		jcbHeures = new JComboBox<Integer>(heures);
	}

	private void createJcbMinutes() {
		Integer[] minutes = {00,15,30,45};
		jcbMinutes = new JComboBox<Integer>(minutes);
	}
	

	
	// *********** GETTERS **************
	
	/**
	 * Renvoie le panel d'informations
	 * @return
	 */
 	public JPanel getPanelInformations(){
		return panelInfomations;
	}
	
	/**
	 * Renvoie le panel 
	 * @return
	 */
	public JScrollPane getPanelTablRDV(){
		return panelTablRDV;
	}
	
	/**
	 * Renvoie le Panel avec les boutons Suppr et Valider
	 * @return
	 */
	public JPanel getPanelBoutons(){
		return panelBoutons;
	}
	
	/**
	 * renvoie la table des RDV
	 * @return
	 */
	public JTable getTableRDV(){
		return tableRDV;
	}

	/**
	 * Renvoie le bouton de modification des informations d'un rdv
	 * @return
	 */
	public JButton getBttModif() {
		return bttModif;
	}
	
	/**
	 * Renvoie le bouton de suppression d'un rdv
	 * @return
	 */
	public JButton getBttSuppr(){
		return bttSuppr;
	}
	
	/**
	 * Renvoie le bouton de validation des information d'un RDV
	 * @return
	 */
	public JButton getBttOK() {
		return bttOK;
	}

	/**
	 * renvoie le panel Pour
	 * (voir create associé pour les détails du panel)
	 * @return
	 */
	public JPanel getPanelPour(){
		return panelPour;
	}

	/**
	 * renvoie le panel Pour
	 * (voir create associé pour les détails du panel)
	 * @return
	 */
	public JPanel getPanelPar() {
		return panelPar;
	}

	/**
	 * renvoie le panel Pour
	 * (voir create associé pour les détails du panel)
	 * @return
	 */
	public JPanel getPanelQuand(){
		return panelQuand;
	}

	public JComboBox<String> getJcbVeto(){
		return jcbVeto;
	}

	public JLabel getJlClient() {
		return jlClient;
	}
	
	public JComboBox<String> getJcbClient() {
		return jcbClient;
	}
	
	public JButton getBttAjoutClient() {
		return bttAjoutClient;
	}
	
	public JLabel getJlAnimal() {
		return jlAnimal;
	}
	
	public JComboBox<String> getJcbAnimal() {
		return jcbAnimal;
	}

	public JButton getBttAjoutAnimal() {
		return bttAjoutAnimal;
	}

	public JLabel getJlVeto() {
		return jlVeto;
	}

	public JLabel getJlDate() {
		return jlDate;
	}
	
	public JLabel getJlHeure() {
		return jlHeure;
	}

	public JLabel getJlH() {
		return jlH;
	}
	
	public JComboBox<Integer> getJcbHeures(){
		return jcbHeures;
	}
	
	public JComboBox<Integer> getJcbMinutes(){
		return jcbMinutes;
	}
	
	public JPanel getPanelHeure() {
		return panelHeure;
	}
}
