package fr.eni.cach.clinique.dal.jdbc;

import java.util.Date;
import java.util.List;

import fr.eni.cach.clinique.bo.Rdv;
import fr.eni.cach.clinique.bo.Veterinaire;
import fr.eni.cach.clinique.dal.DalException;
import fr.eni.cach.clinique.dal.RdvDAO;

public class RdvDAOimplJDBC implements RdvDAO {

	@Override
	public Rdv selectById(int id) throws DalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rdv> selectAll() throws DalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Rdv value) throws DalException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Rdv value) throws DalException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Rdv value) throws DalException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Rdv> selectRdvByVetoAndDate(Veterinaire veto, Date dateRdv) {
		// TODO Auto-generated method stub
		return null;
	}
	
	// Méthodes inutiles
		//**************************************************************
		
		
		
		
		
		//**************************************************************

}
