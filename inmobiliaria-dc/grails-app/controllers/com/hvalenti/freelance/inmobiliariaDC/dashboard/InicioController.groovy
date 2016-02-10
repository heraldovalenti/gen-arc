package com.hvalenti.freelance.inmobiliariaDC.dashboard

class InicioController {
	
	def vencimientosService
	def liquidacionesService
	
	def index() {
		
	}
	
	def pendientes() {
		def pendientesGeneracion = vencimientosService.existenVencimientosPendientesDeGenerar()
		def pendientesLiquidacion = liquidacionesService.existenVencimientosPendientesDeLiquidacion()
		render(view: "/pendientes", model: [pendientesGeneracion: pendientesGeneracion, pendientesLiquidacion: pendientesLiquidacion])
	}

}
