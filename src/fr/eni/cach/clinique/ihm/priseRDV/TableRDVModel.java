package fr.eni.cach.clinique.ihm.priseRDV;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.cach.clinique.bll.RdvManager;
import fr.eni.cach.clinique.bo.Rdv;
import fr.eni.cach.clinique.dal.RdvDAO;
import fr.eni.cach.clinique.ihm.ecranClients.TableAnimalModel;

public class TableRDVModel extends AbstractTableModel {

	private static final long serialVersionUID = 7022554891763668907L;
	
/* ************** SINGLETON ************** */
	
	private static TableRDVModel instance = null;
	
	public static TableRDVModel getInstance () {
		if (instance == null) {
			instance = new TableRDVModel();	
		}
		return instance;
	}
	
	
	
/* *************************************** */
	
	/**
	 * D�finition des noms des colonnes de la JTable
	 */
	private String[] nomsColonnes = {"Heure", "Client", "Animal", "Race"};

	/**
	 * Liste des RDV qui sont ajout�s � la JTable
	 */
	private List<Rdv> listeDeRdv = new ArrayList<>();
	
	
	
	
	/**
	 * Constructeur de TableRDVModel
	 * permet de faire l'ajout des donn�es dans une JTable
	 */
	public TableRDVModel() {
		chargementDonnees();
	}
	
	/**
	 * Charge les donn�es de la BDD dans la liste de rendez-vous
	 */
	public void chargementDonnees() {
		//TODO enlever le bouchon quand BLL dispo
	//	rdv = Catalogue.getInstance().getCatalogue();
		
		//Veterinaire veto = 
		//LocalDateTime date =	
		
		// listeRdv = RdvManager.getInstance().getListeRdv(veto, date);
		
		
		
		fireTableDataChanged();
	}

	/**
	 * D�finit le nombre de colonnes de la JTable
	 * -> taille du tableau contenant le nom des colonnes
	 */
	@Override
	public int getColumnCount() {
		return nomsColonnes.length;
	}

	/**
	 * D�finit le nombre de lignes de la JTable
	 * -> taille de la liste conentant les informations remont�es de la BDD
	 */
	@Override
	public int getRowCount() {
		return listeDeRdv.size();
	}
	
	/**
	 * D�finit le nom des colonnes � partir d'un tableau de Strings
	 */
	public String getColumnName(int column) {
		return nomsColonnes[column];
	}

	/**
	 * Fonction qui permet de peupler la JTable
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = null;
		
		if(rowIndex >= 0 && rowIndex < listeDeRdv.size()) {
			//TODO elever le bouchon quand BLL sera op�rationnelle
			Rdv rdvAAfficher = listeDeRdv.get(rowIndex);
			
			switch (columnIndex) {
			case 0:
				//TODO
			//	value = rdvAAfficher.getDateRdv();
				value = "Mon Heure";
				break;
			case 1:
				//TODO
			//	value = rdvAAfficher.getClient();
				value = "Mon Client";
				break;
			case 2:
				//TODO
			//	value = rdvAAfficher.getAnimal();
				value = "Mon Animal";
				break;
			case 3:
				//TODO
			//	value = rdvAAfficher.getRace();
				value = "Ma Race";
				break;
			}
		}
		
		return value;
	}
	

}
