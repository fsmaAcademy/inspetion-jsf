package br.com.fsma.projeto_web.repositories.interfaces;

import java.util.List;

import br.com.fsma.projeto_web.entities.Bairro;
import br.com.fsma.projeto_web.entities.Cidade;
import br.com.fsma.projeto_web.entities.Estado;

public interface IBairroRepository {
	public void adiciona(Bairro bairro);
	public void atualiza(Bairro bairro);
	public void remove(Bairro bairro);
	public List<Bairro> busca();
	public List<Bairro> busca(String criterio);
	public Bairro buscaPorId(Long id);
	public Bairro buscaPorNome(String nome);
	public List<Bairro> buscaBairroPorCriterioEmCidadeEstado(String criterio, Cidade cidade);
	boolean existe(Bairro bairro);
	Bairro buscaPorCidadeEstado(String nome, Cidade cidade, Estado estado);
	List<Bairro> buscaPorCidade(Cidade cidade);
	boolean existePorId(Long id);
}
