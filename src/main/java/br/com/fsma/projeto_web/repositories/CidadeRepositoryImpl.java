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

import br.com.fsma.projeto_web.entities.Cidade;
import br.com.fsma.projeto_web.entities.Estado;
import br.com.fsma.projeto_web.repositories.interfaces.ICidadeRepository;

@Named
@RequestScoped
public class CidadeRepositoryImpl implements Serializable, ICidadeRepository {

	private static final long serialVersionUID = 1L;

	private Repository<Cidade> repository;

	@Inject
	private EntityManager em;

	@PostConstruct
	public void init() {
		repository = new Repository<Cidade>(this.em, Cidade.class);
	}

	@Override
	public void adiciona(Cidade cidade) {
		repository.adiciona(cidade);
	}

	@Override
	public void atualiza(Cidade cidade) {
		repository.atualiza(cidade);
	}

	@Override
	public void remove(Cidade cidade) {
		repository.remove(cidade);
	}

	@Override
	public List<Cidade> busca() {
		return repository.buscar();
	}

	@Override
	public Cidade buscaPorId(Long id) {
		return repository.buscaPorId(id);
	}

	@Override
	public List<Cidade> busca(String criterio) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT c FROM Cidade c ");
		sb.append("WHERE ");
		sb.append("   c.nome LIKE CONCAT('%',:pCriterio,'%')");
		
		TypedQuery<Cidade> query = em.createQuery(sb.toString(), Cidade.class);
		
		query.setParameter("pCriterio", criterio);

		try {
			return query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}

	@Override
	public Cidade buscaPorNome(String nome) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT c FROM Cidade c ");
		sb.append("WHERE ");
		sb.append("   c.nome = :pNome");
		
		TypedQuery<Cidade> query = em.createQuery(sb.toString(), Cidade.class);
		
		query.setParameter("pNome", nome);

		try {
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

}
