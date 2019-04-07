package br.com.fsma.projeto_web.business;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fsma.projeto_web.bean.EmpresaBean;
import br.com.fsma.projeto_web.business.interfaces.IBairroService;
import br.com.fsma.projeto_web.entities.Bairro;
import br.com.fsma.projeto_web.entities.Cidade;
import br.com.fsma.projeto_web.entities.Estado;
import br.com.fsma.projeto_web.repositories.BairroRepositoryImpl;
import br.com.fsma.projeto_web.repositories.CidadeRepositoryImpl;
import br.com.fsma.projeto_web.repositories.EstadoRepositoryImpl;

@Named
@RequestScoped
public class BairroServiceImpl implements Serializable, IBairroService {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstadoRepositoryImpl estadoRepository;

	@Inject
	private CidadeRepositoryImpl cidadeRepository;

	@Inject
	private BairroRepositoryImpl bairroRepository;

	@Override
	public void adiciona(Bairro bairro) {

		Estado estado = this.estadoRepository.buscarPorId(
				bairro
					.getCidade()
					.getEstado()
					.getId()
				);
		
		Cidade cidade = this.cidadeRepository.buscaPorId(
				bairro
					.getCidade()
					.getId()
				);

		bairro.getCidade().setEstado(estado);
		bairro.setCidade(cidade);

		this.bairroRepository.adiciona(bairro);
	}

	@Override
	public void atualiza(Bairro bairro) {
		Estado estado = this.estadoRepository.buscarPorId(bairro.getCidade().getEstado().getId());
		Cidade cidade = this.cidadeRepository.buscaPorId(bairro.getCidade().getId());
		bairro.getCidade().setEstado(estado);
		bairro.setCidade(cidade);
		this.bairroRepository.atualiza(bairro);
	}

	@Override
	public void remove(Bairro bairro) {
		this.bairroRepository.remove(bairro);
	}

	@Override
	public Bairro buscaPorId(Long id) {
		return this.bairroRepository.buscaPorId(id);
	}

	@Override
	public Bairro buscaPorNome(String nome) {
		return this.bairroRepository.buscaPorNome(nome);
	}

	@Override
	public List<Bairro> buscaBairroPorCriterioEmCidadeEstado(String criterio, Cidade cidade) {
		return this.bairroRepository.buscaBairroPorCriterioEmCidadeEstado(criterio, cidade);
	}
	
	@Override
	public List<Bairro> buscaPorCidade(Cidade cidade) {
		return bairroRepository.buscaPorCidade(cidade);
	}

}
