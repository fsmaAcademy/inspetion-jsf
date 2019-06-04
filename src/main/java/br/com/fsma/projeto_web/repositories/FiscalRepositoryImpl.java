package br.com.fsma.projeto_web.repositories;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.fsma.projeto_web.entities.Fiscal;
import br.com.fsma.projeto_web.repositories.interfaces.IFiscalRepository;

@Named
@RequestScoped
public class FiscalRepositoryImpl implements Serializable, IFiscalRepository {
	
	private static final long serialVersionUID = 1L;

	private Repository<Fiscal> repository;
	
	@Inject
	private EntityManager em;
	
	@PostConstruct
	public void init() {
		repository = new Repository<Fiscal>(em, Fiscal.class);
	}
	
	@Override
	public void adiciona(Fiscal fiscal) {
		repository.adiciona(fiscal);
	}

	@Override
	public void atualiza(Fiscal fiscal) {
		repository.atualiza(fiscal);
	}

	@Override
	public void remove(Fiscal fiscal) {
		repository.remove(fiscal);
	}

	public List<Fiscal> buscar() {
		return repository.buscar();
	}

	public Fiscal busca(Fiscal fiscal) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select f from Fiscal f ");
		jpql.append(" where f = :fiscal ");
		
		TypedQuery<Fiscal> query = em.createQuery(jpql.toString(), Fiscal.class);
		query.setParameter("fiscal", fiscal);
		try {
			return query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}

	public List<Fiscal> buscar(String criterio) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select f from Fiscal f ");
		jpql.append(" where f.nome LIKE :pnome");
		
		TypedQuery<Fiscal> query = em.createQuery(jpql.toString(), Fiscal.class);
		query.setParameter("pnome", criterio + "%");
		
		try {
			return query.getResultList();
		} catch(NoResultException e) {
			return null;
		}
	}

	public Fiscal buscarPorCpf(String cpf) {
		StringBuilder jpql = new StringBuilder();
		jpql.append(" select f from Fiscal f ");
		jpql.append(" where f.cpf = :cpf");
		
		TypedQuery<Fiscal> query = em.createQuery(jpql.toString(), Fiscal.class);
		query.setParameter("cpf", cpf + "%");
		
		try {
			return query.getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}

}
