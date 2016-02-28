package com.hvalenti.freelance.inmobiliariaDC.dashboard

import com.hvalenti.freelance.inmobiliariaDC.GrupoLiquidacion
import com.hvalenti.freelance.inmobiliariaDC.util.DateUtil

class GrupoLiquidacionController {
	
	def grupoLiquidacionService
	
	def index() {
		def ultimosGrupos = grupoLiquidacionService.ultimosGruposLiquidacion()
		render(view: "/grupoLiquidacion/index", model: [gruposLiquidacion: ultimosGrupos])
	}
	
	def generarGrupoLiquidacion() {
		Date when = DateUtil.lastDayOfMonth(new Date())
		def grupoLiquidacion = grupoLiquidacionService.generarGrupoLiquidacion(when)
		if (grupoLiquidacion) {
			flash.message = message(code: 'com.hvalenti.freelance.inmobiliariaDC.GrupoLiquidacion.grupoLiquidacionGenerado')
		} else {
			flash.message = message(code: 'com.hvalenti.freelance.inmobiliariaDC.GrupoLiquidacion.sinLiquidaciones')
		}
		redirect(action: "index")
	}
	
	def show(Long id) {
		def grupoLiquidacion = GrupoLiquidacion.get(id)
		render(view: "/grupoLiquidacion/show", model: [grupoLiquidacion: grupoLiquidacion])
	}

}
