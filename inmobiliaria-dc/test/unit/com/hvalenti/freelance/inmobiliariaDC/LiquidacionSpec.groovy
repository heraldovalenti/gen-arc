package com.hvalenti.freelance.inmobiliariaDC

import grails.test.mixin.Mock
import spock.lang.Specification

@Mock([Liquidacion, DetalleLiquidacion])
class LiquidacionSpec extends Specification {

	def "calcular total sin detalles resulta en total de 0"() {
		given:
		Liquidacion l = new Liquidacion()
		
		when:
		l.calcularTotal()
		
		then:
		l.total == 0
	}
	
	def "calcular total con detalles (10.5 y 20.0) resulta en total de 30.5"() {
		given:
		Liquidacion l = new Liquidacion()
		DetalleLiquidacion d1 = new DetalleLiquidacion(monto: 10.5)
		DetalleLiquidacion d2 = new DetalleLiquidacion(monto: 20.0)
		l.addToDetalles(d1)
		l.addToDetalles(d2)
		
		when:
		l.calcularTotal()
		
		then:
		l.total == 30.5
	}
	
	def "no se puede generar una liquidacion sin detalles"() {
		given:
		Liquidacion l = new Liquidacion()
		l.errors == null
		
		when:
		l.validate()
		
		then:
		l.errors != null
		l.errors['detalles'].code == 'notEmpty'
	}
	
}
