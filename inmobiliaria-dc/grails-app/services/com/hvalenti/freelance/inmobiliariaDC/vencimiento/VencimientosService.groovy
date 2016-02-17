package com.hvalenti.freelance.inmobiliariaDC.vencimiento

import com.hvalenti.freelance.inmobiliariaDC.Contrato

class VencimientosService {
	
	static transactional = true
	
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
	
	def contratosConVencimientosPendientesDeGenerar() {
		Date now = new Date()
		def contratos = new ArrayList<Contrato>()
		for (Contrato c : Contrato.list() ) {
			if (c.existenVencimientosPendientesDeGenerar(now)) {
				contratos.add(c) 
			}
		}
		return contratos
	}
	
	def generarVencimientos() {
		Date now = new Date()
		generarVencimientos(now)
	}
	
	def generarVencimientos(Date now) {
		List<Contrato> contratoList = Contrato.list()
		for (Contrato c : contratoList) {
			c.generarVencimientos(now)
		}
	}
	
	def generarVencimientos(Long contratoId) {
		Date now = new Date()
		generarVencimientos(now,contratoId)
	}
	
	def generarVencimientos(Date now, Long contratoId) {
		Contrato c = Contrato.get(contratoId)
		c.generarVencimientos(now)
	}
}
