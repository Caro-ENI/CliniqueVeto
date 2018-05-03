package fr.eni.cach.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.cach.clinique.bo.Espece;
import fr.eni.cach.clinique.bo.Race;
import fr.eni.cach.clinique.dal.DalException;
import fr.eni.cach.clinique.dal.EspeceDAO;

public class EspeceDAOimplJDBC implements EspeceDAO {

	private static final String SELECT_ALL_ESPECES = "SELECT Races.Espece FROM Races GROUP BY Races.Espece;";
	
	private static final String SELECT_RACE_BY_ESPECE = "SELECT Races.Race FROM Races WHERE Races.Espece =?;";
	
	
	@Override
	public List<Race> selectEspByRace(String espece) throws DalException {
		
		List<Race> racesSelect= new ArrayList<>();
		
		try (Connection cnx = ConnectionDAO.getConnection()) {
			// On considère qu'on a une connexion opérationnelle
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_RACE_BY_ESPECE);
			pstmt.setString(1, espece);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				racesSelect.add(this.raceBuilder(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("selectEspByRace");
		}

		return racesSelect;
	}


	@Override
	public List<Espece> selectAll() throws DalException {
		List<Espece> especesSelect = new ArrayList<>();
		
		try (Connection cnx = ConnectionDAO.getConnection()) {
			// On considère qu'on a une connexion opérationnelle
			Statement stmt = cnx.createStatement();
						
			ResultSet rs = stmt.executeQuery(SELECT_ALL_ESPECES);
			
			while (rs.next()) {
				especesSelect.add(this.especeBuilder(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("selectEspByRace");
		}
		return especesSelect;
	}

	/* ***** BUILDERS ***** */
	
	public Race raceBuilder(ResultSet rs) throws SQLException {
		Race race = new Race();
		
		if (rs != null) {
			race.setRace(rs.getString("Race"));
		}
		return race;
	}
	
	public Espece especeBuilder(ResultSet rs) throws SQLException {
		Espece espece = new Espece();	
		
		if (rs != null) {
			espece.setEspece(rs.getString("Espece"));
		}
		return espece;
	}
	
	
	/* *****METHODES NON UTILISEES ***** */

	@Override
	public void insert(Espece value) throws DalException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Espece value) throws DalException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean delete(Espece value) throws DalException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Espece selectById(int id) throws DalException {
		// TODO Auto-generated method stub
		return null;
	}

	
	

	
	

}
