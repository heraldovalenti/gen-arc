package com.hvalenti.freelance.inmobiliariaDC

class Obligacion {
	
	String concepto
	String detalle
	String frecuencia
	Double montoEstandar
	String responsable
	
	static belongsTo = [contrato: Contrato]
	
	static hasMany = [instancias: InstanciaObligacion]

    static constraints = {
		concepto inList: ["Alquiler","Comision","Impuesto"]
		detalle blank: true, maxSize: 250
		frecuencia inList: ["Mensual","Bimestral","Trimestral","Cuatrimestral","Anual"]
		responsable inList: ["Inmobiliaria", "Locatario", "Locador"]
    }
	
	public String toString() {
		String subString = (detalle && detalle.length() > 20) ? detalle.substring(0, 19) : detalle
		return concepto + " - " + subString + "..."
	}
}
