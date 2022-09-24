package com.bancoit.app;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;

import com.bancoit.entidades.Administracion;
import com.bancoit.entidades.Cliente;
import com.bancoit.entidades.ClienteCuenta;
import com.bancoit.entidades.Cuenta;
import com.bancoit.entidades.Empleado;
import com.bancoit.entidades.IDclienteCuenta;
import com.bancoit.entidades.PKadministracion;
import com.bancoit.entidades.Sucursal;
import com.bancoit.enumerado.TipoCuenta;
import com.bancoit.jdbc.conexion.AdministradorMariaDB;
import com.bancoit.jdbc.excepciones.JDBCExcepcion;
import com.bancoit.jdbc.implementaciones.AdministracionImplementacion;
import com.bancoit.jdbc.implementaciones.ClienteCuentaImplementacion;
import com.bancoit.jdbc.implementaciones.ClienteImplementacion;
import com.bancoit.jdbc.implementaciones.CuentaImplementacion;
import com.bancoit.jdbc.implementaciones.EmpleadoImplementacion;
import com.bancoit.jdbc.implementaciones.SucursalImplementacion;

public class App {
    public static void main( String[] args ) {
    	AdministradorMariaDB administradorMariaDB = AdministradorMariaDB.getAdministradorConexionMariaDB();
    	    	
    	Connection conexionMDB = administradorMariaDB.getConexion();
    	
    	//Declaracion de Implemenciones DAO
    	SucursalImplementacion sucursalImplementacion = null;
    	CuentaImplementacion cuentaImpl = null;
    	ClienteImplementacion clienteImpl = null;
    	EmpleadoImplementacion empleadoImpl = null;
    	ClienteCuentaImplementacion ccImpl = null;
    	AdministracionImplementacion admImpl = null;
    	
    	try {
			 sucursalImplementacion = new SucursalImplementacion(conexionMDB);
			 cuentaImpl = new CuentaImplementacion(conexionMDB);
			 clienteImpl = new ClienteImplementacion(conexionMDB);
			 empleadoImpl = new EmpleadoImplementacion(conexionMDB);
			 ccImpl = new ClienteCuentaImplementacion(conexionMDB);
			 admImpl = new AdministracionImplementacion(conexionMDB);
			 
    	} catch (JDBCExcepcion e) {
			e.printStackTrace();
		}
    	
    	//SUCURSALES
    	Sucursal s1 = new Sucursal("rioGrande_sucursal", "Rio Grande");
    	Sucursal s2 = new Sucursal("ushuaia_sucursal", "Ushuaia");
    	Sucursal s3 = new Sucursal("tolhuin_sucursal", "Tolhuin");
    	
//    	System.out.println(sucursalImplementacion.guardar(s1));
//    	System.out.println(sucursalImplementacion.guardar(s2));
//    	System.out.println(sucursalImplementacion.guardar(s3));
    	
    	s3.setCiudad("Santa Cruz");
    	System.out.println(sucursalImplementacion.guardar(s3));
    	System.out.println(sucursalImplementacion.buscarPorPK("tolhuin_sucursal"));

    	System.out.println(sucursalImplementacion.eliminar(s3));
    	
//    	List<Sucursal> lista = sucursalImplementacion.listar();
//		Iterator<Sucursal> iterador = lista.iterator();
//		while (iterador.hasNext()) {
//			System.out.println(iterador.next());
//		}
    	
    	
    	
    	//EMPLEADOS

    	Empleado emp1 = new Empleado(1, "Fernando", "2901502012", "ushuaia_sucursal");
    	Empleado emp2 = new Empleado(2, "Ezequiel", null, "ushuaia_sucursal");
    	Empleado emp3 = new Empleado(3, "Rocio", "2901512512", "rioGrande_sucursal");
    	Empleado emp4 = new Empleado(4, "Martina", "2901510019", "ushuaia_sucursal");
    	Empleado emp5 = new Empleado(110011, "Camila", "2901589090", "ushuaia_sucursal");
    	Empleado emp6 = new Empleado(110012, "Marianela", "2901124912", "rioGrande_sucursal");
    	
    	empleadoImpl.guardar(emp1);
    	empleadoImpl.guardar(emp2);
    	empleadoImpl.guardar(emp3);
    	empleadoImpl.guardar(emp4);
    	empleadoImpl.guardar(emp5);
    	empleadoImpl.guardar(emp6);
    	   	
    	
//    	List<Empleado> lista = empleadoImpl.listar();
//		Iterator<Empleado> iterador = lista.iterator();
//		while (iterador.hasNext()) {
//			System.out.println(iterador.next());
//		}
    	emp2.setTelefono("2901514299");
    	System.out.println(empleadoImpl.guardar(emp2));
    	System.out.println(empleadoImpl.buscarPorPK(2));
    	System.out.println(empleadoImpl.eliminar(emp2));
    	
    	
    	
    	//ADMINISTRACION
		Administracion adm1 = new Administracion(new PKadministracion("ushuaia_sucursal", 1), "Fernando", "2901502012", 110011, LocalDate.now());
		Administracion adm2 = new Administracion(new PKadministracion("rioGrande_sucursal", 3), "Rocio", "2901512512", 110012, LocalDate.now());
    	Administracion adm3 = new Administracion(new PKadministracion("ushuaia_sucursal", 2), "Martina", "2901510019", 110011, LocalDate.now());

    	System.out.println(admImpl.guardar(adm1));
    	System.out.println(admImpl.guardar(adm2));
    	System.out.println(admImpl.guardar(adm3));

    	
//		List<Administracion> listaAd = admImpl.listar();
//		Iterator<Administracion> itAd = listaAd.iterator();
//		while(itAd.hasNext()) {
//			System.out.println(itAd.next());
//		}
		
		adm2.setTelefono_empleado("3021525245");
    	System.out.println(admImpl.guardar(adm2));
		
		System.out.println(admImpl.buscarPorPK(new PKadministracion("rioGrande_sucursal", 3)));
		
		System.out.println("***************************");
		System.out.println(admImpl.obtenerNombresSubordinados(110011));
		System.out.println(admImpl.obtenerNombresSubordinados(110012));	
    	
		
    	
    	//CLIENTES
    	Cliente cliente1 = new Cliente(111, "Lucas", "Av. La Plata 808", "Rio Grande", 0.0, 3, "rioGrande_sucursal");
    	Cliente cliente2 = new Cliente(112, "Jorge", "Av. Alem 421", "Ushuaia", 0.0, 4, "ushuaia_sucursal");
    	Cliente cliente3 = new Cliente(113, "Paola", "Calafate 801", "Ushuaia", 0.0, 1, "ushuaia_sucursal");
    	Cliente cliente4 = new Cliente(114, "Natalia", "Av Magallanes 123", "Rio Grande", 0.0, 3, "rioGrande_sucursal");
    	
    	System.out.println(clienteImpl.guardar(cliente1));
    	System.out.println(clienteImpl.guardar(cliente2));
    	System.out.println(clienteImpl.guardar(cliente3));
    	System.out.println(clienteImpl.guardar(cliente4));

//		List<Cliente> listaAd = clienteImpl.listar();
//		Iterator<Cliente> itAd = listaAd.iterator();
//		while(itAd.hasNext()) {
//			System.out.println(itAd.next());
//		}
		cliente1.setCalle("Urquiza 222");
    	System.out.println(clienteImpl.guardar(cliente1));
    	System.out.println(clienteImpl.buscarPorPK(111));
    	
    	
    	
		//CUENTAS
		Cuenta cuenta1 = new Cuenta(103, TipoCuenta.CUENTA_CORRIENTE, 1500.78, "ushuaia_sucursal");
		Cuenta cuenta2 = new Cuenta(104, TipoCuenta.CAJA_AHORRO, 54000.78, "ushuaia_sucursal");
		
    	System.out.println(cuentaImpl.guardar(cuenta1));
    	System.out.println(cuentaImpl.guardar(cuenta1));
    	
//		List<Cuenta> listaAd = cuentaImpl.listar();
//		Iterator<Cuenta> itAd = listaAd.iterator();
//		while(itAd.hasNext()) {
//			System.out.println(itAd.next());
//		}
    	
    	cuenta1.setSaldo(cuenta1.getSaldo()+800);
    	System.out.println(cuentaImpl.guardar(cuenta1));

    	System.out.println(cuentaImpl.buscarPorPK(103));
    	


    	//CLIENTE-CUENTAS

    	ClienteCuenta cc = new ClienteCuenta(new IDclienteCuenta(111,102));
    	ccImpl.guardar(cc);
    	
    	ClienteCuenta cc2 = new ClienteCuenta(new IDclienteCuenta(112,103));
    	ccImpl.guardar(cc2);
    	
    	
//    	List<ClienteCuenta> lista = ccImpl.listar();
//		Iterator<ClienteCuenta> iterador = lista.iterator();
//		while (iterador.hasNext()) {
//			System.out.println(iterador.next());
//		}
		

		System.out.println("BUSQUEDA:\n");
		System.out.println(ccImpl.buscarPorPK(new IDclienteCuenta(1,null)));

	
		
		System.out.println("\nBUSQUEDA CLIENTES TITULARES:\n"); 

		List<Integer> listaT = ccImpl.obtenerTitularesDeCuenta(105);
		Iterator<Integer> it = listaT.iterator();
		while(it.hasNext()) {
			System.out.println(clienteImpl.buscarPorPK(it.next()));
		}
		
		System.out.println("\nBUSQUEDA CUENTAS:\n"); 

		List<Integer> listaC = ccImpl.obtenerCuentas(111);
		Iterator<Integer> itC = listaC.iterator();
		while(itC.hasNext()) {
			System.out.println(cuentaImpl.buscarPorPK(itC.next()));
		}


		

		
    }
}

// Sucursal Si
// Cuenta Si
// Cliente Si
// Empleado Si
// Administracion Si