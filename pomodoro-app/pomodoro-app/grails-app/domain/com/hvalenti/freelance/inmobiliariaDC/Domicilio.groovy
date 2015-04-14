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
}
