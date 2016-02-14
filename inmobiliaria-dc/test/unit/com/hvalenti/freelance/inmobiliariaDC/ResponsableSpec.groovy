package com.hvalenti.freelance.inmobiliariaDC

import grails.test.mixin.Mock
import spock.lang.Specification

@Mock(Responsable)
class ResponsableSpec extends Specification {
	
	def setup() {
		def r1 = new Responsable(descripcion: "Inmobiliaria")
		def r2 = new Responsable(descripcion: "Locatario")
		def r3 = new Responsable(descripcion: "Locador")
		mockDomain(Responsable, [r1,r2,r3])
	}
	
	def "equals test"(descr) {
		given:
		def responsable = new Responsable(descripcion: descr)
		
		when:
		def responsableList = Responsable.list()
		
		then:
		responsableList.size == 3
		responsableList.contains(responsable)
		
		where:
		descr << ["Inmobiliaria","Locatario","Locador"]
	}

}
