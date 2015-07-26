package com.hvalenti.freelance.inmobiliariaDC

import grails.test.mixin.*
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

import com.hvalenti.freelance.inmobiliariaDC.util.DateUtil

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class ObligacionSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }
	
	def "generar instancias genera la primer instancia"() {
		given:
		Obligacion o = new Obligacion(diaVencimiento: 12)
		
		when:
		o.generarVencimientos()
		
		then:
		o.vencimientos
		o.vencimientos.size() == 1
		DateUtil.getDayOfMonth(o.vencimientos.first().vencimiento) == 12
	}

    def "generar instancias no debe generar ninguna instancia"() {
		given:
		Date now = new Date()
		Obligacion o = new Obligacion()
		agregarInstancia(o, now)
		agregarInstancia(o, DateUtil.addMonths(now, -1))
		agregarInstancia(o, DateUtil.addMonths(now, -2))
		
		when:
		o.generarVencimientos()
		
		then:
		o.vencimientos.size() == 3
    }
	
	def "generar instancias no debe generar ninguna instancia para mensual"() {
		given:
		Date now = new Date()
		Obligacion o = new Obligacion(diaVencimiento: 1, frecuencia: "Mensual", )
		agregarInstancia(o, now)
		
		when:
		o.generarVencimientos()
		
		then:
		o.vencimientos.size() == 1
	}
	
	def "generar instancias debe generar una instancia para mensual"() {
		given:
		Date now = new Date()
		Obligacion o = new Obligacion(diaVencimiento: 1, frecuencia: "Mensual", )
		agregarInstancia(o, DateUtil.addMonths(now, -1))
		
		when:
		o.generarVencimientos()
		
		then:
		o.vencimientos.size() == 2
	}
	
	def "generar instancias debe generar una instancia para bimestral"() {
		given:
		Date now = new Date()
		Obligacion o = new Obligacion(diaVencimiento: 1, frecuencia: "Bimestral", )
		agregarInstancia(o, DateUtil.addMonths(now, -2))
		
		when:
		o.generarVencimientos()
		
		then:
		o.vencimientos.size() == 2
	}
	
	def "generar instancias no debe generar ninguna instancia para bimestral"() {
		given:
		Date now = new Date()
		Obligacion o = new Obligacion(diaVencimiento: 1, frecuencia: "Bimestral", )
		agregarInstancia(o, DateUtil.addMonths(now, -1))
		
		when:
		o.generarVencimientos()
		
		then:
		o.vencimientos.size() == 1
	}
	
	def "generar instancias no debe generar ninguna instancia para trimestral"() {
		given:
		Date now = new Date()
		Obligacion o = new Obligacion(diaVencimiento: 1, frecuencia: "Trimestral", )
		agregarInstancia(o, DateUtil.addMonths(now, -2))
		
		when:
		o.generarVencimientos()
		
		then:
		o.vencimientos.size() == 1
	}
	
	def "generar instancias debe generar una instancia para trimestral"() {
		given:
		Date now = new Date()
		Obligacion o = new Obligacion(diaVencimiento: 1, frecuencia: "Trimestral", )
		agregarInstancia(o, DateUtil.addMonths(now, -3))
		
		when:
		o.generarVencimientos()
		
		then:
		o.vencimientos.size() == 2
	}
	
	def "generar instancias debe generar una instancia para anual"() {
		given:
		Date now = new Date()
		Obligacion o = new Obligacion(diaVencimiento: 1, frecuencia: "Anual", )
		agregarInstancia(o, DateUtil.addMonths(now, -12))
		
		when:
		o.generarVencimientos()
		
		then:
		o.vencimientos.size() == 2
	}
	
	def "generar instancias no debe generar ninguna instancia para anual"() {
		given:
		Date now = new Date()
		Obligacion o = new Obligacion(diaVencimiento: 1, frecuencia: "Anual", )
		agregarInstancia(o, DateUtil.addMonths(now, -11))
		
		when:
		o.generarVencimientos()
		
		then:
		o.vencimientos.size() == 1
	}
	
	def "generar instancia obligacion genera con vencimiento estandar"(now, vencimiento, expected) {
		given:
		Date d = DateUtil.dateFromString(now)
		Obligacion o = new Obligacion(diaVencimiento: vencimiento)
		
		when:
		o.generarVencimiento(d)
		
		then:
		o.vencimientos.size() == 1
		DateUtil.getDayOfMonth(o.vencimientos.first().vencimiento) == expected
		
		where:
		now << ["2015-01-01", "2015-01-01", "2015-02-01", "2016-02-01", "2015-04-01"]
		vencimiento << [1, 31, 31, 31, 31]
		expected << [1, 31, 28, 29, 30]
	}
	
	private void agregarInstancia(Obligacion obligacion, Date fecha) {
		if (!obligacion.vencimientos) obligacion.vencimientos = new HashSet()
		Long id = obligacion.vencimientos.size()
		Vencimiento instancia = new Vencimiento(vencimiento: fecha)
		obligacion.vencimientos.add(instancia)
	}
}