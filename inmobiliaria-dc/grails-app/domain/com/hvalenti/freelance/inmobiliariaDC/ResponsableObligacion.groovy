package com.hvalenti.freelance.inmobiliariaDC

import com.hvalenti.freelance.inmobiliariaDC.util.DateUtil

class ResponsableObligacion {
	
	Responsable responsable
	Double monto
	Integer diaVencimiento
	
	static hasMany = [vencimientos: Vencimiento]
	
	static belongsTo = [obligacion: Obligacion]
	
	static constraints = {
		diaVencimiento min: 1, max: 31
	}
	
	public void generarVencimiento(Date desde, int frecuenciaMeses) {
		Vencimiento ultimoVencimiento = this.getUltimoVencimiento()
		Vencimiento proximoVencimiento = new Vencimiento(monto: this.monto, obligacion: this)
		if (!ultimoVencimiento) {
			Date vencimiento = DateUtil.dateFromNumbers(DateUtil.getYear(desde), DateUtil.getMonthOfYear(desde), diaVencimiento )
			proximoVencimiento.vencimiento = vencimiento
			this.vencimientos.add(proximoVencimiento)
		} else {
			int monthDifference = DateUtil.relativeMonthDifference(desde, ultimoVencimiento.vencimiento)
			if (monthDifference >= frecuenciaMeses) {
				Date fechaProximoVencimiento = DateUtil.dateFromNumbers(
					DateUtil.getYear(ultimoVencimiento.vencimiento), 
					DateUtil.getMonthOfYear(ultimoVencimiento.vencimiento),
					diaVencimiento)
				fechaProximoVencimiento = DateUtil.addMonths(fechaProximoVencimiento, frecuenciaMeses)
				proximoVencimiento.vencimiento = fechaProximoVencimiento
				this.vencimientos.add(proximoVencimiento)
			}
		}
	}
	
	public Vencimiento getUltimoVencimiento() {
		Vencimiento result = null
		for (Vencimiento vencimiento : vencimientos) {
			if (result == null || vencimiento.vencimiento.after(result.vencimiento)) {
				result = vencimiento
			}
		}
		return result;
	}
	
	public boolean esDeResponsable(Responsable responsable) {
		if (!responsable || !this.responsable) {
			return false
		}
		return ( this.responsable.equals(responsable) )
	}
}