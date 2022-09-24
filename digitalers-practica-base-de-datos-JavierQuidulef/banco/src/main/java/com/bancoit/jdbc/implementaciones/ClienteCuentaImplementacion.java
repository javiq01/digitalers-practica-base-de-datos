package com.bancoit.jdbc.implementaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bancoit.entidades.ClienteCuenta;
import com.bancoit.entidades.IDclienteCuenta;
import com.bancoit.jdbc.excepciones.JDBCExcepcion;

public class ClienteCuentaImplementacion extends ImplementacionGenerica<ClienteCuenta,IDclienteCuenta> {
	PreparedStatement buscarPorID_clientePS;
	PreparedStatement buscarPornumero_CuentaPS;
	
	public ClienteCuentaImplementacion(Connection conexion) throws JDBCExcepcion {
		super(conexion);
	}



	public boolean eliminar(ClienteCuenta clienteCuenta) {
		if (clienteCuenta == null) {
			return false;
		}
		String instruccionPreparadaDelete = "DELETE FROM clientes_Cuentas WHERE id_cliente = ? and numero_cuenta = ?";

			try {
				if (eliminarPS == null) {
				eliminarPS = conexion.prepareStatement(instruccionPreparadaDelete);
				}
				eliminarPS.setInt(1, clienteCuenta.getIdClienteCuenta().getId_cliente());
				eliminarPS.setInt(2, clienteCuenta.getIdClienteCuenta().getNumero_cuenta());
				
				return eliminarPS.executeUpdate() == 1;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return false;
	}

	
	public List<ClienteCuenta> listar() {
		List<ClienteCuenta> listaClientesCuentas = new ArrayList<ClienteCuenta>();
		String consulta = "SELECT id_cliente, numero_cuenta FROM clientes_cuentas;";

		try {
			if(listarPS == null) {
				listarPS = conexion.prepareStatement(consulta);
			}
			ResultSet clientesCuentasDB = listarPS.executeQuery();
			
			while(clientesCuentasDB.next()) {
				IDclienteCuenta idCliente_numeroCuenta  = new IDclienteCuenta();
				idCliente_numeroCuenta.setId_cliente(clientesCuentasDB.getInt("id_cliente"));
				idCliente_numeroCuenta.setNumero_cuenta(clientesCuentasDB.getInt("numero_cuenta"));
				
				ClienteCuenta clienteCuenta = new ClienteCuenta(idCliente_numeroCuenta);
				listaClientesCuentas.add(clienteCuenta);
			}
			return listaClientesCuentas;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				return null;
	}

	
	public ClienteCuenta buscarPorPK(IDclienteCuenta idClienteCuenta) {
		if (idClienteCuenta == null) {
			return null;
		} 
		if (idClienteCuenta.getId_cliente() == null || idClienteCuenta.getNumero_cuenta() == null) {
			return null;
		}

		
		String consulta = "SELECT id_cliente, numero_cuenta FROM clientes_cuentas WHERE id_cliente = ? and numero_cuenta = ?;";
		
		try {
			if (buscarPS == null) {
				buscarPS = conexion.prepareStatement(consulta);
			}
			buscarPS.setInt(1, idClienteCuenta.getId_cliente());
			buscarPS.setInt(2, idClienteCuenta.getNumero_cuenta());
			
			
			ResultSet resultado = buscarPS.executeQuery();
			
			if(resultado.next()) {
				Integer id_cliente = resultado.getInt("id_cliente");
				Integer numero_cuenta = resultado.getInt("numero_cuenta");
				IDclienteCuenta idCliente_numeroCuenta = new IDclienteCuenta(id_cliente, numero_cuenta);
				
				ClienteCuenta clienteCuenta = new ClienteCuenta(idCliente_numeroCuenta);
				
				return clienteCuenta;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return null;
	}

	
	public boolean guardar(ClienteCuenta clienteCuenta) {
		if (buscarPorPK(clienteCuenta.getIdClienteCuenta()) == null) {
			return insertar(clienteCuenta);
		}
		return actualizar(clienteCuenta);		
	}

	
	@Override
	protected boolean insertar(ClienteCuenta clienteCuenta) {
		if (clienteCuenta == null) {
			return false;
		}
		String instruccionPreparadaInsert = "INSERT INTO clientes_cuentas(id_cliente, numero_cuenta) VALUES (?, ?);";
		
			try {
				if (insertarPS == null) {
				insertarPS = conexion.prepareStatement(instruccionPreparadaInsert);
				}
				insertarPS.setInt(1, clienteCuenta.getIdClienteCuenta().getId_cliente());
				insertarPS.setInt(2, clienteCuenta.getIdClienteCuenta().getNumero_cuenta());

				return insertarPS.executeUpdate() == 1;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			return false;
	}

	@Override
	protected boolean actualizar(ClienteCuenta clienteCuenta) {
		if (clienteCuenta == null) {
			return false;
		}
		try {
				String modificacion = "UPDATE clientes_cuentas SET id_cliente = ?, numero_cuenta = ? WHERE id_cliente = ? and numero_cuenta = ?;";
				
				if (actualizarPS == null) {
					actualizarPS = conexion.prepareStatement(modificacion);
				}
				actualizarPS.setInt(1, clienteCuenta.getIdClienteCuenta().getId_cliente());
				actualizarPS.setInt(2, clienteCuenta.getIdClienteCuenta().getNumero_cuenta());
				

				return actualizarPS.executeUpdate() == 1;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return false;
	}

	
	
	// OBTENER LOS NUMEROS DE CUENTA DEL CLIENTE INDICADO
	public List<Integer> obtenerCuentas(Integer id_cliente) {
		if (id_cliente == null) {
			return null;
		}
		List<Integer> numeros_cuentas = new ArrayList<Integer>();
		String consulta = "SELECT numero_cuenta FROM clientes_cuentas WHERE id_cliente = ?;";

		try {
			if (buscarPorID_clientePS == null) {
				buscarPorID_clientePS = conexion.prepareStatement(consulta);
			}
			
			buscarPorID_clientePS.setInt(1, id_cliente);
			
			ResultSet numeros_cuentasDB = buscarPorID_clientePS.executeQuery();
			
			while (numeros_cuentasDB.next()) {
				numeros_cuentas.add(numeros_cuentasDB.getInt("numero_cuenta"));
			}
			return numeros_cuentas;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	// OBTENER LOS ID's DE CLIENTES TITULARES DE LA CUENTA INDICADA
	public List<Integer> obtenerTitularesDeCuenta(Integer numero_cuenta) {
		if (numero_cuenta == null) {
			return null;
		}
		String consulta = "SELECT id_cliente FROM clientes_cuentas WHERE numero_cuenta = 105 ;";
		List<Integer> titularesDeCuenta = new ArrayList<Integer>();

		try {
			if (buscarPornumero_CuentaPS == null) {
				buscarPornumero_CuentaPS = conexion.prepareStatement(consulta);
			}
			
			buscarPornumero_CuentaPS.setInt(1, numero_cuenta);
			
			ResultSet id_clientesDB = buscarPornumero_CuentaPS.executeQuery();
			
			
			while (id_clientesDB.next()) {
				titularesDeCuenta.add(id_clientesDB.getInt("id_cliente"));
			}
			return titularesDeCuenta;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
