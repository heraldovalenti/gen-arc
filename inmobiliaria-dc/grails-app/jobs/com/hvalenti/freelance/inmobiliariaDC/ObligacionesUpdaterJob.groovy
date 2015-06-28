package com.hvalenti.freelance.inmobiliariaDC

import org.springframework.beans.factory.annotation.Autowired

class ObligacionesUpdaterJob {

	@Autowired
	ObligacionesService obligacionesService
		
	static triggers = { 
		//simple name: "obligacionesNuevas", startDelay: 10000L, repeatCount: 2, repeatInterval: 10000L
		cron name: "obligacionesDiarias", startDelay: 60000L, cronExpression: "0 0 0 * * ?"
	}

	def execute() {
		obligacionesService.generarObligaciones()
	}
}
