package br.com.fsma.projeto_web.repositories.interfaces;

import java.util.List;

import br.com.fsma.projeto_web.entities.Estado;

public interface IEstadoRepository {
	public List<Estado> buscar();
	public Estado criar(Estado estado);
	public Estado atualizar(Estado estado);
	public Estado remover(Estado estado);
	public Estado buscarPorId(Long id);
	Estado buscarPorUf(String uf);
	public boolean existe(Estado estado);
}
