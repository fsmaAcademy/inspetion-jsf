package br.com.fsma.projeto_web.business.interfaces;

import java.util.List;

import br.com.fsma.projeto_web.entities.Cidade;
import br.com.fsma.projeto_web.entities.Estado;

public interface ICidadeService {
	public void adiciona(Cidade cidade);
	public void atualiza(Cidade cidade);
	public void remove(Cidade cidade);
	public List<Cidade> busca();
	public List<Cidade> busca(String criterio);
	public Cidade buscaPorId(Long id);
	public Cidade buscaPorNome(String nome);
	public List<Cidade> buscaCidadePorEstadoPorCriterio(String criterio, Estado estado);
	public List<Cidade> buscaPorCriterio(String criterio);
}
