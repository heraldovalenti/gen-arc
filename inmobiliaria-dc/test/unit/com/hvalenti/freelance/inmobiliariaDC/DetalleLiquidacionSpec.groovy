package com.hvalenti.freelance.inmobiliariaDC

import spock.lang.Specification

class DetalleLiquidacionSpec extends Specification {
	
	def "generar liquidacion desde vencimiento"() {
		given:
		Vencimiento v1 = new Vencimiento(id: 1, monto: 10.0)
		
		when:
		DetalleLiquidacion detalle = DetalleLiquidacion.generarDetalleDesdeVencimiento(v1)
		
		then:
		detalle != null
		detalle.monto == 10.0
		detalle.vencimiento.equals(v1)
		v1.liquidacion != null
		v1.liquidacion.equals(detalle)
	}
	
	def "generar liquidacion desde vencimiento vacio"() {
		given:
		Vencimiento v1 = null
		
		when:
		DetalleLiquidacion detalle = DetalleLiquidacion.generarDetalleDesdeVencimiento(v1)
		
		then:
		detalle == null
	}

}
