package fr.eni.cach.clinique.ihm.Personnels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.cach.clinique.bo.Personnel;
import fr.eni.cach.clinique.bo.Rdv;
import fr.eni.cach.clinique.bo.Veterinaire;

public class TablePersonnelModel extends AbstractTableModel {

	private static final long serialVersionUID = 7022554891763668907L;
	
	/**
	 * Définition des noms des colonnes de la JTable
	 */
	private String[] nomsColonnes = {"Nom", "Role", "MotPasse"};

	/**
	 * Liste des personnels qui sont ajoutés à la JTable
	 */
	private List<Personnel> listePersonnels = new ArrayList<>();
	
	
	
	
	/**
	 * Constructeur de TablePersonnelModel -> permet de faire l'ajout de données dans une JTable
	 */
	public TablePersonnelModel() {
		this.chargementDonnees();
	}
	
	public void chargementDonnees() {
		//TODO enlever le bouchon quand BLL dispo
	//	rdv = Catalogue.getInstance().getCatalogue();
		listePersonnels.add(new Veterinaire());
		listePersonnels.add(new Veterinaire());
		listePersonnels.add(new Veterinaire());
		listePersonnels.add(new Veterinaire());
		listePersonnels.add(new Veterinaire());
		listePersonnels.add(new Veterinaire());
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return nomsColonnes.length;
	}

	@Override
	public int getRowCount() {
		return listePersonnels.size();
	}
	
	public String getColumnName(int column) {
		return nomsColonnes[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;
		
		if(rowIndex >= 0 && rowIndex < listePersonnels.size()) {
			Personnel personnelAAfficher = listePersonnels.get(rowIndex);
			
			switch (columnIndex) {
			case 0:
				//TODO
				//value = personnelAAfficher.getNom();
				value = "Morel";
				break;
			case 1:
				//TODO
				//value = personnelAAfficher.getRole();
				value = "vet";
				break;
			case 2:
				//TODO
				//value = personnelAAfficher.getMotPasse();
				value = "*****";
				break;
			}
		}
		
		return value;
	}
	

}
