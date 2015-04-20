package com.hvalenti.freelance.inmobiliariaDC

class Domicilio {
	
	String calle
	String numero
	Integer piso
	String departamento
	String barrio
	String ciudad
	String provincia

    static constraints = {
		calle maxSize: 150
		numero maxSize: 10
		piso nullable: true
		departamento nullable: true, maxSize: 25
		barrio nullable: true, maxSize: 150
		ciudad nullable: true, maxSize: 150
		provincia nullable: true, maxSize: 150
    }
	
	public String toString() {
		String concatenator = ", "
		StringBuilder sb = new StringBuilder()
		sb.append(calle)
		sb.append(numero)
		if (piso) sb.append(concatenator + piso)
		if (departamento) sb.append(concatenator + departamento)
		if (barrio) sb.append(concatenator + barrio)
		if (ciudad) sb.append(concatenator + ciudad)
		if (provincia) sb.append(concatenator + provincia)
		return sb.toString()
	}
}
