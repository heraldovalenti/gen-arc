import com.example.Tag
import com.example.Task
import com.hvalenti.freelance.inmobiliariaDC.Domicilio
import com.hvalenti.freelance.inmobiliariaDC.Inmueble


class BootStrap {

    def init = { servletContext ->
		new Tag(name: "Home").save()
		new Tag(name: "Work").save()
		new Tag(name: "Hobby").save()
		new Tag(name: "Research").save()
		new Task(summary: "System for DC", details: "", deadLine: new Date(), priority: 1).save()
		new Task(summary: "System for ACSR", details: "", deadLine: new Date(), priority: 2).save()
		
    }
    def destroy = {
    }
}
