package com.bancoit.entidades;

import java.util.Objects;

public abstract class Persona {
	protected Integer id;
	protected String nombre;
	
	
	public Persona() {
		super();
	}
	
	public Persona(Integer id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	

	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + "]";
	}

	

	public int hashCode() {
		return Objects.hash(id);
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(id, other.id);
	}

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
