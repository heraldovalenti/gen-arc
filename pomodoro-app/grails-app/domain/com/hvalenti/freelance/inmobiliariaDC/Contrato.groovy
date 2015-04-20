package com.hvalenti.freelance.inmobiliariaDC

class Contrato {
	
	Date inicio
	Date fin
	Inmueble inmueble
	Persona locador
	Persona locatario
	
	static hasMany = [obligaciones: Obligacion, liquidaciones: Liquidacion]

    static constraints = {
		inicio nullable: true
		fin nullable: true
		locador nullable: true
		locatario nullable: true
    }
	
	public String toString() {
		return "Contrato N" + id
	}
}
