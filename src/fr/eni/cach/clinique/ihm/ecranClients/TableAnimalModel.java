package fr.eni.cach.clinique.ihm.ecranClients;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.cach.clinique.bo.Animal;


public class TableAnimalModel extends AbstractTableModel {

	private static final long serialVersionUID = 7022554891763668907L;
	
	/**
	 * Définition des noms des colonnes de la JTable
	 */
	private String[] nomsColonnes = {"Numéro", "Nom", "Sexe", "Couleur", "Race", "Espèce", "Tatouage"};

	/**
	 * Liste des animaux qui sont ajoutés à la JTable
	 */
	private List<Animal> listeAnimaux = new ArrayList<>();
	
	
	
	
	/**
	 * Constructeur de TableAnimalModel -> permet de faire l'ajout de données dans une JTable
	 */
	public TableAnimalModel() {
		this.chargementDonnees();
	}
	
	public void chargementDonnees() {
		//TODO enlever le bouchon quand BLL dispo
	//	rdv = Catalogue.getInstance().getCatalogue();
		listeAnimaux.add(new Animal());
		listeAnimaux.add(new Animal());
		listeAnimaux.add(new Animal());
		listeAnimaux.add(new Animal());
		listeAnimaux.add(new Animal());
		listeAnimaux.add(new Animal());
		fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return nomsColonnes.length;
	}

	@Override
	public int getRowCount() {
		return listeAnimaux.size();
	}
	
	public String getColumnName(int column) {
		return nomsColonnes[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;
		
		if(rowIndex >= 0 && rowIndex < listeAnimaux.size()) {
			//Animal animalAAfficher = listeAnimaux.get(rowIndex);
			
			switch (columnIndex) {
			case 0:
				//TODO
				//value = animalAAfficher.getCodeAnimal();
				value = 1;
				break;
			case 1:
				//TODO
				//value = animalAAfficher.getNomAnimal();
				value = "Mirza";
				break;
			case 2:
				//TODO
				//value = animalAAfficher.getSexe();
				value = "M";
				break;
			case 3:
				//TODO
				//value = animalAAfficher.getCouleur();
				value = "Blanc";
				break;	
			case 4:
				//TODO
				//value = animalAAfficher.getRace();
				value = "Chien";
				break;
			case 5:
				//TODO
				//value = animalAAfficher.getEspece();
				value = "Bulldog";
				break;	
			case 6:
				//TODO
				//value = animalAAfficher.getTatouage();
				value = "#I Love NY";
				break;	
				
			}
		}
		
		return value;
	}
	

}
