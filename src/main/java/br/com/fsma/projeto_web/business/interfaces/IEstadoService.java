package br.com.fsma.projeto_web.business.interfaces;

import java.util.List;

import br.com.fsma.projeto_web.entities.Estado;

public interface IEstadoService {
	List<Estado> buscar();
	Estado buscarPorId(Long id);
	Estado buscarPorUf(String uf);
	Estado criar(Estado estado);
	Estado atualizar(Estado estado);
	Estado remover(Estado estado);
}
