package com.bancoit.jdbc.dao;

import java.util.List;
//DAO
public interface Dao<E,K> {
	boolean guardar(E e); //actualizar e insertar
	
	boolean eliminar(E e);
	
	List<E> listar();
	
	E buscarPorPK(K k);
	
}