package fr.eni.cach.clinique.ihm.Agenda;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.cach.clinique.bll.BLLException;
import fr.eni.cach.clinique.bll.RdvManager;
import fr.eni.cach.clinique.bo.Rdv;
import fr.eni.cach.clinique.bo.Veterinaire;


public class TableAgendaModel extends AbstractTableModel {
	
	// *********** ATTRIBUTS ****************************
	private static final long serialVersionUID = 7022554891763668907L;

	/**
	 * Définition des noms des colonnes de la JTable
	 */
	private String[] nomsColonnes = {"Heure", "Nom du client", "Animal", "Race"};

	/**
	 * Liste des Rdv qui sont ajoutés à la JTable
	 */
	private List<Rdv> listeDeRdv = new ArrayList<>();

	// *********** SINGLETON ****************************
	
	private static TableAgendaModel instance;
	
	public static TableAgendaModel getInstance() {
		if (instance == null) {
			instance = new TableAgendaModel();
		}
		return instance;
		
	}

	/**
	 * Constructeur de TableAgendaModel -> permet de faire l'ajout de données dans une JTable
	 */
	public TableAgendaModel() {
		
	}
	
	// *********** METHODES *****************************

	public void chargementDonnees(Veterinaire veto, Date dateRdv) {
		
		try {
			String dateStr = new SimpleDateFormat("dd-MM-yyyy").format(dateRdv);
			listeDeRdv = RdvManager.getInstance().getListeRdv(veto, dateStr);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return nomsColonnes.length;
	}

	@Override
	public int getRowCount() {
		return listeDeRdv.size();
	}
	
	public String getColumnName(int column) {
		return nomsColonnes[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;
		
		if(rowIndex >= 0 && rowIndex < listeDeRdv.size()) {
			
			Rdv rdvAAfficher = listeDeRdv.get(rowIndex);
			
			switch (columnIndex) {
			case 0:
				
				if (rdvAAfficher.getDateRdv().getMinute() == 0){
					value = String.valueOf(rdvAAfficher.getDateRdv().getHour())+"h"+String.valueOf(rdvAAfficher.getDateRdv().getMinute()+"0");
					}else {
					value = String.valueOf(rdvAAfficher.getDateRdv().getHour())+"h"+String.valueOf(rdvAAfficher.getDateRdv().getMinute());
					}
				
				
			
				break;
			case 1:
				
				value = rdvAAfficher.getClient();
				
				break;
			case 2:
			
				value = rdvAAfficher.getAnimal();
				
				break;
			case 3:
				

				value = rdvAAfficher.getAnimal().getEspece();
				
				break;

		

			}
		}
		
		return value;
	
	}
	
	public Rdv getValueAt(int rowIndex) throws Exception {
		if(rowIndex >= 0 && rowIndex < listeDeRdv.size()) {
			return listeDeRdv.get(rowIndex);
		}
		throw new Exception ("Le RDV n'existe pas en BDD");
		
	}
	
}
