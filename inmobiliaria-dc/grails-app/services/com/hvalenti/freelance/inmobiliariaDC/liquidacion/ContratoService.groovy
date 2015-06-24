package com.hvalenti.freelance.inmobiliariaDC.liquidacion

import grails.validation.ValidationException

import org.springframework.transaction.annotation.Transactional

import com.hvalenti.freelance.inmobiliariaDC.Contrato
import com.hvalenti.freelance.inmobiliariaDC.InstanciaObligacion
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
		
	@Transactional
	def generarObligaciones(Contrato contrato) {
		for(Obligacion o : contrato.obligaciones) {
			generarInstancias(o)
		}
		contrato.save()
	}
	
	def generarInstancias(Obligacion obligacion) {
		def result = InstanciaObligacion.findAll("from InstanciaObligacion as I " 
			+ "where I.obligacion = :obligacion order by vencimiento",
			[obligacion: obligacion]).collect { it[0] }
		return result
	}

}
