package com.bancoit.entidades;

import java.util.Objects;

import com.bancoit.enumerado.TipoCuenta;

public class Cuenta {
	private Integer numero;
	private TipoCuenta tipo;
	private Double saldo;
	private String nombre_sucursal;
	
	
	public Cuenta() {
		super();
	}


	public Cuenta(TipoCuenta tipo) {
		super();
		this.tipo = tipo;
	}


	public Cuenta(Double saldo) {
		super();
		this.saldo = saldo;
	}


	public Cuenta(TipoCuenta tipo, Double saldo) {
		super();
		this.tipo = tipo;
		this.saldo = saldo;
	}


	public Cuenta(Integer numero, TipoCuenta tipo, Double saldo, String nombre_sucursal) {
		super();
		this.numero = numero;
		this.tipo = tipo;
		this.saldo = saldo;
		this.nombre_sucursal = nombre_sucursal;
	}

	
	public String toString() {
		return "Cuenta [numero=" + numero + ", tipo=" + tipo + ", saldo=" + saldo + ", nombre_sucursal="
				+ nombre_sucursal + "]";
	}



	public int hashCode() {
		return Objects.hash(numero);
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuenta other = (Cuenta) obj;
		return Objects.equals(numero, other.numero);
	}

	public Integer getNumero() {
		return numero;
	}


	public void setNumero(Integer numero) {
		this.numero = numero;
	}


	public TipoCuenta getTipo() {
		return tipo;
	}


	public void setTipo(TipoCuenta tipo) {
		this.tipo = tipo;
	}


	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}


	public String getNombre_sucursal() {
		return nombre_sucursal;
	}


	public void setNombre_sucursal(String nombre_sucursal) {
		this.nombre_sucursal = nombre_sucursal;
	}


	
}
