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
	
	public void calcularTotal() {
		double total = 0.0
		for(def detalle : detalles) {
			total += detalle.monto
		}
		this.total = total
	}
}
