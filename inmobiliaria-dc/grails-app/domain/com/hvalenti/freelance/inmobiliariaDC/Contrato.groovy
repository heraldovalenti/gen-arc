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
		fin nullable: true
    }
	
	public String toString() {
		return "Contrato Nro " + id
	}
	
	public boolean existenVencimientosPendientesDeGenerar(Date now) {
		for(def o : obligaciones) {
			if ( o.existenVencimientosPendientesDeGenerar(now) ) {
				return true
			}
		}
		return false
	}
	
	public boolean existenVencimientosPendientesDeLiquidacion(Date now) {
		return ( !vencimientosPendientesDeLiquidacion(now).isEmpty() )
	}
	
	public List<Vencimiento> vencimientosPendientesDeLiquidacion(Date now) {
		def vencimientosPendientes = new ArrayList<Vencimiento>()
		for(def o : obligaciones) {
			vencimientosPendientes.addAll( o.vencimientosPendientesDeLiquidacion(now) )
		}
		return vencimientosPendientes
	}
	
	public void generarLiquidacion(Responsable responsable, Date now) {
		// return if now is null or contrato is not active
		if (!responsable || !now || !this.esContratoActivo(now)) {
			return
		}
		for(def o : obligaciones) {
			def vencimientosPendientes = o.vencimientosPendientesDeLiquidacion(responsable, now)
			if (!vencimientosPendientes.isEmpty()) {
				Liquidacion liquidacion = new Liquidacion(responsable: responsable, fecha: now)
				for (def vencimiento : vencimientosPendientes) {
					def detalleLiquidacion = DetalleLiquidacion.generarDetalleDesdeVencimiento(vencimiento)
					liquidacion.addToDetalles(detalleLiquidacion)
				}
				liquidacion.calcularTotal()
				addToLiquidaciones(liquidacion)
			}
		}
	}
	
	public boolean esContratoActivo(Date now) {
		if (!now) {
			return false
		}
		boolean esIniciado = inicio.before(now) || inicio.equals(now)
		boolean esFinalizado = fin && fin.before(now)
		return ( esIniciado && !esFinalizado )
	}
		
	public void generarVencimientos(Date now) {
		// return if now is null or contrato is not active
		if (!now || !this.esContratoActivo(now)) {
			return
		}
		
		for(Obligacion o : obligaciones) {
			o.generarVencimientos(now)
		}
	}
}
