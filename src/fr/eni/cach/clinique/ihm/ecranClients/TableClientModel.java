package fr.eni.cach.clinique.ihm.ecranClients;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.cach.clinique.bo.Client;
import fr.eni.cach.clinique.bo.Personnel;
import fr.eni.cach.clinique.bo.Rdv;
import fr.eni.cach.clinique.bo.Client;

public class TableClientModel extends AbstractTableModel {

	private static final long serialVersionUID = 7022554891763668907L;
	
	/**
	 * Définition des noms des colonnes de la JTable
	 */
	private String[] nomsColonnes = {"Nom", "Prénom", "Code postal", "Ville"};

	/**
	 * Liste des personnels qui sont ajoutés à la JTable
	 */
	private List<Client> listeClients = new ArrayList<>();
	
	
	
	
	/**
	 * Constructeur de TablePersonnelModel -> permet de faire l'ajout de données dans une JTable
	 */
	public TableClientModel() {
		this.chargementDonnees();
	}
	
	public void chargementDonnees() {
		//TODO enlever le bouchon quand BLL dispo
	//	rdv = Catalogue.getInstance().getCatalogue();
		listeClients.add(new Client());
		listeClients.add(new Client());
		listeClients.add(new Client());
		listeClients.add(new Client());
		listeClients.add(new Client());
		listeClients.add(new Client());
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return nomsColonnes.length;
	}

	@Override
	public int getRowCount() {
		return listeClients.size();
	}
	
	public String getColumnName(int column) {
		return nomsColonnes[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;
		
		if(rowIndex >= 0 && rowIndex < listeClients.size()) {
			Client clientAAfficher = listeClients.get(rowIndex);
			
			switch (columnIndex) {
			case 0:
				//TODO
				//value = personnelAAfficher.getNom();
				value = "Morel";
				break;
			case 1:
				//TODO
				//value = personnelAAfficher.getRole();
				value = "Christophe";
				break;
			case 2:
				//TODO
				//value = personnelAAfficher.getMotPasse();
				value = "35310";
				break;
			case 3:
				//TODO
				//value = personnelAAfficher.getMotPasse();
				value = "Bréal sous Montfort";
				break;	
			}
		}
		
		return value;
	}
	

}
