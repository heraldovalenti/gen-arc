package com.hvalenti.freelance.inmobiliariaDC

import com.hvalenti.freelance.inmobiliariaDC.util.DateUtil

class InstanciaObligacion {
	
	Date vencimiento
	String responsable
	DetalleLiquidacion liquidacion
	Double monto
	String observacion
	
	static belongsTo = [obligacion: Obligacion]

    static constraints = {
		responsable inList: ["Inmobiliaria", "Locatario", "Locador"]
		liquidacion nullable: true
		vencimiento nullable: true
		observacion nullable: true, maxSize: 250
    }
	
	public String toString() {
		def result = obligacion.toString()
		if (observacion) result + " - " + observacion
		return result
	}
}
