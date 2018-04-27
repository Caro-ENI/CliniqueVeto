package fr.eni.cach.clinique.dal;

import java.util.Date;
import java.util.List;

import fr.eni.cach.clinique.bo.Rdv;
import fr.eni.cach.clinique.bo.Veterinaire;

public interface RdvDAO extends DAO<Rdv> {

	// Ne pas oublier de gérer la conversion des dates Java -> SQL
	public List <Rdv> selectRdvByVetoAndDate (Veterinaire veto, Date dateRdv);
	

}
