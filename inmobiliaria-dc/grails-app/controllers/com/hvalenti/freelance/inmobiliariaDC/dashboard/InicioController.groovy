package com.hvalenti.freelance.inmobiliariaDC.dashboard

class InicioController {
	
	def vencimientosService
	def liquidacionesService
	
	def index() {
		def pendientesGeneracion = vencimientosService.existenVencimientosPendientesDeGenerar()
		def pendientesLiquidacion = liquidacionesService.existenVencimientosPendientesDeLiquidacion()
		render(view: "/index", model: [pendientesGeneracion: pendientesGeneracion, pendientesLiquidacion: pendientesLiquidacion])
	}

}
