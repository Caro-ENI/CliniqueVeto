package fr.eni.cach.clinique.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.eni.cach.clinique.configuration.Configuration;



public abstract class ConnectionDAO {

	public static Connection getConnection() throws SQLException
	{
		//obtention des id de connection :
		String url=Configuration.getValue("url"); 
		String utilisateur=Configuration.getValue("utilisateur"); 
		String mdp=Configuration.getValue("mdp");
		
		//obtention d'une connection
		
	//	DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
		Connection cnx = DriverManager.getConnection(url, utilisateur, mdp);
		return cnx;
	}
	
}
