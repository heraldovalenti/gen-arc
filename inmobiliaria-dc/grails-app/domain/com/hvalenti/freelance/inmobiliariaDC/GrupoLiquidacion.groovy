package com.hvalenti.freelance.inmobiliariaDC

class GrupoLiquidacion {

	Date fecha
	
	static hasMany = [liquidaciones: Liquidacion]
	
    static constraints = {
		
    }
	
	public int total() {
		return liquidaciones.size()
	}
	
	public void agregarLiquidaciones(List<Liquidacion> liqList) {
		for (def liq : liqList) {
			this.addToLiquidaciones(liq)
		}
	}
}