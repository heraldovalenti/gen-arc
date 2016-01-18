package com.hvalenti.freelance.inmobiliariaDC.vencimiento

import org.springframework.transaction.annotation.Transactional

import com.hvalenti.freelance.inmobiliariaDC.Contrato

class VencimientosService {
	
	def existenVencimientosPendientesDeGenerar() {
		Date now = new Date()
		for (Contrato c : Contrato.list() ) {
			if (c.existenVencimientosPendientesDeGenerar(now)) {
				return true
			}
		}
		return false
	}
	
	def existenVencimientosPendientesDeGenerar(Long contratoId) {
		Date now = new Date()
		Contrato c = Contrato.get(contratoId);
		if (c) {
			return c.existenVencimientosPendientesDeGenerar(now)
		}
		return false
	}
	
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
	
	@Transactional
	def generarVencimientos() {
		Date now = new Date()
		List<Contrato> contratoList = Contrato.list()
		for (Contrato c : contratoList) {
			c.generarVencimientos(now)
		}
	}
}
