package com.hvalenti.freelance.inmobiliariaDC

import grails.test.mixin.Mock
import spock.lang.Specification

@Mock([GrupoLiquidacion, Liquidacion])
class GrupoLiquidacionSpec extends Specification {

	def "cuando se agregan liquidaciones se les setea la relacion de callback"() {
		given:
		def l1 = new Liquidacion()
		def l2 = new Liquidacion()
		def liquidacionList = new ArrayList<Liquidacion>()
		liquidacionList.add(l1)
		liquidacionList.add(l2)
		def grupoLiquidacion = new GrupoLiquidacion()
		def grupoLiquidacionAux = new GrupoLiquidacion()
		when:
		grupoLiquidacion.agregarLiquidaciones(liquidacionList)

		then:
		grupoLiquidacion.liquidaciones
		!grupoLiquidacion.liquidaciones.isEmpty()
		grupoLiquidacion.liquidaciones.size() == 2
		grupoLiquidacion.liquidaciones[0].grupoLiquidacion == grupoLiquidacion
		grupoLiquidacion.liquidaciones[1].grupoLiquidacion == grupoLiquidacion
		grupoLiquidacion.liquidaciones[0].grupoLiquidacion != grupoLiquidacionAux
		grupoLiquidacion.liquidaciones[1].grupoLiquidacion != grupoLiquidacionAux
	}
}
