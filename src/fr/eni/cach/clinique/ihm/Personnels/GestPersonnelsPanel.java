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

	private static final long serialVersionUID = 4638604561225803319L;
	
	private JPanel panelBoutons;
	private JScrollPane panelTablPersonnel;
	private static JTable tablPersonnel;
	
	private UtilsIHM utilsIHM = UtilsIHM.getInstance();
	
	private JButton bttAjouter;
	private JButton bttSupprimer;
	private JButton bttReinitialiser;
	public static TablePersonnelModel tablPersonnelModel;
	


	public GestPersonnelsPanel() {
		
		//création de la JTable et des boutons
		
		this.createTablPersonnel();
		this.createBttAjouter();
		this.createBttSupprimer();
		this.createBttReinitialiser();
		
		//création des panels
		
		this.createPanelBoutons();
		this.createPanelTablPersonnel();
		
		// Panel global
		
		BorderLayout layoutGlobal = new BorderLayout();
		this.setLayout(layoutGlobal);
		
		this.add(getPanelBoutons(), BorderLayout.NORTH);
		this.add(getPanelTablPersonnel(), BorderLayout.CENTER);
		
		
	}

	

	



	//**********CREATION DES BOUTONS ET DE LA JTABLE*********************
	
	private void createBttReinitialiser() {
		bttReinitialiser = new JButton ("Réinitialiser");
		bttReinitialiser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ReinitialiserMotPassePanel reinitialiserPanel = new ReinitialiserMotPassePanel();
				JInternalFrame jifReinitialiserMotPasse = utilsIHM.createJIF("Réinitialiser mot de passe", reinitialiserPanel);
				jifReinitialiserMotPasse.setSize(500, 350);
				jifReinitialiserMotPasse.setVisible(true);
				CliniqueVetoFrame2.getInstance("").getDesktop().add(jifReinitialiserMotPasse);
				
				
				
				try {
					jifReinitialiserMotPasse.setSelected(true);
		        } catch (java.beans.PropertyVetoException eAjoutCli) {}
				
				
			}
		});
		
		
		
		
					}

	private void createBttSupprimer() {
		bttSupprimer = new JButton ("Supprimer");
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
		bttAjouter = new JButton ("Ajouter");
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
		        } catch (java.beans.PropertyVetoException eAjoutCli) {}
				
				
				
			}
		});
		
		
		
	}

	private void createTablPersonnel() {
		
		tablPersonnelModel = TablePersonnelModel.getInstance();
		tablPersonnel = new JTable(tablPersonnelModel);
		tablPersonnel.setBorder(BorderFactory.createEtchedBorder());
		tablPersonnel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablPersonnel.setRowHeight(30);
		tablPersonnel.getSelectionModel().setSelectionInterval(0, 0);
		
		
	
	}
	
	
	//***************CREATION DES PANELS***************
	
	
	
	private void createPanelBoutons() {
		//permet de définir l'orientation de l'écriture dans le panel 
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panelBoutons = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//panelBoutons.setBackground(Color.GRAY);
		
		// pour mettre une bordure sur le panel boutons : faire appel à la BorderFactory
		panelBoutons.setBorder(BorderFactory.createEtchedBorder());
		
		// -> Récupère la taille de l'écran
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//panelBoutons.setSize(screenSize.width-10, 4);
		
		
		panelBoutons.add(getBttAjouter());
		panelBoutons.add(getBttSupprimer());
		panelBoutons.add(getBttReinitialiser());
		
	}
	
	private void createPanelTablPersonnel() {
		panelTablPersonnel = new JScrollPane();		
		panelTablPersonnel.setViewportView(getTablPersonnel());
		
		//panelTablPersonnel.setBackground(Color.MAGENTA);
		
	}







	
	
	//*****************GETTERS************************
	
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
