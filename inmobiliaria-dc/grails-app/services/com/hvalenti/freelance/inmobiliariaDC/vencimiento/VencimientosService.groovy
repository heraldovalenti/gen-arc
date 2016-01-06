package com.hvalenti.freelance.inmobiliariaDC.vencimiento

import org.springframework.transaction.annotation.Transactional

import com.hvalenti.freelance.inmobiliariaDC.Contrato

class VencimientosService {
	
	def existenVencimientosPendientes() {
		Date now = new Date()
		for (Contrato c : Contrato.list() ) {
			if (c.existenVencimientosPendientesPara(now)) {
				return true
			}
		}			
		return false
	}
	
	def existenVencimientosPendientesParaContrato(Long contratoId) {
		Date now = new Date()
		Contrato c = Contrato.get(contratoId);
		if (c) {
			return c.existenVencimientosPendientesPara(now)
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
