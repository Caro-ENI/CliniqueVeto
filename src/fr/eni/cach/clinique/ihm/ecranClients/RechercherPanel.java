package fr.eni.cach.clinique.ihm.ecranClients;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import fr.eni.cach.clinique.ihm.UtilsIHM;


public class RechercherPanel extends JPanel {

	
	private static final long serialVersionUID = 1950438804010026911L;
	
	private JPanel panelHaut;
	private JScrollPane panelBas;
	
	
	private UtilsIHM utilsIHM = UtilsIHM.getInstance();
	
	private JButton bttRechercher;
	private JTextField tfRechercher;
	private JTable tablClients;
	private TableClientModel modelTablClients;
	
	public RechercherPanel() {
		
		// Initialisation des champs
		
		this.createBttRechercher();
		this.createTfRechercher();
		this.createTablClients();
		
		// Initialisation des panels
		
		this.createPanelHaut();
		this.createPanelBas();
		
		BorderLayout layoutGlobal = new BorderLayout();
		this.setLayout(layoutGlobal);
		
		this.add(getPanelHaut(), BorderLayout.NORTH);
		this.add(getPanelBas(), BorderLayout.CENTER);
		
	}

	//*****************************CREATION DES PANELS***********************
	
	private void createPanelHaut() {
		panelHaut = new JPanel(new GridBagLayout());
		
		utilsIHM.addComponentTo(getTfRechercher(), panelHaut, 0, 0, 1, 1, 0.8, true);
		utilsIHM.addComponentTo(getBttRechercher(), panelHaut, 1, 0, 1, 1, 0.2, true);
		
	}
	
	private void createPanelBas() {
		panelBas = new JScrollPane();
		panelBas.setViewportView(getTablClients());
		
	}

	//**********************CREATION DES COMPONENTS****************************

	private void createTablClients() {
		modelTablClients = TableClientModel.getInstance();
		tablClients = new JTable(modelTablClients);
		tablClients.setBorder(BorderFactory.createEtchedBorder());
		tablClients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablClients.setRowHeight(30);
		tablClients.getSelectionModel().setSelectionInterval(0, 0);
		
	}

	private void createTfRechercher() {
		tfRechercher = new JTextField("nom du client");
		tfRechercher.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				if ('\n' == (e.getKeyChar())) {
					//Demande un rafraichissement de l'affichage avec les données renvoyées par la recherche
					TableClientModel.getInstance().chargementDonnees(getTfRechercher().getText());
				}
			}

			// méthodes non utilisées ici
			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {}
		});
	}

	private void createBttRechercher() {
		bttRechercher = new JButton("Rechercher");
		bttRechercher.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Demande un rafraichissement de l'affichage avec les données renvoyées par la recherche
				TableClientModel.getInstance().chargementDonnees(getTfRechercher().getText());
				
			}
		});
		
	}

	//*************************GETTERS****************************************
	
	public JPanel getPanelHaut() {
		return panelHaut;
	}

	public JScrollPane getPanelBas() {
		return panelBas;
	}

	public JButton getBttRechercher() {
		return bttRechercher;
	}

	public JTextField getTfRechercher() {
		return tfRechercher;
	}

	public JTable getTablClients() {
		return tablClients;
	}
	
	
	
}
