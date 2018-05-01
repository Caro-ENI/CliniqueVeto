package fr.eni.cach.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.cach.clinique.bo.Admin;
import fr.eni.cach.clinique.bo.Client;
import fr.eni.cach.clinique.bo.Personnel;
import fr.eni.cach.clinique.bo.Secretaire;
import fr.eni.cach.clinique.bo.Veterinaire;
import fr.eni.cach.clinique.dal.DalException;
import fr.eni.cach.clinique.dal.PersonnelDAO;

public class PersonnelDAOimplJDBC implements PersonnelDAO {

	// Les requêtes SQL

	private static final String SELECT_BY_ID = "SELECT Personnels.CodePers, Personnels.Nom, Personnels.MotPasse, Personnels.Role, "
			+ "Personnels.Archive FROM Personnels WHERE Personnels.CodePers=?";
	
	
	private static final String SELECT_ALL = "SELECT Personnels.CodePers, Personnels.Nom, Personnels.MotPasse, Personnels.Role, "
			+ "Personnels.Archive FROM Personnels WHERE Personnels.Archive=0";
	
	private static final String INSERT = "INSERT INTO Personnels (Personnels.Nom, Personnels.MotPasse, "
			+ "Personnels.Role, Personnels.Archive) VALUES (?, ?, ?, ?)"; 
	
	private static final String UPDATE = "UPDATE Personnels SET Nom=?, MotPasse=?, Role=?, "
			+ "Archive=? WHERE CodePers=?";
	
	
	

	// ******************************************************************************************

	@Override
	public Personnel selectById(int id) throws DalException {
		Personnel personnel = null;

		try (Connection cnx = ConnectionDAO.getConnection()) {
			// On considère qu'on a une connexion opérationnelle
			
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				personnel = this.personnelBuilder(rs);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("selectById");
		}

		return personnel;
	}



	@Override
	public List<Personnel> selectAll() throws DalException {
		List<Personnel> listeClients = new ArrayList<>();

		try (Connection cnx = ConnectionDAO.getConnection()) {
			// On considère qu'on a une connexion opérationnelle
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while (rs.next()) {
				listeClients.add(this.personnelBuilder(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("selectAll");
		}

		return listeClients;
	}

	@Override
	public void insert(Personnel employe) throws DalException {
		if (employe == null) {
			throw new NullPointerException();
		}

		// A partir d'ici, j'ai forcément un employé non null
		try (Connection cnx = ConnectionDAO.getConnection()) {

			// On considère qu'on a une connexion opérationnelle
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			// Ajout des paramètres à la requête
			preparerParametres(employe, pstmt);

			// Exécution de la requête
			pstmt.executeUpdate();

			// Récupération de l'id généré
			ResultSet rsId = pstmt.getGeneratedKeys();

			if (rsId.next()) {
				employe.setCodePersonnel(rsId.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("insertion personnel");
		}

	}

	
	@Override
	public void update(Personnel employe) throws DalException {

		if (employe == null) {
			throw new NullPointerException();
		}
		// ici, j'ai forcément un employé non null
		try (Connection cnx = ConnectionDAO.getConnection()) {

			// On considère qu'on a une connexion opérationnelle
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);

			// Ajout des paramètres à modifier en base à la requête
			preparerParametres(employe, pstmt);

			// Ajout du critère de restriction
			pstmt.setInt(5, employe.getCodePersonnel());

			// Exécution de la requête
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("update_client");
		}

	

	}

	
	
	
	
	// ******************************************************************
	// Utlitaires
	//********************************************************************
	
	public Personnel personnelBuilder(ResultSet rs) throws SQLException {
		Personnel personne = null;
		String role = rs.getString("Role");

		switch (role) {
		case "adm":
			personne = new Admin();
			break;
		case "vet":
			personne = new Veterinaire();
			break;
		default:
			personne = new Secretaire();
			break;
		}

		personne.setCodePersonnel(rs.getInt("CodePers"));
		personne.setNom(rs.getString("Nom"));
		personne.setMotPasse(rs.getString("MotPasse"));
		personne.setRole(role);
		if (rs.getInt("Archive") == 0) {
			personne.setArchive(false);
		} else {
			personne.setArchive(true);
		}

		return personne;
	}
	
	
	private void preparerParametres(Personnel employe, PreparedStatement pstmt) throws SQLException {
		
		pstmt.setString(1, employe.getNom());
		pstmt.setString(2, employe.getMotPasse());
		pstmt.setString(3, employe.getRole());
		
		if (employe.isArchive())
			pstmt.setByte(4, (byte) 1);

		if (!employe.isArchive())
			pstmt.setByte(4, (byte) 0);
		
	}
	
	
	
	
	
	

	// Méthodes inutiles
	// **************************************************************

	@Override
	public boolean delete(Personnel value) throws DalException {
		// TODO Auto-generated method stub
		return false;
	}
	
	// **************************************************************

}
