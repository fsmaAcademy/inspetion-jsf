package br.com.fsma.projeto_web.business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fsma.projeto_web.business.interfaces.IFiscalizacaoService;
import br.com.fsma.projeto_web.entities.Empresa;
import br.com.fsma.projeto_web.entities.Fiscalizacao;
import br.com.fsma.projeto_web.repositories.FiscalizacaoRepositoryImpl;

@Named
@RequestScoped
public class FiscalizacaoServiceImpl implements Serializable, IFiscalizacaoService {

	private static final long serialVersionUID = 1L;

	@Inject
	private FiscalizacaoRepositoryImpl fiscalizacaoRepository;
	
	@Override
	public void adiciona(Fiscalizacao fiscalizacao) {
		fiscalizacao = settingFiscalizacao(fiscalizacao);		
		fiscalizacaoRepository.adiciona(fiscalizacao);
	}

	@Override
	public void atualiza(Fiscalizacao fiscalizacao) {
		fiscalizacao = settingFiscalizacao(fiscalizacao);
		fiscalizacaoRepository.atualiza(fiscalizacao);
	}

	@Override
	public void remove(Fiscalizacao fiscalizacao) {
		fiscalizacaoRepository.remove(fiscalizacao);
		
	}

	@Override
	public List<Fiscalizacao> busca(LocalDate dataInicio, LocalDate dataFim) {
		return fiscalizacaoRepository.busca(dataInicio, dataFim);
	}

	@Override
	public Fiscalizacao buscaPorId(Long id) {
		return fiscalizacaoRepository.buscaPorId(id);
	}

	@Override
	public List<Fiscalizacao> busca(Empresa empresa, LocalDate dataInicio, LocalDate dataFim) {
		return fiscalizacaoRepository.busca(empresa, dataInicio, dataFim);
	}
	
	private Fiscalizacao settingFiscalizacao(Fiscalizacao fiscalizacao) {
		fiscalizacao.setCep(
				fiscalizacao.getCep()
					.trim()
					.replace(".", "")
					.replace("-", "")
				);
		return fiscalizacao;
	}

}
