package com.hvalenti.freelance.inmobiliariaDC


class Vencimiento {

	Date vencimiento
	Double monto
	DetalleLiquidacion liquidacion

	static belongsTo = [responsableObligacion: ResponsableObligacion]

	static constraints = {
		liquidacion nullable: true
	}
	
	public boolean esPendiente(Date now) {
		return ( liquidacion == null && ( vencimiento.equals(now) || vencimiento.before(now) ))
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false
		}
		if (getClass() != obj.getClass()) {
			return false
		}
		Vencimiento other = (Vencimiento) obj;
		if (id == null) {
			if (other.id != null) {
				return false
			}
		} else if (!id.equals(other.id)) {
			return false
		}
		return true
	}
}
