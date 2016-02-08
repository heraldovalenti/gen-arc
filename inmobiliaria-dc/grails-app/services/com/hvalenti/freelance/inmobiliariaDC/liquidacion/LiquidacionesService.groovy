package com.hvalenti.freelance.inmobiliariaDC.liquidacion

import com.hvalenti.freelance.inmobiliariaDC.Contrato
import com.hvalenti.freelance.inmobiliariaDC.Responsable

class LiquidacionesService {
	
	def existenVencimientosPendientesDeLiquidacion() {
		Date now = new Date()
		for (Contrato c : Contrato.list() ) {
			if (c.existenVencimientosPendientesDeLiquidacion(now)) {
				return true
			}
		}
		return false
	}
	
	def existenVencimientosPendientesDeLiquidacion(Long contratoId) {
		Date now = new Date()
		Contrato c = Contrato.get(contratoId);
		if (c) {
			return c.existenVencimientosPendientesDeLiquidacion(now)
		}
		return false
	}
	
	
	def contratosConVencimientosPendientesDeLiquidacion() {
		Date now = new Date()
		def contratos = new ArrayList<Contrato>()
		for (Contrato c : Contrato.list() ) {
			if (c.existenVencimientosPendientesDeLiquidacion(now)) {
				contratos.add(c)
			}
		}
		return contratos
	}
	
	def generarLiquidacion(Responsable responsable) {
		for (Contrato c : Contrato.list() ) {
			generarLiquidacion(responsable, c)
		}
	}
	
	def generarLiquidacion(Responsable responsable, Contrato contrato) {
		Date now = new Date()
		contrato.generarLiquidacion(responsable, now)
	}

}
