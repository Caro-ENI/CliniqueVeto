package fr.eni.cach.clinique.dal;

import java.util.List;

import fr.eni.cach.clinique.bo.Espece;
import fr.eni.cach.clinique.bo.Race;

public interface EspeceDAO extends DAO<Espece>{

	List<Race> selectEspByRace(String espece) throws DalException;

}
