package br.com.fsma.projeto_web.business;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fsma.projeto_web.business.interfaces.ICidadeService;
import br.com.fsma.projeto_web.entities.Cidade;
import br.com.fsma.projeto_web.entities.Estado;
import br.com.fsma.projeto_web.repositories.CidadeRepositoryImpl;
import br.com.fsma.projeto_web.repositories.EstadoRepositoryImpl;

@Named
@RequestScoped
public class CidadeServiceImpl implements ICidadeService, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstadoRepositoryImpl estadoRepository;
	
	@Inject CidadeRepositoryImpl cidadeRepository;
	
	@Override
	public void adiciona(Cidade cidade) {
		Estado estado = estadoRepository.buscarPorId(cidade.getEstado().getId());
		if (estado == null) return;
		cidade.setEstado(estado);
		cidadeRepository.adiciona(cidade);
	}

	@Override
	public void atualiza(Cidade cidade) {
		cidadeRepository.atualiza(cidade);
	}

	@Override
	public void remove(Cidade cidade) {
		cidadeRepository.remove(cidade);
	}

	@Override
	public List<Cidade> busca() {
		return cidadeRepository.busca();
	}

	@Override
	public List<Cidade> busca(String criterio) {
		return cidadeRepository.busca(criterio);
	}

	@Override
	public Cidade buscaPorId(Long id) {
		return cidadeRepository.buscaPorId(id);
	}

	@Override
	public Cidade buscaPorNome(String nome) {
		return cidadeRepository.buscaPorNome(nome);
	}
	
	
	
}
