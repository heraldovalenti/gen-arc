package com.hvalenti.freelance.inmobiliariaDC

import grails.converters.JSON

class ObligacionController {
	
	def contratoService
	
	static scaffold = Obligacion
	
	def test(Long id) {
		Contrato c = Contrato.get(id)
		Obligacion o = c.obligaciones.first()
		def result = contratoService.generarInstancias(o)
		render result as JSON
	}

}
