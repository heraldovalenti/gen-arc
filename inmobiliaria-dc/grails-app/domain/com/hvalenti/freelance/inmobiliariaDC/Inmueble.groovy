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
		StringBuilder result = new StringBuilder("")
		result.append(tipo)
		if (domicilio) result.append(domicilio.toString())
		return result.toString()
	}
}
