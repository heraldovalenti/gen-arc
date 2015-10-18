package com.hvalenti.freelance.inmobiliariaDC


class Vencimiento {
	
	Date vencimiento
	Double monto
	Responsable responsable
	
	static belongsTo = [liquidacion: DetalleLiquidacion]

    static constraints = {
    }
}
