package br.com.fsma.projeto_web.repositories;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fsma.projeto_web.entities.Estado;
import br.com.fsma.projeto_web.repositories.interfaces.IEstadoRepository;

public class EstadoRepositoryImpl implements Serializable, IEstadoRepository {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager em;

	private Repository<Estado> _repository;
	
	
	@PostConstruct
	void init() {
		_repository = new Repository<Estado>(this.em, Estado.class);
	}
	
	public Estado criar(Estado estado) {
		_repository.adiciona(estado);
		return estado;
	}

	public Estado atualizar(Estado estado){
		em.merge(estado);
		return estado;
	}

	public Estado remover(Estado estado) {
		_repository.remove(estado);
		return estado;
	}

	public Estado buscarPorId(Long id) {
		return _repository.buscaPorId(id);
	}

	@Override
	public Estado buscarPorUf(String uf) {
		TypedQuery<Estado> query = em.createQuery("SELECT estado FROM Estado uf WHERE estado.uf=:pUf", Estado.class);
		query.setParameter("pUf", uf);
		return query.getSingleResult();
	}

	@Override
	public boolean existe(Estado uf) {
		TypedQuery<Estado> query = em.createQuery(
				"SELECT estado FROM Estado uf WHERE uf.nome = :pNome or estado.uf = :pUf",
				Estado.class);
		query.setParameter("pUf", uf.getUf());
		query.setParameter("pNome", uf.getNome());
		try {
			return query.getSingleResult() != null;
		} catch (NoResultException ex) {
			return false;
		}
	}

	@Override
	public List<Estado> buscar() {
		return _repository.buscar();
	}


}
