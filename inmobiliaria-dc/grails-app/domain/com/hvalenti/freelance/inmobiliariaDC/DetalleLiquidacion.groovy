package com.hvalenti.freelance.inmobiliariaDC

class DetalleLiquidacion {
	
	Double monto
	String observacion
	InstanciaObligacion obligacion
	
	static belongsTo = [liquidacion: Liquidacion]

    static constraints = {
		observacion nullable: true, maxSize: 250
    }
}
