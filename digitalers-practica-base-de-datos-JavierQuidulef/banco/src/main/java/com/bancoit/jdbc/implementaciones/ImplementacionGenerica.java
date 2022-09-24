package com.bancoit.jdbc.implementaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.bancoit.jdbc.dao.Dao;
import com.bancoit.jdbc.excepciones.JDBCExcepcion;

public abstract class ImplementacionGenerica<E,K> implements Dao<E, K> {
	// PS = Prepared Statement
	PreparedStatement insertarPS; 	
	PreparedStatement eliminarPS; 	
	PreparedStatement actualizarPS; 
	PreparedStatement buscarPS; 	// buscar (Por PK) Prepared Statement
	PreparedStatement listarPS;
	Connection conexion;
	
	
	public ImplementacionGenerica(Connection conexion) throws JDBCExcepcion   {
		super();
		if (conexion == null) {
			throw new JDBCExcepcion("ERROR! No se puede implementar sin una conexion. El constructor espera un objeto Connection");
		}
		this.conexion = conexion;
	}
	
	protected abstract boolean insertar(E e);
	protected abstract boolean actualizar(E e);
	
	
}
