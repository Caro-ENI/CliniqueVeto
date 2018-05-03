package fr.eni.cach.clinique.bll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.cach.clinique.bo.Rdv;
import fr.eni.cach.clinique.bo.Veterinaire;
import fr.eni.cach.clinique.dal.DAOFactory;
import fr.eni.cach.clinique.dal.DalException;
import fr.eni.cach.clinique.dal.RdvDAO;

public class RdvManager {

	private RdvDAO daoRdv;

	/* ******* SINGLETON ******* */

	private static RdvManager instance = null;

	private RdvManager() {
		daoRdv = DAOFactory.getRdvDAO();
	}

	public static RdvManager getInstance() {

		if (instance == null) {

			instance = new RdvManager();
		}
		return instance;
	}

	/* ******* METHODES DU MANAGER ******* */

	public void addRdv(Rdv rdv) throws BLLException {

		try {
			validationRdv(rdv);
			daoRdv.insert(rdv);
		} catch (DalException e) {
			e.printStackTrace();
			throw new BLLException("Erreur lors de l'insertion du RDV en BDD");
		}
	}
	


	public void deleteRdv (Rdv rdv) throws BLLException {
		
		try {
			daoRdv.delete(rdv);
		} catch (DalException e) {
			e.printStackTrace();
			throw new BLLException("Erreur lors de la suppression en BDD");
		}
	}
	
	public List <Rdv> getListeRdv (Veterinaire veto) throws BLLException {
		
		List <Rdv> listeRdv = new ArrayList <> ();
		
		
		try {
			listeRdv = daoRdv.selectRdvByVeto(veto.getCodePersonnel());
		} catch (DalException e) {
			e.printStackTrace();
			throw new BLLException ("Erreur accès données : la liste des RDV n'est pas récupérable en BDD");
			
		}
		
		return listeRdv;
	}
	
	public List <Rdv> getListeRdv (Veterinaire veto, String date) throws BLLException {
		
		List <Rdv> listeRdv = new ArrayList <> ();
		
		//	DateLabelFormatter formatter = new DateLabelFormatter(); 
			try {
				listeRdv = daoRdv.selectRdvByVetoAndDate(veto.getCodePersonnel(), date);
			} catch (DalException e) {
				
				e.printStackTrace();
				throw new BLLException ("Erreur accès données : la liste des RDV n'est pas récupérable en BDD");
			}
		
		
		return listeRdv;
		
		
	}
	
	// *********** METHODES DE VERIFICATION ******************
	
	private void validationRdv(Rdv rdv) throws BLLException {
		
		if (rdv.getAnimal() == null)
			throw new BLLException ("Le choix d'un animal pour un rdv est obligatoire");
		
		if (rdv.getVeterinaire() == null)
			throw new BLLException("Le choix d'un vétérinaire pour un rdv est obligatoire");	
		
		if (rdv.getDateRdv() == null)
			throw new BLLException("Le choix d'une date de Rdv pour un rdv est obligatoire");
		
		validiteDateRdv (rdv);
			
	}

	private void validiteDateRdv(Rdv rdv) throws BLLException {
		
		if (rdv.getDateRdv().isBefore(LocalDateTime.now()) == true) {
			throw new BLLException ("Impossible de prendre un Rdv pour une date passée");
		}
			
		
	}
	

}
