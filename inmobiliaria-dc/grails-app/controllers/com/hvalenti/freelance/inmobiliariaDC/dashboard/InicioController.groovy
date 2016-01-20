package com.hvalenti.freelance.inmobiliariaDC.dashboard

class InicioController {
	
	def vencimientosService
	
	def index() {
		def pendientesGeneracion = vencimientosService.existenVencimientosPendientesDeGenerar()
		def pendientesLiquidacion = vencimientosService.existenVencimientosPendientesDeLiquidacion()
		render(view: "/index", model: [pendientesGeneracion: pendientesGeneracion, pendientesLiquidacion: pendientesLiquidacion])
	}

}
