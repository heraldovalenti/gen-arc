package com.hvalenti.freelance.inmobiliariaDC



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.validation.ValidationException

import java.text.SimpleDateFormat

import org.springframework.format.datetime.DateFormatter

@Transactional(readOnly = true)
class ContratoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def contratoService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Contrato.list(params), model:[contratoInstanceCount: Contrato.count()]
    }

    def show(Contrato contratoInstance) {
        respond contratoInstance
    }

    def create() {
        def contratoInstance = new Contrato()
		def alquilerObligacionInstance = new Obligacion()
		def comisionObligacionInstance = new Obligacion()
		render(view: "create", model: [contratoInstance: contratoInstance, 
			alquilerObligacionInstance: alquilerObligacionInstance,
			comisionObligacionInstance: comisionObligacionInstance])
    }
	
	@Transactional
	def save() {
		def contratoInstance = new Contrato(params)
		def alquilerObligacionInstance = new Obligacion(montoEstandar: params.montoAlquiler)
		def comisionObligacionInstance = new Obligacion(montoEstandar: params.comision)
		try {
			contratoService.generateContrato(contratoInstance, alquilerObligacionInstance, comisionObligacionInstance)
			render(view: "show", model: [contratoInstance: contratoInstance])
		} catch(ValidationException ex) {
			render(view: "create", model: [contratoInstance: contratoInstance,
				alquilerObligacionInstance: alquilerObligacionInstance,
				comisionObligacionInstance: comisionObligacionInstance])
		}
	}

    def edit(Contrato contratoInstance) {
        respond contratoInstance
    }

    @Transactional
    def update(Contrato contratoInstance) {
        if (contratoInstance == null) {
            notFound()
            return
        }

        if (contratoInstance.hasErrors()) {
            respond contratoInstance.errors, view:'edit'
            return
        }

        contratoInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Contrato.label', default: 'Contrato'), contratoInstance.id])
                redirect contratoInstance
            }
            '*'{ respond contratoInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Contrato contratoInstance) {

        if (contratoInstance == null) {
            notFound()
            return
        }

        contratoInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Contrato.label', default: 'Contrato'), contratoInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'contrato.label', default: 'Contrato'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
