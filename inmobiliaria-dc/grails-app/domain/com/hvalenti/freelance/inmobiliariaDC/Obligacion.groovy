package com.hvalenti.freelance.inmobiliariaDC

import com.hvalenti.freelance.inmobiliariaDC.util.DateUtil

class Obligacion {
	
	String frecuencia
	String concepto
	
	static belongsTo = [contrato: Contrato]
	
	static hasMany = [responsablesObligacion: ResponsableObligacion]
	
    static constraints = {
		frecuencia inList: ["Mensual","Bimestral","Trimestral","Cuatrimestral","Semestral","Anual"]
    }
	
	public String toString() {
		return "Obligacion N" + id
	}
	
	public generarVencimientos(Date desde) {
		int cantidadDeMeses = calcularCantidadDeMeses(this.frecuencia)
		for (ResponsableObligacion responsableObligacion : responsablesObligacion) {
			responsableObligacion.generarVencimiento(desde, cantidadDeMeses)
		}
	}
	
	public void generarVencimientos(Responsable responsable, Date desde) {
		int cantidadDeMeses = calcularCantidadDeMeses(this.frecuencia)
		for (ResponsableObligacion responsableObligacion : responsablesObligacion) {
			if (responsableObligacion.responsable.equals(responsable)) {
				responsableObligacion.generarVencimiento(desde, cantidadDeMeses)
			}
		}
	}
	
	public static int calcularCantidadDeMeses(String frecuencia) {
		int result = 0;
		switch(frecuencia) {
			case "Mensual":
			result = 1
			break
			
			case "Bimestral":
			result = 2
			break
			
			case "Trimestral":
			result = 3
			break
			
			case "Cuatrimestral":
			result = 4
			break
			
			case "Semestral":
			result = 6
			break
			
			case "Anual":
			result = 12
			break
		}
		return result
	}
}
