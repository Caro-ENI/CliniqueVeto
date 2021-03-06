package fr.eni.cach.clinique.ihm.ecranClients;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.cach.clinique.bll.BLLException;
import fr.eni.cach.clinique.bll.ClientManager;
import fr.eni.cach.clinique.bo.Client;

public class TableClientModel extends AbstractTableModel {

	// *********** ATTRIBUTS ****************************

	private static final long serialVersionUID = 7022554891763668907L;

	/**
	 * D�finition des noms des colonnes de la JTable
	 */
	private String[] nomsColonnes = { "Nom", "Pr�nom", "Code postal", "Ville" };

	/**
	 * Liste des personnels qui sont ajout�s � la JTable
	 */
	private List<Client> listeClients = new ArrayList<>();

	// *********** SINGLETON ****************************

	private static TableClientModel instance = null;

	/**
	 * Constructeur de TablePersonnelModel -> permet de faire l'ajout de donn�es
	 * dans une JTable
	 */
	private TableClientModel() {
	}

	public static TableClientModel getInstance() {
		if (instance == null) {
			instance = new TableClientModel();
		}
		return instance;
	}

	// *********** METHODES *****************************

	/**
	 * Charge les donn�es dans la Jtable
	 * 
	 * @param motCle
	 */
	public void chargementDonnees(String motCle) {

		try {
			listeClients = ClientManager.getInstance().getListeClientsMC(motCle);
		} catch (BLLException e) {
			e.printStackTrace();
		}

		fireTableDataChanged();
	}

	/**
	 * Permet de vider les donn�es de la JTable
	 */
	public void dechargementDonnees() {
		listeClients = new ArrayList<>();
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

		if (rowIndex >= 0 && rowIndex < listeClients.size()) {
			Client clientAAfficher = listeClients.get(rowIndex);

			switch (columnIndex) {
			case 0:
				value = clientAAfficher.getNomClient();
				break;
			case 1:
				value = clientAAfficher.getPrenomClient();

				break;
			case 2:
				value = clientAAfficher.getCodePostal();
				break;
			case 3:
				value = clientAAfficher.getVille();
				break;
			}
		}

		return value;
	}

	public Client getValueAt(int rowIndex) throws Exception {
		if (rowIndex >= 0 && rowIndex < listeClients.size()) {
			return listeClients.get(rowIndex);
		}
		throw new Exception("Le Client est introuvable.");

	}

}
