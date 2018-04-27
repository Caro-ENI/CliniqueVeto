package fr.eni.cach.clinique.ihm.Agenda;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import fr.eni.cach.clinique.ihm.UtilsIHM;

public class AgendaPanel extends JPanel{

	private static final long serialVersionUID = -149957493039323454L;
	
	private UtilsIHM utilsIHM = UtilsIHM.getInstance();

	private JLabel lblVeterinaire;
	private JLabel lblDate;
	
	// A modifier par un vrai datePicker
	private JLabel lblDatePicker;
	private JTextField tfDatePicker;
	
	
	// A terme faire des combobox de Vétérinaire
	private JComboBox<String> cbVeterinaire;
	
	
	private JTable tablAgenda;
	
	private JButton bttDossierMedical;
	
	private JPanel panelHaut;
	private JScrollPane panelAgenda;
	private JPanel panelDossierMedical;
	

	public AgendaPanel() {
		
		
	//Initialisation des champs
		
		// Les Labels, TextFields, boutons
		
			this.createLblVeterinaire();
			this.createLblDate();
			this.createLblDatePicker();
			
			this.createTfDatePicker();
			
			this.createBttDossierAnimal();
			
		// La JTable	
			
			this.createTablAgenda();
			
		// Les Panels
			
			this.createPanelHaut();
			this.createPanelAgenda();
			this.createPanelDossierMedical();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}


	//-------------------------CREATION DES PANELS-------------------------------
	

	private void createPanelDossierMedical() {
		// TODO Auto-generated method stub
		
	}



	private void createPanelAgenda() {
		// TODO Auto-generated method stub
		
	}



	private void createPanelHaut() {
		// TODO Auto-generated method stub
		
	}



	//---------------------------CREATION DES COMPONENTS------------------------------------
	


	private void createTablAgenda() {
		// TODO Auto-generated method stub
		
	}





	private void createBttDossierAnimal() {
		// TODO Auto-generated method stub
		
	}





	private void createTfDatePicker() {
		// TODO Auto-generated method stub
		
	}





	private void createLblDatePicker() {
		// TODO Auto-generated method stub
		
	}





	private void createLblDate() {
		// TODO Auto-generated method stub
		
	}


	private void createLblVeterinaire() {
		// TODO Auto-generated method stub
		
	}
	
	
	//------------------------------CREATION DES PANELS---------------------------------
	
	
}
