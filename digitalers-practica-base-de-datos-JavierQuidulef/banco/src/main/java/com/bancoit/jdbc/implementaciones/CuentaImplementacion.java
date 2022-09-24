package com.bancoit.jdbc.implementaciones;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bancoit.entidades.Cuenta;
import com.bancoit.enumerado.TipoCuenta;
import com.bancoit.jdbc.excepciones.JDBCExcepcion;

public class CuentaImplementacion extends ImplementacionGenerica <Cuenta, Integer>{

	
	public CuentaImplementacion(Connection conexion) throws JDBCExcepcion {
		super(conexion);
	}

	// ELIMINAR CUENTA
	public boolean eliminar(Cuenta cuenta) {
		if (cuenta == null) {
			return false;
		}
		String instruccionPreparadaDelete = "DELETE FROM cuentas WHERE numero = ?";

			try {
				if (eliminarPS == null) {
				eliminarPS = conexion.prepareStatement(instruccionPreparadaDelete);
				}
				eliminarPS.setInt(1, cuenta.getNumero());
				
				return eliminarPS.executeUpdate() == 1;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return false;
	}

	
	//LISTAR CUENTAS BANCARIAS
	public List<Cuenta> listar() {
		List<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		String consulta = "SELECT numero, tipo, saldo, nombre_sucursal FROM cuentas;";

		try {
			if(listarPS == null) {
				listarPS = conexion.prepareStatement(consulta);
			}
			ResultSet cuentasDB = listarPS.executeQuery();
			
			while(cuentasDB.next()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setNumero(cuentasDB.getInt("numero"));
				cuenta.setTipo(TipoCuenta.valueOf(cuentasDB.getString("tipo")));
				cuenta.setSaldo(cuentasDB.getDouble("saldo"));
				cuenta.setNombre_sucursal(cuentasDB.getString("nombre_sucursal"));
				listaCuentas.add(cuenta);
			}
			return listaCuentas;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	// BUSCAR CUENTA POR CLAVE PRIMARIA  (numero)
	public Cuenta buscarPorPK(Integer numero) {
		if (numero == null) {
			return null;
		} 
		
		String consulta = "SELECT numero, tipo, saldo, nombre_sucursal FROM cuentas WHERE numero = ?;";
		
		try {
			if (buscarPS == null) {
				buscarPS = conexion.prepareStatement(consulta);
			}
			buscarPS.setInt(1, numero);
			
			ResultSet resultado = buscarPS.executeQuery();
			
			if(resultado.next()) {
				TipoCuenta tipo = TipoCuenta.valueOf(resultado.getString("tipo"));
				Double saldo = resultado.getDouble("saldo");
				String nombre_sucursal = resultado.getString("nombre_sucursal");
				Cuenta cuenta = new Cuenta(numero, tipo, saldo, nombre_sucursal);
				return cuenta;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// INSERTAR O ACTUALIZAR UNA CUENTA
	public boolean guardar(Cuenta cuenta) {
		if (buscarPorPK(cuenta.getNumero()) == null) {
			return insertar(cuenta);
		}
		return actualizar(cuenta);
	}
	
	protected boolean insertar(Cuenta cuenta) {
		if (cuenta == null) {
			return false;
		}
		String instruccionPreparadaInsert = "INSERT INTO cuentas(numero, tipo, saldo, nombre_sucursal) VALUES (?, ?, ?, ?)";
		
			try {
				if (insertarPS == null) {
				insertarPS = conexion.prepareStatement(instruccionPreparadaInsert);
				}
				insertarPS.setInt(1, cuenta.getNumero());
				insertarPS.setString(2, String.valueOf(cuenta.getTipo()));
				insertarPS.setDouble(3, cuenta.getSaldo());
				insertarPS.setString(4, cuenta.getNombre_sucursal());
				
				return insertarPS.executeUpdate() == 1;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return false;
	}

	
	protected boolean actualizar(Cuenta cuenta) {
		if (cuenta == null) {
			return false;
		}
		String modificacion = "";
		int opcion = 0;
		try {
			
			if (cuenta.getTipo() != null && cuenta.getSaldo() == null) {
				modificacion = "UPDATE cuentas SET tipo = ?  WHERE numero = ?;";
				opcion = 1;
			} else if (cuenta.getTipo() == null && cuenta.getSaldo() != null) {
				modificacion = "UPDATE cuentas SET saldo = ?  WHERE numero = ?;";
				opcion = 2;
			} else if (cuenta.getTipo() != null && cuenta.getSaldo() != null) {
				modificacion = "UPDATE cuentas SET tipo = ?, saldo = ?  WHERE numero = ?;";
				opcion = 3;
			} 
			
			
			if (actualizarPS == null) {
				actualizarPS = conexion.prepareStatement(modificacion);
			}

			switch (opcion) { 
				case 1:	//actualiza tipoCuenta
						actualizarPS.setString(1, String.valueOf(cuenta.getTipo()));
						actualizarPS.setInt(2, cuenta.getNumero());
					break;
	
				case 2: //actualiza Saldo
						actualizarPS.setDouble(1, cuenta.getSaldo());
						actualizarPS.setInt(2, cuenta.getNumero());
					break;
				
				case 3: //actualiza tipoCuenta y Saldo
						actualizarPS.setString(1, String.valueOf(cuenta.getTipo()));
						actualizarPS.setDouble(2, cuenta.getSaldo());
						actualizarPS.setInt(3, cuenta.getNumero());
					break;
			}
		
			return actualizarPS.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}



	
}
