package com.hvalenti.freelance.inmobiliariaDC

import com.hvalenti.freelance.inmobiliariaDC.util.DateUtil

class Obligacion {
	
	String concepto
	String frecuencia
	
	static belongsTo = [contrato: Contrato]
	
	static hasMany = [responsablesObligacion: ResponsableObligacion]
	
    static constraints = {
		concepto blank: false, nullable: false, maxSize: 250, minSize: 5
		frecuencia inList: ["Mensual","Bimestral","Trimestral","Cuatrimestral","Semestral","Anual"]
    }
	
	public String toString() {
		return "Obligacion N" + id + ": " + concepto + " (" + frecuencia + ")"
	}
	
	public void generarVencimientos(Date desde) {
		int cantidadDeMeses = calcularCantidadDeMeses(this.frecuencia)
		for (ResponsableObligacion responsableObligacion : responsablesObligacion) {
			responsableObligacion.generarVencimiento(desde, cantidadDeMeses)
		}
	}
	
	public void generarVencimientos(Responsable responsable, Date desde) {
		int cantidadDeMeses = calcularCantidadDeMeses(this.frecuencia)
		for (def responsableObligacion : responsablesObligacion) {
			if (responsableObligacion.esDeResponsable(responsable)) {
				responsableObligacion.generarVencimiento(desde, cantidadDeMeses)
			}
		}
	}
	
	public boolean existenVencimientosPendientesDeGenerar(Date desde) {
		int cantidadDeMeses = calcularCantidadDeMeses(this.frecuencia)
		for (def responsableObligacion : responsablesObligacion) {
			if (responsableObligacion.existeVencimientoPendienteDeGenerar(desde, cantidadDeMeses)) {
				return true
			}
		}
		return false
	}
	
	public List<Vencimiento> vencimientosPendientesDeLiquidacion(Date now) {
		def vencimientosPendientes = new ArrayList<Vencimiento>()
		for (def responsableObligacion : responsablesObligacion) {
			vencimientosPendientes.addAll(responsableObligacion.vencimientosPendientesDeLiquidacion(now))
		}
		return vencimientosPendientes
	}
	
	public List<Vencimiento> vencimientosPendientesDeLiquidacion(Responsable responsable, Date now) {
		def vencimientosPendientes = new ArrayList<Vencimiento>()
		for (def responsableObligacion : responsablesObligacion) {
			if (responsableObligacion.esDeResponsable(responsable)) {
				def vencimientos = responsableObligacion.vencimientosPendientesDeLiquidacion(now)
				vencimientosPendientes.addAll(vencimientos)
			}
		}
		return vencimientosPendientes
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
