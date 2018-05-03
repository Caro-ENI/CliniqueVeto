package fr.eni.cach.clinique.ihm.ecranClients;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.cach.clinique.bll.AnimalManager;
import fr.eni.cach.clinique.bll.BLLException;
import fr.eni.cach.clinique.bo.Animal;

public class TableAnimalModel extends AbstractTableModel {

	// *********** ATTRIBUTS ****************************

	private static final long serialVersionUID = 7022554891763668907L;

	/**
	 * Définition des noms des colonnes de la JTable
	 */
	private String[] nomsColonnes = { "Numéro", "Nom", "Sexe", "Couleur", "Race", "Espèce", "Tatouage" };

	/**
	 * Liste des animaux qui sont ajoutés à la JTable
	 */
	private List<Animal> listeAnimaux = new ArrayList<>();

	// *********** SINGLETON ****************************

	private static TableAnimalModel instance = null;

	/**
	 * Constructeur de TableAnimalModel -> permet de faire l'ajout de données dans
	 * une JTable
	 */
	private TableAnimalModel() {
	}

	public static TableAnimalModel getInstance() {
		if (instance == null) {
			instance = new TableAnimalModel();
		}
		return instance;
	}

	// *********** METHODES *****************************

	public void chargementDonnees(int codeClient) {

		try {
			listeAnimaux = AnimalManager.getInstance().selectAnimaux(codeClient);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		fireTableDataChanged();
	}

	public void dechargementDonnees() {
		listeAnimaux = new ArrayList<>();
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

		if (rowIndex >= 0 && rowIndex < listeAnimaux.size()) {
			Animal animalAAfficher = listeAnimaux.get(rowIndex);

			switch (columnIndex) {
			case 0:
				value = animalAAfficher.getCodeAnimal();
				break;
			case 1:
				value = animalAAfficher.getNomAnimal();
				break;
			case 2:
				value = animalAAfficher.getSexe();
				break;
			case 3:
				value = animalAAfficher.getCouleur();
				break;
			case 4:
				value = animalAAfficher.getRace();
				break;
			case 5:
				value = animalAAfficher.getEspece();
				break;
			case 6:
				value = animalAAfficher.getTatouage();
				break;

			}
		}

		return value;
	}

	public Animal getValueAt(int rowIndex) throws Exception {
		if (rowIndex >= 0 && rowIndex < listeAnimaux.size()) {
			return listeAnimaux.get(rowIndex);
		}
		throw new Exception("L'Animal est introuvable.");

	}

}
