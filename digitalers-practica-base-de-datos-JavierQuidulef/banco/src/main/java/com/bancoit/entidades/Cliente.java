package com.bancoit.entidades;

public class Cliente extends Persona{
	private String calle;
	private String ciudad;
	private Double prestamo;
	private Integer id_empleado;
	private String nombre_sucursal;
	
	
	public Cliente() {
		super();
	}


	public Cliente(Integer id, String nombre, String calle, String ciudad, Double prestamo, Integer id_empleado,
			String nombre_sucursal) {
		super(id, nombre);
		this.calle = calle;
		this.ciudad = ciudad;
		this.prestamo = prestamo;
		this.id_empleado = id_empleado;
		this.nombre_sucursal = nombre_sucursal;
	}

	
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", calle=" + calle + ", ciudad=" + ciudad + ", prestamo="
				+ prestamo + ", id_empleado=" + id_empleado + ", nombre_sucursal=" + nombre_sucursal + "]";
	}

	

	public String getCalle() {
		return calle;
	}


	public void setCalle(String calle) {
		this.calle = calle;
	}


	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public Double getPrestamo() {
		return prestamo;
	}


	public void setPrestamo(Double prestamo) {
		this.prestamo = prestamo;
	}


	public Integer getId_empleado() {
		return id_empleado;
	}


	public void setId_empleado(Integer id_empleado) {
		this.id_empleado = id_empleado;
	}


	public String getNombre_sucursal() {
		return nombre_sucursal;
	}


	public void setNombre_sucursal(String nombre_sucursal) {
		this.nombre_sucursal = nombre_sucursal;
	}
	
	
	
	
}
