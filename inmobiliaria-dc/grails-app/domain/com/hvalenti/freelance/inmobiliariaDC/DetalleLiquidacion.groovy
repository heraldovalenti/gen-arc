package com.hvalenti.freelance.inmobiliariaDC

class DetalleLiquidacion {
	
	Double monto
	String observacion
	
	static belongsTo = [liquidacion: Liquidacion]
	
	static hasOne = [vencimiento: Vencimiento]

    static constraints = {
		observacion nullable: true, maxSize: 250
    }
}