package com.hvalenti.freelance.inmobiliariaDC

class Responsable {

	String descripcion
		
	static constraints = {
		descripcion inList: ["Locador", "Locatario", "Inmobiliaria"], unique: true
	}
	
	public String toString() {
		return descripcion
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null){
			return false
		}
		if (getClass() != obj.getClass()) {
			return false
		}
		Responsable other = (Responsable) obj;
		if (descripcion == null) {
			if (other.descripcion != null) {
				return false
			}
		} else if (!descripcion.equals(other.descripcion)) {
			return false
		}
		return true
	}
	
}
