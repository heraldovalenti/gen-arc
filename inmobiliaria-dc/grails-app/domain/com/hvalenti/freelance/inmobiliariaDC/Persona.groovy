package com.hvalenti.freelance.inmobiliariaDC

class Persona {

    String nombres
	String apellidos
	String telefonos
	String correoElectronico
	Domicilio domicilio

    static constraints = {
		nombres maxSize: 100
		apellidos maxSize: 100
		telefonos maxSize: 250
		correoElectronico maxSize: 250, email: true
		domicilio nullable: true
    }
	
	public String toString() {
		return apellidos + ", " + nombres
	}
}
