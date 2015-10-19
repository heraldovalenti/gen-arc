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
}