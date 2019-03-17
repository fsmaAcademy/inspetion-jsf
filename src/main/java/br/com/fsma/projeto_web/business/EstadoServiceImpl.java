package br.com.fsma.projeto_web.business;

import java.util.List;

import br.com.fsma.projeto_web.business.interfaces.IEstadoService;
import br.com.fsma.projeto_web.entities.Estado;
import br.com.fsma.projeto_web.repositories.EstadoRepositoryImpl;
import br.com.fsma.projeto_web.repositories.interfaces.IEstadoRepository;

public class EstadoServiceImpl implements IEstadoService {
	
	private IEstadoRepository _repository;
	
	public EstadoServiceImpl() {
		_repository = new EstadoRepositoryImpl();
	}

	@Override
	public List<Estado> buscar() {
		List<Estado> estados = _repository.buscar();
		if (estados == null) return null;
		return estados;
	}

	@Override
	public Estado buscarPorId(Long id) {
		Estado estado = _repository.buscarPorId(id);
		if (estado == null) return null;
		return estado;
	}

	@Override
	public Estado buscarPorUf(String uf) {
		Estado estado = _repository.buscarPorUf(uf);
		if (estado == null) return null;
		return estado;
	}

	@Override
	public Estado criar(Estado estado) {
		estado = _repository.criar(estado);
		if (estado == null) return null;
		return estado;
	}

	@Override
	public Estado atualizar(Estado estado) {
		estado = _repository.atualizar(estado);
		if (estado == null) return null;
		return estado;
	}

	@Override
	public Estado remover(Estado estado) {
		estado = _repository.atualizar(estado);
		if (estado == null) return null;
		return estado;
	}


}
