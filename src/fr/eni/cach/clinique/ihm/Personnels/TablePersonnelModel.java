package fr.eni.cach.clinique.ihm.Personnels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.cach.clinique.bo.Personnel;
import fr.eni.cach.clinique.dal.DAOFactory;
import fr.eni.cach.clinique.dal.DalException;
import fr.eni.cach.clinique.dal.PersonnelDAO;

public class TablePersonnelModel extends AbstractTableModel {

	// *********** ATTRIBUTS ****************************

	private static final long serialVersionUID = 7022554891763668907L;
	/**
	 * D�finition des noms des colonnes de la JTable
	 */
	private String[] nomsColonnes = {"Nom", "Role", "MotPasse"};

	/**
	 * Liste des personnels qui sont ajout�s � la JTable
	 */
	private List<Personnel> listePersonnels = new ArrayList<>();
	
	private PersonnelDAO daoPersonnel = DAOFactory.getPersonelDAO();
	
	// *********** SINGLETON ****************************
	
	private static TablePersonnelModel instance =null;
	
	/**
	 * Constructeur de TablePersonnelModel -> permet de faire l'ajout de donn�es dans une JTable
	 */
	private TablePersonnelModel() {
		this.chargementDonnees();
	}
	
	/**
	 * Permet d'obtenir l'instance du model de Jtable TablePersonnelModel
	 * @return
	 */
	public static TablePersonnelModel getInstance(){
		if (instance == null) {
			instance = new TablePersonnelModel();
		}
		return instance;
	}
	 
	// *********** METHODES *****************************

	/**
	 * Charge les donn�es du Personnel dans la Table
	 */
	public void chargementDonnees() {		
		try {
			listePersonnels = daoPersonnel.selectAll();
		} catch (DalException e) {
			// TODO Auto-generated catch block
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
		return listePersonnels.size();
	}
	
	public String getColumnName(int column) {
		return nomsColonnes[column];
	}

	/**
	 * M�thode qui peuple la Jtable avec les informations r�cup�r�es
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;
		
		if(rowIndex >= 0 && rowIndex < listePersonnels.size()) {
			Personnel personnelAAfficher = listePersonnels.get(rowIndex);
			
			switch (columnIndex) {
			case 0:
				
				value = personnelAAfficher.getNom();
				
				break;
			case 1:
				
				value = personnelAAfficher.getRole();
			
				break;
			case 2:
				
				value = "**********";
				
				break;
			}
			
			
		}
		
		return value;
		
	}
	
	/**
	 * M�thode qui renvoie le personnel pr�sent dans la Jtable � l'index pass� en param�tre
	 * @param rowIndex
	 * @return
	 * @throws Exception
	 */
	public Personnel getValueAt (int rowIndex) throws Exception {
		if(rowIndex >= 0 && rowIndex < listePersonnels.size()) {
			return listePersonnels.get(rowIndex);
		}
		throw new Exception ("Le salari� n'existe pas en BDD");
	}
	

}
