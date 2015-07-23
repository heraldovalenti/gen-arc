package com.hvalenti.freelance.inmobiliariaDC

import grails.converters.JSON

class ObligacionController {
	
	def obligacionesService
	
	static scaffold = Obligacion
	
	def generarObligaciones() {
		obligacionesService.generarObligaciones()
		render "OK"
	}
	
}
