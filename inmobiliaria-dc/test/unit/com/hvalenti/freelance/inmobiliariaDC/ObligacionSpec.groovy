
package com.hvalenti.freelance.inmobiliariaDC

import com.hvalenti.freelance.inmobiliariaDC.util.DateUtil;
import com.hvalenti.freelance.inmobiliariaDC.util.DateUtilSpec;

import grails.test.mixin.*
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@Mock([Obligacion, ResponsableObligacion])
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
	
	def "se generan solo vencimientos para el responsable indicado"() {
	}
}