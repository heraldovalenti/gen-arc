package com.hvalenti.freelance.inmobiliariaDC

import com.hvalenti.freelance.inmobiliariaDC.util.DateUtil

class Obligacion {
	
	String responsable
	String frecuencia
	Double monto
	Integer diaVencimiento
	
	static belongsTo = [contrato: Contrato]
	
	static hasMany = [vencimientos: Vencimiento]
	
	static mappings = {
		vencimientos cascade: "all-delete-orphan"
	}

    static constraints = {
		responsable inList: ["Inmobiliaria", "Locatario", "Locador"]
		frecuencia inList: ["Mensual","Bimestral","Trimestral","Cuatrimestral","Anual"]
		diaVencimiento min: 1, max: 31
    }
	
	public String toString() {
		return "Obligacion N" + id
	}
	
	public void generarVencimientos() {
		if (!vencimientos) vencimientos = new HashSet()
		List<Vencimiento> instanciasList = getOrderedVencimientos()
		if (instanciasList.isEmpty()) {
			generarVencimiento()
		} else {
			Vencimiento lastInstancia = instanciasList.first()
			Date now = new Date()
			int monthDifference = DateUtil.monthDifference(lastInstancia.vencimiento, now)
			switch(this.frecuencia) {
				case "Mensual":
				if (monthDifference >= 1) generarVencimiento(now)
				break
				
				case "Bimestral":
				if (monthDifference >= 2) generarVencimiento(now)
				break
				
				case "Trimestral":
				if (monthDifference >= 3) generarVencimiento(now)
				break
				
				case "Cuatrimestral":
				if (monthDifference >= 4) generarVencimiento(now)
				break
				
				case "Anual":
				if (monthDifference >= 12) generarVencimiento(now)
				break
			}
		}
	}
	
	public void generarVencimiento(Date now) {
		if (!vencimientos) vencimientos = new HashSet()
		int dayOfMonth = Math.min(DateUtil.maxDayOfMonth(now), this.diaVencimiento)
		Date vencimiento = DateUtil.dateFromNumbers(DateUtil.getYear(now), DateUtil.getMonthOfYear(now), dayOfMonth )
		Vencimiento nuevaInstancia = new Vencimiento(
			responsable: this.responsable,
			monto: this.monto,
			vencimiento: vencimiento,
			obligacion: this)
		this.vencimientos.add(nuevaInstancia)
	}
	
	private List<Vencimiento> getOrderedVencimientos() {
		List<Vencimiento> instanciasList = new ArrayList<>(vencimientos)
		instanciasList.sort { a, b -> a.vencimiento.getTime() - b.vencimiento.getTime() }
		return instanciasList
	}
}
