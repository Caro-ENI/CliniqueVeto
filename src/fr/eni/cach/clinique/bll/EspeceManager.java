package fr.eni.cach.clinique.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.cach.clinique.bo.Espece;
import fr.eni.cach.clinique.bo.Race;
import fr.eni.cach.clinique.dal.DAOFactory;
import fr.eni.cach.clinique.dal.DalException;
import fr.eni.cach.clinique.dal.EspeceDAO;

public class EspeceManager {

	
/* ******* SINGLETON ******* */
	
	private EspeceDAO daoEspece;
	
	private static EspeceManager instance = null;
	
	private EspeceManager() {
		daoEspece = DAOFactory.getEspeceDAO();
	}
	
	public static EspeceManager getInstance(){
		if (instance == null) {
			instance = new EspeceManager();
		}
		return instance;
	}

	/* ******* METHODES DU MANAGER ******* */

	public List<Race> selectEspByRace(String espece) throws BLLException {
		
		List<Race> racesSelect= new ArrayList<>();
		
		try {
			racesSelect = daoEspece.selectEspByRace(espece);
			
		} catch (DalException e) {
			e.printStackTrace();
			throw new BLLException("Erreur d'accès aux données.");
		}
		
		return racesSelect;
	}


	public List<Espece> selectAllEspeces() throws BLLException{
		List<Espece> especesSelect = new ArrayList<>();
		
		try {
			especesSelect = daoEspece.selectAll();
		} catch (DalException e) {
			e.printStackTrace();
			throw new BLLException("Erreur d'accès aux données.");
		}
	
		return especesSelect;
	}
	
}
