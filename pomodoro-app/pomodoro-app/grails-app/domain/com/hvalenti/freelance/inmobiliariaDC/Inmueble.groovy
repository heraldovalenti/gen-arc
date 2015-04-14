package com.hvalenti.freelance.inmobiliariaDC

class Inmueble {
	
	Persona propietario
	Domicilio domicilio
	String tipo

    static constraints = {
		propietario nullable: true
		domicilio nullable: true
		tipo inList: ["Departamento","Casa","Oficina","Terreno"]
    }
	
	public String toString() {
		return tipo
	}
}
