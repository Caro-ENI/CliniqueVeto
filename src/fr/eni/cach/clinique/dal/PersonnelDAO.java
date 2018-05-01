package fr.eni.cach.clinique.dal;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.cach.clinique.bo.Personnel;

public interface PersonnelDAO extends DAO<Personnel> {
	
	public Personnel personnelBuilder(ResultSet rs) throws SQLException;

}
