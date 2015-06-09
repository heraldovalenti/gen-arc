package com.hvalenti.freelance.inmobiliariaDC.liquidacion.exception;

public class NoSelectionException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String NO_SELECTION_MESSAGE = 
			"Debe seleccionar algun elemento para realizar esta accion";

	public NoSelectionException() {
		super(NO_SELECTION_MESSAGE);
	}
	
}
