package com.hvalenti.freelance.inmobiliariaDC.acceso

class AccesoController {
	
	def index() {
		render(view: "/acceso/acceso")
	}
	
	def login() {
		def user = params.user
		def password = params.password
		
		println user
		println password
		if ("manager".equals(user) && "yerba4mate".equals(password)) {
			session.user = user
			redirect(uri: '')
		} else {
			flash.message = "Usuario o password incorrectos"
			redirect(action: "index")
		}
	}
	
	def logout() {
		session.invalidate()
		redirect(action: "index")
	}

}
