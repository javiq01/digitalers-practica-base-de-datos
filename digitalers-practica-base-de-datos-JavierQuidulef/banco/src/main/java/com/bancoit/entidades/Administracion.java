package com.bancoit.entidades;

import java.time.LocalDate;
import java.util.Objects;

public class Administracion {
	PKadministracion pkAdministracion;
	private String nombre_empleado;
	private String telefono_empleado;
	private Integer id_empleadoJefe;
	private LocalDate fecha_incorporacion;
	
	public Administracion() {
		super();
	}
	


	public Administracion(PKadministracion pkAdministracion, String nombre_empleado, String telefono_empleado,
			Integer id_empleadoJefe, LocalDate fecha_incorporacion) {
		super();
		this.pkAdministracion = pkAdministracion;
		this.nombre_empleado = nombre_empleado;
		this.telefono_empleado = telefono_empleado;
		this.id_empleadoJefe = id_empleadoJefe;
		this.fecha_incorporacion = fecha_incorporacion;
	}




	public String toString() {
		return "Administracion [" + pkAdministracion + ", nombre_empleado="
				+ nombre_empleado + ", telefono_empleado=" + telefono_empleado + ", id_empleadoJefe=" + id_empleadoJefe
				+ ", fecha_incorporacion=" + fecha_incorporacion + "]";
	}


	
	public int hashCode() {
		return Objects.hash(pkAdministracion);
	}

	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Administracion other = (Administracion) obj;
		return Objects.equals(pkAdministracion, other.pkAdministracion);
	}


	
	public PKadministracion getPkAdministracion() {
		return pkAdministracion;
	}


	public void setPkAdministracion(PKadministracion pkAdministracion) {
		this.pkAdministracion = pkAdministracion;
	}


	public String getNombre_empleado() {
		return nombre_empleado;
	}


	public void setNombre_empleado(String nombre_empleado) {
		this.nombre_empleado = nombre_empleado;
	}


	public String getTelefono_empleado() {
		return telefono_empleado;
	}


	public void setTelefono_empleado(String telefono_empleado) {
		this.telefono_empleado = telefono_empleado;
	}


	public Integer getId_empleadoJefe() {
		return id_empleadoJefe;
	}


	public void setId_empleadoJefe(Integer id_empleadoJefe) {
		this.id_empleadoJefe = id_empleadoJefe;
	}


	public LocalDate getFecha_incorporacion() {
		return fecha_incorporacion;
	}


	public void setFecha_incorporacion(LocalDate fecha_incorporacion) {
		this.fecha_incorporacion = fecha_incorporacion;
	}


}
