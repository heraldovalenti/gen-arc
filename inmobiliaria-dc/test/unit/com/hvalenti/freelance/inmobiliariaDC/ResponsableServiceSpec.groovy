package com.hvalenti.freelance.inmobiliariaDC

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(ResponsableService)
class ResponsableServiceSpec extends Specification {

    def setup() {
		def r1 = new Responsable(descripcion: "Inmobiliaria")
		def r2 = new Responsable(descripcion: "Locador")
		def r3 = new Responsable(descripcion: "Locatario")
		mockDomain(Responsable, [r1,r2,r3])
    }

    def cleanup() {
    }
	
	def "test amount"() {
		given:
		def amount = 3
		
		when:
		def count = Responsable.count()
		
		then:
		amount == count
	}

    def "get a responsable from descripcion string"(responsableString, descripcion) {
		given:
		
		when:
		def r = service.responsableFromString(responsableString)
		
		then:
		r.descripcion == descripcion
		
		where:
		responsableString << 	["inmobiliaria", "locador", "locatario"]
		descripcion << 			["Inmobiliaria", "Locador", "Locatario"]
    }
	
}