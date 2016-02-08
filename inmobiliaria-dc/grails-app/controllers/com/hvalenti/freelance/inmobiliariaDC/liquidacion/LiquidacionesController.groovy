package com.hvalenti.freelance.inmobiliariaDC.liquidacion

import com.hvalenti.freelance.inmobiliariaDC.Contrato

class LiquidacionesController {

	def liquidacionesService
	def responsableService
	
	def pendientesLiquidacion() {
		def contratos = liquidacionesService.contratosConVencimientosPendientesDeLiquidacion()
		render(view: "generarLiquidaciones", model: [contratoInstanceList: contratos])
	}
	
	def generarLiquidaciones(String responsable) {
		def r = responsableService.responsableFromString(responsable)
		liquidacionesService.generarLiquidacion(r)
		flash.message = message(code: 'com.hvalenti.freelance.inmobiliariaDC.Contrato.liquidacionGenerada')
		redirect(action: "pendientesLiquidacion")
	}
	
	def generarLiquidacionesParaContrato(String responsable, Long idContrato) {
		def r = responsableService.responsableFromString(responsable)
		def c = Contrato.get(idContrato)
		liquidacionesService.generarLiquidacion(r, c)
		flash.message = message(code: 'com.hvalenti.freelance.inmobiliariaDC.Contrato.liquidacionGenerada')
		redirect(action: "pendientesLiquidacion")
	}
	
}