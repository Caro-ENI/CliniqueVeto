package fr.eni.cach.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.cach.clinique.bo.Animal;
import fr.eni.cach.clinique.dal.AnimalDAO;
import fr.eni.cach.clinique.dal.DalException;



public class AnimalDAOimplJDBC implements AnimalDAO {

	// Les requêtes SQL

	private static final String SELECT_BY_ID = "SELECT Animaux.CodeAnimal, Animaux.NomAnimal, Animaux.Sexe, Animaux.Couleur, "
			+ "Animaux.Race, Animaux.Espece, Animaux.Tatouage,"
			+ "Animaux.Antecedents, Animaux.Archive, Animaux.CodeClient, " + "Clients.NomClient, Clients.PrenomClient "
			+ "FROM Animaux JOIN Clients ON Clients.CodeClient = Animaux.CodeClient "
			+ "WHERE Animaux.CodeAnimal=? and Animaux.Archive=0;";

	private static final String SELECT_ANIMAUX_BY_CLIENT = "SELECT Animaux.CodeAnimal, Animaux.NomAnimal, Animaux.Sexe, Animaux.Couleur, "
			+ "Animaux.Race, Animaux.Espece, Animaux.Tatouage, "
			+ "Animaux.Antecedents, Animaux.Archive, Clients.NomClient, " + "Clients.PrenomClient, Animaux.CodeClient "
			+ "FROM Clients " + "JOIN Animaux " + "ON Clients.CodeClient = Animaux.CodeClient "
			+ "WHERE Animaux.CodeClient=? AND Animaux.Archive=0";

	private static final String INSERT_ANIMAL = "INSERT INTO Animaux(Animaux.NomAnimal, Animaux.Sexe, Animaux.Couleur, "
			+ "Animaux.Race, Animaux.Espece, Animaux.CodeClient, "
			+ "Animaux.Tatouage, Animaux.Antecedents, Animaux.Archive) " + "VALUES (?,?,?,?,?,?,?,?,?)";

	private static final String UPDATE_ANIMAL = "UPDATE Animaux SET " + "Animaux.NomAnimal=?, " + "Animaux.Sexe=?, "
			+ "Animaux.Couleur=?, " + "Animaux.Race=?, " + "Animaux.Espece=?, " + "Animaux.CodeClient=?, "
			+ "Animaux.Tatouage=?, " + "Animaux.Antecedents=?, " + "Animaux.Archive =? "
			+ " WHERE Animaux.CodeAnimal=?";

	
	private static final String DELETE_ANIMAL ="DELETE FROM ANIMAUX WHERE CodeAnimal=?" ;
	
	
	// ******************************************************************************************************
	// ******************************************************************************************************

	// Les méthodes

	/**
	 * retourne un animal non archivé grâce à son code animal
	 */
	@Override
	public Animal selectById(int id) throws DalException {

		Animal animal = null;

		try (Connection cnx = ConnectionDAO.getConnection()) {
			// On considère qu'on a une connexion opérationnelle
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				animal = this.animalBuilder(rs);
				animal.setCodeAnimal(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("selectById");
		}

		return animal;
	}

	/**
	 * retourne la liste des animaux non archivés d'un client identifié par son code client
	 */
	@Override
	public List<Animal> selectAnimauxByClient(int codeClient) throws DalException {

		List<Animal> listeAnimaux = new ArrayList<>();

		try (Connection cnx = ConnectionDAO.getConnection()) {
			// On considère qu'on a une connexion opérationnelle
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ANIMAUX_BY_CLIENT);
			pstmt.setInt(1, codeClient);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				listeAnimaux.add(this.animalBuilder(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("selectAnimauxByClient");
		}

		return listeAnimaux;
	}

	/**
	 * insertion d'un animal et attribution automatique d'un code animal
	 * 
	 */
	@Override
	public void insert(Animal animal) throws DalException {
		if (animal == null) {
			throw new NullPointerException();
		}

		// A partir d'ici, j'ai forcément un animal non null
		try (Connection cnx = ConnectionDAO.getConnection()) {

			// On considère qu'on a une connexion opérationnelle
			PreparedStatement pstmt = cnx.prepareStatement(INSERT_ANIMAL, Statement.RETURN_GENERATED_KEYS);

			// Ajout des paramètres à la requête
			preparerParametres(animal, pstmt);

			// Exécution de la requête
			pstmt.executeUpdate();

			// Récupération de l'id généré
			ResultSet rsId = pstmt.getGeneratedKeys();

			if (rsId.next()) {
				animal.setCodeAnimal(rsId.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("insert_animal");
		}

	}

	
	/**
	 * mise à jour d'un animal identifié par son code animal
	 */
	@Override
	public void update(Animal animal) throws DalException {
		if (animal == null) {
			throw new NullPointerException();
		}
		// ici, j'ai forcément un animal non null
		try (Connection cnx = ConnectionDAO.getConnection()) {
			// On considère qu'on a une connexion opérationnelle
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ANIMAL);

			// Ajout des paramètres à modifier en base à la requête
			preparerParametres(animal, pstmt);

			// Ajout du critère de restriction
			pstmt.setInt(10, animal.getCodeAnimal());

			// Exécution de la requête
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("update_animal");
		}

	}

	/**
	 * suppression d'un animal identifié par son code animal
	 */
	@Override
	public boolean delete(Animal animal) throws DalException {
		boolean suppressionOK=false;
		
		if(animal==null)
		{
			throw new NullPointerException();
		}
		//A partir d'ici, j'ai forcément un animal non null
		
		try(Connection cnx = ConnectionDAO.getConnection())
		{
			//On considère qu'on a une connexion opérationnelle
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_ANIMAL);
			
			//Ajout du critère de restriction
			pstmt.setInt(1, animal.getCodeAnimal());
			
			//Exécution de la requête
			pstmt.executeUpdate();
			suppressionOK=true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		throw new DalException("Suppression impossible");
			
			
		}
		return suppressionOK;
	}
	
	//********************************************************************
	//********************************************************************

	// Utilitaires

	/**
	 * AnimalBuilder
	 * 
	 * @param rs
	 *            : déjà positionné sur un enregistrement existant
	 * @return : un animal
	 * @throws SQLException
	 */
	public Animal animalBuilder(ResultSet rs) throws SQLException {

		Animal animal = new Animal();

		if (rs != null) {
			animal.setCodeAnimal(rs.getInt("CodeAnimal"));
			animal.setNomAnimal(rs.getString("NomAnimal"));
			animal.setSexe(rs.getString("Sexe"));
			animal.setCouleur(rs.getString("Couleur"));
			animal.setRace(rs.getString("Race"));
			animal.setEspece(rs.getString("Espece"));
			animal.setCodeClient(rs.getInt("CodeClient"));
			animal.setTatouage(rs.getString("Tatouage"));
			animal.setAntecedents(rs.getString("Antecedents"));
			if (rs.getInt("Archive") == 0) {
				animal.setArchive(false);
			} else {
				animal.setArchive(true);
			}

		}
		return animal;
	}

	/**
	 * Fonction utliser pour les méthodes insert et update
	 * 
	 * @param animal
	 * @param pstmt
	 * @throws SQLException
	 */
	private void preparerParametres(Animal animal, PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, animal.getNomAnimal());
		pstmt.setString(2, animal.getSexe());
		pstmt.setString(3, animal.getCouleur());
		pstmt.setString(4, animal.getRace());
		pstmt.setString(5, animal.getEspece());
		pstmt.setInt(6, animal.getCodeClient());
		pstmt.setString(7, animal.getTatouage());
		pstmt.setString(8, animal.getAntecedents());

		if (animal.isArchive())
			pstmt.setByte(9, (byte) 1);

		if (!animal.isArchive())
			pstmt.setByte(9, (byte) 0);

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Méthodes inutiles
	// **************************************************************
	@Override
	public List<Animal> selectAll() throws DalException {
		// TODO Auto-generated method stub
		return null;
	}

	// *******************************************************************

}
