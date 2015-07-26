package com.hvalenti.freelance.inmobiliariaDC

import com.hvalenti.freelance.inmobiliariaDC.util.DateUtil

class Vencimiento {
	
	Date vencimiento
	DetalleLiquidacion liquidacion
	Double monto
	
	static belongsTo = [obligacion: Obligacion]

    static constraints = {
		liquidacion nullable: true
    }
	
	public String toString() {
		def result = new StringBuilder("")
		def dateString = DateUtil.formatDate(vencimiento)
		result.append(obligacion.toString());
		result.append(" - ")
		result.append(dateString)
		result.append(" - \$ ")
		result.append(monto)
		return result
	}
}
