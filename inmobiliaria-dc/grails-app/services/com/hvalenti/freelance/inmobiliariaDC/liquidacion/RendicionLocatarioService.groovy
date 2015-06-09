package com.hvalenti.freelance.inmobiliariaDC.liquidacion

import java.util.Calendar;

import grails.validation.ValidationException

import javax.transaction.Transactional

import com.hvalenti.freelance.inmobiliariaDC.Contrato
import com.hvalenti.freelance.inmobiliariaDC.InstanciaObligacion
import com.hvalenti.freelance.inmobiliariaDC.Liquidacion
import com.hvalenti.freelance.inmobiliariaDC.Obligacion

class RendicionLocatarioService {

	public static final String RENDICION_LOCATARIO = "Rendicion de locatario"
	public static final String RENDICION_LOCATARIO_CONTROLLER = "rendicionLocatario"
	public static final String QUERY_RENDICION_LOCATARIO = "from InstanciaObligacion as I " +
		"left join I.obligacion as O " +
		"where I.responsable = 'Locatario' " +
		"and O.contrato = :contrato " +
		"and I.liquidacion is null"
		
	@Transactional
	def listObligaciones(Long id) {
		def contrato = Contrato.get(id)
		def result = InstanciaObligacion.findAll(QUERY_RENDICION_LOCATARIO, [contrato: contrato])
		result = result.collect { it[0] }
		return result
	}
	
	@Transactional
	def generarLiquidacion(Liquidacion liquidacion) throws ValidationException {
		Date fecha = new Date()
		liquidacion.concepto = RENDICION_LOCATARIO
		liquidacion.fecha = fecha
		for (def detalle : liquidacion.detalles) {
			def obligacion = detalle.obligacion
			obligacion.liquidacion = detalle
			if (obligacion.monto != detalle.monto) {
				obligacion.monto = detalle.monto
			}
			if ("Alquiler".equals(obligacion.obligacion.concepto)) {
				generarObligacionesAlquiler(obligacion)
			}
			liquidacion.total += detalle.monto
		}
		liquidacion.save()
		return liquidacion
	}
	
	private void generarObligacionesAlquiler(InstanciaObligacion instanciaObligacionAlquiler) {
		Obligacion obligacionAlquiler = instanciaObligacionAlquiler.obligacion
		Contrato contrato = Contrato.get(obligacionAlquiler.contrato.id)
		Obligacion obligacionComision = getObligacionComision(contrato)
		
		InstanciaObligacion rendicionAlquilerLocador = new InstanciaObligacion(
			responsable: "Inmobiliaria", 
			monto: instanciaObligacionAlquiler.monto, 
			vencimiento: generarVencimiento(instanciaObligacionAlquiler.vencimiento, obligacionAlquiler.vencimientoEstandar),
			obligacion: obligacionAlquiler)
		InstanciaObligacion comisionAlquiler = new InstanciaObligacion(
			responsable: "Inmobiliaria",
			monto: -obligacionComision.montoEstandar,
			vencimiento: generarVencimiento(instanciaObligacionAlquiler.vencimiento, obligacionComision.vencimientoEstandar),
			obligacion: obligacionComision)
		rendicionAlquilerLocador.save()
		comisionAlquiler.save()
	}
	
	private Date generarVencimiento(Date vencimiento, Integer vencimientoEstandar) {
		def calendar = Calendar.getInstance()
		calendar.setTime(vencimiento)
		Integer maxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
		if (vencimientoEstandar > maxDayOfMonth) {
			calendar.set(Calendar.DATE, maxDayOfMonth)
		} else {
		calendar.set(Calendar.DATE, vencimientoEstandar)
		}
		return calendar.getTime()
	}
	
	private Obligacion getObligacionComision(Contrato contrato) {
		for (Obligacion o : contrato.obligaciones) {
			if ("Comision".equals(o.concepto)) {
				return o
			}
		}
	}

}
