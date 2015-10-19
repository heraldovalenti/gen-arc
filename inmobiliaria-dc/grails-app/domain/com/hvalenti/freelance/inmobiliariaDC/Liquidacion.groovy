package com.hvalenti.freelance.inmobiliariaDC

class Liquidacion {

	Date fecha
	Double total
	Responsable responsable

	static hasMany = [detalles: DetalleLiquidacion]

	static belongsTo = [contrato: Contrato]

	static constraints = {
		detalles validator: { val, obj ->
			if (!val || val.isEmpty()) return ["notEmpty"]
		}
	}
}
