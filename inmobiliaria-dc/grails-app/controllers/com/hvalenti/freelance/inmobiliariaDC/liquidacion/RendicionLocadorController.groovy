package com.hvalenti.freelance.inmobiliariaDC.liquidacion

import grails.validation.ValidationException

import com.hvalenti.freelance.inmobiliariaDC.Contrato
import com.hvalenti.freelance.inmobiliariaDC.DetalleLiquidacion
import com.hvalenti.freelance.inmobiliariaDC.Liquidacion

class RendicionLocadorController {
	
	def rendicionLocadorService
	
	def index(Long id) {
		def obligaciones = rendicionLocadorService.listObligaciones(id)
		def liquidacion = (flash.liquidacion) ? flash.liquidacion : new Liquidacion()
		render(view: "/rendiciones/index", model: [liquidacion: liquidacion, instanceList: obligaciones,
			id: id,	controller: rendicionLocadorService.RENDICION_LOCADOR_CONTROLLER,
			titulo: rendicionLocadorService.RENDICION_LOCADOR])
	}
	
	def generarLiquidacion() {
		Long contratoId = Long.parseLong(params.id)
		def liquidacion = new Liquidacion()
		liquidacion.contrato = Contrato.get(contratoId)
		def obligaciones = rendicionLocadorService.listObligaciones(contratoId)
		try {
			for (def o : obligaciones) {
				if ("on".equals(params.get("check-" + o.id))) {
					liquidacion.addToDetalles( new DetalleLiquidacion(
						observacion: params.get("observacion-" + o.id),
						monto: params.get("monto-" + o.id),
						obligacion: o) )
				}
			}

			rendicionLocadorService.generarLiquidacion(liquidacion)
			flash.message = "Rendicion generada exitosamente"
			redirect(controller: "liquidacion", action: "show", id: liquidacion.id)
		} catch (ValidationException ex) {
			flash.exception = ex
			flash.liquidacion = liquidacion
			redirect(action: "index", id: contratoId)
		}
	}

}
