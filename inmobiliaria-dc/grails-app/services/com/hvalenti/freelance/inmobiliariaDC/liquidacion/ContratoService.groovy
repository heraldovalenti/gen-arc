package com.hvalenti.freelance.inmobiliariaDC.liquidacion

import grails.validation.ValidationException

import org.springframework.transaction.annotation.Transactional

import com.hvalenti.freelance.inmobiliariaDC.Contrato
import com.hvalenti.freelance.inmobiliariaDC.Obligacion

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
		contratoInstance.save()
	}

}
