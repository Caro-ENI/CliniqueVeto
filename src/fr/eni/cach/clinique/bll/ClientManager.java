package fr.eni.cach.clinique.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.cach.clinique.bo.Client;
import fr.eni.cach.clinique.dal.ClientDAO;
import fr.eni.cach.clinique.dal.DAOFactory;
import fr.eni.cach.clinique.dal.DalException;

public class ClientManager {

	/* ******* SINGLETON ******* */
	private static ClientManager instance = null;

	private ClientDAO daoClient;

	private ClientManager() {
		daoClient = DAOFactory.getClientDAO();
	}

	public static ClientManager getInstance() {
		if (instance == null) {
			instance = new ClientManager();
		}
		return instance;
	}

	/* ******* METHODES DU MANAGER ******* */

	/**
	 * Permet d'obtenir la liste des Clients enregistrés ou une liste vide si
	 * aucun clients n'est enregistré
	 * 
	 * @return
	 * @throws BLLException
	 */
	public List<Client> getListeClients() throws BLLException {
		List<Client> listeClients = new ArrayList<>();
		try {
			listeClients = daoClient.selectAll();
		} catch (DalException e) {
			throw new BLLException("Erreur accès données : La liste des Clients n'est pas récupérable");
		}
		return listeClients;
	}

	/**
	 * Permet d'obtenir une liste de clients correspondants à un mot clé
	 * 
	 * @param motcle
	 * @return
	 * @throws BLLException
	 */
	public List<Client> getListeClientsMC(String motcle) throws BLLException {
		List<Client> listeClients = new ArrayList<>();
		try {
			listeClients = daoClient.selectByMotCle(motcle);
		} catch (DalException e) {
			throw new BLLException("Erreur accès données : La liste des Clients n'est pas récupérable");
		}
		return listeClients;
	}

	/**
	 * Permet d'obtenir un Client selon son codeClient renvoie un client vide si
	 * il n'est pas trouvé
	 * 
	 * @param codeClient
	 * @return
	 * @throws BLLException
	 */
	public Client getClient(int codeClient) throws BLLException {
		Client client = new Client();
		try {
			client = daoClient.selectById(codeClient);
		} catch (DalException e) {
			throw new BLLException("Le Client n'a pas été trouvé");
		}
		return client;
	}

	/**
	 * Permet d'ajouter un client dans la base
	 * 
	 * @param client
	 * @throws BLLException
	 */
	public void addClient(Client client) throws BLLException {

		this.validerClient(client);

		try {
			daoClient.insert(client);
		} catch (DalException e) {
			throw new BLLException("Le Client n'a pas pu être inséré dans la base");
		}
	}

	/**
	 * Permet de mettre à jour un client dans la base
	 * @param client
	 * @throws BLLException 
	 */
	public void updateClient(Client client) throws BLLException {
		this.validerClient(client);
		
		try {
			daoClient.update(client);
		} catch (DalException e) {
			throw new BLLException("Les informations n'ont pas pu être mise à jour");
		}
	}
	
	/* ******* METHODE DE VERIFICATION ******* */

	/**
	 * Vérifie que les attributs du client passé en paramètre sont valides
	 * 
	 * @param client
	 * @throws BLLException 
	 */
 	private void validerClient(Client client) throws BLLException {
		verificationNomClient(client);
		verificationPrenom(client);
		verificationAdresse1(client);
		verificationAdresse2(client);
		verificationCP(client);
		verificationVille(client);
		verificationNumTel(client);
		verificationAssurance(client);
		verificationEmail(client);
	
	}

	/* ******* SOUS-METHODES DE VERIFICATION ******* */

	private void verificationNomClient(Client client) throws BLLException {
		if (client.getNomClient() == null || client.getNomClient().trim().isEmpty()) {
			throw new BLLException("Le Nom du Client doit être renseigné.");
		} else if (client.getNomClient().length() > 20) {
			throw new BLLException("Le Nom du Client ne doit pas excéder 20 caractères");
		}
	}

	private void verificationPrenom(Client client) throws BLLException {
		if (client.getPrenomClient() == null || client.getPrenomClient().trim().isEmpty()) {
			throw new BLLException("Le Prénom du Client doit être renseigné.");
		} else if (client.getPrenomClient().length() > 20) {
			throw new BLLException("Le Prénom du Client ne doit pas excéder 20 caractères");
		}
	}

	private void verificationAdresse1(Client client) throws BLLException {
		if (client.getAdresse1().length() > 30) {
			throw new BLLException("L'Adresse du Client ne doit pas excéder 30 caractères");
		}
	}

	private void verificationAdresse2(Client client) throws BLLException {
		if (client.getAdresse2().length() > 30) {
			throw new BLLException("L'Adresse du Client ne doit pas excéder 30 caractères");
		}
	}

	private void verificationCP(Client client) throws BLLException {
		if (client.getCodePostal().length() > 6) {
			throw new BLLException("Le Code Postal ne doit pas excéder 6 chiffres");
		}
	}

	private void verificationVille(Client client) throws BLLException {
		if (client.getVille().length() > 25) {
			throw new BLLException("La Ville du Client ne doit pas excéder 25 caractères");
		}
	}

	private void verificationNumTel(Client client) throws BLLException {
		if (client.getNumTel().length() > 15) {
			throw new BLLException("La Ville du Client ne doit pas excéder 15 caractères");
		}
	}

	private void verificationAssurance(Client client) throws BLLException {
		if (client.getAssurance().length() > 30) {
			throw new BLLException("L'Assurance du Client ne doit pas excéder 30 caractères");
		}
	}

	private void verificationEmail(Client client) throws BLLException {
		if (client.getEmail().length() > 50) {
			throw new BLLException("L'email du Client ne doit pas excéder 50 caractères");
		}
	}

	
}
