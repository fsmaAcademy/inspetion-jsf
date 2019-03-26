package br.com.fsma.projeto_web.repositories.interfaces;

import java.util.List;

import br.com.fsma.projeto_web.entities.Estado;

public interface IEstadoRepository {
	public List<Estado> buscar();
	public void adiciona(Estado estado);
	public void atualiza(Estado estado);
	public void remove(Estado estado);
	public Estado buscarPorId(Long id);
	
	public Estado buscaPorUf(String uf);
	public List<Estado> buscarPorNomeOuUf(String criterio);
}
