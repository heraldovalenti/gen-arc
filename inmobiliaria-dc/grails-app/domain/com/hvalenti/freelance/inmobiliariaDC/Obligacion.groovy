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
	
	static mappings = {
		instancias cascade: "all-delete-orphan"
	}

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
	
	public void generarInstancias() {
		
	}
	
	public void ordenarInstancias(List<InstanciaObligacion> instanciasList) {
		Collections.sort(instanciasList, new Comparator<InstanciaObligacion>() {
			@Override 
			int compare(InstanciaObligacion i1, InstanciaObligacion i2) {
				Long diff = i1.vencimiento.getTime() - i2.vencimiento.getTime()
				if (diff == 0) return 0
				else return (diff > 0) ? 1 : -1
			}
		})
	}
}
