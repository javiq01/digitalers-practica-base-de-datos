package com.bancoit.jdbc.implementaciones;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bancoit.entidades.Cliente;
import com.bancoit.jdbc.excepciones.JDBCExcepcion;

public class ClienteImplementacion extends ImplementacionGenerica<Cliente, Integer> {

	public ClienteImplementacion(Connection conexion) throws JDBCExcepcion {
		super(conexion);
	}



	// ELIMINAR UN CLIENTE
	public boolean eliminar(Cliente cliente) {
		if (cliente == null) {
			return false;
		}
		String instruccionPreparadaDelete = "DELETE FROM clientes WHERE id = ?";

		
			try {
				if (eliminarPS == null) {
				eliminarPS = conexion.prepareStatement(instruccionPreparadaDelete);
				}
				eliminarPS.setInt(1, cliente.getId());
				
				return eliminarPS.executeUpdate() == 1;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return false;
	}

	// LISTAR CLIENTES
	public List<Cliente> listar() {
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		String consulta = "SELECT id, nombre, calle, ciudad, prestamo, id_empleado, nombre_sucursal FROM clientes;";

		try {
			if(listarPS == null) {
				listarPS = conexion.prepareStatement(consulta);
			}
			ResultSet clientesDB = listarPS.executeQuery();
			
			while(clientesDB.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(clientesDB.getInt("id"));
				cliente.setNombre(clientesDB.getString("nombre"));
				cliente.setCalle(clientesDB.getString("calle"));
				cliente.setCiudad(clientesDB.getString("ciudad"));
				cliente.setPrestamo(clientesDB.getDouble("prestamo"));
				cliente.setId_empleado(clientesDB.getInt("id_empleado"));
				cliente.setNombre_sucursal(clientesDB.getString("nombre_sucursal"));
				listaClientes.add(cliente);
			}
			return listaClientes;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return null;
	}

	// BUSCAR CLIENTE POR CLAVE PRIMARIA (id)
	public Cliente buscarPorPK(Integer id) {
		if (id == null || id == 0) {
			return null;
		} 
		
		String consulta = "SELECT id, nombre, calle, ciudad, prestamo, id_empleado, nombre_sucursal FROM clientes WHERE id = ?;";
		
		try {
			if (buscarPS == null) {
				buscarPS = conexion.prepareStatement(consulta);
			}
			buscarPS.setInt(1, id);
			
			ResultSet resultado = buscarPS.executeQuery();
			
			if(resultado.next()) {
				String nombre = resultado.getString("nombre");
				String calle = resultado.getString("calle");
				String ciudad = resultado.getString("ciudad");
				Double prestamo = resultado.getDouble("prestamo");
				Integer id_empleado = resultado.getInt("id_empleado");
				String nombre_sucursal = resultado.getString("nombre_sucursal");

				Cliente cliente = new Cliente(id, nombre, calle, ciudad, prestamo, id_empleado, nombre_sucursal);
				return cliente;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	//INSERTAR O ACTUALIZAR UN CLIENTE
	public boolean guardar(Cliente cliente) {
		if (buscarPorPK(cliente.getId()) == null) {
			return insertar(cliente);
		}
		return actualizar(cliente);
	
	}
	
	
	protected boolean insertar(Cliente cliente) {
		if (cliente == null) {
			return false;
		}
		String instruccionPreparadaInsert = "INSERT INTO clientes (id, nombre, calle, ciudad, prestamo, id_empleado, nombre_sucursal) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
			try {
				if (insertarPS == null) {
				insertarPS = conexion.prepareStatement(instruccionPreparadaInsert);
				}
				insertarPS.setInt(1, cliente.getId());
				insertarPS.setString(2, cliente.getNombre());
				insertarPS.setString(3, cliente.getCalle());
				insertarPS.setString(4, cliente.getCiudad());
				insertarPS.setDouble(5, cliente.getPrestamo());
				insertarPS.setInt(6, cliente.getId_empleado());
				insertarPS.setString(7, cliente.getNombre_sucursal());

				return insertarPS.executeUpdate() == 1;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}		
		return false;
	}

	protected boolean actualizar(Cliente cliente) {
		if (cliente == null) {
			return false;
		}
		
		try {
			
			String modificacion = "UPDATE clientes SET calle = ?, ciudad = ?, prestamo = ?, id_empleado = ?, nombre_sucursal = ? WHERE id = ?";
			
			if (actualizarPS == null) {
				actualizarPS = conexion.prepareStatement(modificacion);
			}
			
			actualizarPS.setString(1, cliente.getCalle());
			actualizarPS.setString(2, cliente.getCiudad());
			actualizarPS.setDouble(3, cliente.getPrestamo());
			actualizarPS.setInt(4, cliente.getId_empleado());
			actualizarPS.setString(5, cliente.getNombre_sucursal());
			actualizarPS.setInt(6, cliente.getId());
			
			return actualizarPS.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
