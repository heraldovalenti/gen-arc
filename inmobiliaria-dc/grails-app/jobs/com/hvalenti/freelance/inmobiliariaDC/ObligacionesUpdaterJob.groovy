package com.hvalenti.freelance.inmobiliariaDC

import org.springframework.beans.factory.annotation.Autowired

class ObligacionesUpdaterJob {

	@Autowired
	ObligacionesService obligacionesService
		
	static triggers = { 
		cron name: "obligacionesDiarias", startDelay: 60000L, cronExpression: "0 0 0 * * ?"
	}

	def execute() {
		obligacionesService.generarObligaciones()
	}
}
