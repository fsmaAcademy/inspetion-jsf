package br.com.fsma.projeto_web.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import br.com.fsma.projeto_web.repositories.interfaces.IBaseRepository;

public class BaseRepositoryImpl<T> implements IBaseRepository<T> {

	protected EntityManager em;
	private final Class<T> entity;

	public BaseRepositoryImpl(Class<T> entity, EntityManager em) {
		this.em = em;
		this.entity = entity;
	}
	
	@Override
	public List<T> buscar() {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(entity);
		query.select(query.from(entity));
		List<T> list = em.createQuery(query).getResultList();
		return list;
	}

	@Override
	public T buscarPorId(Long id) {
		T t = em.find(entity, id);
		return t;
	}

	@Override
	public T criar(T t) {
		em.persist(t);
		return t;
	}

	@Override
	public T atualizar(T t) {
		em.merge(t);
		return t;
	}

	@Override
	public T remover(T t) {
		T tAux = t;
		em.remove(em.merge(t));
		return tAux;
	}

}
