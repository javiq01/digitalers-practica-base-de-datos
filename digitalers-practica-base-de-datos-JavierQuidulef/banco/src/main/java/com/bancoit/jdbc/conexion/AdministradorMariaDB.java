package com.bancoit.jdbc.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Singleton
public class AdministradorMariaDB {
	private Connection conexion;
	private static AdministradorMariaDB administradorConexionMariaDB;
	
	private AdministradorMariaDB() {
		super();
		setConexion();
	}
	
	public static AdministradorMariaDB getAdministradorConexionMariaDB() {
		if (administradorConexionMariaDB == null) {
			administradorConexionMariaDB = new AdministradorMariaDB();
		}
		return administradorConexionMariaDB;
	}


	
	public Connection getConexion() {
		return conexion;
	}
	private void setConexion() {
		String url = "jdbc:mariadb://localhost:3306/bancodigitalers";
    	String user = "root";
    	String password = "";
    	String driver = "org.mariadb.jdbc.Driver";
    	
    	
    	try {
    		
    		Class.forName(driver);
			this.conexion = DriverManager.getConnection(url, user, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	
	}

	

}
