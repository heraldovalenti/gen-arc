package com.hvalenti.freelance.inmobiliariaDC

class ResponsableService {
	
	def responsables = [
		inmobiliaria : "Inmobiliaria",
		locador: "Locador",
		locatario: "Locatario"
	]

    def responsableFromString(String responsable) {
		String responsableString = responsables[responsable]
		return Responsable.findByDescripcion(responsableString)
    }
	
	def responsablesKeys() {
		return responsables.keySet().sort { a, b ->
			return a.compareTo(b)
		}
	}
	
	def responsablesValues() {
		return responsables.values().sort { a, b ->
			return a.compareTo(b)
		}
	}
}
