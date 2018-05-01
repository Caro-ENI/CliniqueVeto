package fr.eni.cach.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import fr.eni.cach.clinique.bo.Animal;
import fr.eni.cach.clinique.bo.Client;
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
	
	private static final String SELECT_RDV_PAR_VETO = "SELECT  Personnels.Nom, Agendas.DateRdv, Clients.NomClient, Clients.PrenomClient,  "
			+ "Animaux.NomAnimal, Animaux.Race FROM Agendas "
			+ "JOIN Personnels ON Personnels.CodePers=Agendas.CodeVeto "
			+ "JOIN Animaux	ON Animaux.CodeAnimal=Agendas.CodeAnimal "
			+ "JOIN Clients	ON Clients.CodeClient=Animaux.CodeClient "
			+ "WHERE Agendas.CodeVeto=?";
	
	
	
	
	
	//***********************************************************************************************************************************************************
	
	// L'id en paramètre est celui du veto
	

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
	public List<Rdv> selectRdvByVeto(Veterinaire veto) {
		
	/*	Rdv rdv = null;
		

		try (Connection cnx = ConnectionDAO.getConnection()) {
			// On considère qu'on a une connexion opérationnelle
			
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_RDV_PAR_VETO);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				rdv = this.rdvBuilder(rs);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("selectByIdVeto");
		}
	
		return rdv;
		
		*/
		
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public List<Rdv> selectRdvByVetoAndDate(Veterinaire veto, Date dateRdv) {
		
		
		// TODO Auto-generated method stub
		return null;
	}
	
	// ******************************************************************
	// Utlitaires
	//********************************************************************
	
	private Rdv rdvBuilder(ResultSet rs) throws SQLException {
		Rdv rdv = new Rdv();
		
		AnimalDAO animalDAOCourant = DAOFactory.getAnimalDAO();
		Animal animalCourant = animalDAOCourant.animalBuilder(rs);
		
		ClientDAO clientDAOCourant = DAOFactory.getClientDAO();
		Client clientCourant = clientDAOCourant.clientBuilder(rs, true);
		
		PersonnelDAO vetoDAOCourant = DAOFactory.getPersonelDAO();
		
		Veterinaire vetoCourant = (Veterinaire) vetoDAOCourant.personnelBuilder(rs);
		
		
		
		if (rs != null) {
			rdv.setAnimal(animalCourant);
			rdv.setClient(clientCourant);
			rdv.setVeterinaire(vetoCourant);
			
			rdv.setDateRdv(rs.getDate("DateRdv"));
		}
			
			
		
		
		
		return rdv;
	}



	
	
	
	
	
	
	
	
	
	// Méthodes inutiles
		//**************************************************************
	@Override
	public Rdv selectById(int id) throws DalException {
		return null;
	}

	

	@Override
	public List<Rdv> selectAll() throws DalException {
		// TODO Auto-generated method stub
		return null;
	}
		
		
		
		
		//**************************************************************

}
