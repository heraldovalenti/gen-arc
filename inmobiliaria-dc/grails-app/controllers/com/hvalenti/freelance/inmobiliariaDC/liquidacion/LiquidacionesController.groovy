package com.hvalenti.freelance.inmobiliariaDC.liquidacion

import com.hvalenti.freelance.inmobiliariaDC.Vencimiento
import com.hvalenti.freelance.inmobiliariaDC.Liquidacion

class LiquidacionesController {

	def liquidacionesService
	
	def pendientesLiquidacion() {
		def contratos = liquidacionesService.contratosConVencimientosPendientesDeLiquidacion()
		render(view: "generarLiquidaciones", model: [contratoInstanceList: contratos])
	}
	
	def generarLiquidacionLocador() {
		
	}
	
	def generarLiquidacionLocadorParaContrato(Long id) {
	
	}
	
	def generarLiquidacionLocatario() {
	
	}
	
	def generarLiquidacionLocatarioParaContrato(Long id) {
	
	}
	
	def generarLiquidacionInmobiliaria() {
	
	}
	
	def generarLiquidacionInmobiliariaParaContrato(Long) {
	
	}
	
}