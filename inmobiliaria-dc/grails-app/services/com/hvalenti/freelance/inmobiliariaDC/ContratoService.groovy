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
		alquilerObligacionInstance.vencimientoEstandar = 1
		
		comisionObligacionInstance.responsable = "Locador"
		comisionObligacionInstance.concepto = "Comision"
		comisionObligacionInstance.frecuencia = "Mensual"
		comisionObligacionInstance.vencimientoEstandar = 1
		
		contratoInstance.addToObligaciones(alquilerObligacionInstance)
		contratoInstance.addToObligaciones(comisionObligacionInstance)
		contratoInstance.generarInstanciasObligacion(new Date())
		contratoInstance.save()
	}
		
	def contratosConObligacionesProximas() {
		Date from = new Date()
		Date to = DateUtil.addDays(from, 10)
		List<InstanciaObligacion> instanciasList = InstanciaObligacion.findAll(
			"from InstanciaObligacion as I where I.vencimiento between :from and :to and I.liquidacion is null",
			[from: from, to: to])
		Set<Contrato> contratosList = new HashSet<>()
		for (InstanciaObligacion i : instanciasList) {
			Contrato c = i.obligacion.contrato
			if (c) contratosList.add(c)
		}
		return contratosList
	}
	
	def contratosConObligacionesVencidas() {
		Date now = new Date()
		List<InstanciaObligacion> instanciasList = InstanciaObligacion.findAll(
			"from InstanciaObligacion as I where I.vencimiento < :now and I.liquidacion is null", [now: now])
		Set<Contrato> contratosList = new HashSet<>()
		for (InstanciaObligacion i : instanciasList) {
			Contrato c = i.obligacion.contrato
			if (c) contratosList.add(c)
		}
		return contratosList
	}
}
