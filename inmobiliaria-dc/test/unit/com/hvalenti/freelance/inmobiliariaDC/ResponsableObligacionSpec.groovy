
package com.hvalenti.freelance.inmobiliariaDC

import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

import com.hvalenti.freelance.inmobiliariaDC.util.DateUtil

@Mock([ResponsableObligacion, Vencimiento])
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
	
	def "es de responsable test"() {
		given:
		Responsable r1 = new Responsable(descripcion: "Locador")
		Responsable r2 = new Responsable(descripcion: "Locatario")
		ResponsableObligacion ro1 = new ResponsableObligacion(responsable: r1)
		
		when:
		boolean esResponsableLocador = ro1.esDeResponsable(r1)
		boolean esResponsableLocatario = ro1.esDeResponsable(r2)
		
		then:
		esResponsableLocador == true
		esResponsableLocatario == false
	}
	
	def "vencimientos pendientes para fecha de vencidos"() {
		given:
		Date now = DateUtil.dateFromString("2015-06-01")
		Date fechaVencido = DateUtil.dateFromString("2015-06-01")
		Date fechaNoVencido = DateUtil.dateFromString("2015-06-02")
		ResponsableObligacion r = new ResponsableObligacion()
		Vencimiento vencido = new Vencimiento(vencimiento: fechaVencido)
		Vencimiento noVencido = new Vencimiento(vencimiento: fechaNoVencido)
		Vencimiento liquidado = new Vencimiento(liquidacion: new DetalleLiquidacion())
		r.addToVencimientos(vencido)
		r.addToVencimientos(noVencido)
		r.addToVencimientos(liquidado)
		
		when:
		def pendientes = r.vencimientosPendientes(now)
		
		then:
		pendientes.size() == 1
		def vencimiento = pendientes.get(0)
		vencimiento.vencimiento == fechaVencido
	}
	
	def "sin vencimientos pendientes para fecha de no vencidos todavia"() {
		given:
		Date now = DateUtil.dateFromString("2015-01-01")
		Date fechaNoVencido1 = DateUtil.dateFromString("2015-06-01")
		Date fechaNoVencido2 = DateUtil.dateFromString("2015-06-02")
		ResponsableObligacion r = new ResponsableObligacion()
		Vencimiento noVencido1 = new Vencimiento(vencimiento: fechaNoVencido1)
		Vencimiento noVencido2 = new Vencimiento(vencimiento: fechaNoVencido2)
		Vencimiento liquidado = new Vencimiento(liquidacion: new DetalleLiquidacion())
		r.addToVencimientos(noVencido1)
		r.addToVencimientos(noVencido2)
		r.addToVencimientos(liquidado)
		
		when:
		def pendientes = r.vencimientosPendientes(now)
		
		then:
		pendientes.size() == 0
	}
	
	def "varios vencimientos pendientes para fecha de vencidos"() {
		given:
		Date now = DateUtil.dateFromString("2015-06-03")
		Date fechaVencido1 = DateUtil.dateFromString("2015-06-01")
		Date fechaVencido2 = DateUtil.dateFromString("2015-06-02")
		ResponsableObligacion r = new ResponsableObligacion()
		Vencimiento vencido1 = new Vencimiento(vencimiento: fechaVencido1)
		Vencimiento vencido2 = new Vencimiento(vencimiento: fechaVencido2)
		Vencimiento liquidado = new Vencimiento(liquidacion: new DetalleLiquidacion())
		r.addToVencimientos(vencido1)
		r.addToVencimientos(vencido2)
		r.addToVencimientos(liquidado)
		
		when:
		def pendientes = r.vencimientosPendientes(now)
		
		then:
		pendientes.size() == 2
	}
}
