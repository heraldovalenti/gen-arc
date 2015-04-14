package com.hvalenti.freelance.inmobiliariaDC

class InstanciaObligacion {
	
	Date vencimiento
	DetalleLiquidacion liquidacion
	
	static belongsTo = [obligacion: Obligacion]

    static constraints = {
		liquidacion nullable: true
    }
}
