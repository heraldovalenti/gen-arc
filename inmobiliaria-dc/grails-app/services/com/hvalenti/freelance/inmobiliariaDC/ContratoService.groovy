package com.hvalenti.freelance.inmobiliariaDC

import grails.validation.ValidationException

import org.springframework.transaction.annotation.Transactional

import com.hvalenti.freelance.inmobiliariaDC.util.DateUtil

class ContratoService {
	
	@Transactional
	def generateContrato(Contrato contratoInstance, 
		Obligacion alquilerObligacionInstance, 
		Obligacion comisionObligacionInstance) throws ValidationException {
		alquilerObligacionInstance.responsable = "Locatario"
		alquilerObligacionInstance.concepto = "Alquiler"
		alquilerObligacionInstance.frecuencia = "Mensual"
		alquilerObligacionInstance.diaVencimiento = 1
		
		comisionObligacionInstance.responsable = "Locador"
		comisionObligacionInstance.concepto = "Comision"
		comisionObligacionInstance.frecuencia = "Mensual"
		comisionObligacionInstance.diaVencimiento = 1
		
		contratoInstance.addToObligaciones(alquilerObligacionInstance)
		contratoInstance.addToObligaciones(comisionObligacionInstance)
		contratoInstance.generarVencimientos(new Date())
		contratoInstance.save()
	}
		
	def contratosConObligacionesProximas() {
		Date from = new Date()
		Date to = DateUtil.addDays(from, 10)
		List<Vencimiento> instanciasList = Vencimiento.findAll(
			"from Vencimiento as I where I.vencimiento between :from and :to and I.liquidacion is null",
			[from: from, to: to])
		Set<Contrato> contratosList = new HashSet<>()
		for (Vencimiento i : instanciasList) {
			Contrato c = i.obligacion.contrato
			if (c) contratosList.add(c)
		}
		return contratosList
	}
	
	def contratosConObligacionesVencidas() {
		Date now = new Date()
		List<Vencimiento> instanciasList = Vencimiento.findAll(
			"from Vencimiento as I where I.vencimiento < :now and I.liquidacion is null", [now: now])
		Set<Contrato> contratosList = new HashSet<>()
		for (Vencimiento i : instanciasList) {
			Contrato c = i.obligacion.contrato
			if (c) contratosList.add(c)
		}
		return contratosList
	}
}
