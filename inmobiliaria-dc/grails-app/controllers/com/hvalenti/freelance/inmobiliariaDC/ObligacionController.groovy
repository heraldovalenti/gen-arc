package com.hvalenti.freelance.inmobiliariaDC

import grails.converters.JSON

class ObligacionController {
	
	def vencimientosService
	
	static scaffold = Obligacion
	
	def generarObligaciones() {
		vencimientosService.generarObligaciones()
		render "OK"
	}
	
}
