package br.com.fsma.projeto_web.repositories.interfaces;

import java.util.List;

import br.com.fsma.projeto_web.entities.Cidade;
import br.com.fsma.projeto_web.entities.Estado;

public interface ICidadeRepository {
	
	public void adiciona(Cidade cidade);
	public void atualiza(Cidade cidade);
	public void remove(Cidade cidade);
	public List<Cidade> busca();
	public List<Cidade> busca(String criterio);
	public Cidade buscaPorId(Long id);
	public Cidade buscaPorNome(String nome);
	public List<Cidade> buscaCidadeEmEstadoPorCriterio(String criterio, Estado Estado);
	
	
}
