package fr.eni.cach.clinique.ihm.cliniqueVeto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import fr.eni.cach.clinique.ihm.UtilsIHM;
import fr.eni.cach.clinique.ihm.connexion.ConnexionFrame;
import fr.eni.cach.clinique.ihm.priseRDV.PriseRDVPanel;

public class CliniqueVetoFrame extends JFrame {

	private static final long serialVersionUID = -6577457469948527041L;

	private UtilsIHM utilsIHM = UtilsIHM.getInstance();

	private JPanel panelGlobal;
	private JPanel panelNavigation;

	private JButton bttDeco;
	private JButton bttRdv;
	private JButton bttAgneda;
	private JButton bttGestClient;
	private JButton bttGestPersonnel;
	private JLabel lblActualite;

	/**
	 * Constructeur de la fenêtre Clinque Vétérinaire (1b)
	 */
	public CliniqueVetoFrame() {

		//Création d'une fenêtre de connexion
		ConnexionFrame connectionFrame = new ConnexionFrame();
		connectionFrame.setVisible(true);
		connectionFrame.setLocationRelativeTo(this);
		connectionFrame.setAlwaysOnTop(true);

		// Paramètres de la fenêtre
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	//	this.setLocation(400,380);
		this.setSize(400, 300);
		this.setTitle("Clinique Vétérinaire");
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		// Initialisation des boutons :
		this.createBttDeco();
		this.createBttRdv();
		this.createBttAgenda();
		this.createBttGestClient();
		this.createBttGestPersonnel();
		this.createLblActu();

		// Initialisation des Panels :
		this.createPanelNavigation();
		this.createPanelGlobal();

		// définition du panel général
		this.setContentPane(getPanelGlobal());

	}

	// *********** CREATION PANELS **************

	/**
	 * Création du PanelGlobal
	 */
	private void createPanelGlobal() {
		panelGlobal = new JPanel(new BorderLayout());
		panelGlobal.setBackground(Color.PINK);
		panelGlobal.add(getPanelNavigation(), BorderLayout.NORTH);

	}

	/**
	 * Création du Panel de Navigation avec les boutons prendreRDB, Agenda,
	 * Gestionclient et GestionPersonnel
	 */
	private void createPanelNavigation() {
		panelNavigation = new JPanel(new GridBagLayout());
		
		utilsIHM.addComponentTo(getLblActualite(), panelNavigation, 0, 0, 1, 1, 1, true);
		utilsIHM.addComponentTo(getBttDeco(), panelNavigation, 1, 0, 1, 1, 1, false);	
		utilsIHM.addComponentTo(getBttRdv(), panelNavigation, 0, 1, 1, 1, 1, true);
		utilsIHM.addComponentTo(getBttAgenda(), panelNavigation, 1, 1, 1, 1, 1, true);
		utilsIHM.addComponentTo(getBttGestClient(), panelNavigation, 0, 2, 1, 1, 1, true);
		utilsIHM.addComponentTo(getBttGestPersonnel(), panelNavigation, 1, 2, 1, 1, 1, true);
		panelNavigation.setBackground(Color.CYAN);

	}

	// *********** CREATION BOUTONS **************

	/**
	 * Crée le bouton de déconnection
	 */
	private void createBttDeco() {
		bttDeco = new JButton("Déconnexion");
		bttDeco.setHorizontalAlignment(SwingConstants.RIGHT);
	}

	/**
	 * Crée le bouton de Prise de RDV
	 */
	private void createBttRdv() {
		bttRdv = new JButton("Prendre RDV");
		bttRdv.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PriseRDVPanel priseRdv = new PriseRDVPanel();
				priseRdv.setVisible(true);
			}
		});
	}

	/**
	 * Crée le bouton d'Agenda
	 */
	private void createBttAgenda() {
		bttAgneda = new JButton("Agenda");
	}

	/**
	 * Crée le bouton de gestion Clients
	 */
	private void createBttGestClient() {
		bttGestClient = new JButton("Gestion Clients");
	}

	/**
	 * Crée le bouton de Gestion du personnel
	 */
	private void createBttGestPersonnel() {
		bttGestPersonnel = new JButton("Gestion Personnel");
	}

	/**
	 * crée le label de bienvenue et d'actualités
	 */
	private void createLblActu() {
		lblActualite = new JLabel("BONJOUR UTILISATEUR !");
		lblActualite.setHorizontalAlignment(SwingConstants.CENTER);
	}

	// *********** GETTEURS **************
	/**
	 * renvoie le bouton de déconnection
	 * 
	 * @return
	 */
	public JButton getBttDeco() {
		return bttDeco;
	}

	/**
	 * renvoie le bouton ouvrant la fenêrte de prise de RDV
	 * 
	 * @return
	 */
	public JButton getBttRdv() {
		return bttRdv;
	}

	/**
	 * Renvoie le bouton ouvrant la fenêtre d'Agenda
	 * 
	 * @return
	 */
	public JButton getBttAgenda() {
		return bttAgneda;
	}

	/**
	 * Renvoie le bouton ouvrant la fenêtre de gestion des clients
	 * 
	 * @return
	 */
	public JButton getBttGestClient() {
		return bttGestClient;
	}

	/**
	 * Renvoie le bouton ouvrant la fenêtre de gestion du personnel
	 * 
	 * @return
	 */
	public JButton getBttGestPersonnel() {
		return bttGestPersonnel;
	}

	/**
	 * Renvoie le label d'actualités
	 * 
	 * @return
	 */
	public JLabel getLblActualite() {
		return lblActualite;
	}

	/**
	 * Renvoie le panel global contenant le panel de déco et le panel des
	 * boutons de navigation
	 * 
	 * @return panelGlobal
	 */
	public JPanel getPanelGlobal() {
		return panelGlobal;
	}

	/**
	 * Renvoie le panel de Navigation
	 * 
	 * @return
	 */
	public JPanel getPanelNavigation() {
		return panelNavigation;
	}

}
