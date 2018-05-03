package fr.eni.cach.clinique.ihm.Personnels;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import fr.eni.cach.clinique.bll.PersonnelManager;
import fr.eni.cach.clinique.bo.Personnel;
import fr.eni.cach.clinique.ihm.UtilsIHM;
import fr.eni.cach.clinique.ihm.cliniqueVeto.CliniqueVetoFrame2;

public class GestPersonnelsPanel extends JPanel {

	// *********** ATTRIBUTS ****************************

	private static final long serialVersionUID = 4638604561225803319L;

	private JPanel panelBoutons;
	private JScrollPane panelTablPersonnel;
	private static JTable tablPersonnel;

	private UtilsIHM utilsIHM = UtilsIHM.getInstance();

	private JButton bttAjouter;
	private JButton bttSupprimer;
	private JButton bttReinitialiser;
	public static TablePersonnelModel tablPersonnelModel;

	// *********** CONSTRUCTEUR PRINCIPAL ***************

	public GestPersonnelsPanel() {

		// création de la JTable et des boutons

		this.createTablPersonnel();
		this.createBttAjouter();
		this.createBttSupprimer();
		this.createBttReinitialiser();

		// création des panels

		this.createPanelBoutons();
		this.createPanelTablPersonnel();

		// Panel global

		BorderLayout layoutGlobal = new BorderLayout();
		this.setLayout(layoutGlobal);

		this.add(getPanelBoutons(), BorderLayout.NORTH);
		this.add(getPanelTablPersonnel(), BorderLayout.CENTER);

	}

	// *********** CREATION PANELS **********************

	private void createPanelBoutons() {
		// permet de définir l'orientation de l'écriture dans le panel
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panelBoutons = new JPanel(new FlowLayout(FlowLayout.LEFT));

		// pour mettre une bordure sur le panel boutons : faire appel à la BorderFactory
		panelBoutons.setBorder(BorderFactory.createEtchedBorder());

		panelBoutons.add(getBttAjouter());
		panelBoutons.add(getBttSupprimer());
		panelBoutons.add(getBttReinitialiser());
	}

	private void createPanelTablPersonnel() {
		panelTablPersonnel = new JScrollPane();
		panelTablPersonnel.setViewportView(getTablPersonnel());
	}

	// *********** CREATION BOUTONS *********************

	private void createBttReinitialiser() {
		bttReinitialiser = utilsIHM.createBttAvecIcon("Réinitialiser", UtilsIHM.IconesEnum.REINIT);
		bttReinitialiser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ReinitialiserMotPassePanel reinitialiserPanel = new ReinitialiserMotPassePanel();
				JInternalFrame jifReinitialiserMotPasse = utilsIHM.createJIF("Réinitialiser mot de passe",
						reinitialiserPanel);
				jifReinitialiserMotPasse.setSize(500, 350);
				jifReinitialiserMotPasse.setVisible(true);
				CliniqueVetoFrame2.getInstance("").getDesktop().add(jifReinitialiserMotPasse);

				try {
					jifReinitialiserMotPasse.setSelected(true);
				} catch (java.beans.PropertyVetoException eAjoutCli) {
				}

			}
		});

	}

	private void createBttSupprimer() {
		bttSupprimer = utilsIHM.createBttAvecIcon("Supprimer", UtilsIHM.IconesEnum.SUPPRIMER);
		bttSupprimer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					Personnel personnelASupprimer = tablPersonnelModel.getValueAt(tablPersonnel.getSelectedRow());
					personnelASupprimer.setArchive(true);

					PersonnelManager.getInstance().updatePersonnel(personnelASupprimer);
					// Permet de rafraichir la JTable
					tablPersonnelModel.chargementDonnees();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

	}

	private void createBttAjouter() {
		// On crée le bouton grâce au créateur de bouton avec icones de UtilsIHM
		// UtilsIHM.IconesEnum.AJOUTER -> désigne le nom de l'icone que l'on ajoute qui
		// est stocké dans une enum interne : IconesEnum
		bttAjouter = utilsIHM.createBttAvecIcon("Ajouter", UtilsIHM.IconesEnum.AJOUTER);
		bttAjouter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AjouterPersonnelPanel ajoutPanel = new AjouterPersonnelPanel();
				JInternalFrame jifAjoutSalarie = utilsIHM.createJIF("Ajouter un salarié", ajoutPanel);
				jifAjoutSalarie.setSize(500, 350);
				jifAjoutSalarie.setVisible(true);
				CliniqueVetoFrame2.getInstance("").getDesktop().add(jifAjoutSalarie);

				try {
					jifAjoutSalarie.setSelected(true);
				} catch (java.beans.PropertyVetoException eAjoutCli) {
				}

			}
		});

	}

	// *********** CREATION TABLES **********************

	private void createTablPersonnel() {

		tablPersonnelModel = TablePersonnelModel.getInstance();
		tablPersonnel = new JTable(tablPersonnelModel);
		tablPersonnel.setBorder(BorderFactory.createEtchedBorder());
		tablPersonnel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablPersonnel.setRowHeight(30);
		tablPersonnel.getSelectionModel().setSelectionInterval(0, 0);

	}

	// *********** GETTERS ******************************

	public JPanel getPanelBoutons() {
		return panelBoutons;
	}

	public JScrollPane getPanelTablPersonnel() {
		return panelTablPersonnel;
	}

	public static JTable getTablPersonnel() {
		return tablPersonnel;
	}

	public JButton getBttAjouter() {
		return bttAjouter;
	}

	public JButton getBttSupprimer() {
		return bttSupprimer;
	}

	public JButton getBttReinitialiser() {
		return bttReinitialiser;
	}

	public static TablePersonnelModel getTablPersonnelModel() {
		return tablPersonnelModel;
	}

}
