package com.hvalenti.freelance.inmobiliariaDC

class DetalleLiquidacion {
	
	Double monto
	
	static belongsTo = [liquidacion: Liquidacion]

    static constraints = {
    }
}
