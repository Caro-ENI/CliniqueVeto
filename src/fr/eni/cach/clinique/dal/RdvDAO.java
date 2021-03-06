package fr.eni.cach.clinique.dal;

import java.util.List;

import fr.eni.cach.clinique.bo.Rdv;

public interface RdvDAO extends DAO<Rdv> {

	// Ne pas oublier de g�rer la conversion des dates Java -> SQL
	public List <Rdv> selectRdvByVetoAndDate (int codeVeto, String dateRdv) throws DalException;
	
	public List <Rdv> selectRdvByVeto (int codeVeto) throws DalException;
	

}
