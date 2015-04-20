package com.hvalenti.freelance.inmobiliariaDC

class Obligacion {
	
	String responsable
	String concepto
	String frecuencia
	String detalle
	Double montoEstandar
	Integer vencimientoEstandar
	
	static belongsTo = [contrato: Contrato]
	
	static hasMany = [instancias: InstanciaObligacion]

    static constraints = {
		responsable inList: ["Inmobiliaria", "Locatario", "Locador"]
		concepto inList: ["Alquiler","Comision","Impuesto"]
		frecuencia inList: ["Mensual","Bimestral","Trimestral","Cuatrimestral","Anual"]
		detalle blank: true, maxSize: 250
		vencimientoEstandar min: 1, max: 31
    }
	
	public String toString() {
		String subString = (detalle && detalle.length() > 20) ? detalle.substring(0, 19) : detalle
		return concepto + " - " + subString + "..."
	}
}
