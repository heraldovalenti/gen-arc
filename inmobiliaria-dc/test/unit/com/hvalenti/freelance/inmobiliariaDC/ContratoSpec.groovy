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

    def setup() {
    }

    def cleanup() {
    }

    def "generar instancias para contrato activo sin limite"() {
		given:
		Date now = DateUtil.dateFromString("2015-06-01")
		Date inicio = DateUtil.dateFromString("2015-01-01")
		def obligacion = mockFor(Obligacion)
		obligacion.demand.generarInstancias(1) {->}
		Obligacion obligacionMock = obligacion.createMock()
		
		Contrato contrato = new Contrato(inicio: inicio)
		contrato.obligaciones = new HashSet<>()
		contrato.obligaciones.add(obligacionMock)
		
		when:
		contrato.generarInstanciasObligacion(now)
		
		then:
		obligacion.verify()
    }
	
	def "generar instancias para contrato activo"() {
		given:
		Date now = DateUtil.dateFromString("2015-06-01")
		Date inicio = DateUtil.dateFromString("2015-01-01")
		Date fin = DateUtil.dateFromString("2016-01-01")
		def obligacion = mockFor(Obligacion)
		obligacion.demand.generarInstancias(1) {->}
		Obligacion obligacionMock = obligacion.createMock()
		
		Contrato contrato = new Contrato(inicio: inicio, fin: fin)
		contrato.obligaciones = new HashSet<>()
		contrato.obligaciones.add(obligacionMock)
		
		when:
		contrato.generarInstanciasObligacion(now)
		
		then:
		obligacion.verify()
	}
	
	def "generar instancias para contrato vencido"() {
		given:
		def obligacionMock = mockFor(Obligacion)
		obligacionMock.demand.generarInstancias(0) {->}

		Date now = DateUtil.dateFromString("2017-01-01")
		Date inicio = DateUtil.dateFromString("2015-01-01")
		Date fin = DateUtil.dateFromString("2016-01-01")
		
		Contrato contrato = new Contrato(inicio: inicio, fin: fin)
		contrato.obligaciones = new HashSet<>()
		contrato.obligaciones.add(obligacionMock.createMock())
		
		when:
		contrato.generarInstanciasObligacion(now)
		
		then:
		obligacionMock.verify()
	}
}