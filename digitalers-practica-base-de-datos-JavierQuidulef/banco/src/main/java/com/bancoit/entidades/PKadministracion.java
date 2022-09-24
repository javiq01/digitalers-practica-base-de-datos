package com.bancoit.entidades;

import java.util.Objects;

public class PKadministracion {
	private String nombre_sucursal;
	private Integer id_empleado;
	
	
	public PKadministracion() {
		super();
	}

	public PKadministracion(String nombre_sucursal, Integer id_empleado) {
		super();
		this.nombre_sucursal = nombre_sucursal;
		this.id_empleado = id_empleado;
	}
	
	
	public String toString() {
		return "[nombre_sucursal=" + nombre_sucursal + ", id_empleado=" + id_empleado + "]";
	}
	
	
	
	public int hashCode() {
		return Objects.hash(id_empleado, nombre_sucursal);
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PKadministracion other = (PKadministracion) obj;
		return Objects.equals(id_empleado, other.id_empleado) && Objects.equals(nombre_sucursal, other.nombre_sucursal);
	}

	
	
	public String getNombre_sucursal() {
		return nombre_sucursal;
	}

	public void setNombre_sucursal(String nombre_sucursal) {
		this.nombre_sucursal = nombre_sucursal;
	}

	public Integer getId_empleado() {
		return id_empleado;
	}

	public void setId_empleado(Integer id_empleado) {
		this.id_empleado = id_empleado;
	}

	
}
