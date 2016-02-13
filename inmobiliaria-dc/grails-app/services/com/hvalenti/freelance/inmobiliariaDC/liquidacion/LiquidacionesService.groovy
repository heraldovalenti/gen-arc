package com.hvalenti.freelance.inmobiliariaDC.liquidacion

import com.hvalenti.freelance.inmobiliariaDC.Contrato
import com.hvalenti.freelance.inmobiliariaDC.Liquidacion
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
	
	List<Liquidacion> generarLiquidacion(Date now) {
		def liquidaciones = new ArrayList<Liquidacion>()
		for (Responsable r : Responsable.list()) {
			liquidaciones.addAll( generarLiquidacion(now, r) )
		}
		return liquidaciones
	}
	
	def generarLiquidacion(Date now, Responsable responsable) {
		def liquidaciones = new ArrayList<Liquidacion>()
		for (Contrato c : Contrato.list() ) {
			def liq = generarLiquidacion(responsable, c, now)
			if (liq) liquidaciones.add(liq)
		}
		return liquidaciones
	}
	
	def generarLiquidacion(Responsable responsable) {
		def liquidaciones = new ArrayList<Liquidacion>()
		for (Contrato c : Contrato.list() ) {
			def liq = generarLiquidacion(responsable, c)
			if (liq) liquidaciones.add(liq)
		}
		return liquidaciones
	}
	
	def generarLiquidacion(Responsable responsable, Contrato contrato) {
		Date now = new Date()
		return generarLiquidacion(responsable, contrato, now)
	}
	
	def generarLiquidacion(Responsable responsable, Contrato contrato, Date now) {
		return contrato.generarLiquidacion(responsable, now)
	}

}
