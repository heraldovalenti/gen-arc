package com.hvalenti.freelance.inmobiliariaDC.dashboard

class InicioController {
	
	def contratoService
	
	def index() {
		def proximos = contratoService.contratosConObligacionesProximas()
		def vencidos = contratoService.contratosConObligacionesVencidas()
		render(view: "/index", model: [vencidos: vencidos, proximos: proximos])
	}

}
