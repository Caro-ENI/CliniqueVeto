package fr.eni.cach.clinique.ihm.Personnels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

import fr.eni.cach.clinique.ihm.UtilsIHM;
import fr.eni.cach.clinique.ihm.cliniqueVeto.CliniqueVetoFrame2;
import fr.eni.cach.clinique.ihm.priseRDV.TableRDVModel;

public class GestPersonnelsPanel extends JPanel {

	private static final long serialVersionUID = 4638604561225803319L;
	
	private JPanel panelBoutons;
	private JScrollPane panelTablPersonnel;
	private JTable tablPersonnel;
	
	private UtilsIHM utilsIHM = UtilsIHM.getInstance();
	
	private JButton bttAjouter;
	private JButton bttSupprimer;
	private JButton bttReinitialiser;
	private TablePersonnelModel tablPersonnelModel;

	public GestPersonnelsPanel() {
		
		//cr�ation de la JTable et des boutons
		
		this.createTablPersonnel();
		this.createBttAjouter();
		this.createBttSupprimer();
		this.createBttReinitialiser();
		
		//cr�ation des panels
		
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
		bttReinitialiser = new JButton ("R�initialiser");
					}

	private void createBttSupprimer() {
		bttSupprimer = new JButton ("Supprimer");
		
	}

	private void createBttAjouter() {
		bttAjouter = new JButton ("Ajouter");
		bttAjouter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AjouterPersonnelPanel ajoutPanel = new AjouterPersonnelPanel();
				JInternalFrame jifAjoutSalarie = utilsIHM.createJIF("Ajouter un salari�", ajoutPanel);
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
		
		tablPersonnelModel = new TablePersonnelModel();
		tablPersonnel = new JTable(tablPersonnelModel);
		tablPersonnel.setBorder(BorderFactory.createEtchedBorder());
		tablPersonnel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablPersonnel.setRowHeight(30);
		tablPersonnel.getSelectionModel().setSelectionInterval(0, 0);
	
	}
	
	
	//***************CREATION DES PANELS***************
	
	
	
	private void createPanelBoutons() {
		//permet de d�finir l'orientation de l'�criture dans le panel 
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panelBoutons = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//panelBoutons.setBackground(Color.GRAY);
		
		// pour mettre une bordure sur le panel boutons : faire appel � la BorderFactory
		panelBoutons.setBorder(BorderFactory.createEtchedBorder());
		
		// -> R�cup�re la taille de l'�cran
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

	public JTable getTablPersonnel() {
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

	public TablePersonnelModel getTablPersonnelModel() {
		return tablPersonnelModel;
	}
	
}
