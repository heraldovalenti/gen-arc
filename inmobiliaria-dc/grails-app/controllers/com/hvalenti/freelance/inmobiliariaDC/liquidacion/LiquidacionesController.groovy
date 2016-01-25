package com.hvalenti.freelance.inmobiliariaDC.liquidacion

import com.hvalenti.freelance.inmobiliariaDC.Vencimiento
import com.hvalenti.freelance.inmobiliariaDC.Liquidacion

class LiquidacionesController {

	def liquidacionesService
	
	def pendientesLiquidacion() {
		def contratos = liquidacionesService.contratosConVencimientosPendientesDeLiquidacion()
		render(view: "generarLiquidaciones", model: [contratoInstanceList: contratos])
	}
	
	
}