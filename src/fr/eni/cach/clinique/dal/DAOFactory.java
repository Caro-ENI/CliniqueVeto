package fr.eni.cach.clinique.dal;

import java.util.List;

import fr.eni.cach.clinique.bo.Espece;
import fr.eni.cach.clinique.bo.Race;
import fr.eni.cach.clinique.configuration.Configuration;
import fr.eni.cach.clinique.dal.jdbc.AnimalDAOimplJDBC;
import fr.eni.cach.clinique.dal.jdbc.ClientDAOimplJDBC;
import fr.eni.cach.clinique.dal.jdbc.EspeceDAOimplJDBC;
import fr.eni.cach.clinique.dal.jdbc.PersonnelDAOimplJDBC;
import fr.eni.cach.clinique.dal.jdbc.RdvDAOimplJDBC;

public abstract class DAOFactory {

	public static ClientDAO getClientDAO() {

		switch (Configuration.getValue("typeSauvegarde")) {
		case "jdbc":
			return new ClientDAOimplJDBC();
		default:
			return new ClientDAOimplJDBC();
		}
	}

	public static PersonnelDAO getPersonelDAO() {

		switch (Configuration.getValue("typeSauvegarde")) {
		case "jdbc":
			return new PersonnelDAOimplJDBC();
		default:
			return new PersonnelDAOimplJDBC();
		}
	}

	public static AnimalDAO getAnimalDAO() {

		switch (Configuration.getValue("typeSauvegarde")) {
		case "jdbc":
			return new AnimalDAOimplJDBC();
		default:
			return new AnimalDAOimplJDBC();
		}
	}

	public static RdvDAO getRdvDAO() {

		switch (Configuration.getValue("typeSauvegarde")) {
		case "jdbc":
			return new RdvDAOimplJDBC();
		default:
			return new RdvDAOimplJDBC();
		}
	}
	
	public static EspeceDAO getEspeceDAO() {

		switch (Configuration.getValue("typeSauvegarde")) {
		case "jdbc":
			return new EspeceDAOimplJDBC();
		default:
			return new EspeceDAOimplJDBC();
		}
	}
}
