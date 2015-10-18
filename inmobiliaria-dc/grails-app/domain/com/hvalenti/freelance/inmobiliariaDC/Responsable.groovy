package com.hvalenti.freelance.inmobiliariaDC

class Responsable {
	
	String responsable
	Double monto
	Integer diaVencimiento
	
	static belongsTo = [obligacion: Obligacion]
	
	static constraints = {
		responsable inLIst: ["Locador", "Locatario", "Inmobiliaria"]
		diaVencimiento min: 1, max: 31
	}
}