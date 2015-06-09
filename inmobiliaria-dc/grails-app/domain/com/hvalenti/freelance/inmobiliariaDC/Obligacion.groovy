package com.hvalenti.freelance.inmobiliariaDC

class Obligacion {
	
	String responsable
	String concepto
	String frecuencia
	String observacion
	Double montoEstandar
	Integer vencimientoEstandar
	
	static belongsTo = [contrato: Contrato]
	
	static hasMany = [instancias: InstanciaObligacion]

    static constraints = {
		contrato nullable: true
		responsable inList: ["Inmobiliaria", "Locatario", "Locador"]
		concepto inList: ["Alquiler","Comision","Impuesto"]
		frecuencia inList: ["Mensual","Bimestral","Trimestral","Cuatrimestral","Anual"]
		observacion nullable: true, maxSize: 250
		vencimientoEstandar min: 1, max: 31
    }
	
	public String toString() {
		def result = concepto
		if (observacion) result + " - " + observacion
		return result
	}
}
