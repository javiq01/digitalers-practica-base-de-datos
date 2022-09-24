package com.bancoit.entidades;

import java.util.Objects;

public class IDclienteCuenta {
	private Integer id_cliente;
	private Integer numero_cuenta;
	

	public IDclienteCuenta() {
		super();
	}


	public IDclienteCuenta(Integer id_cliente, Integer numero_cuenta) {
		super();
		this.id_cliente = id_cliente;
		this.numero_cuenta = numero_cuenta;
	}
	
	
	
	public String toString() {
		return "IDclienteCuenta [id_cliente=" + id_cliente + ", numero_cuenta=" + numero_cuenta + "]";
	}

	

	public int hashCode() {
		return Objects.hash(id_cliente, numero_cuenta);
	}


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IDclienteCuenta other = (IDclienteCuenta) obj;
		return Objects.equals(id_cliente, other.id_cliente) && Objects.equals(numero_cuenta, other.numero_cuenta);
	}



	public Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	public Integer getNumero_cuenta() {
		return numero_cuenta;
	}
	public void setNumero_cuenta(Integer numero_cuenta) {
		this.numero_cuenta = numero_cuenta;
	}
	
	
}
