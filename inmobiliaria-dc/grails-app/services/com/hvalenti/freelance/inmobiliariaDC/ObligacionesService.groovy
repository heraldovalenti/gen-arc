package com.hvalenti.freelance.inmobiliariaDC

import org.springframework.transaction.annotation.Transactional

import com.hvalenti.freelance.inmobiliariaDC.Contrato

class ObligacionesService {
	
	@Transactional
	public void generarObligaciones() {
		List<Contrato> contratoList = Contrato.list()
		for (Contrato c : contratoList) {
			c.generarInstanciasObligacion()
		}
	}
}
