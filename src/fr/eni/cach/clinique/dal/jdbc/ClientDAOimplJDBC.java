package fr.eni.cach.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.cach.clinique.bo.Animal;
import fr.eni.cach.clinique.bo.Client;
import fr.eni.cach.clinique.dal.AnimalDAO;
import fr.eni.cach.clinique.dal.ClientDAO;
import fr.eni.cach.clinique.dal.DAOFactory;
import fr.eni.cach.clinique.dal.DalException;


public class ClientDAOimplJDBC implements ClientDAO {

	// Les requ�tes SQL

	public static final String SELECT_BY_ID = "SELECT  Clients.CodeClient, Clients.NomClient, Clients.PrenomClient, Clients.Adresse1, Clients.Adresse2, Clients.CodePostal, Clients.Ville, Clients.NumTel, Clients.Assurance, "
			+ "Clients.Email, Clients.Remarque, Clients.Archive, "
			+ "Animaux.CodeAnimal, Animaux.NomAnimal, Animaux.Sexe, Animaux.Couleur, "
			+ "Animaux.Race, Animaux.Espece, Animaux.Tatouage, " + "Animaux.Antecedents, Animaux.Archive "
			+ "FROM Animaux JOIN Clients ON Animaux.CodeClient=Clients.CodeClient "
			+ "WHERE Clients.CodeClient=? and Animaux.Archive=0";

	public static final String SELECT_ALL = "SELECT CodeClient, NomClient, PrenomClient, Adresse1, "
			+ "Adresse2, CodePostal, Ville, NumTel, Assurance, Email, " + "Remarque, Archive  FROM Clients "
			+ "WHERE Archive=0";

	public static final String INSERT = "INSERT INTO CLIENTS (NomClient, PrenomClient, Adresse1, "
			+ "Adresse2, CodePostal, Ville, NumTel, Assurance, Email, Remarque, Archive) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

	public static final String UPDATE = "	UPDATE CLIENTS SET "
			+ "NomClient=?, PrenomClient=?, Adresse1=?, Adresse2=?, CodePostal=?, Ville=?, "
			+ "NumTel=?, Assurance=?, Email=?, Remarque=?, Archive=? WHERE CodeClient=?";

	public static final String DELETE = "DELETE FROM CLIENTS WHERE CodeClient=?";
	
	public static final String SELECT_BY_MOT_CLE = SELECT_ALL+ " AND CHARINDEX (?, NomClient) > 0";

	// ******************************************************************************************************
	// ******************************************************************************************************

	// Les m�thodes

	/**
	 * S�lectionne un client gr�ce � son code client et retourne le client avec
	 * sa liste d'animaux non archiv�s
	 */
	@Override
	public Client selectById(int id) throws DalException {

		Client client = null;

		try (Connection cnx = ConnectionDAO.getConnection()) {
			// On consid�re qu'on a une connexion op�rationnelle
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				client = this.clientBuilder(rs, true);
				client.setCodeClient(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("selectById");
		}

		return client;
	}

	/**
	 * Retourne la liste compl�te des clients non archiv�s SANS leurs animaux
	 */
	@Override
	public List<Client> selectAll() throws DalException {
		List<Client> listeClients = new ArrayList<>();

		try (Connection cnx = ConnectionDAO.getConnection()) {
			// On consid�re qu'on a une connexion op�rationnelle
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while (rs.next()) {
				listeClients.add(this.clientBuilder(rs, false));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("selectAll");
		}

		return listeClients;
	}

	/**
	 * Insertion d'un client et attribution automatique d'un code client
	 */
	@Override
	public void insert(Client client) throws DalException {
		if (client == null) {
			throw new NullPointerException();
		}

		// A partir d'ici, j'ai forc�ment un animal non null
		try (Connection cnx = ConnectionDAO.getConnection()) {

			// On consid�re qu'on a une connexion op�rationnelle
			PreparedStatement pstmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			// Ajout des param�tres � la requ�te
			preparerParametres(client, pstmt);

			// Ex�cution de la requ�te
			pstmt.executeUpdate();

			// R�cup�ration de l'id g�n�r�
			ResultSet rsId = pstmt.getGeneratedKeys();

			if (rsId.next()) {
				client.setCodeClient(rsId.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("insertion client");
		}

	}

	/**
	 * mise � jour d'un client identifi� par son code client
	 */
	@Override
	public void update(Client client) throws DalException {
		if (client == null) {
			throw new NullPointerException();
		}
		// ici, j'ai forc�ment un client non null
		try (Connection cnx = ConnectionDAO.getConnection()) {

			// On consid�re qu'on a une connexion op�rationnelle
			PreparedStatement pstmt = cnx.prepareStatement(UPDATE);

			// Ajout des param�tres � modifier en base � la requ�te
			preparerParametres(client, pstmt);

			// Ajout du crit�re de restriction
			pstmt.setInt(12, client.getCodeClient());

			// Ex�cution de la requ�te
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("update_client");
		}

	}

	/**
	 * Suppression d'un client identifi� par son code client
	 */
	@Override
	public boolean delete(Client client) throws DalException {
		boolean suppressionOK = false;

		if (client == null) {
			throw new NullPointerException();
		}
		// A partir d'ici, j'ai forc�ment un animal non null
		try (Connection cnx = ConnectionDAO.getConnection()) {
			// On consid�re qu'on a une connexion op�rationnelle
			PreparedStatement pstmt = cnx.prepareStatement(DELETE);

			// Ajout du crit�re de restriction
			pstmt.setInt(1, client.getCodeClient());

			// Ex�cution de la requ�te
			pstmt.executeUpdate();
			suppressionOK = true;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new DalException("Suppression impossible du client");

		}
		return suppressionOK;
	}

	/**
	 * retourne une liste de client dont le nom contient le mot cl�
	 */
	@Override
	public List<Client> selectByMotCle(String motCle) throws DalException {

		if(motCle == null) {
			throw new DalException("Le mot-cl� est obligatoire");
		}
		
		List<Client> clients = new ArrayList<>();
		
		try(Connection cnx = ConnectionDAO.getConnection())
		{
			//On consid�re qu'on a une connexion op�rationnelle
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_MOT_CLE);
			pStmt.setString(1, motCle);
			
			ResultSet rs = pStmt.executeQuery();
			while(rs.next())
			{
				clients.add(this.clientBuilder(rs, false));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DalException("selectByMotCle");
		}
		
		return clients;
	
		
		
	}

	// ********************************************************************
	// ********************************************************************

	// Utilitaires

	/**
	 * Clientbuilder
	 * 
	 * @param rs
	 *            : d�j� positionn� sur un enregistrement existant
	 * @return : un client avec ou sans sa liste d'animaux
	 * @throws SQLException
	 */
	public Client clientBuilder(ResultSet rs, Boolean avecAnimaux) throws SQLException {

		Client client = new Client();
		List<Animal> animauxDuCli = new ArrayList<>();
		AnimalDAO animalDAOCourant = DAOFactory.getAnimalDAO();
		Animal animalCourant;

		if (rs != null) {
			client.setCodeClient(rs.getInt("CodeClient"));
			client.setNomClient(rs.getString("NomClient"));
			client.setPrenomClient(rs.getString("PrenomClient"));
			client.setAdresse1(rs.getString("Adresse1"));
			client.setAdresse2(rs.getString("Adresse2"));
			client.setCodePostal(rs.getString("CodePostal"));
			client.setVille(rs.getString("Ville"));
			client.setNumTel(rs.getString("NumTel"));
			client.setAssurance(rs.getString("Assurance"));
			client.setEmail(rs.getString("Email"));
			client.setRemarque(rs.getString("Remarque"));
			if (rs.getByte("Archive") == 0) {
				client.setArchive(false);
			} else {
				client.setArchive(true);
			}

			if (avecAnimaux) {
				do {
					animalCourant = animalDAOCourant.animalBuilder(rs);
					animauxDuCli.add(animalCourant);
				} while (rs.next());
				client.setListeAnimaux(animauxDuCli);

			} else {
				client.setListeAnimaux(animauxDuCli);

			}
		}
		return client;
	}

	/**
	 * Fonction utilis�e pour les m�thodes insert et update
	 * 
	 * @param client
	 * @param pstmt
	 * @throws SQLException
	 */
	private void preparerParametres(Client client, PreparedStatement pstmt) throws SQLException {
		pstmt.setString(1, client.getNomClient());
		pstmt.setString(2, client.getPrenomClient());
		pstmt.setString(3, client.getAdresse1());
		pstmt.setString(4, client.getAdresse2());
		pstmt.setString(5, client.getCodePostal());
		pstmt.setString(6, client.getVille());
		pstmt.setString(7, client.getNumTel());
		pstmt.setString(8, client.getAssurance());
		pstmt.setString(9, client.getEmail());
		pstmt.setString(10, client.getRemarque());

		if (client.isArchive())
			pstmt.setByte(11, (byte) 1);

		if (!client.isArchive())
			pstmt.setByte(11, (byte) 0);

	}

	

}
