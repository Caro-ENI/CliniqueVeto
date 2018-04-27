package fr.eni.cach.clinique.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.eni.cach.clinique.bo.Animal;

public interface AnimalDAO extends DAO<Animal>{
	
	public List <Animal> selectAnimauxByClient (int codeClient) throws DalException;

	public Animal animalBuilder(ResultSet rs) throws SQLException;
	

}
