package com.bancoit.entidades;

public class Empleado extends Persona{

	private String telefono;
	private String nombre_sucursal;
	
	
	public Empleado() {
		super();
	}


	public Empleado(Integer id, String nombre, String telefono, String nombre_sucursal) {
		super(id, nombre);
		this.telefono = telefono;
		this.nombre_sucursal = nombre_sucursal;
	}


	
	public String toString() {
		return "Empleado [id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + ", nombre_sucursal="
				+ nombre_sucursal + "]";
	}



	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getNombre_sucursal() {
		return nombre_sucursal;
	}


	public void setNombre_sucursal(String nombre_sucursal) {
		this.nombre_sucursal = nombre_sucursal;
	}
	
	
	
	
}
