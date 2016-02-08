package com.hvalenti.freelance.inmobiliariaDC

import grails.transaction.Transactional

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
}
