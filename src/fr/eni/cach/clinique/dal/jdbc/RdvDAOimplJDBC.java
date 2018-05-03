package fr.eni.cach.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import fr.eni.cach.clinique.bo.Animal;
import fr.eni.cach.clinique.bo.Client;
import fr.eni.cach.clinique.bo.Personnel;
import fr.eni.cach.clinique.bo.Rdv;
import fr.eni.cach.clinique.bo.Veterinaire;
import fr.eni.cach.clinique.dal.AnimalDAO;
import fr.eni.cach.clinique.dal.ClientDAO;
import fr.eni.cach.clinique.dal.DAOFactory;
import fr.eni.cach.clinique.dal.DalException;
import fr.eni.cach.clinique.dal.PersonnelDAO;
import fr.eni.cach.clinique.dal.RdvDAO;

public class RdvDAOimplJDBC implements RdvDAO {

	// Les Requêtes SQL

	private static final String SELECT_RDV_PAR_VETO = "SELECT  Personnels.CodePers, Personnels.Nom, Personnels.MotPasse, Personnels.Role, Personnels.Archive, "
			+ "Animaux.CodeAnimal, Animaux.NomAnimal, Animaux.Sexe, Animaux.Couleur, Animaux.Race, Animaux.Espece, Animaux.Tatouage, Animaux.Antecedents, Animaux.Archive, "
			+ "Clients.CodeClient, Clients.NomClient, Clients.PrenomClient, Clients.Adresse1, Clients.Adresse2, Clients.CodePostal, Clients.Ville, Clients.NumTel, "
			+ "Clients.Assurance, Clients.Email, Clients.Remarque, Clients.Archive, Agendas.DateRdv FROM Agendas JOIN Personnels ON Personnels.CodePers=Agendas.CodeVeto "
			+ "JOIN Animaux ON Animaux.CodeAnimal=Agendas.CodeAnimal JOIN Clients	ON Clients.CodeClient=Animaux.CodeClient WHERE Agendas.CodeVeto=?";

	private static final String SELECT_RDV_PAR_VETO_DATE = SELECT_RDV_PAR_VETO
			+ " AND CAST (Agendas.DateRdv AS date)=?;";
	
	private static final String INSERT_RDV = "INSERT INTO Agendas (Agendas.CodeAnimal, Agendas.CodeVeto, Agendas.DateRdv) "
			+ "VALUES (?,?,?)";
	
	private static final String DELETE_RDV = "DELETE FROM AGENDAS WHERE DateRdv = ?";

	// ***********************************************************************************************************************************************************

	// L'id en paramètre est celui du veto

	@Override
	public void insert(Rdv rdv) throws DalException {
		if (rdv == null) {
			throw new NullPointerException ();
		}
		
		try (Connection cnx = ConnectionDAO.getConnection();) {
			
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_RDV);
			
			preparerParametres (rdv, pstmt);
			
			// Exécution de la requête
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("insertion rdv");
		}
		
		
		

	}

	@Override
	public boolean delete(Rdv rdv) throws DalException {
		boolean suppressionOK = false;
		
		if (rdv == null) {
			
			throw new NullPointerException(); 
		}
		
		try (Connection cnx = ConnectionDAO.getConnection();){
			
			//On considère qu'on a une connexion opérationnelle
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_RDV);
			//Ajout du critère de restriction
			pstmt.setTimestamp(1, Timestamp.valueOf(rdv.getDateRdv()));
			//Exécution de la requête
			pstmt.executeUpdate();
			suppressionOK=true;
					
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("suppresion rdv");
		}
		
		return suppressionOK;
	}

	@Override
	public List<Rdv> selectRdvByVeto(int codeVeto) throws DalException {

		List<Rdv> rdvDuVeto = new ArrayList<Rdv>();

		try (Connection cnx = ConnectionDAO.getConnection()) {
			// On considère qu'on a une connexion opérationnelle

			// recherche des RDV grâce au codeVeto
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_RDV_PAR_VETO);
			pstmt.setInt(1, codeVeto);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				rdvDuVeto.add(this.rdvBuilder(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("selectByIdVeto");
		}

		return rdvDuVeto;
	}

	@Override
	public List<Rdv> selectRdvByVetoAndDate(int codeVeto, String dateRdv) throws DalException {
		List<Rdv> rdvDuVeto = new ArrayList<Rdv>();

		try (Connection cnx = ConnectionDAO.getConnection()) {
			// On considère qu'on a une connexion opérationnelle

			// recherche des RDV grâce au codeVeto
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_RDV_PAR_VETO_DATE);
			pstmt.setInt(1, codeVeto);
			pstmt.setString(2, dateRdv);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				rdvDuVeto.add(this.rdvBuilder(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("selectByIdVetoAndDate");
		}

		return rdvDuVeto;
	}

	// ******************************************************************
	// Utlitaires
	// ********************************************************************

	private Rdv rdvBuilder(ResultSet rs) throws SQLException {
		Rdv rdv = new Rdv();

		if (rs != null) {

			PersonnelDAO vetoDAOCourant = DAOFactory.getPersonelDAO();
			Personnel vetoCourant = vetoDAOCourant.personnelBuilder(rs);
			rdv.setVeterinaire((Veterinaire) vetoCourant);

			AnimalDAO animalDAOCourant = DAOFactory.getAnimalDAO();
			Animal animalCourant = animalDAOCourant.animalBuilder(rs);
			rdv.setAnimal(animalCourant);

			ClientDAO clientDAOCourant = DAOFactory.getClientDAO();
			Client clientCourant = clientDAOCourant.clientBuilder(rs, false);
			rdv.setClient(clientCourant);

			rdv.setDateRdv(rs.getTimestamp("DateRdv").toLocalDateTime());

		}

		return rdv;
	}
	
	private void preparerParametres(Rdv rdv, PreparedStatement pstmt) throws SQLException {
		pstmt.setInt(1, rdv.getAnimal().getCodeAnimal());
		pstmt.setInt(2, rdv.getVeterinaire().getCodePersonnel());
		pstmt.setTimestamp(3, Timestamp.valueOf(rdv.getDateRdv()));
	}
	
	
	

	// Méthodes inutiles
	// **************************************************************
	@Override
	public Rdv selectById(int id) throws DalException {
		return null;
	}

	@Override
	public List<Rdv> selectAll() throws DalException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void update(Rdv value) throws DalException {
		// TODO Auto-generated method stub

	}

	// **************************************************************

}
