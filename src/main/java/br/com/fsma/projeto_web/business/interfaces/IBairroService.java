package br.com.fsma.projeto_web.business.interfaces;

import java.util.List;

import br.com.fsma.projeto_web.entities.Bairro;
import br.com.fsma.projeto_web.entities.Cidade;
import br.com.fsma.projeto_web.entities.Estado;

public interface IBairroService {
	public void adiciona(Bairro bairro);
	public void atualiza(Bairro bairro);
	public void remove(Bairro bairro);
	public Bairro buscaPorId(Long id);
	public Bairro buscaPorNome(String nome);
	public List<Bairro> buscaBairroPorCriterioEmCidadeEstado(String criterio, Cidade cidade);
	List<Bairro> buscaPorCidade(Cidade cidade);
}
