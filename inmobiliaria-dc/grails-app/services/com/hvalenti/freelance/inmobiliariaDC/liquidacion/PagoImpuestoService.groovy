package com.hvalenti.freelance.inmobiliariaDC.liquidacion

import grails.validation.ValidationException

import javax.transaction.Transactional

import com.hvalenti.freelance.inmobiliariaDC.Contrato
import com.hvalenti.freelance.inmobiliariaDC.Vencimiento
import com.hvalenti.freelance.inmobiliariaDC.Liquidacion

class PagoImpuestoService {
	
	public static final String PAGO_IMPUESTO = "Pago de impuestos"
	public static final String PAGO_IMPUESTO_CONTROLLER = "pagoImpuesto"
	public static final String QUERY_PAGO_IMPUESTO = "from InstanciaObligacion as I " +
		"left join I.obligacion as O " +
		"where I.responsable = 'Inmobiliaria' " +
		"and O.concepto = 'Impuesto' " +
		"and O.contrato = :contrato " +
		"and I.liquidacion is null"
		
	@Transactional
	def listObligaciones(Long id) {
		def contrato = Contrato.get(id)
		def result = Vencimiento.findAll(QUERY_PAGO_IMPUESTO, [contrato: contrato])
		result = result.collect { it[0] }
		return result
	}
	
	@Transactional
	def generarLiquidacion(Liquidacion liquidacion) throws ValidationException {
		Date fecha = new Date()
		liquidacion.concepto = PAGO_IMPUESTO
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
