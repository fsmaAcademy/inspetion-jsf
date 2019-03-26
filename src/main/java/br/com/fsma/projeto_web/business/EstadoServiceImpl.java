package br.com.fsma.projeto_web.business;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.fsma.projeto_web.business.interfaces.IEstadoService;
import br.com.fsma.projeto_web.entities.Estado;
import br.com.fsma.projeto_web.repositories.EstadoRepositoryImpl;
import br.com.fsma.projeto_web.util.SelecionaEstado;

@Named
@RequestScoped
public class EstadoServiceImpl implements IEstadoService, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstadoRepositoryImpl estadoRepository;

	@Override
	public void adiciona(Estado estado) {
		if ((estado.getNome() != null) && (estado.getUf() == null)) {
			estado = SelecionaEstado.getEstadoInstance(estado.getNome());
		}
		estadoRepository.adiciona(estado);
	}

	@Override
	public List<Estado> buscar() {
		return estadoRepository.buscar();
	}

	@Override
	public Estado buscarPorId(Long id) {
		return estadoRepository.buscarPorId(id);
	}

	@Override
	public Estado buscarPorUf(String uf) {
		return estadoRepository.buscaPorUf(uf);
	}

	@Override
	public void atualizar(Estado estado) {
		estadoRepository.atualiza(estado);
	}

	@Override
	public void remover(Estado estado) {
		 estadoRepository.remove(estado);
	}

	@Override
	public List<Estado> buscarPorNomeOuUf(String criterio) {
		return this.estadoRepository.buscarPorNomeOuUf(criterio);
	}

}
