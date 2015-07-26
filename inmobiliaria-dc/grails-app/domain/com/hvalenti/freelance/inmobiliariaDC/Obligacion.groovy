package com.hvalenti.freelance.inmobiliariaDC

import com.hvalenti.freelance.inmobiliariaDC.util.DateUtil

class Obligacion {
	
	String responsable
	String frecuencia
	Double monto
	Integer diaVencimiento
	
	static belongsTo = [contrato: Contrato]
	
	static hasMany = [instancias: Vencimiento]
	
	static mappings = {
		instancias cascade: "all-delete-orphan"
	}

    static constraints = {
		contrato nullable: true
		responsable inList: ["Inmobiliaria", "Locatario", "Locador"]
		frecuencia inList: ["Mensual","Bimestral","Trimestral","Cuatrimestral","Anual"]
		diaVencimiento min: 1, max: 31
    }
	
	public String toString() {
		return "Obligacion N" + id
	}
	
	public void generarInstancias() {
		if (!instancias) instancias = new HashSet()
		List<Vencimiento> instanciasList = getOrderedInstancias()
		if (instanciasList.isEmpty()) {
			generarInstanciaObligacion()
		} else {
			Vencimiento lastInstancia = instanciasList.first()
			Date now = new Date()
			int monthDifference = DateUtil.monthDifference(lastInstancia.vencimiento, now)
			switch(this.frecuencia) {
				case "Mensual":
				if (monthDifference >= 1) generarInstanciaObligacion(now)
				break
				
				case "Bimestral":
				if (monthDifference >= 2) generarInstanciaObligacion(now)
				break
				
				case "Trimestral":
				if (monthDifference >= 3) generarInstanciaObligacion(now)
				break
				
				case "Cuatrimestral":
				if (monthDifference >= 4) generarInstanciaObligacion(now)
				break
				
				case "Anual":
				if (monthDifference >= 12) generarInstanciaObligacion(now)
				break
			}
		}
	}
	
	public void generarInstanciaObligacion(Date now) {
		if (!instancias) instancias = new HashSet()
		int dayOfMonth = Math.min(DateUtil.maxDayOfMonth(now), this.diaVencimiento)
		Date vencimiento = DateUtil.dateFromNumbers(DateUtil.getYear(now), DateUtil.getMonthOfYear(now), dayOfMonth )
		Vencimiento nuevaInstancia = new Vencimiento(
			responsable: this.responsable,
			monto: this.monto,
			vencimiento: vencimiento,
			obligacion: this)
		this.instancias.add(nuevaInstancia)
	}
	
	private List<Vencimiento> getOrderedInstancias() {
		List<Vencimiento> instanciasList = new ArrayList<>(instancias)
		instanciasList.sort { a, b -> a.vencimiento.getTime() - b.vencimiento.getTime() }
		return instanciasList
	}
}
