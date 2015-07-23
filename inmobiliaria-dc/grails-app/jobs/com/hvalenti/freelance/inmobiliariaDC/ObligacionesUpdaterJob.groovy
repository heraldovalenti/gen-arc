package com.hvalenti.freelance.inmobiliariaDC

import org.springframework.beans.factory.annotation.Autowired

class ObligacionesUpdaterJob {

	@Autowired
	ObligacionesService obligacionesService
		
	static triggers = { 
		simple name: "obligaciones", startDelay: 10000L, repeatInterval: 60000L //repeatCount: 2, 
		//cron name: "obligacionesDiarias", startDelay: 60000L, cronExpression: "0 0 0 * * ?"
	}

	def execute() {
		obligacionesService.generarObligaciones()
	}
}
