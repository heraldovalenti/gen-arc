package com.example

import grails.converters.JSON

import com.hvalenti.freelance.inmobiliariaDC.Contrato
import com.hvalenti.freelance.inmobiliariaDC.Obligacion

class PruebaController {

    def list() {
		render params as JSON
	}
	
	def get() {
		render params as JSON
	}
	
	def save() {
		render params as JSON
	}
	
	def update() {
		render params as JSON
	}
	
	def delete() {
		render params as JSON
	}
	
	def obligaciones() {
		render (view: "/obligacion/index", 
			model: [obligacionInstanceList: Obligacion.list(), 
				contratos: Contrato.list()])
	}
}
