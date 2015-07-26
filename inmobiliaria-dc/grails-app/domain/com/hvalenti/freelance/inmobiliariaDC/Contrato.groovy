package com.hvalenti.freelance.inmobiliariaDC

class Contrato {
	
	Inmueble inmueble
	Persona locador
	Persona locatario
	Date inicio
	Date fin
	
	static hasMany = [obligaciones: Obligacion, liquidaciones: Liquidacion]

    static constraints = {
		locador nullable: true
		locatario nullable: true
		inicio nullable: true
		fin nullable: true
    }
	
	static mappings = {
		obligaciones cascade: "all-delete-orphan"
	}
	
	public String toString() {
		return "Contrato N" + id
	}
	
	public void generarInstanciasObligacion(Date now) {
		// return if now is null
		if (!now) return
		// return if Contrato has not started yet 
		if ( inicio.after(now) && !inicio.equals(now) ) return
		// return if Contrato has finished
		if ( fin.before(now) && !fin.equals(now) ) return
		for(Obligacion o : obligaciones) {
			o.generarInstancias()
		}
	}
}
