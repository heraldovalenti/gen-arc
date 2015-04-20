package com.hvalenti.freelance.inmobiliariaDC

class InstanciaObligacion {
	
	Date vencimiento
	String responsable
	DetalleLiquidacion liquidacion
	Double monto
	
	static belongsTo = [obligacion: Obligacion]

    static constraints = {
		responsable inList: ["Inmobiliaria", "Locatario", "Locador"]
		liquidacion nullable: true
    }
}
