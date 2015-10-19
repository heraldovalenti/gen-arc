package com.hvalenti.freelance.inmobiliariaDC


class Vencimiento {
	
	Date vencimiento
	Double monto
	
	static belongsTo = [responsableObligacion: ResponsableObligacion]

    static constraints = {
    }

		@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vencimiento other = (Vencimiento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
}
