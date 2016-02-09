package com.hvalenti.freelance.inmobiliariaDC.vencimiento

class VencimientosController {
	
	static defaultAction = "pendientesGeneracion"
	
	def vencimientosService
	
	def pendientesGeneracion() {
		def contratos = vencimientosService.contratosConVencimientosPendientesDeGenerar()
		render(view: "generarVencimientos", model: [contratoInstanceList: contratos])
	}
	
	def generarVencimientos() {
		vencimientosService.generarVencimientos()
		flash.message = message(code: 'com.hvalenti.freelance.inmobiliariaDC.Contrato.vencimientosGenerados')
		redirect(action: "pendientesGeneracion")
	}
	
	def generarVencimientosContrato(Long idContrato) {
		vencimientosService.generarVencimientos(idContrato)
		flash.message = message(code: 'com.hvalenti.freelance.inmobiliariaDC.Contrato.vencimientosGenerados')
		redirect(action: "pendientesGeneracion")
	}
	
}