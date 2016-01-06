
package com.hvalenti.freelance.inmobiliariaDC

import grails.test.mixin.*
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification

import com.hvalenti.freelance.inmobiliariaDC.util.DateUtil

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@Mock([Obligacion, ResponsableObligacion, Vencimiento])
class ObligacionSpec extends Specification {
	
    def "calcular cantidad de meses de frecuencia"(frecuenciaString, frecuenciaInteger) {
		given:
		Obligacion obligacion = new Obligacion()
		
		when:
		int result = obligacion.calcularCantidadDeMeses(frecuenciaString)
		
		then:
		result == frecuenciaInteger
		
		where:
		frecuenciaString << ["Mensual","Bimestral","Trimestral","Cuatrimestral","Semestral","Anual"]
		frecuenciaInteger << [1, 2, 3, 4, 6, 12]
	}
	
	def "es posible agregar responsables en obligacion mediante addTo"() {
		given:
		Obligacion obligacion = new Obligacion()
		ResponsableObligacion responsableObligacion = new ResponsableObligacion()
		
		when:
		obligacion.addToResponsablesObligacion(responsableObligacion)
		
		then:
		obligacion.responsablesObligacion.size() == 1
	}
	
	def "se generan solo vencimientos para el responsable indicado"(calls, esDeResponsable) {
		given:
		Obligacion o1 = new Obligacion()
		Responsable r1 = new Responsable(descripcion: "Locador")
		o1.responsablesObligacion = new HashSet<>()
		def roMock = mockFor(ResponsableObligacion)
		roMock.demand.esDeResponsable(1) { r -> esDeResponsable }
		roMock.demand.generarVencimiento(calls) { Date -> }
		o1.responsablesObligacion.add(roMock.createMock())
		
		when:
		o1.generarVencimientos(r1, new Date())
		
		then:
		roMock.verify()
		
		where:
		calls << [1,0]
		esDeResponsable << [true, false]
	}
	
	def "se generan vencimientos para todos los responsables"() {
		given:
		Obligacion o1 = new Obligacion()
		o1.responsablesObligacion = new HashSet<>()
		def roMock = mockFor(ResponsableObligacion)
		roMock.demand.generarVencimiento(3) { Date -> }
		o1.responsablesObligacion.add(roMock.createMock())
		o1.responsablesObligacion.add(roMock.createMock())
		o1.responsablesObligacion.add(roMock.createMock())
		
		when:
		o1.generarVencimientos(new Date())
		
		then:
		roMock.verify()
	}
	
	def "vencimientos pendientes solo para responsable"() {
		given:
		Date now = DateUtil.dateFromString("2015-06-05")
		Date fechaVencimiento1 = DateUtil.dateFromString("2015-06-01")
		Date fechaVencimiento2 = DateUtil.dateFromString("2015-06-10")
		Date fechaVencimiento3 = DateUtil.dateFromString("2015-06-02")
		Obligacion o = new Obligacion()
		Responsable r1 = new Responsable(descripcion: "Locatario")
		Responsable r2 = new Responsable(descripcion: "Locador")
		ResponsableObligacion ro1 = new ResponsableObligacion(responsable: r1)
		ResponsableObligacion ro2 = new ResponsableObligacion(responsable: r2)
		Vencimiento v1 = new Vencimiento(vencimiento: fechaVencimiento1)
		Vencimiento v2 = new Vencimiento(vencimiento: fechaVencimiento2)
		Vencimiento v3 = new Vencimiento(vencimiento: fechaVencimiento3, liquidacion: new DetalleLiquidacion())
		ro1.addToVencimientos(v1)
		ro2.addToVencimientos(v2)
		ro2.addToVencimientos(v3)
		o.addToResponsablesObligacion(ro1)
		o.addToResponsablesObligacion(ro2)
		
		when:
		def pendientes = o.vencimientosPendientesDeLiquidacion(r1, now)
		
		then:
		pendientes.size() == 1
		pendientes[0].vencimiento == fechaVencimiento1
	}
	
	def "vencimientos pendientes de liquidacion sin especificar el responsable"() {
		given:
		Date now = DateUtil.dateFromString("2015-06-11")
		Date fechaVencimiento1 = DateUtil.dateFromString("2015-06-01")
		Date fechaVencimiento2 = DateUtil.dateFromString("2015-06-10")
		Date fechaVencimiento3 = DateUtil.dateFromString("2015-06-02")
		Obligacion o = new Obligacion()
		Responsable r1 = new Responsable(descripcion: "Locatario")
		Responsable r2 = new Responsable(descripcion: "Locador")
		ResponsableObligacion ro1 = new ResponsableObligacion(responsable: r1)
		ResponsableObligacion ro2 = new ResponsableObligacion(responsable: r2)
		Vencimiento v1 = new Vencimiento(vencimiento: fechaVencimiento1)
		Vencimiento v2 = new Vencimiento(vencimiento: fechaVencimiento2)
		Vencimiento v3 = new Vencimiento(vencimiento: fechaVencimiento3, liquidacion: new DetalleLiquidacion())
		ro1.addToVencimientos(v1)
		ro2.addToVencimientos(v2)
		ro2.addToVencimientos(v3)
		o.addToResponsablesObligacion(ro1)
		o.addToResponsablesObligacion(ro2)
		
		when:
		def pendientes = o.vencimientosPendientesDeLiquidacion(now)
		
		then:
		pendientes.size() == 2
		pendientes[0].vencimiento == fechaVencimiento1
		pendientes[1].vencimiento == fechaVencimiento2
	}
}