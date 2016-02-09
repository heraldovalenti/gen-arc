package com.hvalenti.freelance.inmobiliariaDC

class Inmueble {
	
	Persona propietario
	Domicilio domicilio
	String tipo

    static constraints = {
		tipo inList: ["Departamento","Casa","Oficina","Terreno"]
		propietario nullable: true
		domicilio nullable: true
    }
	
	public String toString() {
		StringBuilder result = new StringBuilder("")
		result.append(tipo)
		if (domicilio) {
			result.append(" - ")
			result.append(domicilio.toString())
		}
		return result.toString()
	}
}
