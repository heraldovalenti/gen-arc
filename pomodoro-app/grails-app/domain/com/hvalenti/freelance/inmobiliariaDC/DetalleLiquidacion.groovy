package com.hvalenti.freelance.inmobiliariaDC

class DetalleLiquidacion {
	
	Double monto
	String observacion
	
	static belongsTo = [liquidacion: Liquidacion]

    static constraints = {
		observacion maxSize: 250
    }
}
