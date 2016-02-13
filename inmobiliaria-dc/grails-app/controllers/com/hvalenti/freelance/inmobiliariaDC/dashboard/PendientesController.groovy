package com.hvalenti.freelance.inmobiliariaDC.dashboard

class PendientesController {
	
	def vencimientosService
	def liquidacionesService
	
	def index() {
		def pendientesGeneracion = vencimientosService.existenVencimientosPendientesDeGenerar()
		def pendientesLiquidacion = liquidacionesService.existenVencimientosPendientesDeLiquidacion()
		render(view: "/dashboard/pendientes", model: [pendientesGeneracion: pendientesGeneracion, pendientesLiquidacion: pendientesLiquidacion])
	}

}
