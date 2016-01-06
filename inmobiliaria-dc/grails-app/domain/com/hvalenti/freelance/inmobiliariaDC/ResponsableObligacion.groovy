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
		Vencimiento proximoVencimientoGenerado = obtenerVencimientoGenerado(desde, frecuenciaMeses)
		if (proximoVencimientoGenerado) {
			this.vencimientos.add(proximoVencimientoGenerado)
		}
	}
	
	public boolean existeVencimientoPendienteDeGenerar(Date desde, int frecuenciaMeses) {
		return ( obtenerVencimientoGenerado(desde, frecuenciaMeses) != null )
	}
	
	private Vencimiento obtenerVencimientoGenerado(Date desde, int frecuenciaMeses) {
		Vencimiento ultimoVencimiento = this.getUltimoVencimiento()
		Vencimiento proximoVencimiento = new Vencimiento(monto: this.monto, obligacion: this)
		Vencimiento proximoVencimientoGenerado = null
		if (!ultimoVencimiento) {
			Date vencimiento = DateUtil.dateFromNumbers(DateUtil.getYear(desde), DateUtil.getMonthOfYear(desde), diaVencimiento )
			proximoVencimiento.vencimiento = vencimiento
			proximoVencimientoGenerado = proximoVencimiento
		} else {
			int monthDifference = DateUtil.relativeMonthDifference(desde, ultimoVencimiento.vencimiento)
			if (monthDifference >= frecuenciaMeses) {
				Date fechaProximoVencimiento = DateUtil.dateFromNumbers(
					DateUtil.getYear(ultimoVencimiento.vencimiento),
					DateUtil.getMonthOfYear(ultimoVencimiento.vencimiento),
					diaVencimiento)
				fechaProximoVencimiento = DateUtil.addMonths(fechaProximoVencimiento, frecuenciaMeses)
				proximoVencimiento.vencimiento = fechaProximoVencimiento
				proximoVencimientoGenerado = proximoVencimiento
			}
		}
		return proximoVencimientoGenerado
	}
	
	public List<Vencimiento> vencimientosPendientesDeLiquidacion(Date now) {
		def vencimientosPendientes = new ArrayList<Vencimiento>()
		for(def vencimiento : vencimientos) {
			if (vencimiento.esPendienteDeLiquidacion(now)) {
				vencimientosPendientes.add(vencimiento)
			}
		}
		return vencimientosPendientes
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