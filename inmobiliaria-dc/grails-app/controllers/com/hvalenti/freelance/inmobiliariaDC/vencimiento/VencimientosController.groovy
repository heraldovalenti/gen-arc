package com.hvalenti.freelance.inmobiliariaDC.vencimiento

class VencimientosController {
	
	def vencimientosService
	
	def pendientesGeneracion() {
		def contratos = vencimientosService.contratosConVencimientosPendientesDeGenerar()
		render(view: "generarVencimientos", model: [contratoInstanceList: contratos])
	}
	
	def pendientesLiquidacion() {
		def contratos = vencimientosService.contratosConVencimientosPendientesDeLiquidacion()
	}
	
	def generarVencimientos() {
		vencimientosService.generarVencimientos()
		flash.message = message(code: 'com.hvalenti.freelance.inmobiliariaDC.Contrato.vencimientosGenerados')
		redirect(action: "pendientesGeneracion")
	}
	
	def generarVencimientosContrato(Long id) {
		vencimientosService.generarVencimientos(id)
		flash.message = message(code: 'com.hvalenti.freelance.inmobiliariaDC.Contrato.vencimientosGenerados')
		redirect(action: "pendientesGeneracion")
	}
	
}