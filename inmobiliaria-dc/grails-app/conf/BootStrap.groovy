import com.hvalenti.freelance.inmobiliariaDC.Contrato
import com.hvalenti.freelance.inmobiliariaDC.Inmueble
import com.hvalenti.freelance.inmobiliariaDC.Vencimiento
import com.hvalenti.freelance.inmobiliariaDC.Obligacion

class BootStrap {

    def init = { servletContext ->
		/*Inmueble i1 = new Inmueble(tipo: "Departamento").save()
		Contrato c1 = new Contrato(inmueble: i1).save()
		Obligacion o1 = new Obligacion(
			responsable: "Inmobiliaria", 
			concepto: "Impuesto",
			frecuencia: "Mensual",
			observacion: "Municipalidad",
			montoEstandar: 250,
			vencimientoEstandar: 1)
		Obligacion o2 = new Obligacion(
			responsable: "Inmobiliaria",
			concepto: "Impuesto",
			frecuencia: "Mensual",
			observacion: "Municipalidad",
			montoEstandar: 550,
			vencimientoEstandar: 1)
		InstanciaObligacion i11 = new InstanciaObligacion(
			vencimiento: new Date(),
			responsable: "Inmobiliaria",
			observacion: " ",
			monto: 150.0)
		InstanciaObligacion i12 = new InstanciaObligacion(
			vencimiento: new Date(),
			responsable: "Locador",
			observacion: "",
			monto: 250.0)
		InstanciaObligacion i13 = new InstanciaObligacion(
			vencimiento: new Date(),
			responsable: "Inmobiliaria",
			observacion: "",
			monto: 350.0)
		InstanciaObligacion i21 = new InstanciaObligacion(
			vencimiento: new Date(),
			responsable: "Locatario",
			observacion: "",
			monto: 450.0)
		InstanciaObligacion i22 = new InstanciaObligacion(
			vencimiento: new Date(),
			responsable: "Inmobiliaria",
			observacion: "",
			monto: 450.0)
		o1.addToInstancias(i11)
		o1.addToInstancias(i12)
		o1.addToInstancias(i13)
		
		o2.addToInstancias(i21)
		o2.addToInstancias(i22)
		
		c1.addToObligaciones(o1)
		c1.addToObligaciones(o2)
		
		o1.save()
		o2.save()*/
    }
    def destroy = {
    }
}
