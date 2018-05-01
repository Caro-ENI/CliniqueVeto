package fr.eni.cach.clinique.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.eni.cach.clinique.bo.Client;


public interface ClientDAO extends DAO<Client> {
	
	
	public List<Client> selectByMotCle(String nomClient) throws DalException;
	
	public Client clientBuilder(ResultSet rs, Boolean avecAnimaux) throws SQLException;

	

}
