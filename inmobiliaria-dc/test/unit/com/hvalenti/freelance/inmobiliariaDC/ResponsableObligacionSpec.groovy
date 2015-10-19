
package com.hvalenti.freelance.inmobiliariaDC

import grails.test.mixin.*
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

import com.hvalenti.freelance.inmobiliariaDC.util.DateUtil

@TestMixin(GrailsUnitTestMixin)
class ResponsableObligacionSpec extends Specification {
	
	def "al solicitar el ultimo vencimiento no se indica ningun vencimiento cuando responsable obligacion no tiene vencimientos"() {
		given:
		ResponsableObligacion responsableObligacion = new ResponsableObligacion(diaVencimiento: 12, monto: 0.0)
		
		when:
		Vencimiento ultimo = responsableObligacion.getUltimoVencimiento()
		
		then:
		ultimo == null
	}
	
	def "al solicitar el ultimo vencimiento responsable obligacion devuelve el vencimiento con mayor fecha"() {
		given:
		Vencimiento v1 = new Vencimiento(vencimiento: DateUtil.dateFromNumbers(2015, 1, 1))
		Vencimiento v2 = new Vencimiento(vencimiento: DateUtil.dateFromNumbers(2015, 2, 1))
		ResponsableObligacion responsableObligacion = new ResponsableObligacion(diaVencimiento: 12, monto: 0.0)
		responsableObligacion.vencimientos = new HashSet()
		responsableObligacion.vencimientos.add(v1)
		responsableObligacion.vencimientos.add(v2)
		
		when:
		Vencimiento ultimo = responsableObligacion.getUltimoVencimiento()
		
		then:
		ultimo != null
		ultimo.equals(v2)
	}
	
	def "al generar vencimientos sin vencimientos anteriores se genera un vencimiento en la fecha indicada"() {
		given:
		int frecuencia = Obligacion.calcularCantidadDeMeses("Mensual")
		Date date = DateUtil.dateFromNumbers(2015, 1, 1)
		ResponsableObligacion responsableObligacion = new ResponsableObligacion(diaVencimiento: 12, monto: 0.0)
		responsableObligacion.vencimientos = new HashSet()
		
		when:
		responsableObligacion.generarVencimiento(date, frecuencia)
		
		then:
		Vencimiento ultimoVencimiento = responsableObligacion.ultimoVencimiento
		ultimoVencimiento != null
		DateUtil.getMonthOfYear(ultimoVencimiento.vencimiento) == DateUtil.getMonthOfYear(date)
		DateUtil.getDayOfMonth(ultimoVencimiento.vencimiento) == 12
	}
	
	def "al generar vencimiento mensual con el vencimiento del mes ya generado no se generan nuevos vencimientos"() {
		given:
		int frecuencia = Obligacion.calcularCantidadDeMeses("Mensual")
		Date fechaVencimiento = DateUtil.dateFromNumbers(2015, 1, 12)
		ResponsableObligacion responsableObligacion = new ResponsableObligacion(diaVencimiento: 12, monto: 0.0)
		responsableObligacion.vencimientos = new HashSet()
		Vencimiento v1 = new Vencimiento(id: 1, vencimiento: fechaVencimiento, monto: 0.0)
		responsableObligacion.vencimientos.add(v1)
		responsableObligacion.vencimientos.size() == 1
		
		when:
		responsableObligacion.generarVencimiento(fechaVencimiento, frecuencia)
		
		then:
		responsableObligacion.vencimientos.size() == 1
		Vencimiento ultimoVencimiento = responsableObligacion.ultimoVencimiento
		ultimoVencimiento != null
		ultimoVencimiento.equals(v1)
	}
	
	def "al generar vencimiento mensual con el vencimiento del mes anterior generado se genera el vencimiento del mes"() {
		given:
		int frecuencia = Obligacion.calcularCantidadDeMeses("Mensual")
		Date fechaVencimientoAnterior = DateUtil.dateFromNumbers(2015, 1, 12)
		ResponsableObligacion responsableObligacion = new ResponsableObligacion(diaVencimiento: 12, monto: 0.0)
		responsableObligacion.vencimientos = new HashSet()
		Vencimiento v1 = new Vencimiento(id: 1, vencimiento: fechaVencimientoAnterior, monto: 0.0)
		responsableObligacion.vencimientos.add(v1)
		responsableObligacion.vencimientos.size() == 1
		
		when:
		Date mesActual = DateUtil.dateFromNumbers(2015, 2, 1)
		responsableObligacion.generarVencimiento(mesActual, frecuencia)
		
		then:
		responsableObligacion.vencimientos.size() == 2
		Vencimiento ultimoVencimiento = responsableObligacion.ultimoVencimiento
		ultimoVencimiento != null
		Date fechaUltimoVencimiento = ultimoVencimiento.vencimiento
		DateUtil.getMonthOfYear(fechaUltimoVencimiento) == 2
		DateUtil.getDayOfMonth(fechaUltimoVencimiento) == 12
	}

}
