package com.bancoit.jdbc.implementaciones;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bancoit.entidades.Empleado;
import com.bancoit.jdbc.excepciones.JDBCExcepcion;

public class EmpleadoImplementacion extends ImplementacionGenerica<Empleado,Integer>{


	public EmpleadoImplementacion(Connection conexion) throws JDBCExcepcion {
		super(conexion);
	}

	// ELIMINAR UN EMPLEADO
	public boolean eliminar(Empleado empleado) {
		if (empleado == null) {
			return false;
		}
		String instruccionPreparadaDelete = "DELETE FROM empleados WHERE id = ?";

			try {
				if (eliminarPS == null) {
				eliminarPS = conexion.prepareStatement(instruccionPreparadaDelete);
				}
				eliminarPS.setInt(1, empleado.getId());
				
				return eliminarPS.executeUpdate() == 1;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return false;
	}

	// LISTAR EMPLEADOS
	public List<Empleado> listar() {
		List<Empleado> listaEmpleados = new ArrayList<Empleado>();
		String consulta = "SELECT id, nombre, telefono, nombre_sucursal FROM empleados;";

		try {
			if(listarPS == null) {
				listarPS = conexion.prepareStatement(consulta);
			}
			ResultSet empleadosDB = listarPS.executeQuery();
			
			while(empleadosDB.next()) {
				Empleado empleado = new Empleado();
				empleado.setId(empleadosDB.getInt("id"));
				empleado.setNombre(empleadosDB.getString("nombre"));
				empleado.setTelefono(empleadosDB.getString("telefono"));
				empleado.setNombre_sucursal(empleadosDB.getString("nombre_sucursal"));
				
				listaEmpleados.add(empleado);
			}
			return listaEmpleados;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	// BUSCAR UN EMPLEADO POR CLAVE PRIMARIA (id)
	public Empleado buscarPorPK(Integer id) {
		if (id == null) {
			return null;
		} 
		
		String consulta = "SELECT id, nombre, telefono, nombre_sucursal FROM empleados WHERE id = ?;";
		
		try {
			if (buscarPS == null) {
				buscarPS = conexion.prepareStatement(consulta);
			}
			buscarPS.setInt(1, id);
			
			ResultSet resultado = buscarPS.executeQuery();
			
			if(resultado.next()) {
				String nombre = resultado.getString("nombre");
				String telefono = resultado.getString("telefono");
				String nombre_sucursal = resultado.getString("nombre_sucursal");
				Empleado empleado = new Empleado(id, nombre, telefono, nombre_sucursal);
				return empleado;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// INSERTAR O ACTUALIZAR DATOS DE UN EMPLEADO
	public boolean guardar(Empleado empleado) {
		if (buscarPorPK(empleado.getId()) == null) {
			return insertar(empleado);
		}
		return actualizar(empleado);
	}
	
	
	protected boolean insertar(Empleado empleado) {
		if (empleado == null) {
			return false;
		}
		String instruccionPreparadaInsert = "INSERT INTO empleados(id, nombre, telefono, nombre_sucursal) VALUES (?, ?, ?, ?);";
		
			try {
				if (insertarPS == null) {
				insertarPS = conexion.prepareStatement(instruccionPreparadaInsert);
				}
				insertarPS.setInt(1, empleado.getId());
				insertarPS.setString(2, empleado.getNombre());
				insertarPS.setString(3, empleado.getTelefono());
				insertarPS.setString(4, empleado.getNombre_sucursal());
				
				return insertarPS.executeUpdate() == 1;
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			return false;
	}

	
	protected boolean actualizar(Empleado empleado) {
		if (empleado == null) {
			return false;
		}
		try {
				String modificacion = "UPDATE empleados SET telefono = ?, nombre_sucursal = ? WHERE id = ?";
				
				if (actualizarPS == null) {
					actualizarPS = conexion.prepareStatement(modificacion);
				}
				
				actualizarPS.setString(1, empleado.getTelefono());
				actualizarPS.setString(2, empleado.getNombre_sucursal());
				actualizarPS.setInt(3, empleado.getId());
				
				return actualizarPS.executeUpdate() == 1;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
