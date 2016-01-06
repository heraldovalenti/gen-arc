
package com.hvalenti.freelance.inmobiliariaDC

import com.hvalenti.freelance.inmobiliariaDC.util.DateUtil;

import spock.lang.Specification;

class VencimientoSpec extends Specification {
	
	def "vencimiento no es pendiente cuando tiene liquidacion asociada"() {
		given:
		Date now = DateUtil.dateFromString("2015-06-01")
		Vencimiento v1 = new Vencimiento()
		v1.vencimiento = now
		v1.liquidacion = new DetalleLiquidacion()
		
		when:
		boolean pendiente = v1.esPendienteDeLiquidacion(now)
		
		then:
		pendiente == false
	}
	
	def "vencimiento no es pendiente cuando la fecha es anterior al vencimiento"() {
		given:
		Date fechaVencimiento = DateUtil.dateFromString("2015-06-02")
		Date now = DateUtil.dateFromString("2015-06-01")
		Vencimiento v1 = new Vencimiento()
		v1.vencimiento = fechaVencimiento
		v1.liquidacion = null
		
		when:
		boolean pendiente = v1.esPendienteDeLiquidacion(now)
		
		then:
		pendiente == false
	}
	
	def "vencimiento es pendiente cuando la fecha es igual al vencimiento"() {
		given:
		Date now = DateUtil.dateFromString("2015-06-01")
		Vencimiento v1 = new Vencimiento()
		v1.vencimiento = now
		v1.liquidacion = null
		
		when:
		boolean pendiente = v1.esPendienteDeLiquidacion(now)
		
		then:
		pendiente == true
	}
	
	def "vencimiento es pendiente cuando la fecha es despues al vencimiento"() {
		given:
		Date fechaVencimiento = DateUtil.dateFromString("2015-06-01")
		Date now = DateUtil.dateFromString("2015-06-02")
		Vencimiento v1 = new Vencimiento()
		v1.vencimiento = fechaVencimiento
		v1.liquidacion = null
		
		when:
		boolean pendiente = v1.esPendienteDeLiquidacion(now)
		
		then:
		pendiente == true
	}
	
	def "vencimiento no es pendiente cuando no se indica fecha"() {
		given:
		Vencimiento v1 = new Vencimiento()
		
		when:
		boolean pendiente = v1.esPendienteDeLiquidacion(null)
		
		then:
		pendiente == false
	}

}
