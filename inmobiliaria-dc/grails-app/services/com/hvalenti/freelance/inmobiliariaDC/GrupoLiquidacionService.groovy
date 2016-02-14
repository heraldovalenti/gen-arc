package com.hvalenti.freelance.inmobiliariaDC

class GrupoLiquidacionService {
	
	def vencimientosService
	def liquidacionesService
	
	def ultimosGruposLiquidacion() {
		GrupoLiquidacion.list(max: 10, sort: "fecha", order: "desc")
	}
	
	def generarGrupoLiquidacion(Date hasta) {
		def grupoLiquidacion = null
		vencimientosService.generarVencimientos(hasta)
		def liquidacionesGeneradas = liquidacionesService.generarLiquidacion(hasta)
		if (!liquidacionesGeneradas.isEmpty()) {
			grupoLiquidacion = new GrupoLiquidacion(fecha: new Date())
			grupoLiquidacion.agregarLiquidaciones(liquidacionesGeneradas)
		}
		return grupoLiquidacion
	}

}
