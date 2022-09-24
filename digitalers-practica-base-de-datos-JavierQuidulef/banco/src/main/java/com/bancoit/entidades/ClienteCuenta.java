package com.bancoit.entidades;

public class ClienteCuenta {
	private IDclienteCuenta idClienteCuenta;

	public ClienteCuenta(IDclienteCuenta idClienteCuenta) {
		super();
		this.idClienteCuenta = idClienteCuenta;
	}

	
	
	public String toString() {
		return "ClienteCuenta [" + idClienteCuenta + "]";
	}


	public IDclienteCuenta getIdClienteCuenta() {
		return idClienteCuenta;
	}

	public void setIdClienteCuenta(IDclienteCuenta idClienteCuenta) {
		this.idClienteCuenta = idClienteCuenta;
	}

	
	
	
}
