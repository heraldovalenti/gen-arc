package com.hvalenti.freelance.inmobiliariaDC

class DetalleLiquidacion {
	
	Double monto
	String observacion
	Vencimiento vencimiento
	
	static belongsTo = [liquidacion: Liquidacion]
	
    static constraints = {
		observacion nullable: true, maxSize: 250
    }
	
	public static DetalleLiquidacion generarDetalleDesdeVencimiento(Vencimiento vencimiento) {
		if (!vencimiento) {
			return null
		}
		DetalleLiquidacion detalle = new DetalleLiquidacion()
		detalle.vencimiento = vencimiento
		detalle.monto = vencimiento.monto
		return detalle
	}
}