package com.hvalenti.freelance.inmobiliariaDC

class Liquidacion {
	
	String concepto
	Date fecha
	
	static hasMany = [detalles: DetalleLiquidacion]
	
	static belongsTo = [contrato: Contrato]

    static constraints = {
		concepto inList: ["Rendicion de locatario", 
			"Rendicion a locador", "Pago de impuestos"]
    }
}
