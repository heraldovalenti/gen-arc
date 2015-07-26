package com.hvalenti.freelance.inmobiliariaDC


class Liquidacion {
	
	String concepto
	Date fecha
	Double total
	
	static hasMany = [detalles: DetalleLiquidacion, generadas: Vencimiento]
	
	static belongsTo = [contrato: Contrato]
	
	static mapping = {
		detalles cascade: "all-delete-orphan"
		generadas cascade: "all-delete-orphan"
	}

    static constraints = {
		concepto inList: ["Rendicion de locatario", 
			"Rendicion a locador", "Pago de impuestos"]
		detalles validator: { val, obj ->
			if (!val || val.isEmpty()) return ["notEmpty"]
		}
    }
}
