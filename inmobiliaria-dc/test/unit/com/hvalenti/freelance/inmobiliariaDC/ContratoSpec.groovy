package com.hvalenti.freelance.inmobiliariaDC

import grails.test.mixin.*
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

import com.hvalenti.freelance.inmobiliariaDC.util.DateUtil

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
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
	
	def "generar instancias para contrato activo"() {
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
}