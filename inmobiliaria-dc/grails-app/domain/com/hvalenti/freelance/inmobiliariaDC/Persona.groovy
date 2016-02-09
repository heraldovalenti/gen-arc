package com.hvalenti.freelance.inmobiliariaDC

import java.util.Formatter.FormatSpecifier;

import grails.rest.Resource

class Persona {

	String apellidos
    String nombres
	String telefonos
	String correoElectronico
	Domicilio domicilio

    static constraints = {
		apellidos maxSize: 100
		nombres maxSize: 100
		telefonos maxSize: 250
		correoElectronico maxSize: 250, email: true
		domicilio nullable: true
    }
	
	public String toString() {
		return apellidos + ", " + nombres
	}
}
