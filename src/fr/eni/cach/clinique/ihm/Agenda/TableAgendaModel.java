package fr.eni.cach.clinique.ihm.Agenda;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.cach.clinique.bo.Rdv;


public class TableAgendaModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 7022554891763668907L;
	
	
/* ************** SINGLETON ************** */
	
	private static TableAgendaModel instance = null;
	
	public static TableAgendaModel getInstance () {
		if (instance == null) {
			instance = new TableAgendaModel();	
		}
		return instance;
	}
	
	/**
	 * Constructeur de TableAgendaModel -> permet de faire l'ajout de données dans une JTable
	 */
	private TableAgendaModel() {
		this.chargementDonnees();
	}
	
/* *************************************** */
	
	
	
	
	/**
	 * Définition des noms des colonnes de la JTable
	 */
	private String[] nomsColonnes = {"Heure", "Nom du client", "Animal", "Race"};

	/**
	 * Liste des Rdv qui sont ajoutés à la JTable
	 */
	private List<Rdv> listeRdv = new ArrayList<>();
	
	
	
	

	
	public void chargementDonnees() {
		//TODO enlever le bouchon quand BLL dispo
	//	rdv = Catalogue.getInstance().getCatalogue();
		listeRdv.add(new Rdv());
		listeRdv.add(new Rdv());
		listeRdv.add(new Rdv());
		listeRdv.add(new Rdv());
		listeRdv.add(new Rdv());
		listeRdv.add(new Rdv());
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return nomsColonnes.length;
	}

	@Override
	public int getRowCount() {
		return listeRdv.size();
	}
	
	public String getColumnName(int column) {
		return nomsColonnes[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;
		
		if(rowIndex >= 0 && rowIndex < listeRdv.size()) {
			//Rdv rdvAAfficher = listeRdv.get(rowIndex);
			
			switch (columnIndex) {
			case 0:
				//TODO
				
				value = new SimpleDateFormat("HH:mm").format(new Date().getTime());
				break;
			case 1:
				//TODO
				
				value = "Honnete Camille";
				break;
			case 2:
				//TODO
				
				value = "Rubi";
				break;
			case 3:
				//TODO
				
				value = "Race";
				break;	

			}
		}
		
		return value;
	}
	

	public Rdv getValueAt(int rowIndex) throws Exception {
		if(rowIndex >= 0 && rowIndex < listeRdv.size()) {
			return listeRdv.get(rowIndex);
		}
		throw new Exception ("Le RDV n'existe pas en BDD");
		
	}
	
	

}
