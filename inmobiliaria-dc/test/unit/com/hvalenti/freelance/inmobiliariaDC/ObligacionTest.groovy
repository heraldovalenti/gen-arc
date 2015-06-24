package com.hvalenti.freelance.inmobiliariaDC

import org.junit.Assert
import org.junit.Test

class ObligacionTest {

	@Test
	public void ordenarInstanciasTest() {
		Date now = new Date()
		Obligacion o = new Obligacion()
		List<InstanciaObligacion> instanciaList = new ArrayList<>()
		InstanciaObligacion i1, i2, i3
		i1 = new InstanciaObligacion(id: 1, vencimiento: new Date(now.getTime() - 1000))
		i2 = new InstanciaObligacion(id: 3, vencimiento: new Date(now.getTime() + 1000))
		i3 = new InstanciaObligacion(id: 2, vencimiento: now)
		instanciaList.add(i1)
		instanciaList.add(i2)
		instanciaList.add(i3)
		o.ordenarInstancias(instanciaList)
		def expecteds = [i1, i2, i3]
		def actuals = [instanciaList[0], instanciaList[1], instanciaList[2]]
		Assert.assertEquals(expecteds, actuals)
    }
}
