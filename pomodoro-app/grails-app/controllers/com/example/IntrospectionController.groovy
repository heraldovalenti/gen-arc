package com.example

import grails.converters.JSON

class IntrospectionController {
	
	def index() {
		def var = [
			"class" : Task.constraints.getClass().getName()
			]
		var.put("foo", "bar")
		Map map = Task.constraints
		for(def k in map.keySet()) {
			var.put(k, map.get(k))
		}
		render var as JSON
	}

}
