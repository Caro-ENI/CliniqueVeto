package fr.eni.cach.clinique.ihm.priseRDV;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import fr.eni.cach.clinique.bll.AnimalManager;
import fr.eni.cach.clinique.bll.BLLException;
import fr.eni.cach.clinique.bll.ClientManager;
import fr.eni.cach.clinique.bll.Utilitaires.DateLabelFormatter;
import fr.eni.cach.clinique.bo.Animal;
import fr.eni.cach.clinique.bo.Client;
import fr.eni.cach.clinique.ihm.UtilsIHM;
import fr.eni.cach.clinique.ihm.cliniqueVeto.CliniqueVetoFrame2;
import fr.eni.cach.clinique.ihm.ecranAnimaux.AnimauxPanel;
import fr.eni.cach.clinique.ihm.ecranClients.AjoutClientPanel;

public class PriseRDVPanel extends JPanel {
	/*
	 * ORGANISATION DE LA CLASSE : 
	 * - d�claration des attributs 
	 * - constructeur principal 
	 * - cr�ation des panels 
	 * - creation des boutons 
	 * - m�thodes annexes 
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
	 * Panel contenant les boutons de l'�dition d'un RDV
	 */
	private JPanel panelBoutons;
	
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
	
	
	/**
	 * Tables des RDV
	 */
	private JTable tableRDV;
	
	//Boutons de l'�dition d'un rdv
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
	
	private JDatePicker datePicker;
		
	//TODO changer en v�t�rinaire
	private JComboBox<String> jcbVeto;

	private JComboBox<Client> jcbClient;
	private JComboBox<Animal> jcbAnimal;
	
	private JComboBox<Integer> jcbHeures;
	private JComboBox<Integer> jcbMinutes;
	
	private JPanel panelHeure;
	
	private UtilsIHM utilsIHM = UtilsIHM.getInstance();
	
	/**
	 * mod�le de JTable disposant les donn�es dans la JTable
	 */
	private TableRDVModel modelTablRDV;
	
	
	// *********** CONSTRUCTEUR ***************
	
	/**
	 * Constructeur de la fen�tre Prise de RDV (3)
	 * Permet de saisir un nouveau RDV
	 * Permet de visualiser les RDV � une date donn�e
	 * Permet d'�diter un RDV d�j� pris
	 */
	public PriseRDVPanel() {
			
		// cr�ation de la JTable
		this.createTableRDV();
		
		// cr�ation des Boutons 
		this.createBttOK();
		this.createBttSuppr();
		this.createBttModif();
		this.createBttAjoutClient();
		this.createBttAjoutAniaml();
		
		// cr�ation des JComboBox 
		this.createJcbVeto();
		this.createJcbAnimal();
		this.createJcbClient();
		this.createJcbHeures();
		this.createJcbMinutes();
		
		// cr�ation des JLabels 
		this.createJlClient();
		this.createJlAnimal();
		this.createJlVeto();
		this.createJlDate();
		this.createJlHeure();
		this.createJlH();
		
		// cr�ation du DatePicker
		
		this.createDatePicker();

		
		//cr�ation des panels
		this.createPanelHeure();
		this.createPanelPour();
		this.createPanelPar();
		this.createPanelQuand();
		
		this.createPanelInformation();
		this.createPanelTablRDV();
		this.createPanelBoutons();
	
		//D�finition du Layout global 
		BorderLayout layoutGlobal = new BorderLayout();
		this.setLayout(layoutGlobal);

		//ajout des panels au panel g�n�ral 
		this.add(getPanelInformations(), BorderLayout.NORTH);
		this.add(getPanelTablRDV(), BorderLayout.CENTER);
		this.add(getPanelBoutons(), BorderLayout.SOUTH);
		
	}

	
	


	// *********** CREATE PANELS **************
	/**
	 * Cr�e le panel d'informations du RDV � partir de 3 sous-panels
	 * panelPour
	 * panelPar
	 * panelQuand
	 */
	private void createPanelInformation(){
		panelInfomations = new JPanel(new GridBagLayout());
		panelInfomations.setBackground(Color.LIGHT_GRAY);
		
		utilsIHM.addComponentTo(getPanelPour(), panelInfomations, 0, 0, 1, 1, 0.5, true);
		utilsIHM.addComponentTo(getPanelPar(), panelInfomations, 1, 0, 1, 1, 0.5, true);
		utilsIHM.addComponentTo(getPanelQuand(), panelInfomations, 2, 0, 1, 1, 0.5, true);
		

	}
	
	/**
	 * Cr�e le Panel affichant les RDVs (contient la JTable des RDV)
	 */
	private void createPanelTablRDV() {
		panelTablRDV = new JScrollPane();		
		panelTablRDV.setViewportView(getTableRDV());
	}
	
	/**
	 * cr�e le panel contenant les boutons d'�dition des RDV
	 * MODIFIER SUPPRIMER VALIDER
	 */
	private void createPanelBoutons() {
		//permet de d�finir l'orientation de l'�criture dans le panel (� reformuler si meilleure explication)
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		panelBoutons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		//l'ordre des .add d�termine la position des boutons 
		panelBoutons.add(getBttModif());
		panelBoutons.add(getBttSuppr());
		panelBoutons.add(getBttOK());
		
	}
	
	/**
	 * Cr�e le panel Pour : Renseigne les infos du client gr�ce � des JComboBox
	 * nom du client
	 * nom de son animal 
	 * Si l'une des donn�es n'est pas en BDD il est possible de la cr�er � la vol�e 
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
	 * Cr�e le panel Par : renseigne sur le v�to gr�ce � des JComboBox
	 * par d�faut donne le premier veto disponible (� la premi�re date disponible)
	 */
	private void createPanelPar() {
		panelPar = new JPanel(new GridBagLayout());
		panelPar.setBorder(new TitledBorder("Par : "));
		
		utilsIHM.addComponentTo(getJlVeto(), panelPar, 0, 0, 1, 1, 0.2, true);
		utilsIHM.addComponentTo(getJcbVeto(), panelPar, 0, 1, 1, 1, 0.8, true);
	
	}
	
	/**
	 * Cr�e le panel Quand : renseigne la date du Rdv
	 * Date rentr�e dans un champ ou gr�ce � une JDatePicker
	 * Heure avec 2 Jcombobox (heures et minutes) -> seuelement tous les 15min de dispo pour les mins
	 * par d�faut donne le premier veto disponible (� la premi�re date/heure disponible)
	 */
	private void createPanelQuand() {
		panelQuand = new JPanel(new GridBagLayout());
		panelQuand.setBorder(new TitledBorder("Quand : "));
		
		utilsIHM.addComponentTo(getJlDate(), panelQuand, 0, 0, 1, 1, 0.2, true);
		utilsIHM.addComponentTo((JComponent) getDatePicker(), panelQuand, 0, 1, 1, 1, 0.5, true);
		
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
	 * Cr�e la Table des RDV � partir d'un mod�le de donn�es
	 * doit �tre r�actif � la date saisie dans le panelQuand
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
 	
 	
 	// ****************** CREATE JDATE PICKER ******************************
 	
 	
 	private void createDatePicker() {
 		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Aujourd'hui");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
	}

	
	// *********** CREATE BOUTONS **************
	
	/**
	 * Cr�e le bouton valider 
	 * (au clic) enregistre le RDV saisi dans le panel Information
	 */
	private void createBttOK(){
		bttOK = new JButton("Valider");
	}

	/**
	 * Cr�e le bouton supprimer 
	 * (au clic) supprime le RDV s�lectionn� dans la Table des RDV
	 */
	private void createBttSuppr() {
		bttSuppr = new JButton("Supprimer");
	}

	/**
	 * Cr�e le bouton valider 
	 * (au clic) modifie le RDV s�lectionn� dans la Table des RDV
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
				AnimauxPanel panelAnimal = new AnimauxPanel(jcbClient.getSelectedItem().toString());
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
		jlVeto = new JLabel("V�t�rinaire");
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
		//permet de ne pas s�lectionner par d�faut le nom d'un v�to
		jcbVeto.setSelectedItem(null);
	}
	
	private void createJcbClient(){
		jcbClient = new JComboBox<>();

		try {
			List<Client> listeClients = ClientManager.getInstance().getListeClients();
			for (Client cliCourant : listeClients) {
				jcbClient.addItem(cliCourant);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
		jcbClient.setSelectedItem(null);
		jcbClient.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				//v�rification que l'objet de la jcombobox est bien un client
				if (jcbClient.getSelectedItem() instanceof Client) {
				
					//le client s�lectionn� est stock� dans une variable interm�diaire
					Client client = (Client) jcbClient.getSelectedItem();
					//on utilise AnimalManager pour r�cuperer la liste des animaux associ�e au client
						List<Animal> animauxClient = AnimalManager.getInstance().selectAnimaux(client.getCodeClient());
						jcbAnimal.removeAllItems();
						for (Animal animal : animauxClient) {
							jcbAnimal.addItem(animal);
						}
					}
					} catch (BLLException e1) {
						JOptionPane.showMessageDialog(PriseRDVPanel.this, "Impossible d'acc�der aux animaux du Client",
								"Erreur", JOptionPane.WARNING_MESSAGE);
						e1.printStackTrace();
				}	
			}
		});
	}
	
	private void createJcbAnimal(){
		jcbAnimal = new JComboBox<>();
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
	 * (voir create associ� pour les d�tails du panel)
	 * @return
	 */
	public JPanel getPanelPour(){
		return panelPour;
	}

	/**
	 * renvoie le panel Pour
	 * (voir create associ� pour les d�tails du panel)
	 * @return
	 */
	public JPanel getPanelPar() {
		return panelPar;
	}

	/**
	 * renvoie le panel Pour
	 * (voir create associ� pour les d�tails du panel)
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
	
	public JComboBox<Client> getJcbClient() {
		return jcbClient;
	}
	
	public JButton getBttAjoutClient() {
		return bttAjoutClient;
	}
	
	public JLabel getJlAnimal() {
		return jlAnimal;
	}
	
	public JComboBox<Animal> getJcbAnimal() {
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





	public JPanel getPanelInfomations() {
		return panelInfomations;
	}





	public JDatePicker getDatePicker() {
		return datePicker;
	}





	public TableRDVModel getModelTablRDV() {
		return modelTablRDV;
	}
	
	
	
}
