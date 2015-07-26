package com.hvalenti.freelance.inmobiliariaDC.liquidacion

import grails.validation.ValidationException

import javax.transaction.Transactional

import com.hvalenti.freelance.inmobiliariaDC.Contrato
import com.hvalenti.freelance.inmobiliariaDC.Vencimiento
import com.hvalenti.freelance.inmobiliariaDC.Liquidacion

class RendicionLocadorService {
	
	public static final String RENDICION_LOCADOR = "Rendicion a locador"
	public static final String RENDICION_LOCADOR_CONTROLLER = "rendicionLocador"
	public static final String QUERY_RENDICION_LOCADOR = "from InstanciaObligacion as I " +
		"left join I.obligacion as O " +
		"where I.responsable = 'Inmobiliaria' " +
		"and O.concepto in ('Alquiler','Comision') " +
		"and O.contrato = :contrato " +
		"and I.liquidacion is null"
		
	@Transactional
	def listObligaciones(Long id) {
		def contrato = Contrato.get(id)
		def result = Vencimiento.findAll(QUERY_RENDICION_LOCADOR, [contrato: contrato])
		result = result.collect { it[0] }
		return result
	}
	
	@Transactional
	def generarLiquidacion(Liquidacion liquidacion) throws ValidationException {
		Date fecha = new Date()
		liquidacion.concepto = RENDICION_LOCADOR
		liquidacion.fecha = fecha
		liquidacion.total = 0
		for (def detalle : liquidacion.detalles) {
			def obligacion = detalle.obligacion
			obligacion.liquidacion = detalle
			if (obligacion.monto != detalle.monto) {
				obligacion.monto = detalle.monto
			}
			liquidacion.total += detalle.monto
		}
		liquidacion.save()
		return liquidacion
	}

}
