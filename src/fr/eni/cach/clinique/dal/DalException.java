package fr.eni.cach.clinique.dal;

public class DalException extends Exception {

	private static final long serialVersionUID = 3946403277377688014L;

	public DalException(String message) {
		super("DalException: " + message);
	}
}
