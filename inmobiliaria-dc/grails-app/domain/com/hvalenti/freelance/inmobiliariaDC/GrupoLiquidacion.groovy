package com.hvalenti.freelance.inmobiliariaDC

class GrupoLiquidacion {

	Date fecha
	
	static hasMany = [liquidaciones: Liquidacion]
	
    static constraints = {
		
    }
}
