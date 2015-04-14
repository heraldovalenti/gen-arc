package com.hvalenti.freelance.inmobiliariaDC

class Liquidacion {
	
	Date fecha
	
	static hasMany = [detalles: DetalleLiquidacion]

    static constraints = {
    }
}
