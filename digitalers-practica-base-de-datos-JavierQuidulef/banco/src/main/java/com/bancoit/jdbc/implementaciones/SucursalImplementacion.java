package com.bancoit.jdbc.implementaciones;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bancoit.entidades.Sucursal;
import com.bancoit.jdbc.excepciones.JDBCExcepcion;

public class SucursalImplementacion extends ImplementacionGenerica<Sucursal, String>{
	
	//CONSTRUCTOR
	public SucursalImplementacion(Connection conexion) throws JDBCExcepcion {
		super(conexion);
	}

	
	//ELIMINAR UNA SUCURSAL
	public boolean eliminar(Sucursal sucursal) {
		if (sucursal == null) {
			return false;
		}
		String instruccionPreparadaDelete = "DELETE FROM sucursales WHERE nombre = ?";

		
			try {
				if (eliminarPS == null) {
				eliminarPS = conexion.prepareStatement(instruccionPreparadaDelete);
				}
				eliminarPS.setString(1, sucursal.getNombre());
				
				return eliminarPS.executeUpdate() == 1;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		
		return false;
	}
	

	// LISTAR SUCURSALES
	public List<Sucursal> listar() {
		List<Sucursal> listaSucursales = new ArrayList<Sucursal>();
		String consulta = "SELECT nombre, ciudad FROM sucursales;";

		try {
			if(listarPS == null) {
				listarPS = conexion.prepareStatement(consulta);
			}
			ResultSet sucursalesDB = listarPS.executeQuery();
			
			while(sucursalesDB.next()) {
				Sucursal sucursal = new Sucursal();
				sucursal.setNombre(sucursalesDB.getString("nombre"));
				sucursal.setCiudad(sucursalesDB.getString("ciudad"));
				
				listaSucursales.add(sucursal);
			}
			return listaSucursales;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	// BUSCAR SUCURSAL POR CLAVE PRIMARIA (nombre)
	public Sucursal buscarPorPK(String nombre) {
		if (nombre == null || nombre.length() == 0) { //nombre vacio
			return null;
		} 
		
		String consulta = "SELECT nombre, ciudad FROM sucursales WHERE nombre = ?;";
		
		try {
			if (buscarPS == null) {
				buscarPS = conexion.prepareStatement(consulta);
			}
			buscarPS.setString(1, nombre);
			
			ResultSet resultado = buscarPS.executeQuery();
			
			if(resultado.next()) {
				String ciudad = resultado.getString("ciudad");
				Sucursal sucursal = new Sucursal(nombre, ciudad);
				return sucursal;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
		return null;
	}

	
	// ACTUALIZAR O INSERTAR UNA SUCURSAL
	public boolean guardar(Sucursal sucursal) {
		if (buscarPorPK(sucursal.getNombre()) == null) {
			return insertar(sucursal);
		}
		return actualizar(sucursal);
	
	}

	
	
	protected boolean insertar(Sucursal sucursal) {
		if (sucursal == null) {
			return false;
		}
		String instruccionPreparadaInsert = "INSERT INTO sucursales(nombre,ciudad) VALUES (?, ?)";
		
			try {
				if (insertarPS == null) {
				insertarPS = conexion.prepareStatement(instruccionPreparadaInsert);
				}
				insertarPS.setString(1, sucursal.getNombre());
				insertarPS.setString(2, sucursal.getCiudad());
				
				return insertarPS.executeUpdate() == 1;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
	}
	
	
	protected boolean actualizar(Sucursal sucursal) {
		if (sucursal == null) {
			return false;
		}
		String modificacion = "UPDATE sucursales SET ciudad = ?  WHERE nombre = ?;";
		try {
			if (actualizarPS == null) {
				actualizarPS = conexion.prepareStatement(modificacion);
			}

			actualizarPS.setString(1,sucursal.getCiudad());
			actualizarPS.setString(2,sucursal.getNombre());
			
			return actualizarPS.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	



}
