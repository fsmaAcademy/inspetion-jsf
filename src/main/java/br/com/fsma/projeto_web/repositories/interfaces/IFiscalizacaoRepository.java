package br.com.fsma.projeto_web.repositories.interfaces;

import java.time.LocalDate;
import java.util.List;

import br.com.fsma.projeto_web.entities.Empresa;
import br.com.fsma.projeto_web.entities.Fiscalizacao;

public interface IFiscalizacaoRepository {
	public void adiciona(Fiscalizacao fiscalizacao);
	public void atualiza(Fiscalizacao fiscalizacao);
	public void remove(Fiscalizacao fiscalizacao);
	public List<Fiscalizacao> busca(LocalDate dataInicio, LocalDate dataFim);
	public Fiscalizacao buscaPorId(Long id);
	public List<Fiscalizacao> busca(Empresa empresa, LocalDate dataInicio, LocalDate dataFim);
}
