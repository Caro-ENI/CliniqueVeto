package fr.eni.cach.clinique.dal;

import java.util.List;

import fr.eni.cach.clinique.bo.Client;


public interface ClientDAO extends DAO<Client> {
	
	
	public List<Client> selectByMotCle(String nomClient) throws DalException;

	

}
