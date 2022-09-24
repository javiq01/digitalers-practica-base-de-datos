package com.bancoit.jdbc.implementaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.bancoit.entidades.Administracion;
import com.bancoit.entidades.PKadministracion;
import com.bancoit.jdbc.excepciones.JDBCExcepcion;

public class AdministracionImplementacion extends ImplementacionGenerica<Administracion,PKadministracion>{
	PreparedStatement buscarPorIdJefePS;

	
	public AdministracionImplementacion(Connection conexion) throws JDBCExcepcion {
		super(conexion);
	}

	
	public boolean eliminar(Administracion administracion) {
		if (administracion == null) {
			return false;
		}
		String instruccionPreparadaDelete = "DELETE FROM administracion WHERE nombre_sucursal = ? and id_empleado = ?";

		
			try {
				
				if (eliminarPS == null) {
				eliminarPS = conexion.prepareStatement(instruccionPreparadaDelete);
				}
				eliminarPS.setString(1, administracion.getPkAdministracion().getNombre_sucursal());
				eliminarPS.setInt(2, administracion.getPkAdministracion().getId_empleado());
				
				return eliminarPS.executeUpdate() == 1;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return false;
	}

	
	public List<Administracion> listar() {
		List<Administracion> listaAdministraciones = new ArrayList<>();
		String consulta = "SELECT nombre_sucursal, id_empleado, nombre_empleado, telefono_empleado, id_empleadoJefe, fecha_incorporacion FROM administracion;";
		
		try {
			if(listarPS == null) {
				listarPS = conexion.prepareStatement(consulta);
			}
			ResultSet administracionesDB = listarPS.executeQuery();
			
			while(administracionesDB.next()) {
				String nombre_sucursal = administracionesDB.getString("nombre_sucursal");
				Integer id_empleado = administracionesDB.getInt("id_empleado");
				PKadministracion pk = new PKadministracion(nombre_sucursal, id_empleado);
				
				String nombre_empleado = administracionesDB.getString("nombre_empleado");
				String telefono_empleado = administracionesDB.getString("telefono_empleado");
				Integer id_empleadoJefe = administracionesDB.getInt("id_empleadoJefe");
				LocalDate fecha_incorporacion = LocalDate.parse(administracionesDB.getString("fecha_incorporacion"));
				
				Administracion registroEmpleado = new Administracion(pk, nombre_empleado, telefono_empleado, id_empleadoJefe, fecha_incorporacion);
			
				listaAdministraciones.add(registroEmpleado);
			}
			
			return listaAdministraciones;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// BUSCAR EN ADMNISTRACION EL REGISTRO DE UN EMPLEADO POR CLAVE PRIMARIA (nombre_sucursal, id_empleado)
	public Administracion buscarPorPK(PKadministracion pkAdminisracion) {
		if(pkAdminisracion == null) {
			return null;
		}
		if(pkAdminisracion.getNombre_sucursal() == null || pkAdminisracion.getId_empleado() == null) {
			return null;
		}
		String consulta = "SELECT nombre_sucursal, id_empleado, nombre_empleado, telefono_empleado, id_empleadoJefe, fecha_incorporacion FROM administracion WHERE nombre_sucursal = ? and id_empleado = ?;";
		
		try {
			if (buscarPS == null) {
				buscarPS = conexion.prepareStatement(consulta);
			}
			
			buscarPS.setString(1,pkAdminisracion.getNombre_sucursal());
			buscarPS.setInt(2, pkAdminisracion.getId_empleado());
			
			ResultSet resultadoBusqueda = buscarPS.executeQuery();
			
			if (resultadoBusqueda.next()) {

				String nombre_empleado = resultadoBusqueda.getString("nombre_empleado");
				String telefono_empleado = resultadoBusqueda.getString("telefono_empleado");
				Integer id_empleadoJefe = resultadoBusqueda.getInt("id_empleadoJefe");
				LocalDate fecha_incorporacion = LocalDate.parse(resultadoBusqueda.getString("fecha_incorporacion"));
				
				Administracion registroEmpleado = new Administracion(pkAdminisracion, nombre_empleado, telefono_empleado, id_empleadoJefe, fecha_incorporacion);
				return  registroEmpleado;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean guardar(Administracion administracion) {
		if (buscarPorPK(administracion.getPkAdministracion()) == null) {
			return insertar(administracion);
		}
		return actualizar(administracion);
	}


	
	protected boolean insertar(Administracion administracion) {
		if (administracion == null) {
			return false;
		}
		String instruccionPreparadaInsert = "INSERT INTO administracion(nombre_sucursal, id_empleado, nombre_empleado, telefono_empleado, id_empleadoJefe, fecha_incorporacion) VALUES (?, ?, ?, ?, ?, now());";
		
			try {
				if (insertarPS == null) {
					insertarPS = conexion.prepareStatement(instruccionPreparadaInsert);
				}
				insertarPS.setString(1, administracion.getPkAdministracion().getNombre_sucursal());
				insertarPS.setInt(2, administracion.getPkAdministracion().getId_empleado());
				insertarPS.setString(3, administracion.getNombre_empleado());
				insertarPS.setString(4, administracion.getTelefono_empleado());
				insertarPS.setInt(5, administracion.getId_empleadoJefe());
				
				return insertarPS.executeUpdate() == 1;
			} catch (SQLException e) {
				e.printStackTrace();
			}		return false;
	}

	
	protected boolean actualizar(Administracion administracion) {
		if (administracion == null) {
			return false;
		}
		String modificacion = "UPDATE administracion SET nombre_sucursal = ?, telefono_empleado = ?, id_empleadoJefe = ?  WHERE id_empleado = ?;";
		
		try {
			if (actualizarPS == null) {
				actualizarPS = conexion.prepareStatement(modificacion);
			}

			actualizarPS.setString(1,administracion.getPkAdministracion().getNombre_sucursal());
			actualizarPS.setString(2,administracion.getTelefono_empleado());
			actualizarPS.setInt(3,administracion.getId_empleadoJefe());
			actualizarPS.setInt(4,administracion.getPkAdministracion().getId_empleado());
			
			return actualizarPS.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}		return false;
	}
	
	
	// select nombre_empleado from administracion where id_empleadoJefe = 110011;

		public List<String> obtenerNombresSubordinados(Integer id_empleadoJefe) {
			if (id_empleadoJefe == null) {
				return null;
			}
			List<String> nombreSubordinados = new ArrayList<String>();
			String consulta = "SELECT nombre_empleado FROM administracion WHERE id_empleadoJefe = ?;";
			
			try {
				
					if(buscarPorIdJefePS == null) {
						buscarPorIdJefePS = conexion.prepareStatement(consulta);	
					}
					buscarPorIdJefePS.setInt(1, id_empleadoJefe);
					
					ResultSet empleadosSubOrdinados = buscarPorIdJefePS.executeQuery();
					
					while (empleadosSubOrdinados.next()) {
						String nombreSub = empleadosSubOrdinados.getString("nombre_empleado");
						nombreSubordinados.add(nombreSub);
					}
				
					return nombreSubordinados;
			
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return null;
		}
		

}
