package com.bancoit.jdbc.excepciones;

public class JDBCExcepcion extends Exception{


	private static final long serialVersionUID = 1L;

	public JDBCExcepcion() {
		super();
	}

	public JDBCExcepcion(String message) {
		super(message);
	}

}
