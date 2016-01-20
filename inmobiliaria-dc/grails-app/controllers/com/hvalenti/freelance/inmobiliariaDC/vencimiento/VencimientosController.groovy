package com.hvalenti.freelance.inmobiliariaDC.vencimiento

class VencimientosController {
	
	def vencimientosService
	
	def pendientesGeneracion() {
		def contratos = vencimientosService.contratosConVencimientosPendientesDeGenerar()
		render(view: "index", model: [contratoInstanceList: contratos])
	}
	
	def pendientesLiquidacion() {
		def contratos = vencimientosService.contratosConVencimientosPendientesDeLiquidacion()
	}
	
}
