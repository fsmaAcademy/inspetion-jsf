package br.com.fsma.projeto_web.business.interfaces;

import java.util.List;

import br.com.fsma.projeto_web.entities.Estado;

public interface IEstadoService {
	List<Estado> buscarPorNomeOuUf(String criterio);
	void adiciona(Estado estado);
	void atualizar(Estado estado);
	void remover(Estado estado);
	List<Estado> buscar();
	Estado buscarPorId(Long id);
	Estado buscarPorUf(String uf);
}
