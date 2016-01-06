package com.hvalenti.freelance.inmobiliariaDC

import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

import com.hvalenti.freelance.inmobiliariaDC.util.DateUtil

@Mock([Contrato, Liquidacion, DetalleLiquidacion, Obligacion, ResponsableObligacion, Vencimiento])
class ContratoSpec extends Specification {

	def "el contrato esta activo cuando no tiene fecha de fin"() {
		given:
		Date now = DateUtil.dateFromString("2015-01-01")
		Date inicio = DateUtil.dateFromString("2015-01-01")
		Contrato c = new Contrato(inicio: inicio)
		
		when:
		boolean contratoActivo = c.esContratoActivo(now)
		
		then:
		contratoActivo
	}
	
	def "el contrato esta activo cuando la fecha indicada es la misma que la fecha de inicio"() {
		given:
		Date now = DateUtil.dateFromString("2015-01-01")
		Date inicio = DateUtil.dateFromString("2015-01-01")
		Contrato c = new Contrato(inicio: inicio)
		
		when:
		boolean contratoActivo = c.esContratoActivo(now)
		
		then:
		contratoActivo
	}
	
	def "el contrato esta activo cuando la fecha indicada es la misma que la fecha de fin"() {
		given:
		Date now = DateUtil.dateFromString("2015-12-31")
		Date inicio = DateUtil.dateFromString("2015-12-31")
		Contrato c = new Contrato(inicio: inicio)
		
		when:
		boolean contratoActivo = c.esContratoActivo(now)
		
		then:
		contratoActivo
	}
	
	def "el contrato esta activo cuando la fecha indicada esta entre la fecha de inicio y de fin"() {
		given:
		Date now = DateUtil.dateFromString("2015-06-01")
		Date inicio = DateUtil.dateFromString("2015-01-01")
		Date fin = DateUtil.dateFromString("2015-12-31")
		Contrato c = new Contrato(inicio: inicio, fin: fin)
		
		when:
		boolean contratoActivo = c.esContratoActivo(now)
		
		then:
		contratoActivo
	}
	
	def "el contrato esta inactivo cuando la fecha indicada esta antes de la fecha de inicio"() {
		given:
		Date now = DateUtil.dateFromString("2015-01-01")
		Date inicio = DateUtil.dateFromString("2015-06-01")
		Contrato c = new Contrato(inicio: inicio)
		
		when:
		boolean contratoActivo = c.esContratoActivo(now)
		
		then:
		!contratoActivo
	}
	
	def "el contrato esta inactivo cuando la fecha indicada esta despues de la fecha de fin"() {
		given:
		Date now = DateUtil.dateFromString("2016-01-01")
		Date inicio = DateUtil.dateFromString("2015-01-01")
		Date fin = DateUtil.dateFromString("2015-12-31")
		Contrato c = new Contrato(fin: fin, inicio: inicio)
		
		when:
		boolean contratoActivo = c.esContratoActivo(now)
		
		then:
		!contratoActivo
	}
	
	def "generar vencimientos para contrato activo"() {
		given:
		Date now = DateUtil.dateFromString("2015-06-01")
		Date inicio = DateUtil.dateFromString("2015-01-01")
		Date fin = DateUtil.dateFromString("2016-01-01")
		def obligacion = mockFor(Obligacion)
		obligacion.demand.generarVencimientos(1) {->}
		Obligacion obligacionMock = obligacion.createMock()
		
		Contrato contrato = new Contrato(inicio: inicio, fin: fin)
		contrato.obligaciones = new HashSet<>()
		contrato.obligaciones.add(obligacionMock)
		
		when:
		contrato.generarVencimientos(now)
		
		then:
		obligacion.verify()
	}
	
	def "existen vencimientos pendientes de generar"(vencimientoDayOfMonth, nowDayOfMonth) {
		given:
		Date inicio = DateUtil.dateFromString("2015-01-01")
		Date fin = DateUtil.dateFromString("2016-01-01")
		Date now = DateUtil.dateFromNumbers(2015, 3, nowDayOfMonth)
		Vencimiento v1 = new Vencimiento(vencimiento: DateUtil.dateFromNumbers(2015, 2, vencimientoDayOfMonth))
		ResponsableObligacion responsableObligacion = new ResponsableObligacion(diaVencimiento: 12, monto: 0.0)
		responsableObligacion.vencimientos = new HashSet()
		responsableObligacion.vencimientos.add(v1)
		Obligacion obligacion = new Obligacion(frecuencia: "Mensual")
		obligacion.responsablesObligacion = new HashSet()
		obligacion.responsablesObligacion.add(responsableObligacion)
		
		Contrato contrato = new Contrato(inicio: inicio, fin: fin)
		contrato.obligaciones = new HashSet<>()
		contrato.obligaciones.add(obligacion)
		
		when:
		boolean existenVencimientosPendientesDeGenerar = contrato.existenVencimientosPendientesDeGenerar(now)
		
		then:
		existenVencimientosPendientesDeGenerar
		
		where:
		vencimientoDayOfMonth << [1, 12, 20, 1, 20, 28]
		nowDayOfMonth << [20, 12, 1, 1, 20, 31]
	}
	
	def "no existen vencimientos pendientes de generar"() {
		given:
		Date now = DateUtil.dateFromString("2015-02-12")
		Date inicio = DateUtil.dateFromString("2015-01-01")
		Date fin = DateUtil.dateFromString("2016-01-01")
		Vencimiento v1 = new Vencimiento(vencimiento: DateUtil.dateFromString("2015-02-01"))
		ResponsableObligacion responsableObligacion = new ResponsableObligacion(diaVencimiento: 12, monto: 0.0)
		responsableObligacion.vencimientos = new HashSet()
		responsableObligacion.vencimientos.add(v1)
		Obligacion obligacion = new Obligacion(frecuencia: "Mensual")
		obligacion.responsablesObligacion = new HashSet()
		obligacion.responsablesObligacion.add(responsableObligacion)
		
		Contrato contrato = new Contrato(inicio: inicio, fin: fin)
		contrato.obligaciones = new HashSet<>()
		contrato.obligaciones.add(obligacion)
		
		when:
		boolean existenVencimientosPendientesDeGenerar = contrato.existenVencimientosPendientesDeGenerar(now)
		
		then:
		!existenVencimientosPendientesDeGenerar
	}
	
	def "generar instancias para contrato vencido"() {
		given:
		def obligacionMock = mockFor(Obligacion)
		obligacionMock.demand.generarVencimientos(0) {->}

		Date now = DateUtil.dateFromString("2017-01-01")
		Date inicio = DateUtil.dateFromString("2015-01-01")
		Date fin = DateUtil.dateFromString("2016-01-01")
		
		Contrato contrato = new Contrato(inicio: inicio, fin: fin)
		contrato.obligaciones = new HashSet<>()
		contrato.obligaciones.add(obligacionMock.createMock())
		
		when:
		contrato.generarVencimientos(now)
		
		then:
		obligacionMock.verify()
	}
	
	def "no se genera liquidacion para contrato no activo"() {
		given:
		Date now = DateUtil.dateFromString("2017-01-01")
		Date inicio = DateUtil.dateFromString("2015-01-01")
		Date fin = DateUtil.dateFromString("2016-01-01")
		Contrato c = new Contrato(inicio: inicio, fin: fin)
		//c.liquidaciones = new HashSet<>()
		def liquidacionMock = mockFor(Liquidacion)
		liquidacionMock.demand.putAt { -> }
		c.addToLiquidaciones(liquidacionMock.createMock())
		
		when:
		c.generarLiquidacion(new Responsable(), now)
		
		then:
		c.liquidaciones.size() == 1
	}
	
	def "se genera liquidacion con 1 vencimiento pendiente del responsable"() {
		given:
		Date now = DateUtil.dateFromString("2015-11-01")
		Date inicio = DateUtil.dateFromString("2014-01-01")
		Date fin = DateUtil.dateFromString("2015-12-31")
		Contrato c = new Contrato(inicio: inicio, fin: fin)
		Obligacion o1 = new Obligacion()
		Responsable r1 = new Responsable(descripcion: "Locatario")
		ResponsableObligacion ro1 = new ResponsableObligacion(responsable: r1)
		Vencimiento v1 = new Vencimiento(vencimiento: now, monto: 10.0)
		ro1.addToVencimientos(v1)
		o1.addToResponsablesObligacion(ro1)
		c.addToObligaciones(o1)
		
		when:
		c.generarLiquidacion(r1, now)
		
		then:
		c.liquidaciones
		c.liquidaciones.size() == 1
		c.liquidaciones[0].total == 10.0
	}
	
	def "se genera liquidacion con 2 vencimientos pendientes del responsable"(m11, m12, m21, total) {
		given:
		Date now = DateUtil.dateFromString("2015-06-01")
		Date inicio = DateUtil.dateFromString("2014-01-01")
		Date fin = DateUtil.dateFromString("2015-12-31")
		Date fechaVencimiento =DateUtil.dateFromString("2015-06-01") 
		Contrato c = new Contrato(inicio: inicio, fin: fin)
		Obligacion o1 = new Obligacion()
		Responsable r1 = new Responsable(descripcion: "Locatario")
		Responsable r2 = new Responsable(descripcion: "Locador")
		ResponsableObligacion ro1 = new ResponsableObligacion(responsable: r1)
		ResponsableObligacion ro2 = new ResponsableObligacion(responsable: r2)
		Vencimiento v1 = new Vencimiento(vencimiento: now, monto: m11)
		Vencimiento v2 = new Vencimiento(vencimiento: now, monto: m12)
		Vencimiento v3 = new Vencimiento(vencimiento: now, monto: m21)
		ro1.addToVencimientos(v1)
		ro1.addToVencimientos(v2)
		ro2.addToVencimientos(v3)
		o1.addToResponsablesObligacion(ro1)
		o1.addToResponsablesObligacion(ro2)
		c.addToObligaciones(o1)
		
		when:
		c.generarLiquidacion(r1, now)
		
		then:
		c.liquidaciones
		c.liquidaciones.size() == 1
		c.liquidaciones[0].total == total
		
		where:
		m11 << [5.0, 6.0, 7.0]
		m12 << [5.5, 6.5, 7.5]
		m21 << [10.0, 20.0, 30.0]
		total << [10.5, 12.5, 14.5]
	}
}