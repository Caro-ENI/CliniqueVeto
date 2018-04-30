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
	 * Permet d'obtenir la liste des Clients enregistr�s ou une liste vide si
	 * aucun clients n'est enregistr�
	 * 
	 * @return
	 * @throws BLLException
	 */
	public List<Client> getListeClients() throws BLLException {
		List<Client> listeClients = new ArrayList<>();
		try {
			listeClients = daoClient.selectAll();
		} catch (DalException e) {
			throw new BLLException("Erreur acc�s donn�es : La liste des Clients n'est pas r�cup�rable");
		}
		return listeClients;
	}

	/**
	 * Permet d'obtenir une liste de clients correspondants � un mot cl�
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
			throw new BLLException("Erreur acc�s donn�es : La liste des Clients n'est pas r�cup�rable");
		}
		return listeClients;
	}

	/**
	 * Permet d'obtenir un Client selon son codeClient renvoie un client vide si
	 * il n'est pas trouv�
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
			throw new BLLException("Le Client n'a pas �t� trouv�");
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
			throw new BLLException("Le Client n'a pas pu �tre ins�r� dans la base");
		}
	}

	/**
	 * Permet de mettre � jour un client dans la base
	 * @param client
	 * @throws BLLException 
	 */
	public void updateClient(Client client) throws BLLException {
		this.validerClient(client);
		
		try {
			daoClient.update(client);
		} catch (DalException e) {
			throw new BLLException("Les informations n'ont pas pu �tre mise � jour");
		}
	}
	
	/* ******* METHODE DE VERIFICATION ******* */

	/**
	 * V�rifie que les attributs du client pass� en param�tre sont valides
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
			throw new BLLException("Le Nom du Client doit �tre renseign�.");
		} else if (client.getNomClient().length() > 20) {
			throw new BLLException("Le Nom du Client ne doit pas exc�der 20 caract�res");
		}
	}

	private void verificationPrenom(Client client) throws BLLException {
		if (client.getPrenomClient() == null || client.getPrenomClient().trim().isEmpty()) {
			throw new BLLException("Le Pr�nom du Client doit �tre renseign�.");
		} else if (client.getPrenomClient().length() > 20) {
			throw new BLLException("Le Pr�nom du Client ne doit pas exc�der 20 caract�res");
		}
	}

	private void verificationAdresse1(Client client) throws BLLException {
		if (client.getAdresse1().length() > 30) {
			throw new BLLException("L'Adresse du Client ne doit pas exc�der 30 caract�res");
		}
	}

	private void verificationAdresse2(Client client) throws BLLException {
		if (client.getAdresse2().length() > 30) {
			throw new BLLException("L'Adresse du Client ne doit pas exc�der 30 caract�res");
		}
	}

	private void verificationCP(Client client) throws BLLException {
		if (client.getCodePostal().length() > 6) {
			throw new BLLException("Le Code Postal ne doit pas exc�der 6 chiffres");
		}
	}

	private void verificationVille(Client client) throws BLLException {
		if (client.getVille().length() > 25) {
			throw new BLLException("La Ville du Client ne doit pas exc�der 25 caract�res");
		}
	}

	private void verificationNumTel(Client client) throws BLLException {
		if (client.getNumTel().length() > 15) {
			throw new BLLException("La Ville du Client ne doit pas exc�der 15 caract�res");
		}
	}

	private void verificationAssurance(Client client) throws BLLException {
		if (client.getAssurance().length() > 30) {
			throw new BLLException("L'Assurance du Client ne doit pas exc�der 30 caract�res");
		}
	}

	private void verificationEmail(Client client) throws BLLException {
		if (client.getEmail().length() > 50) {
			throw new BLLException("L'email du Client ne doit pas exc�der 50 caract�res");
		}
	}

	
}
