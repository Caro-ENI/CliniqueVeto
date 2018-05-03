package fr.eni.cach.clinique.ihm.ecranClients;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import fr.eni.cach.clinique.bo.Client;
import fr.eni.cach.clinique.ihm.UtilsIHM;

public class RechercherPanel extends JPanel {

	private static final long serialVersionUID = 1950438804010026911L;

	private JPanel panelHaut;
	private JScrollPane panelBas;
	private JPanel panelInfo;

	private UtilsIHM utilsIHM = UtilsIHM.getInstance();

	private JButton bttRechercher;
	private JTextField tfRechercher;
	private JTable tablClients;
	private TableClientModel modelTablClients;
	private JLabel jlInfo;

	public RechercherPanel(GestClientPanel panelGestParent) {

		// Initialisation des champs

		this.createBttRechercher();
		this.createTfRechercher();
		this.createTablClients(panelGestParent);
		this.createJlInfo();

		// Initialisation des panels

		this.createPanelHaut();
		this.createPanelBas();
		this.createPanelInfo();

		BorderLayout layoutGlobal = new BorderLayout();
		this.setLayout(layoutGlobal);

		this.add(getPanelHaut(), BorderLayout.NORTH);
		this.add(getPanelBas(), BorderLayout.CENTER);
		this.add(getPanelInfo(), BorderLayout.SOUTH);

	}

	// *****************************CREATION DES PANELS***********************

	private void createPanelHaut() {
		panelHaut = new JPanel(new GridBagLayout());

		utilsIHM.addComponentTo(getTfRechercher(), panelHaut, 0, 0, 1, 1, 0.8, true);
		utilsIHM.addComponentTo(getBttRechercher(), panelHaut, 1, 0, 1, 1, 0.2, true);

	}

	private void createPanelBas() {
		panelBas = new JScrollPane();
		panelBas.setViewportView(getTablClients());

	}

	private void createPanelInfo(){
		panelInfo = new JPanel(new GridBagLayout());
		UtilsIHM.getInstance().addComponentTo(getJlInfo(), panelInfo, 0, 0, 1, 1, 1, true);
	}
	
	// **********************CREATION DES COMPONENTS****************************

	private void createTablClients(GestClientPanel panelGestParent) {
		modelTablClients = TableClientModel.getInstance();
		tablClients = new JTable(modelTablClients);
		tablClients.setBorder(BorderFactory.createEtchedBorder());
		tablClients.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablClients.setRowHeight(30);
		tablClients.getSelectionModel().setSelectionInterval(0, 0);
		tablClients.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) { // quand double clic

					try {
						// on récupère le client sélectionné
						Client clientSelect = TableClientModel.getInstance().getValueAt(tablClients.getSelectedRow());
						// on l'envoie au panel parent (GestClientPanel) pour que celui-ci rafraichisse
						// l'affichage
						panelGestParent.refreshAffichageClient(clientSelect);
						// on masque la fenêtre de recherche :
						// ici on est dans un panel, si on fait RechercherPanel.this.setVisible(false)
						// le panel va bien disparaître mais pas la JInternalFrame autour qui restera
						// alors vide
						// il faut donc récuperer le component parent grâce au .getParent()
						RechercherPanel.this.getParent().getParent().getParent().setVisible(false);
						

					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}

			}

			/* **Méthodes non utilisées ici** */
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

		});

	}

	private void createTfRechercher() {
		tfRechercher = new JTextField("");
		tfRechercher.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if ('\n' == (e.getKeyChar())) {
					// Demande un rafraichissement de l'affichage avec les données renvoyées par la
					// recherche
					TableClientModel.getInstance().chargementDonnees(getTfRechercher().getText());
					if (tablClients.getRowCount() == 0) {
						JOptionPane.showMessageDialog(RechercherPanel.this, "Aucun client ne correspond à votre recherche",
								"Information", JOptionPane.INFORMATION_MESSAGE);

					}
				}
			}

			// méthodes non utilisées ici
			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
	}

	private void createBttRechercher() {
		bttRechercher = new JButton("Rechercher");
		bttRechercher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Demande un rafraichissement de l'affichage avec les données renvoyées par la
				// recherche
				TableClientModel.getInstance().chargementDonnees(getTfRechercher().getText());
				if (tablClients.getRowCount() == 0) {
					JOptionPane.showMessageDialog(RechercherPanel.this, "Aucun client ne correspond à votre recherche",
							"Information", JOptionPane.INFORMATION_MESSAGE);

				}

			}
		});

	}

	private void createJlInfo(){
		jlInfo = new JLabel("Double-cliquer pour choisir un client.");
		jlInfo.setHorizontalAlignment(JLabel.CENTER);
	}

	
	// *************************GETTERS****************************************

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

	public JPanel getPanelInfo(){
		return panelInfo;
	}

	public JLabel getJlInfo(){
		return jlInfo;
	}

}
