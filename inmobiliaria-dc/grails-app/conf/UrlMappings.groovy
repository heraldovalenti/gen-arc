class UrlMappings {

	static mappings = {

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

		//"/"(controller: "inicio", action: "index")
		"/"(uri: "home.html")
        "500"(view: '/error')
	}
}
