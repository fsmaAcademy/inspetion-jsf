package br.com.fsma.projeto_web.util;

import br.com.fsma.projeto_web.entities.Estado;

public abstract class SelecionaEstado {
	public static Estado getEstadoInstance(String federateUnitForExtesion) {

		Estado estado;

		switch (federateUnitForExtesion.toLowerCase()) {

		case "são paulo":
		case "sao paulo":
			estado = new Estado();
			estado.setUf("SP");
			estado.setNome("São Paulo");
			return estado;

		case "rio de janeiro":
			estado = new Estado();
			estado.setUf("RJ");
			estado.setNome("Rio de Janeiro");
			return estado;

		case "espirito santo":
			estado = new Estado();
			estado.setUf("ES");
			estado.setNome("Espirito Santos");
			return estado;

		case "minas gerais":
			estado = new Estado();
			estado.setUf("MG");
			estado.setNome("Minas Gerais");
			return estado;

		case "bahia":
			estado = new Estado();
			estado.setUf("BA");
			estado.setNome("Bahia");
			return estado;
		}

		return null;
	}
}
