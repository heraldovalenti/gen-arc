class UrlMappings {

	static mappings = {
		"/rest/$entity"(controller: "prueba", action: "list", method: "GET")
		"/rest/$entity/$id"(controller: "prueba", action: "get", method: "GET")
		"/rest/$entity"(controller: "prueba", action: "save", method: "POST")
		"/rest/$entity/$id"(controller: "prueba", action: "update", method: "PUT")
		"/rest/$entity/$id"(controller: "prueba", action: "delete", method: "DELETE")

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view: "/index")
        "500"(view: '/error')
	}
}
