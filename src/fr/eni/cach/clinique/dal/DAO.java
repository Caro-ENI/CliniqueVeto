package fr.eni.cach.clinique.dal;

import java.util.List;

public interface DAO<T> {
	

	/**
	 * 
	 * @param id
	 * @return l'objet correspondant à l'id ou null 
	 * si n'existe pas ou si problème à l'éxécution. 
	 * @throws DalException 
	 */
	public abstract T selectById(int id) throws DalException;
	
	/**
	 * 
	 * @return une liste d'objets jamais null
	 */
	public abstract List<T> selectAll() throws DalException;
	
	/**
	 * 
	 * @param objet obligatoirement non null
	 * @exception NullPointerException si l'article est null
	 */
	public abstract void insert(T value) throws DalException;
	/**
	 * 
	 * @param objet obligatoirement non null
	 * @exception NullPointerException si l'article est null
	 */
	public abstract void update(T value) throws DalException;
	
	/**
	 * 
	 * @param objet obligatoirement non null
	 * @exception NullPointerException si l'article est null
	 */
	public abstract boolean delete(T value) throws DalException;

}
