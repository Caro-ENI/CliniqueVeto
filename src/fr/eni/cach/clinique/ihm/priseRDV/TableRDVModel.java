package fr.eni.cach.clinique.ihm.priseRDV;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.cach.clinique.bll.BLLException;
import fr.eni.cach.clinique.bll.RdvManager;
import fr.eni.cach.clinique.bll.Utilitaires.DateLabelFormatter;
import fr.eni.cach.clinique.bo.Rdv;
import fr.eni.cach.clinique.bo.Veterinaire;
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
	 * Définition des noms des colonnes de la JTable
	 */
	private String[] nomsColonnes = {"Heure", "Client", "Animal", "Espèce"};

	/**
	 * Liste des RDV qui sont ajoutés à la JTable
	 */
	private List<Rdv> listeDeRdv = new ArrayList<>();
	
	
	
	
	/**
	 * Constructeur de TableRDVModel
	 * permet de faire l'ajout des données dans une JTable
	 */
	private TableRDVModel() {
		
		
	}
	
	/**
	 * Charge les données de la BDD dans la liste de rendez-vous
	 */
	public void chargementDonnees(Veterinaire veto, Date date) {
		
		//DateLabelFormatter formatter = new DateLabelFormatter(); 
	
		try {
			//String dateStr = formatter.valueToString(date);
			String dateStr = new SimpleDateFormat("dd-MM-yyyy").format(date);
			listeDeRdv = RdvManager.getInstance().getListeRdv(veto, dateStr);
		} catch (BLLException e) {
			e.printStackTrace();
		}
	
		
		
		
		
		
		fireTableDataChanged();
	}

	/**
	 * Définit le nombre de colonnes de la JTable
	 * -> taille du tableau contenant le nom des colonnes
	 */
	@Override
	public int getColumnCount() {
		return nomsColonnes.length;
	}

	/**
	 * Définit le nombre de lignes de la JTable
	 * -> taille de la liste conentant les informations remontées de la BDD
	 */
	@Override
	public int getRowCount() {
		return listeDeRdv.size();
	}
	
	/**
	 * Définit le nom des colonnes à partir d'un tableau de Strings
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
			//TODO elever le bouchon quand BLL sera opérationnelle
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
				//TODO
				value = rdvAAfficher.getClient();
				
				break;
			case 2:
				//TODO
				value = rdvAAfficher.getAnimal();
				
				break;
			case 3:
				//TODO
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
