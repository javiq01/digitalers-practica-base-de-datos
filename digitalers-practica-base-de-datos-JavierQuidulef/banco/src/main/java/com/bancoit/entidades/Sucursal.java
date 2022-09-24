package com.bancoit.entidades;

import java.util.Objects;

public class Sucursal {
	private String nombre;
	private String ciudad;
	
	
	public Sucursal() {
		super();
	}
	
	public Sucursal(String nombre, String ciudad) {
		super();
		this.nombre = nombre;
		this.ciudad = ciudad;
	}

	
	public String toString() {
		return "Sucursal [nombre=" + nombre + ", ciudad=" + ciudad + "]";
	}
	
	
	public int hashCode() {
		return Objects.hash(nombre);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sucursal other = (Sucursal) obj;
		return Objects.equals(nombre, other.nombre);
	}
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	
}
